package com.example.smarthomeapp.result_jzp;

import android.content.Context;
import android.util.Log;

import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.model.AirConditionStatus;
import com.example.smarthomeapp.model.CurtainStatus;
import com.example.smarthomeapp.model.LampStatus;
import com.example.smarthomeapp.model.SheSwitchStatus;
import com.example.smarthomeapp.model.WaterHeaterStatus;
import com.example.smarthomeapp.util.AsyncHttpUtil;
import com.example.smarthomeapp.util.GsonUtil;
import com.google.gson.Gson;

import java.sql.Timestamp;

/**
 * Created by ������ on 2015/7/22.
 */
public class AppliancesUpdateResolver implements
        HttpResultProcessListener{

    public static void updateLampStatus(Context context,LampStatus lampStatus,
                                   HttpResultProcessListener listener){
        String url = "http://202.117.14.247:8080/smarthome/appliance/lamp";
        Gson gson = GsonUtil.create();
        String tmpstr = gson.toJson(lampStatus, LampStatus.class);
        AsyncHttpUtil.requestJson(context, url, tmpstr, listener);
    }


    public static void updateCurtainStatus(Context context,CurtainStatus curtainStatus ,
                                         HttpResultProcessListener listener){
        String url = "http://202.117.14.247:8080/smarthome/appliance/curtain";
        curtainStatus.setCurtainStatusRecordTime(new Timestamp(System.currentTimeMillis()));
        curtainStatus.setIsControlledByUser(false);


        Gson gson = GsonUtil.create();
        String tmpstr = gson.toJson(curtainStatus, CurtainStatus.class);
        AsyncHttpUtil.requestJson(context,url,tmpstr,listener);
    }

    public static void updateSheSwitchStatus(Context context,SheSwitchStatus sheSwitchStatus ,
                                            HttpResultProcessListener listener){

        String url = "http://202.117.14.247:8080/smarthome/appliance/sheSwitch";
        Gson gson = GsonUtil.create();
        String tmpstr = gson.toJson(sheSwitchStatus, SheSwitchStatus.class);
        AsyncHttpUtil.requestJson(context,url,tmpstr,listener);
    }

    public static void updateAirConditionStatus(Context context,AirConditionStatus airConditionStatus  ,
                                            HttpResultProcessListener listener){

        String url = "http://202.117.14.247:8080/smarthome/appliance/airCondition";
        Gson gson = GsonUtil.create();
        String tmpstr = gson.toJson(airConditionStatus, AirConditionStatus.class);
        AsyncHttpUtil.requestJson(context,url,tmpstr,listener);
    }

    public static void updateWaterHeaterStatus(Context context,WaterHeaterStatus waterHeaterStatus  ,
                                               HttpResultProcessListener listener){

        String url = "http://202.117.14.247:8080/smarthome/appliance/waterHeater";
        Gson gson = GsonUtil.create();
        String tmpstr = gson.toJson(waterHeaterStatus, WaterHeaterStatus.class);
        AsyncHttpUtil.requestJson(context,url,tmpstr,listener);
    }

    public void processing(int status, String responsString){
        Log.d(AppliancesUpdateResolver.class.getSimpleName(), responsString);
    }

}

