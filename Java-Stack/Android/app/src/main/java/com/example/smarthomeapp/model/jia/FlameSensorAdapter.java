package com.example.smarthomeapp.model.jia;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.model.FlameSensorData;

/**
 * Created by 卧龙风 on 2015/7/25.
 */
public class FlameSensorAdapter extends SensorDataAdapter {
    private static final int FLAME_DRAWABLE_ID = R.drawable.fire;
    private FlameSensorData flameSensorData;

    public FlameSensorAdapter(Context context, FlameSensorData flameSensorData) {
        super(context);
        this.flameSensorData = flameSensorData;
        if (this.flameSensorData == null) {
            this.flameSensorData = new FlameSensorData();
        }
    }

    public void setFlameSensorData(FlameSensorData flameSensorData) {
        this.flameSensorData = flameSensorData;
        if (this.flameSensorData == null) {
            this.flameSensorData = new FlameSensorData();
        }
    }

    @Override
    public String getSensorData() {

        if (flameSensorData.getFlameData() == null)
            return "";
        else
            return flameSensorData.getFlameData().toString()+"Lux";
    }

    @Override
    public Drawable getSensorImage() {

        return getContext().getResources().getDrawable(FLAME_DRAWABLE_ID);
    }

    @Override
    public String getSensorDescription() {

        return getContext().getString(R.string.sensor_flame);

    }
}
