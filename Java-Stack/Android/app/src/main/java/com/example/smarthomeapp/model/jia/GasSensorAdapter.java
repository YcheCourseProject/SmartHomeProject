package com.example.smarthomeapp.model.jia;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.model.GasSensorData;

/**
 * Created by 卧龙风 on 2015/7/25.
 */
public class GasSensorAdapter extends SensorDataAdapter {
    private static final int GAS_DRAWABLE_ID = R.drawable.air;
    GasSensorData gasSensorData;

    public GasSensorAdapter(Context context, GasSensorData gasSensorData) {
        super(context);
        this.gasSensorData = gasSensorData;
        if (this.gasSensorData == null) {
            this.gasSensorData = new GasSensorData();
        }
    }

    public void setGasSensorData(GasSensorData gasSensorData) {
        this.gasSensorData = gasSensorData;
        if (this.gasSensorData == null) {
            this.gasSensorData = new GasSensorData();
        }
    }

    @Override
    public String getSensorData() {

        if (gasSensorData.getGasData() == null)
            return "";
        else
            return gasSensorData.getGasData().toString();
    }

    @Override
    public Drawable getSensorImage() {

        return getContext().getResources().getDrawable(GAS_DRAWABLE_ID);
    }

    @Override
    public String getSensorDescription() {

        return getContext().getString(R.string.sensor_gas);
    }
}
