package com.example.shems.activities;

import com.example.shems.ASGApplication;
import com.example.shems.daos.meters.ge.GE;
import com.example.shems.daos.meters.sentron.Sentron;
import com.example.shems.util.CommonFunction;
import com.example.smarthome.R;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MenuActivity extends FatherActivity implements OnClickListener,
        android.content.DialogInterface.OnClickListener {

    public static final int REFRESH = 0;
    TextView txtViewTypeName;
    TextView txtViewIP;
    TextView txtViewState;
    View processBar;
    GridView gridview_functions;
    EditText et_passwd;

    // 实例化一个handler
    Handler handler = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Log.i("MenuActivity.oncreate", "0");
        connector = ((ASGApplication) getApplication()).getConnector();
        meter = ((ASGApplication) getApplication()).getMeter();
        this.txtViewTypeName = (TextView) this
                .findViewById(R.id.menu_tvTypeName);
        this.txtViewIP = (TextView) this.findViewById(R.id.menu_tvIP);
        this.txtViewState = (TextView) this.findViewById(R.id.menu_tvState);
        this.gridview_functions = (GridView) this
                .findViewById(R.id.gridView_functions2);
        //创建一个获取R中内容的对象
        Resources resources=this.getResources();
        String funarr[]=resources.getStringArray(R.array.Menu_Function_Items);
        String cnnstatus[]=resources.getStringArray(R.array.Connection_Stauts);
        this.gridview_functions.setAdapter(new ImageAdpter(this,funarr));
        this.gridview_functions.setNumColumns(2);// 设置每行列数
        this.gridview_functions.setGravity(Gravity.CENTER);// 位置居中
        this.gridview_functions.setVerticalSpacing(50);// 垂直间隔
        this.gridview_functions.setHorizontalSpacing(10);// 水平间隔

        this.gridview_functions
                .setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        switch (position) {
                            case 2: {

                                Intent myintent = new Intent();
                                myintent.setClass(MenuActivity.this,
                                        GetRealTimePriceActivity.class);
                                MenuActivity.this.startActivity(myintent);
                                MenuActivity.this.finish();

                                break;
                            }
                            case 1: {

                                Intent myintent = new Intent();

                                myintent.setClass(MenuActivity.this,
                                        HBAChartActivity.class);
                                MenuActivity.this.startActivity(myintent);
                                MenuActivity.this.finish();

                                break;
                            }
                            case 3:
                            {
                                Intent myintent = new Intent();

                                myintent.setClass(MenuActivity.this,
                                        ShowSQLActivity.class);
                                MenuActivity.this.startActivity(myintent);
                                MenuActivity.this.finish();

                                break;
                            }
                            case 0:
                            {
                                if ( connector == null || connector.ipAddressStr.equals("") ) {
                                    new AlertDialog.Builder(MenuActivity.this).setTitle("Error")
                                            .setMessage("Please connect the meter first.")
                                            .setPositiveButton("Back", null).show();
                                }
                                else
                                {
                                    Intent myintent = new Intent();

                                    myintent.setClass(MenuActivity.this,
                                            LoadCheckActivity.class);
                                    MenuActivity.this.startActivity(myintent);
                                    MenuActivity.this.finish();
                                }
                                break;
                            }

                        }
                    }
                });
        if (connector == null || connector.ipAddressStr.equals("")) {
            this.txtViewState.setTextColor(Color.BLACK);
            this.txtViewState.setText(cnnstatus[1]);
        } else {
            if (connector.type == 0) {

                this.txtViewTypeName.setText("Nexus 1272");

            } else if (connector.type == 1) {

                this.txtViewTypeName.setText("PAC4200");

            } else if (connector.type == 2) {

                this.txtViewTypeName.setText("Scheider PM8");

            }
            this.txtViewIP.setText(connector.ipAddressStr);
            this.txtViewState.setText(cnnstatus[0]);
        }

        // Log.i("MenuActivity.oncreate", "1st");
        // this.connector.connectNonjamod("192.168.1.118", 502, 255, 2);

        handler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                Bundle data = msg.getData();

                if (data.getString("type").equals("passwdIdentify")) {
                    // 如果是施耐德电表，不用输入密码
                    if (meter.connector.type == 2) {
                        turnToModifyActivity();
                        return;
                    }

                    MenuActivity.this.et_passwd = new EditText(
                            MenuActivity.this);
                    AlertDialog.Builder builder = new AlertDialog.Builder(
                            MenuActivity.this)
                            .setView(MenuActivity.this.et_passwd);
                    builder.setPositiveButton("Yes", MenuActivity.this);
                    builder.setNegativeButton("No", MenuActivity.this);

                    if (meter.connector.type == 0) {

                        builder.setTitle("Please input the key(10 characters):");
                        builder.show();

                    } else if (meter.connector.type == 1) {
                        builder.setTitle("Please input the key(4 digitals):");
                        builder.show();

                    }

                } else if (data.getString("type").equals("passwdValidate")) {

                    if (meter.connector.type == 0) {
                        meter.passwd = et_passwd.getText().toString().trim();
                        Log.i("MenuActivity.handleMessage", "gePasswd="
                                + meter.passwd);
                    } else if (meter.connector.type == 1) {
                        meter.passwd = Sentron
                                .changeIntToPasswdStr(Integer
                                        .parseInt(et_passwd.getText()
                                                .toString().trim()));

                    }

                    turnToModifyActivity();

                } else if (data.getString("type").equals("passwdInvalidate")) {
                    CommonFunction.showDialog("Fail!", "Password wrong",
                            MenuActivity.this);
                }

            }
        };
        Log.i("MenuActivity.oncreate", "end");

    }

    public void turnToModifyActivity() {

        Intent myintent = new Intent();
        myintent.setClass(this, ModifyParaActivity.class);
        this.startActivity(myintent);
        this.finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_menu, menu);
        return true;
    }

    //菜单的相关操作
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // TODO Auto-generated method stub
        if(item.getItemId()==R.id.menu_hardset){
            Intent myintent = new Intent();

            myintent.setClass(MenuActivity.this,
                    ScanActivity.class);
            MenuActivity.this.startActivity(myintent);
            //MenuActivity.this.finish();
        }
        if(item.getItemId()==R.id.menu_aboutus){
            Intent myintent = new Intent();

            myintent.setClass(MenuActivity.this,
                    ConnectingActivity.class);
            MenuActivity.this.startActivity(myintent);
            //MenuActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void onClick(View v) {
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        return super.onKeyDown(keyCode, event);
//    }

    public void onClick(DialogInterface dialog, int which) {
        // TODO Auto-generated method stub
        if (which == dialog.BUTTON_POSITIVE) {
            if (meter.connector.type == 0) {
                if (MenuActivity.this.et_passwd.getText().toString().trim()
                        .length() != 10) {
                    CommonFunction.showDialog("Warning!",
                            "Password's length must be 10!", MenuActivity.this);
                } else {
                    PasswdIdentifyThread thread = new PasswdIdentifyThread();
                    thread.start();
                }

            } else if (meter.connector.type == 1) {
                int passwd = 0;
                try {
                    passwd = Integer.parseInt(et_passwd.getText().toString()
                            .trim());
                } catch (Exception exception) {
                    CommonFunction.showDialog("Fail!",
                            "Password must be digitals", MenuActivity.this);
                    return;
                }
                if (passwd > 9999) {
                    CommonFunction
                            .showDialog(
                                    "Fail!",
                                    "Password must be digitals and not larger than 9999!",
                                    MenuActivity.this);
                } else {
                    PasswdIdentifyThread thread = new PasswdIdentifyThread();
                    thread.start();
                }
            }

        } else if (which == dialog.BUTTON_NEGATIVE) {

        }

        dialog.cancel();
    }

    class PasswdIdentifyThread extends Thread {

        @Override
        public void run() {
            Bundle data = new Bundle();
            boolean suc = false;
            connector.connectNonjamod(connector.ipAddressStr, connector.port,
                    connector.unitID, connector.type);

            if (meter.connector.type == 0) {
                suc = ((GE) meter).validatePasswd(et_passwd.getText()
                        .toString().trim());
            } else if (meter.connector.type == 1) {
                suc = ((Sentron) meter)
                        .changeSentronPasswdState(
                                Short.parseShort(et_passwd.getText().toString()
                                        .trim()), (short) 0);
            }
            connector.disconnect();

            if (suc) {
                data.putString("type", "passwdValidate");
            } else {
                data.putString("type", "passwdInvalidate");
            }

            Message msg = new Message();
            msg.setData(data);
            handler.sendMessage(msg);

        }
    }

    private class ImageAdpter extends BaseAdapter {
        private Context context;
        private int width = 100;
        private int height = 100;
        private Integer[] images = {
                //R.drawable.menu_devicesetting,
                R.drawable.menu_onlineidentify,
                R.drawable.menu_offlineidentify,
                R.drawable.optimizemenu,
                R.drawable.menu_loadinfo,
                //R.drawable.menu_about_us
        };

        private String[] texts;	//对应图片的文本内容



        public ImageAdpter(Context context,String[] str)
        {
            this.context=context;
            texts=str;
        }

        public int getCount() {
            return images.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int id) {
            return id;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout linearLayout;
            linearLayout = new LinearLayout(this.context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setGravity(Gravity.CENTER);
            ImageView iv =new ImageView(this.context);
            iv.setImageResource(images[position]);
            iv.setLayoutParams(new AbsListView.LayoutParams(width, height));

            linearLayout.addView(iv);					//添加照片的VIEW

            TextView tv = new TextView(this.context);
            tv.setText(texts[position]);
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(15);
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setPadding(0, 20, 0, 30);
            linearLayout.addView(tv);			//添加文字的VIEW
            return linearLayout;

        }

    }


}
