package com.example.smarthomeapp.model.jia;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.model.LightSensorData;

/**
 * Created by 卧龙风 on 2015/7/25.
 */
public class LightSensorAdapter extends SensorDataAdapter {
    private static final int LIGHTDRAWABLEID = R.drawable.light;
    LightSensorData lightSensorData;

    public LightSensorAdapter(Context context, LightSensorData lightSensorData) {
        super(context);
        this.lightSensorData = lightSensorData;
        if(this.lightSensorData==null){
            this.lightSensorData=new LightSensorData();
        }
    }

    public void setLightSensorData(LightSensorData lightSensorData) {
        this.lightSensorData = lightSensorData;
        if(this.lightSensorData==null){
            this.lightSensorData=new LightSensorData();
        }
    }

    @Override
    public String getSensorData() {
        if(lightSensorData.getLightData() == null)
            return "";
        else
            return lightSensorData.getLightData().toString();

    }

    @Override
    public Drawable getSensorImage() {

            return getContext().getResources().getDrawable(LIGHTDRAWABLEID);
    }

    @Override
    public String getSensorDescription() {

            return getContext().getString(R.string.sensor_light);
    }
}
