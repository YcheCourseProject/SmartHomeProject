package com.example.shems.models.event_detection;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;

import com.example.smarthome.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

/**
 * 空调设备
 * Created by CHEYulin on 2015/5/17.
 */
public class AirConditioner extends Device{

    public AirConditioner(Context context) {
        super(context);
    }

    @Override
    public String getDeviceName() {
        return getContext().getString(R.string.air_conditioner);
    }

    @Override
    public Drawable getDevicePicture() {
        return getContext().getResources().getDrawable(R.drawable.airconditioner);
    }

    @Override
    public void getDetailDialog(Object... objects) {
        OnClickListener onClickListener=new OnClickListener() {
            public void onClick(DialogPlus dialogPlus, View view) {

            }
        };
        final DialogPlus dialog = new DialogPlus.Builder(getContext())
                .setContentHolder(new ViewHolder(R.layout.dialog_item_ac_data))
                .setHeader(R.layout.dialog_header)
                .setCancelable(true)
                .setGravity(DialogPlus.Gravity.BOTTOM)
                .setOnClickListener(onClickListener)
                .setBackgroundColorResourceId(R.color.transparent)
                .setPadding(60,40,60,40)
                .create();
        dialog.show();
    }
}
