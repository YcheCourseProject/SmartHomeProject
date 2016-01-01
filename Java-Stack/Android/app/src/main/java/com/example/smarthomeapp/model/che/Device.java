package com.example.smarthomeapp.model.che;

import android.content.Context;
import android.graphics.drawable.Drawable;

/**
 * Created by CHEYulin on 2015/5/17.
 */
public abstract class Device {

    private Context context;

    public Context getContext() {
        return context;
    }

    protected Device(Context context) {
        this.context = context;
    }


    public abstract String getDeviceName();


    public abstract Drawable getDevicePicture();


    public abstract void getDetailDialog(Object...objects);
}
