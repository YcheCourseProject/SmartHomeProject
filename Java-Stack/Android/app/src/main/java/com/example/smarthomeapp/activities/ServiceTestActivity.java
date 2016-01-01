package com.example.smarthomeapp.activities;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.services.CollectDataService;
import com.example.smarthomeapp.services.CountService;
import com.example.smarthomeapp.thirdparty.sina.GetCalendarEvent;
import com.example.smarthomeapp.thirdparty.sina.GetSMS;

public class ServiceTestActivity extends Activity {

    private static final String TAG1 = "ServiceTestActivity";

    private Button startServiceBtn;
    private Button stopServiceBtn;
    private Button bindServiceBtn;
    private Button unbindServiceBtn;
    private Button setGpsIntervalBtn;

    private CountService countService;

    private CollectDataService collectDataService;

    private SharedPreferences sharedPreferences;

    /**
     * 连接activity 和 service
     */
    private ServiceConnection connection = new ServiceConnection() {
        //获取服务对象时的操作
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            collectDataService = ((CollectDataService.ServiceBinder)iBinder).getService();
        }

        //无法获取服务对象时的操作
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            collectDataService = null;

        }

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);

        sharedPreferences = getSharedPreferences("test", Activity.MODE_PRIVATE);
        GetSMS.setInterval(sharedPreferences.getInt("TimerTaskInterval", 120000));
        GetCalendarEvent.setInterval(sharedPreferences.getInt("TimerTaskInterval", 12000));
        int gpsInterval = sharedPreferences.getInt("GpsInterval", 10000);


        startServiceBtn = (Button)findViewById(R.id.startServiceBtn);
        stopServiceBtn = (Button)findViewById(R.id.stopServiceBtn);
        bindServiceBtn = (Button)findViewById(R.id.bindServiceBtn);
        unbindServiceBtn = (Button)findViewById(R.id.unBindServiceBtn);
        setGpsIntervalBtn = (Button)findViewById(R.id.setGpsIntervalBtn);

        startServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ServiceTestActivity.this, CollectDataService.class);
                startService(intent);
                Log.d(TAG1, "start service");

            }
        });

        stopServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceTestActivity.this, CollectDataService.class );
                stopService(intent);
                collectDataService = null;
                Log.d(TAG1, "stop service");
            }
        });

        bindServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ServiceTestActivity.this, CollectDataService.class);
                bindService(intent, connection, Context.BIND_AUTO_CREATE );
                Log.d(TAG1, "bind service");
            }
        });

        unbindServiceBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                unbindService(connection);
                Log.d(TAG1, "unbind service");
            }
        });

        setGpsIntervalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(connection!=null && collectDataService != null){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("TimerTaskInterval", 100);
                    editor.commit();

                    GetSMS.setInterval(sharedPreferences.getInt("TimerTaskInterval", 120000));
                    GetCalendarEvent.setInterval(sharedPreferences.getInt("TimerTaskInterval", 120000));

                    Log.d(TAG1, String.valueOf(GetSMS.getInterval()));

                    //调用service中的方法
                    collectDataService.setGpsInterval(sharedPreferences.getInt("GpsInterval", 10000));
                    collectDataService.restartSmsTimer();

                    collectDataService.restartCalendarTimer();
                    collectDataService.restartWeiBoEventTimer(100000);


                    Log.d(TAG1, "set Gps interval");
                }else{
                    if (connection == null){
                        Log.d(TAG1, "connection null ");
                    }else{
                        Log.d(TAG1, "collectDataService null");
                    }
                }
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_service_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
