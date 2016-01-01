package com.example.shems.activities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import com.example.shems.ASGApplication;
import com.example.shems.daos.meters.scheider.Scheider;
import com.example.shems.daos.sqllite.HisLogDB;
import com.example.shems.util.CommonFunction;
import com.example.smarthome.R;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.text.Editable;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ShowLogActivity extends FatherActivity implements OnClickListener {


    private ProgressDialog pd;
    EditText edittext_interval;
    Button button_log;
    Button button_events;
    Button button_hisdbin;
    Button button_clear;
    Button button_intoAchart;
    Button button_stimuli;
    HisLogDB db_hislog_operator;
    Handler handler;
    ListView myListView;
    StringBuffer sb = new StringBuffer();
    List<String> arraylist;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_log);
        db_hislog_operator = new HisLogDB(this);
        db_hislog_operator.onUpgrade();
//		Log.i("initial db", "数据库中表先扔出//再重新创建// end");
        arraylist = new ArrayList<String>();
        //全局变量拿到
        connector = ((ASGApplication) getApplication()).getConnector(); // getApplication干什么用呢?????????
        meter = ((ASGApplication) getApplication()).getMeter();
        db_hislog_operator = new HisLogDB(this);
        db_hislog_operator.onUpgrade();
        //显示的初始化
        this.edittext_interval = (EditText) this.findViewById(R.id.editText_hint_log_lasttime);
        this.button_log = (Button) this.findViewById(R.id.button_getlog);
        this.button_log.setOnClickListener(this);
        this.button_clear = (Button) this.findViewById(R.id.button_clear);
        this.button_clear.setOnClickListener(this);
        this.button_hisdbin = (Button) this.findViewById(R.id.button_infromlogdb);
        this.button_hisdbin.setOnClickListener(this);
        this.button_events = (Button) this.findViewById(R.id.button_offlinecheck);
        this.button_events.setOnClickListener(this);
        this.button_intoAchart = (Button) this.findViewById(R.id.button1_intoAchart);
        this.button_intoAchart.setOnClickListener(this);
        this.button_stimuli = (Button) this.findViewById(R.id.button_stimuli);
        this.button_stimuli.setOnClickListener(this);
        this.myListView = (ListView) this.findViewById(R.id.listView_detaillist);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, R.layout.arraylist, this.arraylist);
        myListView.setAdapter(adapter);
        myListView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView arg0, View arg1, int arg2, long arg3) {
                Log.i("你选的是", arg0.getSelectedItem().toString());
            }

            public void onNothingSelected(AdapterView arg0) {
                // TODO Auto-generated method stub
            }
        });


        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                Bundle record = msg.getData();
                if (record != null) {
                    if (record.getString("communication") != null) {
                        pd.dismiss();
                        CommonFunction.showDialog("错误信息", "电表通讯失败", ShowLogActivity.this);
                        button_log.setEnabled(true);
                        button_hisdbin.setEnabled(true);
                    }
                    if (record.getString("import_finish") != null) {
                        button_log.setEnabled(true);
                        button_hisdbin.setEnabled(true);
                        pd.dismiss();
                    }
                    if (record.getLong("timestamp") > 0)//correctly get data
                    {

                        StringBuffer tempsb = new StringBuffer();
                        tempsb.append(record.getInt("row"));    //row
                        long timestamp = record.getLong("timestamp");
                        Date date = new Date(timestamp);        //date
                        tempsb.append("  " + date.toLocaleString());
                        tempsb.append(" \nactive power:" + record.getFloat("ap") + "W");
                        tempsb.append(" \nreactive power:" + record.getFloat("rp") + "var");
                        tempsb.append(" \npower factor:" + record.getFloat("power_factor") + "");
                        //textview_showlog.setText(sb);
                        arraylist.add(tempsb.toString());

                    }
                }
                //Log.i("length", ""+arraylist.size());
                myListView.invalidateViews();    //刷新ss
            }

        };

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.show_log, menu);
        return true;
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            Intent intent = new Intent();
//            intent.setClass(this, MenuActivity.class);
//            this.startActivity(intent);
//            this.finish();
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    public void onClick(View view) {
        try {
            if (view == this.button_intoAchart) {

                HisLogDB logdb = new HisLogDB(this);
                Cursor cursor = logdb.selectAllForTimeStamp();
                if (cursor.getCount() == 0) {
                    CommonFunction.showDialog("error", "没有日志数据", this);
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(this, HisAChartActivity.class);
                this.startActivity(intent);
                this.finish();
            }
            if (view == this.button_clear) {
                this.db_hislog_operator = new HisLogDB(this);
                this.db_hislog_operator.open();
                this.db_hislog_operator.drop();
                this.db_hislog_operator.onCreate();
                this.db_hislog_operator.close();
                Toast toast = Toast.makeText(this, "clear success", Toast.LENGTH_SHORT);

                toast.show();
            }
            // TODO Auto-generated method stub
            if (view == this.button_hisdbin) {

                arraylist.clear();
                String examedstr = ((Editable) this.edittext_interval.getText()).toString();
                //Log.i("examedstr",examedstr);
                Pattern p = Pattern.compile("[0-9]*");
                Matcher m = p.matcher(examedstr);
                Log.i("exeamed result", "" + m.matches());
                if (m.matches() && (!examedstr.equals(""))) {
                    this.button_hisdbin.setEnabled(false);
                    this.button_hisdbin.setEnabled(false);
                    pd = new ProgressDialog(this);
                    pd.setMessage("正在从数据库中获取历史日志信息中...");
                    pd.show();
                    //得到终止的时间戳
                    Date curr = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(curr);
                    cal.add(Calendar.HOUR, -Integer.parseInt(examedstr));
                    Date enddate = cal.getTime();
                    Long endTime = enddate.getTime();    //获得endtiem的时间戳
                    //Log.i("toast", "fdsfsdf");
                    //Toast.makeText(this, enddate.toString(), 20000);
                    Show showthread = new Show(this, endTime);
                    showthread.start();
                } else {
                    Toast.makeText(
                            this, "输入的格式有误",
                            Toast.LENGTH_LONG).show();
                }
            }
            if (view == this.button_events) {
                Intent intent = new Intent();
                intent.setClass(this, LoadListActivity.class);
                this.startActivity(intent);
                this.finish();
            }
            if (view == this.button_log) {

                arraylist.clear();
                String examedstr = ((Editable) this.edittext_interval.getText()).toString();
                //Log.i("examedstr",examedstr);
                Pattern p = Pattern.compile("[0-9]*");
                Matcher m = p.matcher(examedstr);
                //Log.i("exeamed result", ""+m.matches());
                if (m.matches() && (!examedstr.equals(""))) {
                    this.button_log.setEnabled(false);
                    this.button_hisdbin.setEnabled(false);
                    //得到终止的时间戳
                    pd = new ProgressDialog(this);
                    pd.setMessage("正在获取最新电表日志中...");
                    pd.show();
                    Date curr = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(curr);
                    cal.add(Calendar.HOUR, -Integer.parseInt(examedstr));
                    Date enddate = cal.getTime();
                    Long endTime = enddate.getTime();    //获得endtiem的时间戳
                    //Log.i("toast", "fdsfsdf");
                    //Toast.makeText(this, enddate.toString(), 20000);
                    LogThread logthread = new LogThread(this, endTime);
                    logthread.start();
                } else {

                }

            }
            if (view == this.button_stimuli) {
                arraylist.clear();
                String examedstr = ((Editable) this.edittext_interval.getText()).toString();
                //Log.i("examedstr",examedstr);
                Pattern p = Pattern.compile("[0-9]*");
                Matcher m = p.matcher(examedstr);
                Log.i("exeamed result", "" + m.matches());
                if (m.matches() && (!examedstr.equals(""))) {
                    this.button_hisdbin.setEnabled(false);
                    this.button_hisdbin.setEnabled(false);
                    this.modifylog();
                    pd = new ProgressDialog(this);
                    pd.setMessage("正在从数据库中获取历史日志信息中...");
                    pd.show();
                    //得到终止的时间戳
                    Date curr = new Date();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(curr);
                    cal.add(Calendar.HOUR, -Integer.parseInt(examedstr));
                    Date enddate = cal.getTime();
                    Long endTime = enddate.getTime();    //获得endtiem的时间戳
                    //Log.i("toast", "fdsfsdf");
                    //Toast.makeText(this, enddate.toString(), 20000);
                    Show showthread = new Show(this, endTime);
                    showthread.start();
                } else {
                    Toast.makeText(
                            this, "输入的格式有误",
                            Toast.LENGTH_LONG).show();
                }


            }

        } catch (Exception e) {
            CommonFunction.showDialog("错误信息", "电表通讯失败", this);
        }

    }

    class Show extends LogThread {

        public Show(Context context, Long endtimeStamp) {
            super(context, endtimeStamp);
            // TODO Auto-generated constructor stub
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            this.Show();
        }

        public void Show() {

            Log.i(" 从数据库中读取", "下面从数据库中读取");

            //第二步：
            db_hislog_operator = new HisLogDB(this.context);
            db_hislog_operator.open();
            Cursor cursor = db_hislog_operator.select(endtimeStamp);
            Float ap = 0f;
            Float rp = 0f;
            Float power_factor = 0f;
            long timestamp = -1;        //handler 里面处理 如果是-1就有问题
            int i = 0;

            while (cursor.moveToNext()) {

                timestamp = cursor.getLong(0);
                ap = cursor.getFloat(1);
                rp = cursor.getFloat(2);
                power_factor = cursor.getFloat(3);
                i++;
                Bundle record = new Bundle();
                record.putLong("timestamp", timestamp);
                record.putFloat("ap", ap);
                record.putFloat("rp", rp);
                record.putFloat("power_factor", power_factor);
                record.putInt("row", i);
                Message message = new Message();
                message.setData(record);
                handler.sendMessage(message);
            }
            Bundle data = new Bundle();
            data.putString("import_finish", "ok");
            Message msg = new Message();
            msg.setData(data);
            handler.sendMessage(msg);
            db_hislog_operator.close();//关闭
            Log.i("data end", "data end");


        }

    }

    class LogThread extends Thread {

        Context context;    //上下文  也就是所在的Activity
        Long endtimeStamp;        //到之前的某个时间结点的时间戳

        public LogThread(Context context, Long endtimeStamp) {
            super();
            this.context = context;
            this.endtimeStamp = endtimeStamp;
            // TODO Auto-generated constructor stub
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            super.run();
            try {
                getLog2DB_and_Show();
            } catch (Exception e) {
                Log.i("fail", "communication");
                Bundle error = new Bundle();
                Message msg = new Message();
                error.putString("communication", "error");
                msg.setData(error);
                handler.sendMessage(msg);
            }
        }

        public void getLog2DB_and_Show() {
            Log.i("开始log", "开始");
            //	第一步：
            ((Scheider) meter).getLog(this.context);        //先导入数据库

            //下面用来显示

            Log.i("结束log,从数据库中读取", "下面从数据库中读取");

            //第二步：
            db_hislog_operator = new HisLogDB(this.context);
            db_hislog_operator.open();
            Cursor cursor = db_hislog_operator.select(endtimeStamp);
            Float ap = 0f;
            Float rp = 0f;
            Float power_factor = 0f;
            long timestamp = -1;        //handler 里面处理 如果是-1就有问题
            int i = 0;

            while (cursor.moveToNext()) {
                timestamp = cursor.getLong(0);
                ap = cursor.getFloat(1);
                rp = cursor.getFloat(2);
                power_factor = cursor.getFloat(3);
                i++;
                Bundle record = new Bundle();
                record.putLong("timestamp", timestamp);
                record.putFloat("ap", ap);
                record.putFloat("rp", rp);
                record.putFloat("power_factor", power_factor);
                //Log.i("rp", ""+rp);
                record.putInt("row", i);
                Message message = new Message();
                message.setData(record);
                handler.sendMessage(message);
            }
            Bundle data = new Bundle();
            data.putString("import_finish", "ok");
            Message msg = new Message();
            msg.setData(data);
            handler.sendMessage(msg);
            db_hislog_operator.close();//关闭
            Log.i("data end", "data end");


        }
    }

    public void modifylog() {

        HisLogDB db_operator = new HisLogDB(this);
        db_operator.open();
        Date date = new Date();

        long timestamp = date.getTime();
        timestamp = timestamp - timestamp % 1000;
        date = new Date(timestamp);

        //第0条模拟记录
        float ap = 0f;
        float rp = 0f;
        float power_factor = 1f;
        date.setMinutes(00);
        date.setSeconds(00);
        db_operator.insert(date, ap, rp, power_factor);
        //1
        ap = 0f;
        rp = 0f;
        power_factor = 1f;
        date.setMinutes(00);
        date.setSeconds(05);
        db_operator.insert(date, ap, rp, power_factor);
        //2
        ap = 0f;
        rp = 0f;
        power_factor = 1f;
        date.setMinutes(00);
        date.setSeconds(10);
        db_operator.insert(date, ap, rp, power_factor);
        //3
        ap = 32.82f;
        rp = -13.87f;
        power_factor = 0.92f;
        date.setMinutes(00);
        date.setSeconds(15);

        db_operator.insert(date, ap, rp, power_factor);
        //3
        ap = 32.82f;
        rp = -13.87f;
        power_factor = 0.92f;
        date.setMinutes(00);
        date.setSeconds(20);

        db_operator.insert(date, ap, rp, power_factor);
        //3
        ap = 32.82f;
        rp = -13.87f;
        power_factor = 0.92f;
        date.setMinutes(00);
        date.setSeconds(25);

        db_operator.insert(date, ap, rp, power_factor);
        //3
        ap = 32.82f;
        rp = -13.87f;
        power_factor = 0.92f;
        date.setMinutes(00);
        date.setSeconds(30);

        db_operator.insert(date, ap, rp, power_factor);
        //3
        ap = 32.82f;
        rp = -13.87f;
        power_factor = 0.92f;
        date.setMinutes(00);
        date.setSeconds(35);

        db_operator.insert(date, ap, rp, power_factor);
        //3
        ap = 32.82f;
        rp = -13.87f;
        power_factor = 0.92f;
        date.setMinutes(00);
        date.setSeconds(40);

        db_operator.insert(date, ap, rp, power_factor);
        //第二条模拟记录
        ap = 34.10f;
        rp = -9.58f;
        power_factor = 0.96f;
        date.setSeconds(45);
        db_operator.insert(date, ap, rp, power_factor);
        //三
        ap = 34.17f;
        rp = -8.31f;
        power_factor = 0.97f;
        date.setSeconds(50);
        db_operator.insert(date, ap, rp, power_factor);
        //四
        ap = 33.01f;
        rp = -9.03f;
        power_factor = 0.97f;
        date.setSeconds(55);
        db_operator.insert(date, ap, rp, power_factor);
        //五
        ap = 33.91f;
        rp = -9.02f;
        power_factor = 0.97f;
        date.setMinutes(01);
        date.setSeconds(00);
        db_operator.insert(date, ap, rp, power_factor);
        //六
        ap = 0f;
        rp = 0f;
        power_factor = 1f;
        date.setSeconds(05);

        db_operator.insert(date, ap, rp, power_factor);
        //7
        ap = 0f;
        rp = 0f;
        power_factor = 1f;
        date.setSeconds(10);

        db_operator.insert(date, ap, rp, power_factor);
        //8
        ap = 0f;
        rp = 0f;
        power_factor = 1f;
        date.setSeconds(15);

        db_operator.insert(date, ap, rp, power_factor);
        //9
        ap = 0f;
        rp = 0f;
        power_factor = 1f;
        date.setSeconds(20);

        db_operator.insert(date, ap, rp, power_factor);
        ap = 0f;
        rp = 0f;
        power_factor = 1f;
        date.setSeconds(25);

        db_operator.insert(date, ap, rp, power_factor);
        ap = 0f;
        rp = 0f;
        power_factor = 1f;
        date.setSeconds(30);

        db_operator.insert(date, ap, rp, power_factor);
        ap = 0f;
        rp = 0f;
        power_factor = 1f;
        date.setSeconds(35);

        db_operator.insert(date, ap, rp, power_factor);
        ap = 0f;
        rp = 0f;
        power_factor = 1f;
        date.setSeconds(40);

        db_operator.insert(date, ap, rp, power_factor);
        db_operator.close();
        Log.i("modify", "sucess");
    }
}
