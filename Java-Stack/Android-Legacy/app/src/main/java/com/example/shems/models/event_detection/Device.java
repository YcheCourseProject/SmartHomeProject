package com.example.shems.models.event_detection;

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

    /**
     * 获取设备名称
     * @return
     */
    public abstract String getDeviceName();

    /**
     * 获取设备对应Drawable图片
     * @return
     */
    public abstract Drawable getDevicePicture();

    /**
     * 打开设备对应的设置详情Dialog
     * @param objects
     */
    public abstract void getDetailDialog(Object ...objects);
}
