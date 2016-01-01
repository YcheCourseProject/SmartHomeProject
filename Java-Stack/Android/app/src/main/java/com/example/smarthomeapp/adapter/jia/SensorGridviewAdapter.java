package com.example.smarthomeapp.adapter.jia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.model.jia.SensorDataAdapter;

/**
 * Created by 卧龙风 on 2015/7/25.
 */
public class SensorGridviewAdapter extends BaseAdapter {

    Context context;

    public SensorDataAdapter[] getSensorDataAdapters() {
        return sensorDataAdapters;
    }

    SensorDataAdapter[] sensorDataAdapters;

    public void setSensorDataAdapters(SensorDataAdapter[] sensorDataAdapters) {
        this.sensorDataAdapters = sensorDataAdapters;
    }

    public SensorGridviewAdapter(Context context, SensorDataAdapter[] sensorDataAdapters) {
        this.context = context;
        this.sensorDataAdapters = sensorDataAdapters;
    }

    @Override
    public int getCount() {

        if (sensorDataAdapters == null || sensorDataAdapters.length == 0)
            return 0;
        else
            return sensorDataAdapters.length;
    }

    @Override
    public Object getItem(int position) {
        if (sensorDataAdapters != null && sensorDataAdapters.length > position)
            return sensorDataAdapters[position];
        else
            return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder{
        ImageView imageView;
        TextView valueTextView;
        TextView descriptionTextView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if(convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.environment_item,null);
            viewHolder.valueTextView= (TextView) convertView.findViewById(R.id.item_environment_showdata);
            viewHolder.descriptionTextView= (TextView) convertView.findViewById(R.id.item_environment_type);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.item_image_environment);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.valueTextView.setText(sensorDataAdapters[position].getSensorData());
        viewHolder.descriptionTextView.setText(sensorDataAdapters[position].getSensorDescription());
        viewHolder.imageView.setImageDrawable(sensorDataAdapters[position].getSensorImage());
        return  convertView;
    }
}

