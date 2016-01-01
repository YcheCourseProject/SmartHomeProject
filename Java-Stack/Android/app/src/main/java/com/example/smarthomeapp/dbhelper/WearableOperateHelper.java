package com.example.smarthomeapp.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.smarthomeapp.model.WearableDeviceInfo;

/**
 * Created by 家琪 on 2015/7/7.
 */
public class WearableOperateHelper extends DBHelper {

    public WearableOperateHelper(Context context) {
        super(context);
    }

    @Override
    public void add(Object obj) {

        WearableDeviceInfo wearable = (WearableDeviceInfo)obj;
        database.execSQL("INSERT INTO wearable_device_info VALUES(NULL, (SELECT moving_type_id FROM moving_status WHERE moving_type = ?), ?,?,?,?,?,?,?,?,?,datetime());",
                new Object[]{
                        wearable.getMovingStatus().getMovingType(),
                        wearable.getAcceleratedSpeedX(),
                        wearable.getAcceleratedSpeedY(),
                        wearable.getAcceleratedSpeedZ(),
                        wearable.getGyroscopeX(),
                        wearable.getGyroscopeY(),
                        wearable.getGyroscopeZ(),
                        wearable.getBodyTemperature(),
                        wearable.getHeartRate(),
                        wearable.getSpeed()
        });
        Log.e("WearableOperateHelper add", "add");

    }

    @Override
    public void delete() {

    }

    @Override
    public void query() throws Exception{

    }
}
