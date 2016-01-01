package com.example.smarthomeapp.services;


import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import android.app.Service;
import android.content.Context;
import android.content.Intent;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.example.smarthomeapp.dbhelper.DBHelper;
import com.example.smarthomeapp.dbhelper.DBHelperFactory;
import com.example.smarthomeapp.dbhelper.DBManager;
import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.model.GpsInfo;
import com.example.smarthomeapp.model.MovingStatus;
import com.example.smarthomeapp.model.SocialInfo;
import com.example.smarthomeapp.model.WearableDeviceInfo;
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

public class CollectDataService extends Service {

    private static final int GPS = 1;
    private static final int SMS = 2;
    private static final int MICROSOFTBAND = 3;

    private static final int CALENDAR = 4;
    private static final int WEIBO = 5;


    private static final String TAG3 = "CollectDataService";
    private static final String REMOTE_HOST_SOCIAL = "http://202.117.14.247:8080/smarthome/socialInfo/list";
    private static final String REMOTE_HOST_GPS = "http://202.117.14.247:8080/smarthome/gpsInfo";
    private static final String REMOTE_HOST_BAND =  "http://202.117.14.247:8080/smarthome/wearableInfo";

    private DBManager dbManager;   //管理数据库;
    private ThreadPool threadPool;   //线程池 —— 写数据库

    private Context context;

    private LocationManager locationManager;
    private Location location;
    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            updateData(location);
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
    private int gpsInterval = 0;


    private Handler mHandler;

    private Timer mTimer;
    private SMSTimeTask smsTimeTask;

    private Timer calendarTimer;
    private CalendarTimerTask calendarTimerTask;


    private Timer weiboTimer;
    private WeiBoEventTimerTask weiBoEventTimerTask;


    private BandClient client = null;
    private Float [] accelerated_speed = {(float)0, (float)0, (float)0};
    private Float [] gyroscope = {(float)0, (float)0, (float)0};
    private Float body_temperature = Float.valueOf(0);
    private String moving_status = "unknown";
    private Float speed = Float.valueOf(0);
    private Float heart_rate = Float.valueOf(0);


    private SharedPreferences mySharedPreferences;
    private static int weiboInterval = 120000;



    public CollectDataService() {
    }

    @Override
    public void onCreate(){

        super.onCreate();
        context = this;

        mHandler = new Handler(){

            @Override
            public void handleMessage(Message message){
                Log.d(TAG3, " handleMessage " + message.what);

                Gson gson = GsonUtil.create();

                switch (message.what){
                    case GPS:
                        String gps = message.getData().getString("GPSINFO");
                        GpsInfo gpsObj = gson.fromJson(gps,GpsInfo.class);
                        Log.d(TAG3, gps);
                        AsyncHttpUtil.requestJson(context,
                                REMOTE_HOST_GPS,
                                gps,
                                new HttpResultProcessListener() {
                                    @Override
                                    public void processing(int status, String responsString) {
                                        if (responsString != null) {
                                            Log.d(TAG3, responsString);
                                        }
                                    }
                                });
                        threadPool = ThreadPool.getThreadPool(20);
                        threadPool.addTask(new WriteThread((gpsObj)));
                        break;
                    case SMS:
                        String sms = message.getData().getString("SMS");
                        Log.d(TAG3, "SMS " + sms);
                        ArrayList<SocialInfo> smsEvent = new ArrayList<SocialInfo>();
                        smsEvent = gson.fromJson(sms,  new TypeToken<ArrayList<SocialInfo>>(){}.getType());
                        AsyncHttpUtil.requestJson(context,
                                REMOTE_HOST_SOCIAL,
                                sms,
                                new HttpResultProcessListener() {
                                    @Override
                                    public void processing(int status, String responsString) {
                                        Log.d(TAG3, "processing" + String.valueOf(status));
                                        if(responsString != null){
                                            Log.d(TAG3, responsString);
                                        }
                                    }
                                });
                        threadPool = ThreadPool.getThreadPool(20);
                        for(SocialInfo social : smsEvent){
                            threadPool.addTask(new WriteThread((social)));
                        }
                        break;
                    case MICROSOFTBAND:
                        Log.d(TAG3, "MICROSOFTBAND");

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

                        Log.d(TAG3, wearable);

                        AsyncHttpUtil.requestJson(context,
                                REMOTE_HOST_BAND,
                                wearable,
                                new HttpResultProcessListener() {
                                    @Override
                                    public void processing(int status, String responsString) {
                                        Log.d(TAG3, "processing 1 " +status );
                                        if(responsString != null){
                                            Log.d(TAG3, responsString);
                                        }
                                    }
                                });

                        threadPool = ThreadPool.getThreadPool(20);
                        threadPool.addTask(new WriteThread((wearableDeviceInfo)));
                        break;

                    case WEIBO:
                        String weiBo = message.getData().getString("WEIBO");
                        Long lastestId = message.getData().getLong("WeiBoLatestID");
                        Log.d(TAG3, weiBo);
                        Log.d(TAG3, String.valueOf(lastestId));
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
                                            Log.d(TAG3, responsString);
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

        startLocationListener(gpsInterval);

        mTimer = new Timer();
        smsTimeTask = new SMSTimeTask();
        mTimer.schedule(smsTimeTask, 2000, GetSMS.getInterval());

        calendarTimer = new Timer();
        calendarTimerTask = new CalendarTimerTask();
        calendarTimer.schedule(calendarTimerTask, 2000, GetCalendarEvent.getInterval());

        new appTask().execute();


        mySharedPreferences = getSharedPreferences("test", Context.MODE_PRIVATE);
        GetWeiBoEvent.setLatestId(mySharedPreferences.getLong("WeiBoLatestID", 3866420464724979L));

        weiboTimer = new Timer();
        weiBoEventTimerTask = new WeiBoEventTimerTask();
        weiboTimer.schedule(weiBoEventTimerTask, 2000, weiboInterval);



    }

    @Override
    public void onDestroy(){
        stopLocationListener();
        if(mTimer != null){
            mTimer.cancel();
        }
        if (calendarTimer != null){
            calendarTimer.cancel();
        }
        if(client != null){
            try {
                client.getSensorManager().unregisterAllListeners();
            }catch (BandIOException e){
                Log.d(TAG3, e.getMessage());
            }
        }

        if (weiboTimer != null){
            weiboTimer.cancel();
        }
        SharedPreferences.Editor editor =
                mySharedPreferences.edit().putLong("WeiBoLatestID", GetWeiBoEvent.getLatestId());
        editor.commit();


    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");

        IBinder iBinder = new ServiceBinder();
        return iBinder;

    }

    /**
     * 内部类
     * 为了在Activity中获得服务的实例
     */
    public class ServiceBinder extends Binder {
        public CollectDataService getService(){
            return CollectDataService.this;
        }
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
            DBHelper dbHelper = (DBHelper) DBHelperFactory.DBHelperGenerateFactory(entity.getClass().toString(), context);
            if (dbHelper!=null){
                Log.d(TAG3, "dbHelper not null");
                dbHelper.add(entity);
            }else{
                Log.d(TAG3, "dbHelper null");
            }
        }
    }

    /**
     * 开启GPS服务监听器
     */
    private void startLocationListener(int interval){

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){

            Log.e(TAG3, "provider enabled");
        }else{

            Log.e(TAG3, "provider disabled");
        }

        locationManager.requestLocationUpdates(
                LocationManager.NETWORK_PROVIDER,
                interval,
                0,
                locationListener
        );

    }

    /**
     * 关闭GPS服务监听器
     */
    private void stopLocationListener(){
        locationManager.removeUpdates(locationListener);
    }


    /**
     * 处理GPS数据
     * @param location
     */
    private void updateData(Location location){

        if(location!=null){
            //获得经纬度
            double latitude=location.getLatitude();
            double longitude=location.getLongitude();
            double distance = getDistance(latitude, longitude, 108.98, 34.25);

            Message msg = new Message();
            msg.what =GPS;
            Bundle bundle = new Bundle();

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
            Log.d(TAG3, "location  null");
        }
    }

    /**
     * 计算离家目的地距离
     * @param la
     * @param lo
     * @param homeLa
     * @param homeLo
     * @return
     */
    private double getDistance(double la, double lo, double homeLa, double homeLo){
        double DEF_PI = 3.14159265359; // PI
        double DEF_2PI = 6.28318530712; // 2*PI
        double DEF_PI180 = 0.01745329252; // PI/180.0
        double DEF_R = 6370693.5; // radius of earth
        double ew1,ns1,ew2,ns2;
        double dx,dy,dew;
        double distance;
        ew1 = lo * DEF_PI180;
        ns1 = la * DEF_PI180;
        ew2 = homeLo * DEF_PI180;
        ns2 = homeLa * DEF_PI180;
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

    public void setGpsInterval(int interval){
        gpsInterval = interval;
        stopLocationListener();
        startLocationListener(gpsInterval);
    }

    /**
     * 获得短信
     */
    class SMSTimeTask extends TimerTask {

        @Override
        public void run() {

            Log.d(TAG3, "SMSTimerTask SMS run");

            GetSMS getSMS = GetSMS.getInstance(context);

            ArrayList<SocialInfo> socialList = getSMS.getSMS();

            if(socialList != null && socialList.size() > 0){

                Gson gson = GsonUtil.create();
                String str = gson.toJson(socialList, socialList.getClass());
                Log.d(TAG3, str);

                Message msg = new Message();
                msg.what = SMS;
                Bundle bundle = new Bundle();
                bundle.putString("SMS", str);
                msg.setData(bundle);
                mHandler.sendMessage(msg);

            }else{
                Log.d(TAG3, "no message in last 20 minutes");
            }

        }
    }


    class WeiBoEventTimerTask extends TimerTask{

        @Override
        public void run() {

            Log.d(TAG3, "WeiBoEventTimerTask weibo event run");

            GetWeiBoEvent getWeiBoEvent = GetWeiBoEvent.getInstance(context, mHandler);


            getWeiBoEvent.getWeiBoEvent();


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
                Log.d(TAG3, str);

                Message msg = new Message();
                msg.what = CALENDAR;
                Bundle bundle = new Bundle();
                bundle.putString("CALENDAR", str);
                msg.setData(bundle);
                mHandler.sendMessage(msg);

            }else{
                Log.d(TAG3, "no message in last 20 minutes");
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
                        //client.getSensorManager().requestHeartRateConsent(context, heartRateConsentListener);
                    }else{
                        startHRListener();
                    }
                    client.getSensorManager().registerGyroscopeEventListener(bandGyroscopeEventListener,SampleRate.MS128);
                    client.getSensorManager().registerDistanceEventListener(mBandDistanceEventListener);
                } else {
                    Log.d(TAG3, "Band isn't connected. Please make sure bluetooth is on and the band is in range.");
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
                Log.d(TAG3, "ExceptionMessage " + exceptionMessage);
                Log.d(TAG3, e.getMessage());
            } catch (Exception e) {
                Log.d(TAG3, e.getMessage());
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
                Log.d(TAG3, str);
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
                Log.d(TAG3, str);
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
//               Log.d(TAG3, str);
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
                Log.d(TAG3, str);
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
            Log.d(TAG3,  ex.getMessage());
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
            Log.d(TAG3, exceptionMessage);

        } catch (Exception e) {
            Log.d(TAG3, e.getMessage());
        }
    }

    private HeartRateConsentListener heartRateConsentListener = new HeartRateConsentListener() {
        @Override
        public void userAccepted(boolean b) {
            if(b == true){
                startHRListener();
            }
            else{
                Log.d(TAG3, "user do not consent");
            }
        }
    };



    private boolean getConnectedBandClient() throws InterruptedException, BandException {
        if (client == null) {
            BandInfo[] devices = BandClientManager.getInstance().getPairedBands();
            if (devices.length == 0) {
                Log.d(TAG3, "band isn't pared with you phone");
                Toast.makeText(context, "Band isn't paired with your phone.", Toast.LENGTH_LONG).show();
                return false;
            }
            client = BandClientManager.getInstance().create(getBaseContext(), devices[0]);
        } else if (ConnectionState.CONNECTED == client.getConnectionState()) {
            return true;
        }

        Log.d(TAG3, "Band is connecting...");
        return ConnectionState.CONNECTED == client.connect().await();
    }


    /**
     * 重新开启短信定时器
     */
    public void restartSmsTimer(){
        mTimer.cancel();
        mTimer = null;
        smsTimeTask = null;
        mTimer = new Timer();
        smsTimeTask = new SMSTimeTask();
        mTimer.schedule(smsTimeTask, 2000, GetSMS.getInterval());
    }

    /**
     * 重新开启日历事件计时器
     */
    public void restartCalendarTimer(){
        calendarTimer.cancel();
        calendarTimer = null;
        calendarTimer = new Timer();
        calendarTimerTask = null;
        calendarTimerTask = new CalendarTimerTask();
        calendarTimer.schedule(calendarTimerTask, 2000, GetCalendarEvent.getInterval());
    }


    public void restartWeiBoEventTimer(int interval){
        weiboInterval = interval;
        weiboTimer.cancel();
        weiboTimer = null;
        weiboTimer = new Timer();
        weiBoEventTimerTask = new WeiBoEventTimerTask();
        weiboTimer.schedule(weiBoEventTimerTask, 2000, weiboInterval);
    }


}
