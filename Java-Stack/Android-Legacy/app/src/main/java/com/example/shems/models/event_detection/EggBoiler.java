package com.example.shems.models.event_detection;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.smarthome.R;

/**
 * Created by CHEYulin on 2015/5/25.
 */
public class EggBoiler extends Device {

    public EggBoiler(Context context) {
        super(context);
    }

    @Override
    public String getDeviceName() {
        return getContext().getString(R.string.egg_boiler);
    }

    @Override
    public Drawable getDevicePicture() {
        return getContext().getResources().getDrawable(R.drawable.egg_boiler);
    }

    @Override
    public void getDetailDialog(Object... objects) {

    }
}
