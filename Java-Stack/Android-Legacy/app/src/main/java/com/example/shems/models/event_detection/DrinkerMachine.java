package com.example.shems.models.event_detection;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.smarthome.R;

/**
 * 饮水机
 * Created by CHEYulin on 2015/5/23.
 */
public class DrinkerMachine extends Device{

    public DrinkerMachine(Context context) {
        super(context);
    }

    @Override
    public String getDeviceName() {
        return getContext().getString(R.string.drinker_machine);
    }

    @Override
    public Drawable getDevicePicture() {
        return getContext().getResources().getDrawable(R.drawable.drinker_machine);
    }

    @Override
    public void getDetailDialog(Object... objects) {

    }
}
