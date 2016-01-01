package com.example.smarthomeapp.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.smarthomeapp.model.GpsInfo;

/**
 * Created by 家琪 on 2015/7/7.
 */
public class GpsOperateHelper extends DBHelper {

    private static final String TAG2 = "GpsOperateHelper";

    public GpsOperateHelper(Context context) {
        super(context);
    }

    @Override
    public void add(Object obj) {

        GpsInfo gpsInfo = (GpsInfo)obj;
        database.execSQL("INSERT INTO gps_info VALUES(NULL, ?, ?, ?, datetime())", new Object[]{
                gpsInfo.getGpsLongitude(),
                gpsInfo.getGpsLatitude(),
                gpsInfo.getDistanceFromHome()
        });
        Log.d(TAG2, "add ");

    }

    @Override
    public void query() throws  Exception{


    }

    @Override
    public void delete() {

    }
}
