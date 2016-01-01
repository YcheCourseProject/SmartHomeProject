package com.example.smarthomeapp.result_jzp;

import android.content.Context;
import android.util.Log;

import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.listener.ReflectionUtil;
import com.example.smarthomeapp.util.AsyncHttpUtil;

import java.util.Set;

/**
 * Created by 卧龙风 on 2015/7/23.
 */
public class SensorQueryResolver implements HttpResultProcessListener {
    private static final String REMOTE_HOST_IP = "202.117.14.247";

    public static void querylightSensorData(Context context,Set<Integer> idSet,
                                        HttpResultProcessListener listener){
        String url = "http://202.117.14.247:8080/smarthome/sensorBox/lightsensor/list";
        //AsyncHttpUtil.requestJsonViaGet(context,url,listener);
        AsyncHttpUtil.requestJsonViaGetWithIDParam(context,url,listener,idSet);
    }

    public static void queryflameSensorData(Context context,Set<Integer> idSet,
                                     HttpResultProcessListener listener){
        String url = "http://202.117.14.247:8080/smarthome/sensorBox/flamesensor/list";
        AsyncHttpUtil.requestJsonViaGetWithIDParam(context,url,listener,idSet);
        //AsyncHttpUtil.requestJsonViaGet(context,url,listener);
    }

    public static void querygasSensorData(Context context,Set<Integer> idSet,
                                    HttpResultProcessListener listener){
        String url = "http://202.117.14.247:8080/smarthome/sensorBox/gassensor/list";
        AsyncHttpUtil.requestJsonViaGetWithIDParam(context,url,listener,idSet);
        //AsyncHttpUtil.requestJsonViaGet(context,url,listener);
    }

    public static void queryplrSensorData(Context context,Set<Integer> idSet,
                                   HttpResultProcessListener listener){
        String url = "http://202.117.14.247:8080/smarthome/sensorBox/plrsensor/list";
       AsyncHttpUtil.requestJsonViaGetWithIDParam(context,url,listener,idSet);
       // AsyncHttpUtil.requestJsonViaGet(context,url,listener);
    }

    public static void querytemperatureSensorData(Context context,Set<Integer> idSet,
                                   HttpResultProcessListener listener){
        String url = "http://202.117.14.247:8080/smarthome/sensorBox/temperaturesensor/list";
        AsyncHttpUtil.requestJsonViaGetWithIDParam(context,url,listener,idSet);
        //AsyncHttpUtil.requestJsonViaGet(context,url,listener);
    }




    @Override
    public void processing(int status, String responsString){
      //Log.d(SensorQueryResolver.class.getSimpleName(), responsString);
        ReflectionUtil reflectionUtil = new ReflectionUtil(responsString);
       reflectionUtil.logResponseData(SensorQueryResolver.class);
    }
}
