package com.example.shems.models.event_detection;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.TextView;

import com.example.smarthome.R;
import com.orhanobut.dialogplus.DialogPlus;
import com.orhanobut.dialogplus.OnClickListener;
import com.orhanobut.dialogplus.ViewHolder;

/**
 * 热水器设备
 * Created by CHEYulin on 2015/5/17.
 */
public class WaterHeater extends Device{

    public WaterHeater(Context context) {
        super(context);
    }

    @Override
    public String getDeviceName() {
        return getContext().getString(R.string.water_heater);
    }

    @Override
    public Drawable getDevicePicture() {
        return getContext().getResources().getDrawable(R.drawable.waterheater);
    }

    @Override
    public void getDetailDialog(Object... objects) {
        OnClickListener onClickListener=new OnClickListener() {
            public void onClick(DialogPlus dialogPlus, View view) {

            }
        };
        final DialogPlus dialog = new DialogPlus.Builder(getContext())
                .setContentHolder(new ViewHolder(R.layout.dialog_item_wh_data))
                .setHeader(R.layout.dialog_header)
                .setCancelable(true)
                .setGravity(DialogPlus.Gravity.BOTTOM)
                .setOnClickListener(onClickListener)
                .setBackgroundColorResourceId(R.color.transparent)
                .setPadding(60,40,60,40)
                .create();
        dialog.show();
        View view=dialog.getHeaderView();
        TextView titleTextView= (TextView) view.findViewById(R.id.txt_dialog_title);
        titleTextView.setText(getContext().getString(R.string.settings_water_heater));
    }
}
