package com.example.smarthomeapp.model.jia;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by 卧龙风 on 2015/7/25.
 */
public abstract class SensorDataAdapter {
    private Context context;

    public SensorDataAdapter(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }

    public abstract String getSensorData();
    public abstract Drawable getSensorImage();
    public abstract String getSensorDescription();
}
