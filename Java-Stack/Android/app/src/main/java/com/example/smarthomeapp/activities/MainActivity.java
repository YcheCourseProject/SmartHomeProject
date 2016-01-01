package com.example.smarthomeapp.activities;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Message;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.dbhelper.DBHelper;
import com.example.smarthomeapp.dbhelper.DBHelperFactory;
import com.example.smarthomeapp.dbhelper.DBManager;
import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.model.ActivityType;
import com.example.smarthomeapp.model.GpsInfo;
import com.example.smarthomeapp.model.MovingStatus;
import com.example.smarthomeapp.model.SocialSource;
import com.example.smarthomeapp.model.WearableDeviceInfo;
import com.example.smarthomeapp.model.SocialInfo;
import com.example.smarthomeapp.thirdparty.sina.GetCalendarEvent;
import com.example.smarthomeapp.thirdparty.sina.GetSMS;
import com.example.smarthomeapp.thirdparty.sina.GetWeiBoEvent;
import com.example.smarthomeapp.threadhelp.ThreadPool;
import com.example.smarthomeapp.util.AsyncHttpUtil;
import com.example.smarthomeapp.util.GsonUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.microsoft.band.BandClient;
import com.microsoft.band.BandClientManager;
import com.microsoft.band.BandException;
import com.microsoft.band.BandIOException;
import com.microsoft.band.BandInfo;
import com.microsoft.band.ConnectionState;
import com.microsoft.band.UserConsent;
import com.microsoft.band.sensors.BandAccelerometerEvent;
import com.microsoft.band.sensors.BandAccelerometerEventListener;
import com.microsoft.band.sensors.BandDistanceEvent;
import com.microsoft.band.sensors.BandDistanceEventListener;
import com.microsoft.band.sensors.BandGyroscopeEvent;
import com.microsoft.band.sensors.BandGyroscopeEventListener;
import com.microsoft.band.sensors.BandHeartRateEvent;
import com.microsoft.band.sensors.BandHeartRateEventListener;
import com.microsoft.band.sensors.BandSkinTemperatureEvent;
import com.microsoft.band.sensors.BandSkinTemperatureEventListener;
import com.microsoft.band.sensors.HeartRateConsentListener;
import com.microsoft.band.sensors.MotionType;
import com.microsoft.band.sensors.SampleRate;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;


public class MainActivity extends ActionBarActivity {

    private static final String TAG = "MainActivity";

    private static final int GPS = 1;
    private static final int MICROSOFTBAND = 2;
    private static final int SMS = 3;
    private static final int CALENDAR = 4;
    private static final int WEIBO = 5;
    private static final String REMOTE_HOST_SOCIAL = "http://202.117.14.247:8080/smarthome/socialInfo/list";
    private static final String REMOTE_HOST_GPS = "http://202.117.14.247:8080/smarthome/gpsInfo";
    private static final String REMOTE_HOST_BAND =  "http://202.117.14.247:8080/smarthome/wearableInfo";

    private DBManager dbManager;
    private ThreadPool threadPool;

    private Context context;
    private Handler mHandler ;

    private double latitude=0.0;
    private double longitude=0.0;
    private LocationManager locationManager;
    private Location location;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateData(location);
            Log.d(TAG, "Location changed");
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {

        }

        @Override
        public void onProviderEnabled(String s) {

        }

        @Override
        public void onProviderDisabled(String s) {

        }
    };

    private Button gpsStartBtn;
    private Button gpsEndBtn;
    private Button bandStartBtn;
    private Button bandEndBtn;
    private Button smsStartBtn;
    private Button smsEndBtn;
    private Button calendarStartBtn;
    private Button calendarEndBtn;
    private Button submitIntervalBtn;
    private Button startWeiboBtn;

    private EditText TimerEditText;

    private BandClient client = null;
    private Float [] accelerated_speed = {(float)0, (float)0, (float)0};
    private Float [] gyroscope = {(float)0, (float)0, (float)0};
    private Float body_temperature = Float.valueOf(0);
    private String moving_status = "unknown";
    private Float speed = Float.valueOf(0);
    private Float heart_rate = Float.valueOf(0);


    private Timer mTimer;
    private SMSTimeTask myTimerTask;

    private Timer calendarTimer;
    private CalendarTimerTask calendarTimerTask;

    private SharedPreferences mySharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbManager = new DBManager(this);
        dbManager.openDatabase();
        dbManager.closeDatabase();

        GpsInfo gpsInfo = new GpsInfo();
        Log.d(TAG, gpsInfo.getClass().toString());

        WearableDeviceInfo wearableDeviceInfo = new WearableDeviceInfo();
        Log.d(TAG, wearableDeviceInfo.getClass().toString());


        context = this;

        mHandler = new Handler(){
            @Override
            public void handleMessage(Message message){

                Log.d(TAG, "HandleMessage" + message.what);
                Gson gson = GsonUtil.create();

                switch (message.what){
                    case GPS:
                        String gpsInfo = message.getData().getString("GPSINFO");
                        GpsInfo gpsObj = gson.fromJson(gpsInfo,GpsInfo.class);
                        Log.d(TAG, gpsInfo);
                        AsyncHttpUtil.requestJson(context,
                                REMOTE_HOST_GPS ,
                                gpsInfo,
                                new HttpResultProcessListener() {
                                        @Override
                                        public void processing(int status, String responsString) {
                                                 if(responsString != null){
                                                     Log.d(TAG, responsString);
                                                     Toast.makeText(context, responsString, Toast.LENGTH_SHORT).show();
                                                 }
                                        }
                           });
                        threadPool = ThreadPool.getThreadPool(20);
                        threadPool.addTask(new WriteThread((gpsObj)));
                        break;
                    case MICROSOFTBAND:
                        Log.d(TAG, "MICROSOFTBAND");

                        WearableDeviceInfo wearableDeviceInfo=new WearableDeviceInfo();
                        MovingStatus movingStatus=new MovingStatus();
                        movingStatus.setMovingType(moving_status);
                        wearableDeviceInfo.setMovingStatus(movingStatus);
                        wearableDeviceInfo.setAcceleratedSpeedX(accelerated_speed[0]);
                        wearableDeviceInfo.setAcceleratedSpeedY(accelerated_speed[1]);
                        wearableDeviceInfo.setAcceleratedSpeedZ(accelerated_speed[2]);
                        wearableDeviceInfo.setBodyTemperature(body_temperature);
                        wearableDeviceInfo.setGyroscopeX(gyroscope[0]);
                        wearableDeviceInfo.setGyroscopeY(gyroscope[1]);
                        wearableDeviceInfo.setGyroscopeZ(gyroscope[2]);
                        wearableDeviceInfo.setHeartRate(heart_rate);
                        wearableDeviceInfo.setSpeed(speed);
                        wearableDeviceInfo.setWearableInfoRecordTime(new Timestamp(System.currentTimeMillis()));

                        String wearable = gson.toJson(wearableDeviceInfo);
                        Log.d(TAG, wearable);

                        AsyncHttpUtil.requestJson(context,
                                REMOTE_HOST_BAND,
                                wearable,
                                new HttpResultProcessListener() {
                                    @Override
                                    public void processing(int status, String responsString) {
                                        Log.d(TAG, "processing 1 " +status );
                                        if(responsString != null){
                                            Log.d(TAG, responsString);
                                            Toast.makeText(context, responsString, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                        Log.d(TAG, wearableDeviceInfo.getClass().toString());

                        threadPool = ThreadPool.getThreadPool(20);
                        threadPool.addTask(new WriteThread((wearableDeviceInfo)));
                        break;

                    case SMS:

                        String sms = message.getData().getString("SMS");

                        Log.d(TAG, "SMS " + sms);

                        ArrayList<SocialInfo> smsEvent = new ArrayList<SocialInfo>();

                        smsEvent = gson.fromJson(sms,  new TypeToken<ArrayList<SocialInfo>>(){}.getType());

                        AsyncHttpUtil.requestJson(context,
                                REMOTE_HOST_SOCIAL,
                                sms,
                                new HttpResultProcessListener() {
                                    @Override
                                    public void processing(int status, String responsString) {
                                        Log.d(TAG, "processing" + String.valueOf(status));
                                        if(responsString != null){
                                            Log.d(TAG, responsString);
                                            Toast.makeText(context, responsString, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });

                        threadPool = ThreadPool.getThreadPool(20);
                        for(SocialInfo social : smsEvent){
                            threadPool.addTask(new WriteThread((social)));
                        }
                        break;
                    case CALENDAR:
                        Log.d(TAG, "CALENDAR");
                        String calendar = message.getData().getString("CALENDAR");
                        Log.d(TAG, calendar);
                        ArrayList<SocialInfo> calendarEvent = gson.fromJson(
                                calendar,
                                new TypeToken<ArrayList<SocialInfo>>(){}.getType()
                        );
                        AsyncHttpUtil.requestJson(context,
                                REMOTE_HOST_SOCIAL,
                                calendar,
                                new HttpResultProcessListener() {
                                    @Override
                                    public void processing(int status, String responsString) {
                                        if(responsString != null){
                                            Log.d(TAG, responsString);
                                            Toast.makeText(context, responsString, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        threadPool = ThreadPool.getThreadPool(20);
                        for(SocialInfo social : calendarEvent){
                            threadPool.addTask(new WriteThread((social)));
                        }
                        break;
                    case WEIBO:

                        String weiBo = message.getData().getString("WEIBO");
                        Long lastestId = message.getData().getLong("WeiBoLatestID");
                        Log.d(TAG, weiBo);
                        Log.d(TAG, String.valueOf(lastestId));
                        mySharedPreferences.edit().putLong("WeiBoLatestID", lastestId);
                        mySharedPreferences.edit().commit();

                        ArrayList<SocialInfo> weiBoEvent = gson.fromJson(
                                weiBo,
                                new TypeToken<ArrayList<SocialInfo>>(){}.getType()
                        );
                        AsyncHttpUtil.requestJson(context,
                                REMOTE_HOST_SOCIAL,
                                weiBo,
                                new HttpResultProcessListener() {
                                    @Override
                                    public void processing(int status, String responsString) {
                                        if(responsString != null){
                                            Log.d(TAG, responsString);
                                            Toast.makeText(context, responsString, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        threadPool = ThreadPool.getThreadPool(20);
                        for(SocialInfo social : weiBoEvent){
                            threadPool.addTask(new WriteThread((social)));
                        }
                        break;

                }

            }
        };

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            Toast.makeText(context, "provider enabled", Toast.LENGTH_LONG).show();
            Log.e(TAG, "provider enabled");
        }else{
            Toast.makeText(context, "provider disabled", Toast.LENGTH_LONG).show();
            Log.e(TAG, "provider disabled");
        }

        gpsStartBtn = (Button)findViewById(R.id.GpsStartBtn);
        gpsEndBtn = (Button)findViewById(R.id.GpsEndBtn);
        bandStartBtn = (Button)findViewById(R.id.bandStartBtn);
        bandEndBtn = (Button)findViewById(R.id.bandEndBand);
        smsStartBtn = (Button)findViewById(R.id.startSMSBtn);
        smsEndBtn = (Button)findViewById(R.id.endSMSBtn);
        calendarStartBtn = (Button)findViewById(R.id.startCalendarBtn);
        calendarEndBtn = (Button)findViewById(R.id.endCalendarBtn);
        submitIntervalBtn = (Button)findViewById(R.id.submit_interval);
        startWeiboBtn = (Button)findViewById(R.id.startWeiboBtn);

        TimerEditText = (EditText)findViewById(R.id.TimerInterval);

        gpsStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //设置gps监听器 ， 10000ms触发一次, 第三个参数为位置的最小变化值
                locationManager.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        10000,
                        0,
                        locationListener
                );
            }
        });

        gpsEndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                locationManager.removeUpdates(locationListener);
            }
        });

        bandStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new appTask().execute();

            }
        });

        bandEndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(client != null){
                    try {
                        client.getSensorManager().unregisterAllListeners();
                    }catch (BandIOException e){
                        Log.d(TAG, e.getMessage());
                    }
                }

            }
        });

        smsStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mTimer = new Timer(true);
                myTimerTask = new SMSTimeTask();
                mTimer.schedule(myTimerTask, 2000, GetSMS.getInterval());

            }
        });

        smsEndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimer.cancel();
            }
        });

        calendarStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarTimer = new Timer(true);
                calendarTimerTask = new CalendarTimerTask();
                calendarTimer.schedule(calendarTimerTask, 3000, GetCalendarEvent.getInterval());
            }
        });

        calendarEndBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarTimer.cancel();
            }
        });

        submitIntervalBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = TimerEditText.getText().toString();
                int interval = Integer.valueOf(text);

                SharedPreferences.Editor editor = mySharedPreferences.edit();
                editor.putInt("TimerTaskInterval", interval);
                editor.commit();

                GetSMS.setInterval(interval);

                mTimer.cancel();
                calendarTimer.cancel();

                mTimer = new Timer();
                myTimerTask = new SMSTimeTask();
                mTimer.schedule(myTimerTask, 2000, GetSMS.getInterval());

                calendarTimer.cancel();
                calendarTimerTask = new CalendarTimerTask();
                calendarTimer.schedule(calendarTimerTask, 2000, GetCalendarEvent.getInterval());

            }
        });

        startWeiboBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GetWeiBoEvent getWeiBoEvent = GetWeiBoEvent.getInstance(context, mHandler);
                getWeiBoEvent.getWeiBoEvent();
            }
        });

        mySharedPreferences = getSharedPreferences("test", Activity.MODE_PRIVATE);
        GetSMS.setInterval(mySharedPreferences.getInt("TimerTaskInterval", 12000));
        GetCalendarEvent.setInterval(mySharedPreferences.getInt("TimerTaskInterval", 12000));
        GetWeiBoEvent.setLatestId(mySharedPreferences.getLong("WeiBoLatestID", 3866420464724979L));



    }

    @Override
    protected void onPause(){
        super.onPause();
        if (client != null) {
            try {
                client.getSensorManager().unregisterAccelerometerEventListeners();
            } catch (BandIOException e) {
                Log.d(TAG, e.getMessage());
            }
        }
    }

    /**
     * 获得短信
     */
    class SMSTimeTask extends TimerTask {

        @Override
        public void run() {

            Log.d(TAG, "MyTimerTask SMS run");

            GetSMS getSMS = GetSMS.getInstance(context);

            ArrayList<SocialInfo> socialList = getSMS.getSMS();

           if(socialList != null && socialList.size() > 0){

               Gson gson = GsonUtil.create();
               String str = gson.toJson(socialList, socialList.getClass());
               Log.d(TAG, str);

               Message msg = new Message();
               msg.what = SMS;
               Bundle bundle = new Bundle();
               bundle.putString("SMS", str);
               msg.setData(bundle);
               mHandler.sendMessage(msg);
           }else{
               Log.d(TAG, "no message in last 20 minutes");
           }


        }
    }

    /**
     * 获得日历事件
     */
    class CalendarTimerTask extends TimerTask{
        @Override
        public void run(){
            GetCalendarEvent getCalendarEvent = GetCalendarEvent.getInstance(context);

            ArrayList<SocialInfo> socialList = getCalendarEvent.getCalendarEvent();

            if(socialList != null && socialList.size() > 0){

                Gson gson = GsonUtil.create();
                String str = gson.toJson(socialList);
                Log.d(TAG, str);

                Message msg = new Message();
                msg.what = CALENDAR;
                Bundle bundle = new Bundle();
                bundle.putString("CALENDAR", str);
                msg.setData(bundle);
                mHandler.sendMessage(msg);
            }else{
                Log.d(TAG, "no message in last 20 minutes");
            }
        }
    }




    private class appTask extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {
            try {
                if (getConnectedBandClient()) {
                    client.getSensorManager().registerAccelerometerEventListener(mAccelerometerEventListener, SampleRate.MS128);
                    client.getSensorManager().registerSkinTemperatureEventListener(mBandSkinTemperatureEventListener);
                    if(client.getSensorManager().getCurrentHeartRateConsent() != UserConsent.GRANTED){
                        client.getSensorManager().requestHeartRateConsent(MainActivity.this,heartRateConsentListener);
                    }else{
                        startHRListener();
                    }
                    client.getSensorManager().registerGyroscopeEventListener(bandGyroscopeEventListener,SampleRate.MS128);
                    client.getSensorManager().registerDistanceEventListener(mBandDistanceEventListener);
                } else {
                    Log.d(TAG, "Band isn't connected. Please make sure bluetooth is on and the band is in range.");
                }
            } catch (BandException e) {
                String exceptionMessage=" ";
                switch (e.getErrorType()) {
                    case UNSUPPORTED_SDK_VERSION_ERROR:
                        exceptionMessage = "Microsoft Health BandService doesn't support your SDK Version. Please update to latest SDK.";
                        break;
                    case SERVICE_ERROR:
                        exceptionMessage = "Microsoft Health BandService is not available. Please make sure Microsoft Health is installed and that you have the correct permissions.";
                        break;
                    default:
                        exceptionMessage = "Unknown error occured: " + e.getMessage();
                        break;
                }
                Log.d(TAG, "ExceptionMessage " + exceptionMessage);
                Log.d(TAG, e.getMessage());
            } catch (Exception e) {
                Log.d(TAG, e.getMessage());
            }
            return null;
        }
    }


    private BandAccelerometerEventListener mAccelerometerEventListener = new BandAccelerometerEventListener() {
        @Override
        public void onBandAccelerometerChanged(final BandAccelerometerEvent event) {
            if (event != null) {
                accelerated_speed[0] = event.getAccelerationX();
                accelerated_speed[1] = event.getAccelerationY();
                accelerated_speed[2] = event.getAccelerationZ();
                String str = String.format("Accelerometer =\n X = %.3f \n Y = %.3f\n Z = %.3f", accelerated_speed[0],
                        accelerated_speed[1], accelerated_speed[2]);
//                Log.d(TAG, str);
//                Message msg = new Message();
//                msg.what = MICROSOFTBAND;
//                mHandler.sendMessage(msg);
            }
        }
    };

    private BandSkinTemperatureEventListener mBandSkinTemperatureEventListener = new BandSkinTemperatureEventListener() {
        @Override
        public void onBandSkinTemperatureChanged(BandSkinTemperatureEvent bandSkinTemperatureEvent) {
            if(bandSkinTemperatureEvent != null){
                body_temperature = bandSkinTemperatureEvent.getTemperature();
                String str = String.format("SkinTemp = %.1f", body_temperature);
                Log.d(TAG, str);
                Message msg = new Message();
                msg.what = MICROSOFTBAND;
                mHandler.sendMessage(msg);
            }
        }
    };

    private BandHeartRateEventListener mBandHeartRateEventListener = new BandHeartRateEventListener() {
        @Override
        public void onBandHeartRateChanged(BandHeartRateEvent bandHeartRateEvent) {
            if(bandHeartRateEvent != null){
                heart_rate = (float)bandHeartRateEvent.getHeartRate();
                String str = String.format("HeartRate = %.3f", heart_rate);
                Log.d(TAG, str);
                Message msg = new Message();
                msg.what = MICROSOFTBAND;
                mHandler.sendMessage(msg);
            }
        }
    };

    private BandGyroscopeEventListener bandGyroscopeEventListener = new BandGyroscopeEventListener() {
        @Override
        public void onBandGyroscopeChanged(BandGyroscopeEvent bandGyroscopeEvent) {
            if(bandGyroscopeEvent != null){

                gyroscope[0] = bandGyroscopeEvent.getAngularVelocityX();
                gyroscope[1] = bandGyroscopeEvent.getAngularVelocityY();
                gyroscope[2] = bandGyroscopeEvent.getAngularVelocityZ();
                String str = String.format("Gyroscope =\nX_An = %.3f\n Y_An = %.3f\n Z_An = %.3f",
                        gyroscope[0], gyroscope[1], gyroscope[2]);
//               Log.d(TAG, str);
//                Message msg = new Message();
//                msg.what = MICROSOFTBAND;
//                mHandler.sendMessage(msg);
             }
        }
    };
    private BandDistanceEventListener mBandDistanceEventListener = new BandDistanceEventListener() {
        @Override
        public void onBandDistanceChanged(BandDistanceEvent bandDistanceEvent) {
            if(bandDistanceEvent != null){
                SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm:ss");
                long Distance = bandDistanceEvent.getTotalDistance();
                String motionTypeString = "Unknown";
                MotionType motionType = bandDistanceEvent.getMotionType();
                switch (motionType){
                    case WALKING: motionTypeString = "walking"; break;
                    case JOGGING: motionTypeString = "jogging"; break;
                    case RUNNING: motionTypeString = "running"; break;
                    case IDLE: motionTypeString = "idle"; break;
                    case UNKNOWN: motionTypeString = "unknown"; break;
                    default: //do nothing
                }
                speed = bandDistanceEvent.getSpeed()/100;
                moving_status = motionTypeString;

                String str = String.format("Distance = %.2f %s\nSpeed = %.2f %s\n",Distance/100.0, "m",
                        speed,"m/s") + "MotionType = " + motionTypeString + "\n" + "Time = " + format.format(new Date(bandDistanceEvent.getTimestamp()));
                Log.d(TAG, str);
                Message msg = new Message();
                msg.what = MICROSOFTBAND;
                mHandler.sendMessage(msg);
            }
        }
    };

    private void startHRListener() {
        try {
            // register HR sensor event listener
            client.getSensorManager().registerHeartRateEventListener(mBandHeartRateEventListener);
        } catch (BandIOException ex) {
            Log.d(TAG,  ex.getMessage());
        } catch (BandException e) {
            String exceptionMessage="";
            switch (e.getErrorType()) {
                case UNSUPPORTED_SDK_VERSION_ERROR:
                    exceptionMessage = "Microsoft Health BandService doesn't support your SDK Version. Please update to latest SDK.";
                    break;
                case SERVICE_ERROR:
                    exceptionMessage = "Microsoft Health BandService is not available. Please make sure Microsoft Health is installed and that you have the correct permissions.";
                    break;
                default:
                    exceptionMessage = "Unknown error occurred: " + e.getMessage();
                    break;
            }
            Log.d(TAG, exceptionMessage);

        } catch (Exception e) {
           Log.d(TAG, e.getMessage());
        }
    }

    private HeartRateConsentListener heartRateConsentListener = new HeartRateConsentListener() {
        @Override
        public void userAccepted(boolean b) {
            if(b == true){
                startHRListener();
            }
            else{
                Log.d(TAG, "user do not consent");
            }
        }
    };



    private boolean getConnectedBandClient() throws InterruptedException, BandException {
        if (client == null) {
            BandInfo[] devices = BandClientManager.getInstance().getPairedBands();
            if (devices.length == 0) {
                Log.d(TAG, "band isn't pared with you phone");
                Toast.makeText(context, "Band isn't paired with your phone.", Toast.LENGTH_LONG).show();
                return false;
            }
            client = BandClientManager.getInstance().create(getBaseContext(), devices[0]);
        } else if (ConnectionState.CONNECTED == client.getConnectionState()) {
            return true;
        }

       Log.d(TAG, "Band is connecting...");
       return ConnectionState.CONNECTED == client.connect().await();
    }

    /**
     * 处理数据库
     */
    class WriteThread implements Runnable{

        private Object entity;

        WriteThread(Object obj){
            entity = obj;
        }

        @Override
        public void run() {
            DBHelper dbHelper = (DBHelper)DBHelperFactory.DBHelperGenerateFactory(entity.getClass().toString(), context);
            if (dbHelper!=null){
                Log.d(TAG, "dbHelper not null");
                dbHelper.add(entity);
                //Log.d(TAG, "add success");
            }else{
                Log.d(TAG, "dbHelper null");
            }
        }
    }

    /**
     * 处理经纬度数据
     * @param location
     */
    private void updateData(Location location){
        double latHome = 108.98;
        double longHome =34.25 ;
        if(location!=null){
            //获得经纬度
            latitude=location.getLatitude();
            longitude=location.getLongitude();
            double distance = getDistance(latHome, longHome, latitude, longitude);

            Log.d(TAG, latitude + " " + longitude + " " + distance);

            Message msg = new Message();
            msg.what =GPS;
            Bundle bundle = new Bundle();
            Date date = new Date();

            GpsInfo gpsInfo =  new GpsInfo();
            gpsInfo.setGpsLatitude(latitude);
            gpsInfo.setGpsLongitude(longitude);
            gpsInfo.setGpsRecordTime(new Timestamp(System.currentTimeMillis()));
            gpsInfo.setDistanceFromHome((float)distance);

            Gson gson = GsonUtil.create();
            String gpsStr = gson.toJson(gpsInfo);

            bundle.putString("GPSINFO", gpsStr);
            msg.setData(bundle);
            mHandler.sendMessage(msg);
        }else{
            Log.d(TAG, "location 为 null");
        }
    }

    /**
     * 计算与目标地点距离
     * @param longitude1
     * @param latitude1
     * @param longitude2
     * @param latitude2
     * @return
     */
    private   double  getDistance(double longitude1, double latitude1,
                               double longitude2, double latitude2){

        double DEF_PI = 3.14159265359; // PI
        double DEF_2PI = 6.28318530712; // 2*PI
        double DEF_PI180 = 0.01745329252; // PI/180.0
        double DEF_R = 6370693.5; // radius of earth
        double ew1,ns1,ew2,ns2;
        double dx,dy,dew;
        double distance;
        ew1 = longitude1 * DEF_PI180;
        ns1 = latitude1 * DEF_PI180;
        ew2 = longitude2 * DEF_PI180;
        ns2 = latitude2 * DEF_PI180;
        dew = ew1-ew2;
        if(dew > DEF_PI)
            dew = DEF_2PI - dew;
        else if (dew < -DEF_PI)
            dew = DEF_2PI + dew;
        dx = DEF_R * Math.cos(ns1) * dew;
        dy = DEF_R * (ns1-ns2);
        distance = Math.sqrt(dx*dx+dy*dy);

        return  distance;
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
