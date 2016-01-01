package com.example.smarthomeapp.dbhelper;

import android.content.Context;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 家琪 on 2015/7/7.
 */
public class DBHelperFactory {

    static Map<String, String> dbHelperMap = new HashMap<String, String>() ;

    static {

        dbHelperMap.put("class com.example.smarthomeapp.model.GpsInfo", "com.example.smarthomeapp.dbhelper.GpsOperateHelper");
        dbHelperMap.put("class com.example.smarthomeapp.model.WearableDeviceInfo", "com.example.smarthomeapp.dbhelper.WearableOperateHelper");
        dbHelperMap.put("class com.example.smarthomeapp.model.SocialInfo", "com.example.smarthomeapp.dbhelper.SocialOperateHelper");

    }

    public static Object DBHelperGenerateFactory(String className, Context context)  {

        Constructor [] constructors = null;
        try {
            constructors = Class.forName(dbHelperMap.get(className)).getConstructors();
            try {
                Object obj =  constructors[0].newInstance(context);
                return obj;
            } catch (InstantiationException e) {
                Log.e("Exception type ", "InstantiationException");
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                Log.e("Exception type ", "IllegalAccessException");
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                Log.e("Exception type ", "InvocationTargetException");
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            Log.e("Exception type ", "ClassNotFoundException");
            e.printStackTrace();
        }

        return null;


    }




}
