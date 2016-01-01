package com.example.smarthomeapp.result_jzp;

import android.content.Context;

import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.listener.ReflectionUtil;
import com.example.smarthomeapp.util.AsyncHttpUtil;

import java.util.Set;

/**
 * Created by 卧龙风 on 2015/7/23.
 */
public class MeterQueryResolver implements HttpResultProcessListener {

    public static void queryMeterData(Context context,Set<Integer> idSet,
                               HttpResultProcessListener listener){
        String url = "http://202.117.14.247:8080/smarthome/smartMeter/list";
        AsyncHttpUtil.requestJsonViaGetWithIDParam(context,url,listener,idSet);
        //AsyncHttpUtil.requestJsonViaGet(context, url, listener);

    }
    @Override
    public void processing(int status, String responsString){
        //  Log.d(SensorQueryResolver.class.getSimpleName(), responsString);
        ReflectionUtil reflectionUtil = new ReflectionUtil(responsString);
        reflectionUtil.logResponseData(MeterQueryResolver.class);
    }
}
