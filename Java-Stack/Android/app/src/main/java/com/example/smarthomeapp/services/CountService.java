package com.example.smarthomeapp.services;

import android.app.Service;
import android.content.Intent;

import android.os.Binder;
import android.os.IBinder;
import android.os.Parcel;
import android.util.Log;

public class CountService extends Service {

    boolean threadDisable;
    int count;
    private static final String TAG2 = "CountService";



    public CountService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
      //  throw new UnsupportedOperationException("Not yet implemented");

        return null;

    }

    public void onCreate(){
        super.onCreate();

        /**
         * 创建一个线程
         */
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!threadDisable){
                    try{
                        Thread.sleep(1000);
                    }catch (InterruptedException e){

                    }
                    count++;
                    Log.d(TAG2, "Count is " + count);
                }
            }
        }).start();

    }

    public void onDestroy(){
        threadDisable = true;
    }

    public int getCount(){
        return count;
    }

    /**
     * 内部类
     * 为了在Activity中获得服务的实例
     */
     public class ServiceBinder extends Binder{
        public CountService getService(){
            return CountService.this;
        }

        public boolean onTransact(int code, Parcel data, Parcel reply, int flags){

            Log.d(TAG2, "onTransact");
            return false;

        }

    }

}
