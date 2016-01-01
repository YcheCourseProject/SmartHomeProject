package com.example.smarthomeapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.model.LampStatus;
import com.example.smarthomeapp.result_jzp.AppliancesUpdateResolver;

/**
 * Created by acer on 2015/7/21.
 */
public class LightFragmentAdapter extends BaseAdapter {
    private Context context;
    private LampStatus[] lightStatusesArray;
    private LightFragmentAdapter lightFragmentAdapter = this;
    private boolean[] isON;

    public LightFragmentAdapter(Context context, LampStatus[] lightStatusesArray, boolean[] isON) {
        this.context = context;
        this.lightStatusesArray = lightStatusesArray;
        this.isON = isON;
    }

    public void setLightStatusesArray(LampStatus[] lightStatusesArray) {
        this.lightStatusesArray = lightStatusesArray;
    }

    private HttpResultProcessListener httpResultProcessListener = new HttpResultProcessListener() {
        @Override
        public void processing(int status, String responsString) {
            if (status == 201) {
                Toast.makeText(context, "照明设备控制成功", Toast.LENGTH_SHORT).show();
                if (status == 201) {
                    Toast.makeText(context, "????????", Toast.LENGTH_SHORT).show();
                }

                if (status == 201) {
                    Toast.makeText(context, "????????", Toast.LENGTH_SHORT).show();
                }
            }
        }
    };



    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if (lightStatusesArray == null)
            return 0;
        else
            return lightStatusesArray.length;
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    private class ViewHolder {
        TextView text;
        ImageView descriptionImageView;
        ImageView buttonImageView;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_light, null);
            holder = new ViewHolder();
            holder.text = (TextView) convertView.findViewById(R.id.textview_light);
            holder.buttonImageView = (ImageView) convertView.findViewById(R.id.imageview_light_button);
            holder.descriptionImageView = (ImageView) convertView.findViewById(R.id.imageview_light);
            holder.buttonImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lightStatusesArray[position].setLampStatusId(position);
                    if (lightStatusesArray[position].getLampStatus() == 1) {
                        lightStatusesArray[position].setLampStatus(0);
                    } else lightStatusesArray[position].setLampStatus(1);
                    AppliancesUpdateResolver.updateLampStatus(context, lightStatusesArray[position], httpResultProcessListener);
                    if(lightStatusesArray[position].getLampStatus()==1)
                   {lightStatusesArray[position].setLampStatus(0);
                    }
                    else lightStatusesArray[position].setLampStatus(1);
                    AppliancesUpdateResolver.updateLampStatus(context,lightStatusesArray[position],httpResultProcessListener);
                    lightFragmentAdapter.notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.text.setText("" + lightStatusesArray[position].getLamp().getLampId());
        holder.descriptionImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.lamp));
        if (lightStatusesArray[position].getLampStatus() == 1) {
            holder.buttonImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.switch_on_normal));
        } else {
            holder.buttonImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.switch_off_normal));
        }
        return convertView;
    }
}

