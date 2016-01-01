package com.example.smarthomeapp.result_jzp;

import android.content.Context;

import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.listener.ReflectionUtil;
import com.example.smarthomeapp.util.AsyncHttpUtil;

import java.util.Set;

/**
 * Created by 卧龙风 on 2015/7/24.
 */
public class AppliancesQueryResolver implements HttpResultProcessListener {
    //private static final String REMOTE_HOST_IP = "202.117.14.247";

    public static void queryLampStatusArray(Context context,Set<Integer> idSet,
                                     HttpResultProcessListener listener){
        String url = "http://202.117.14.247:8080/smarthome/appliance/lamp/list";
        AsyncHttpUtil.requestJsonViaGetWithIDParam(context, url, listener, idSet);
    }

    public static void queryCurtainStatusArray(Context context,Set<Integer> idSet,
                                     HttpResultProcessListener listener){
        String url = "http://202.117.14.247:8080/smarthome/appliance/curtain/list";
        AsyncHttpUtil.requestJsonViaGetWithIDParam(context,url,listener,idSet);
        //AsyncHttpUtil.requestJsonViaGet(context,url,listener);
    }

    public static void querySheSwitchStatusArray(Context context,Set<Integer> idSet,
                                   HttpResultProcessListener listener){
        String url = "http://202.117.14.247:8080/smarthome/appliance/sheSwitch/list";
        AsyncHttpUtil.requestJsonViaGetWithIDParam(context,url,listener,idSet);
        //AsyncHttpUtil.requestJsonViaGet(context,url,listener);
    }

    public static void queryAirConditionStatusArray(Context context,Set<Integer> idSet,
                                   HttpResultProcessListener listener){
        String url = "http://202.117.14.247:8080/smarthome/appliance/airCondition/list";
        AsyncHttpUtil.requestJsonViaGetWithIDParam(context,url,listener,idSet);
    }

    public void queryWaterHeaterStatusArray(Context context,Set<Integer> idSet,
                                           HttpResultProcessListener listener){
        String url = "http://202.117.14.247:8080/smarthome/appliance/waterHeater/list";
        AsyncHttpUtil.requestJsonViaGetWithIDParam(context,url,listener,idSet);
        //AsyncHttpUtil.requestJsonViaGet(context,url,listener);
    }




    @Override
    public void processing(int status, String responsString){
        ReflectionUtil reflectionUtil = new ReflectionUtil(responsString);
        reflectionUtil.logResponseData(AppliancesQueryResolver.class);
    }
}
