package com.example.smarthomeapp.model.jia;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.model.PlrSensorData;

/**
 * Created by 卧龙风 on 2015/7/25.
 */
public class PlrSensorAdapter extends SensorDataAdapter {
    private static final int HUMAN_DRAWABLE_ID = R.drawable.human;
    private PlrSensorData plrSensorData;

    public PlrSensorAdapter(Context context, PlrSensorData plrSensorData) {
        super(context);
        this.plrSensorData = plrSensorData;
        if (this.plrSensorData == null) {
            this.plrSensorData = new PlrSensorData();
        }
    }

    public void setPlrSensorData(PlrSensorData plrSensorData) {
        this.plrSensorData = plrSensorData;
        if (this.plrSensorData == null) {
            this.plrSensorData = new PlrSensorData();
        }
    }

    @Override
    public String getSensorData() {
        if (plrSensorData.getPlrData() == null)
            return "";
        else if (plrSensorData.getPlrData() == false) {
            return getContext().getString(R.string.sensor_plr_false);
        } else {
            return getContext().getString(R.string.sensor_plr_true);
        }
    }

    @Override
    public Drawable getSensorImage() {

        return getContext().getResources().getDrawable(HUMAN_DRAWABLE_ID);
    }

    @Override
    public String getSensorDescription() {

        return getContext().getString(R.string.sensor_human);
    }
}
