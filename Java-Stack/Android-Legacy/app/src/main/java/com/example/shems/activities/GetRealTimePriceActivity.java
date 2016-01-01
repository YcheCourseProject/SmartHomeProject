package com.example.shems.activities;

import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.example.shems.ASGApplication;
import com.example.shems.models.electrical_prices.RealTimePrice;
import com.example.shems.models.electrical_prices.RegionPrice;
import com.example.shems.util.CommonFunction;
import com.example.smarthome.R;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GetRealTimePriceActivity extends Activity implements
        OnClickListener {
    private ProgressDialog pd;
    Spinner spinner_add;
    Button button_gain_price;
    Button button_2optimize;
    Button button_2waterHeater;
    Button button_2airconditioner;
    TextView textview_hintwifi;
    ListView listview_show_realtime_price = null;
    GridView toolbarGrid;
    Handler handler;
    JudgeWifi netthread;
    boolean threadrun=true;
    List<String> arraylist = new ArrayList<String>();

    private ArrayAdapter adapter_spinner;
    private ArrayAdapter adapter_listview;
    final static String FOLDER = "/baseinfo/";
    final static String FILENAME = "realtimeprice";
    final static String SUFFIX = ".txt";
    List<RegionPrice> pricelist;
    int selectindex = 0;
    private static final String[] addrStr = {"BeiJing",RealTimePrice.region1,
            RealTimePrice.region2, RealTimePrice.region3,
            RealTimePrice.region4, RealTimePrice.region5,
            RealTimePrice.region6, RealTimePrice.region7,
            RealTimePrice.region8, RealTimePrice.region9 };
    int[] menu_toolbar_image_array = { R.drawable.controlbar_homepage,
            R.drawable.snow,
            R.drawable.hotwater ,
            R.drawable.laundry,
    };

    String[] menu_toolbar_name_array;
    public final double[]	defaultprices=
            {
                    24.61,24.25,23.61,22.66,23.23,23.6,
                    25.74,25.39,30.15,32.56,34.83,42.74,
                    37.22,34.94,30.93,30.46,31.93,38.79,
                    38.72,44.56,39.76,31.48,28.74,21.50
            };
    private final int TOOLBAR_ITEM_PAGEHOME = 0;// 首页
    private final int TOOLBAR_ITEM_AIR = 1;//
    private final int TOOLBAR_ITEM_WATER = 2;//
    private final int TOOLBAR_ITEM_OTHER = 3;//

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        threadrun=false;
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            Intent intent = new Intent();
//            intent.setClass(this, MenuActivity.class);
//            this.startActivity(intent);
//            this.finish();
//        }
//        return super.onKeyDown(keyCode, event);
//
//    }

    /**
     * 构造菜单Adapter
     *
     * @param menuNameArray
     *            名称
     * @param imageResourceArray
     *            图片
     * @return SimpleAdapter
     */
    private SimpleAdapter getMenuAdapter(String[] menuNameArray,
                                         int[] imageResourceArray) {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < menuNameArray.length; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", imageResourceArray[i]);
            map.put("itemText", menuNameArray[i]);
            Log.i("testtxt",  menuNameArray[i]);
            data.add(map);
        }
        SimpleAdapter simperAdapter = new SimpleAdapter(this, data,
                R.layout.item_menu, new String[] { "itemImage", "itemText" },
                new int[] { R.id.item_image, R.id.item_text });
        return simperAdapter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_time_price);
        Resources resources=this.getResources();
        menu_toolbar_name_array = resources.getStringArray(R.array.Optimize_Fnction_Items);
        threadrun=true;
        textview_hintwifi=(TextView)this.findViewById(R.id.textview_hintwifi);
        spinner_add = (Spinner) this.findViewById(R.id.spinner_address);
        button_gain_price = (Button) this
                .findViewById(R.id.button_search_for_price);
        button_gain_price.setOnClickListener(this);

        listview_show_realtime_price = (ListView) this
                .findViewById(R.id.listView_realtime_price);

        adapter_spinner = new ArrayAdapter(this,
                android.R.layout.simple_spinner_dropdown_item, addrStr); // 把地址添加上去
        adapter_listview = new ArrayAdapter(this, R.layout.item_load_events,
                arraylist);
        spinner_add.setAdapter(adapter_spinner);
        listview_show_realtime_price.setAdapter(adapter_listview);

        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setHomeButtonEnabled(false);

        // 创建底部菜单 Toolbar
        toolbarGrid = (GridView) findViewById(R.id.GridView_toolbar);
        toolbarGrid.setBackgroundResource(R.color.black);// 设置背景
        toolbarGrid.setNumColumns(4);// 设置每行列数
        toolbarGrid.setGravity(Gravity.CENTER);// 位置居中

        toolbarGrid.setVerticalSpacing(10);// 垂直间隔
        toolbarGrid.setHorizontalSpacing(10);// 水平间隔

        toolbarGrid.setAdapter(getMenuAdapter(menu_toolbar_name_array,
                menu_toolbar_image_array));// 设置菜单Adapter

        /** 监听底部菜单选项 **/
        toolbarGrid.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                Toast.makeText(GetRealTimePriceActivity.this,
                        menu_toolbar_name_array[arg2], Toast.LENGTH_SHORT)
                        .show();
                switch (arg2) {
                    case TOOLBAR_ITEM_PAGEHOME:
                    {
                        Intent intent = new Intent();
                        intent.setClass(GetRealTimePriceActivity.this,MenuActivity .class);
                        GetRealTimePriceActivity.this.startActivity(intent);
                        GetRealTimePriceActivity.this.finish();
                        break;
                    }

                    case TOOLBAR_ITEM_AIR:
                    {
                        Intent intent = new Intent();
                        intent.setClass(GetRealTimePriceActivity.this, AirConditionerActivity.class);
                        GetRealTimePriceActivity.this.startActivity(intent);
                        GetRealTimePriceActivity.this.finish();
                    }
                    break;
                    case TOOLBAR_ITEM_WATER:
                    {
                        Intent intent = new Intent();
                        intent.setClass(GetRealTimePriceActivity.this, WHActivity.class);
                        GetRealTimePriceActivity.this.startActivity(intent);
                        GetRealTimePriceActivity.this.finish();
                    }
                    break;
                    case TOOLBAR_ITEM_OTHER:
                    {
                        Intent intent = new Intent();
                        intent.setClass(GetRealTimePriceActivity.this, OptimizeActivity.class);
                        GetRealTimePriceActivity.this.startActivity(intent);
                        GetRealTimePriceActivity.this.finish();
                        break;
                    }
                }


            }
        });

        this.initListView();
        // 处理线程：
        final Context context = this;

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                Bundle data;
                if ((data = msg.getData()) != null) {
                    if (data.getString("read") != null
                            && data.getString("read").equals("read")) {
                        Log.i("pro2", "pro2");
                        ReadPriceThread readThread = new ReadPriceThread();
                        readThread.start();

                    } else if (data.getString("update") != null
                            && data.getString("update").equals("update")) {
                        Toast.makeText(context, "read ok", Toast.LENGTH_SHORT).show();

                        updateListView();
                        pd.dismiss();
                        button_gain_price.setEnabled(true);
                        CommonFunction.showDialog("Hint", "Data access sucess",
                                (Activity) context);


                    } else if (data.getString("alert") != null
                            && data.getString("alert").equals("alert")) {
                        pd.dismiss();
                        button_gain_price.setEnabled(true);
                        CommonFunction.showDialog("Hint", "Data access failure",
                                (Activity) context);
                        initListView();

                    }
                    else if(data.getString("hintwifi")!=null)
                    {
                        if(data.getString("hintwifi").equals("no"))
                        {
//						Toast.makeText(GetRealTimePriceActivity.this,"no wifi",100).show();
//						textview_hintwifi.setVisibility(View.VISIBLE);
                            textview_hintwifi.setTextColor(Color.RED);
                            textview_hintwifi.setText("Warning:we cannot reach Internet");
                        }
                        else if(data.getString("hintwifi").equals("yes"))
                        {
//						Toast.makeText(GetRealTimePriceActivity.this,"get wifi",100).show();
                            textview_hintwifi.setEnabled(false);
                            textview_hintwifi.setTextColor(Color.BLACK);
                            textview_hintwifi.setText("Net status is ok");
                        }
                    }
                }
            }

        };

        netthread=new JudgeWifi();
        netthread.start();
    }

    public void initListView()
    {
        double []temprealprices=((ASGApplication) getApplication()).getRealTimePrice();
        if(temprealprices[0]==0)
        {
            temprealprices=this.defaultprices;
            ((ASGApplication) getApplication()).setRealTimePrice(temprealprices);
            for(int i=0;i<24;i++)
            {
                temprealprices[i]/=1000;
            }
        }
        //下面是更新
        long timestamp=new Date().getTime();

        Date tempdate=new Date(timestamp);
        tempdate.setMinutes(0);
        tempdate.setSeconds(0);

        for (int i = 0; i < 24; i++) {
            tempdate.setHours(i);
            Resources resources=this.getResources();
            String[] formatstr=resources.getStringArray(R.array.Real_Time_Price_List_Format);

            String s = formatstr[0] +tempdate.toLocaleString()+ formatstr[1]
                    + (temprealprices[i]*1000)+formatstr[2];
            Toast.makeText(this, s, Toast.LENGTH_SHORT);

            arraylist.add(s);
        }
        this.listview_show_realtime_price.invalidateViews();
    }
    public void updateListView() {
        int pos = this.spinner_add.getSelectedItemPosition(); // spinner 的pos
        Toast.makeText(this, "updateing",  Toast.LENGTH_SHORT);
        arraylist.clear();
        int index = this.spinner_add.getSelectedItemPosition();
        double prices[] = new double[24];
        for (int i = 0; i < 24; i++) {
            RegionPrice temp = pricelist.get(i);
            prices[23 - i] = temp.prices[pos]/1000 ; // 转换成 每kwh的
            Resources resources=this.getResources();
            String[] formatstr=resources.getStringArray(R.array.Real_Time_Price_List_Format);
            String s = formatstr[0] + temp.date.toLocaleString() + formatstr[1]
                    +(temp.prices[index]*1000)+formatstr[2];
            Toast.makeText(this, s,  Toast.LENGTH_SHORT);

            arraylist.add(s);
        }
        ((ASGApplication) getApplication()).setRealTimePrice(prices);
        this.listview_show_realtime_price.invalidateViews();
    }

    class WritePrice2txtThread extends Thread {
        Context context;

        WritePrice2txtThread(Context context) {
            this.context = context;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            if (this.gainRawData()) {

                Bundle bundle = new Bundle();
                bundle.putString("read", "read");
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);
                Log.i("procedure1 end", "111111111111");
            }
        }

        public boolean gainRawData() {
            try {
                URL url = new URL(
                        "http://www.gdfsuezenergyresources.com/Daily_Dump/new_imports/NEISO_HOURLY_DA.csv");
                HttpURLConnection conn = (HttpURLConnection) url
                        .openConnection();
                conn.setRequestMethod("GET");
                conn.setConnectTimeout(4000);
                conn.setReadTimeout(10000);
                Log.i("connect", "connect ok");
                InputStream inStream = conn.getInputStream(); // 通过输入流获取html二进制数据

                this.readInputStream2txt(inStream);
                Log.i("realtime write", "success");
                return true;
            } catch (Exception e) {
                Log.i("exception", e.toString());
                Log.i("fail", "fail");
                Bundle hint = new Bundle();
                Message msg = new Message();
                hint.putString("alert", "alert");
                msg.setData(hint);
                handler.sendMessage(msg);
                return false;
            }

        }

        public void readInputStream2txt(InputStream instream)
                throws IOException {

            String foldername = Environment.getExternalStorageDirectory()
                    .getPath() + FOLDER;
            File folder = new File(foldername); // 文件夹的路径
            if (folder != null && !folder.exists()) {
                if (!folder.mkdir() && !folder.isDirectory()) {
                    Log.d("TAG", "Error: make dir failed!");
                    return;
                }
            }
            String targetPath = foldername + FILENAME + SUFFIX;
            File targetFile = new File(targetPath);
            Log.i("write2fielname", targetPath);
            if (targetFile != null) {
                if (targetFile.exists()) {
                    targetFile.delete();
                }
            }

            BufferedWriter bw = new BufferedWriter(new FileWriter(targetFile));
            ByteArrayOutputStream outStream = new ByteArrayOutputStream();
            byte[] buffer = new byte[1204];
            int max = 60000;
            long amount = 0;
            int len = 0;

            while ((len = instream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
                amount = amount + len;
                Log.i("progress1", ((double) amount) / max + " len:" + len);
                if (amount > max) { // modify
                    break;
                }
            }
            String result = new String(outStream.toByteArray());

            bw.write(result);
            bw.flush();
            outStream.close();

            outStream = new ByteArrayOutputStream();
            amount = 0;
            len = 0;
            while ((len = instream.read(buffer)) != -1) {
                outStream.write(buffer, 0, len);
                amount = amount + len;
                Log.i("progress2", ((double) amount) / max + "  len:" + len);
                if (amount > max) { // modify
                    break;
                }
            }

            String s = new String(outStream.toByteArray());
            result += s;
            bw.write(s);

            instream.close();
            bw.close();
        }
    }

    class ReadPriceThread extends Thread {

        public ReadPriceThread() {
            super();
            // TODO Auto-generated constructor stub
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            if (this.readprice()) {
                Bundle bundle = new Bundle();
                bundle.putString("update", "update");
                Message msg = new Message();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }

        }

        private boolean readprice() {
            String foldername = Environment.getExternalStorageDirectory()
                    .getPath() + FOLDER;
            String targetPath = foldername + FILENAME + SUFFIX;
            File file = new File(targetPath);
            if (!file.exists()) {

                return false;
            }

            Log.i("filename", targetPath);
            pricelist = RealTimePrice.parsetxt(file);
            if (pricelist != null) {
                for (int i = 0; i < pricelist.size(); i++) {

                    Log.i("showdetailprice",
                            pricelist.get(i).date.toLocaleString());
                }

                return true;
            } else
                return false;

        }

    }

    public void onClick(View view) {
        // TODO Auto-generated method stub
        threadrun=false;
        if (view == this.button_gain_price) {
            pd = new ProgressDialog(this);
            pd.setMessage("正在获取实时电价中...");
            pd.show();
            this.button_gain_price.setEnabled(false);
            arraylist.clear();
            this.listview_show_realtime_price.invalidateViews();
            WritePrice2txtThread writeThread = new WritePrice2txtThread(this);
            writeThread.start();
            Log.i("end", "end");
        } else if (view == this.button_2optimize) {
            Intent intent = new Intent();
            intent.setClass(this, OptimizeActivity.class);
            this.startActivity(intent);
            this.finish();
        } else if (view == this.button_2waterHeater) {
            Intent intent = new Intent();
            intent.setClass(this, WaterHeaterOptimzieActivity.class);
            this.startActivity(intent);
            this.finish();
        } else if (view == this.button_2airconditioner) {
            Intent intent = new Intent();
            intent.setClass(this, AirConditionerActivity.class);
            this.startActivity(intent);
            this.finish();
        }
    }

    class  JudgeWifi extends Thread
    {

        public JudgeWifi() {
            super();
            // TODO Auto-generated constructor stub
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            //判断是否联网
            while(threadrun)
            {
                ConnectivityManager cwjManager=(ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo info = cwjManager.getActiveNetworkInfo();
                if (info != null && info.isAvailable()){
                    Bundle bundle=new Bundle();
                    bundle.putString("hintwifi", "yes");
                    Message message=new Message();
                    message.setData(bundle);
                    handler.sendMessage(message);
                    Log.i("???", "okwifi");
                }
                else
                {
                    Bundle bundle=new Bundle();
                    bundle.putString("hintwifi", "no");
                    Message message=new Message();
                    message.setData(bundle);
                    handler.sendMessage(message);
                    Log.i("???", "nowifi");


                }
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }

    }
}
