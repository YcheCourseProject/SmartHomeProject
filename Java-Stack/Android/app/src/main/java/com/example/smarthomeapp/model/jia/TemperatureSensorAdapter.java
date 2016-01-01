package com.example.smarthomeapp.model.jia;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.model.TemperatureSensorData;

/**
 * Created by 卧龙风 on 2015/7/25.
 */
public class TemperatureSensorAdapter extends SensorDataAdapter {
    private static final int TEMPERATURE_DRAWABLE_ID = R.drawable.temperature;
    private TemperatureSensorData temperatureSensorData;

    public TemperatureSensorAdapter(Context context, TemperatureSensorData temperatureSensorData) {
        super(context);
        this.temperatureSensorData = temperatureSensorData;
        if (this.temperatureSensorData == null) {
            this.temperatureSensorData = new TemperatureSensorData();
        }
    }

    public void setTemperatureSensorData(TemperatureSensorData temperatureSensorData) {
        this.temperatureSensorData = temperatureSensorData;
        if (this.temperatureSensorData == null) {
            this.temperatureSensorData = new TemperatureSensorData();
        }
    }

    @Override
    public String getSensorData() {
        if (temperatureSensorData.getTemperatureData() == null)
            return "";
        else
            return temperatureSensorData.getTemperatureData().toString()+"℃";
    }

    @Override
    public Drawable getSensorImage() {

        return getContext().getResources().getDrawable(TEMPERATURE_DRAWABLE_ID);
    }

    @Override
    public String getSensorDescription() {

        return getContext().getString(R.string.sensor_temperature);
    }
}
