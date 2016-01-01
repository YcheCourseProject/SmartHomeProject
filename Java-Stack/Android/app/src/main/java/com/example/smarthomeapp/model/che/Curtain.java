package com.example.smarthomeapp.model.che;

import android.content.Context;
import android.graphics.drawable.Drawable;

import com.example.smarthomeapp.R;


/**
 * Created by CHEYulin on 2015/5/25.
 */
public class Curtain extends Device{
    public Curtain(Context context) {
        super(context);
    }

    @Override
    public String getDeviceName() {
        return getContext().getString(R.string.curtain);
    }

    @Override
    public Drawable getDevicePicture() {
        return getContext().getResources().getDrawable(R.drawable.curtain);
    }

    @Override
    public void getDetailDialog(Object... objects) {

    }
}
