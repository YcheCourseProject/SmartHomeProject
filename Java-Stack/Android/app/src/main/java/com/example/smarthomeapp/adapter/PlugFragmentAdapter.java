package com.example.smarthomeapp.adapter;

/**
 * Created by acer on 2015/7/20.
 */
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
import com.example.smarthomeapp.model.SheSwitchStatus;
import com.example.smarthomeapp.result_jzp.AppliancesUpdateResolver;

public class PlugFragmentAdapter extends BaseAdapter {
    private Context context;
    private PlugFragmentAdapter plugFragmentAdapter=this;
    private SheSwitchStatus[] sheSwitchStatus;
    private boolean[] isON;


    public PlugFragmentAdapter(Context context,SheSwitchStatus[] sheSwitchStatus,boolean[] isON) {
        this.context = context;
        this.sheSwitchStatus = sheSwitchStatus;
        this.isON = isON;
    }

    public void setSheSwitchStatus(SheSwitchStatus[] sheSwitchStatus) {
        this.sheSwitchStatus = sheSwitchStatus;
    }

    private HttpResultProcessListener httpResultProcessListener = new HttpResultProcessListener() {
        @Override
        public void processing(int status, String responsString) {
            if(status == 201){
                Toast.makeText(context,"插座添加成功",Toast.LENGTH_SHORT).show();
            }
        }
    };
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        if(sheSwitchStatus == null)
            return 0;
        else
            return sheSwitchStatus.length;
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

    private class ViewHolder{
        TextView text;
        ImageView descriptionImageView;
        ImageView buttonImageView;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        ViewHolder holder=null;
        if(convertView==null){
            convertView=LayoutInflater.from(context).inflate(R.layout.item_plug, null);
            holder=new ViewHolder();
            holder.text=(TextView) convertView.findViewById(R.id.textview_plug);
            holder.buttonImageView=(ImageView)convertView.findViewById(R.id.imageview_plug_button);
            holder.descriptionImageView=(ImageView)convertView.findViewById(R.id.imageview_plug);
            holder.buttonImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sheSwitchStatus[position].setSheSwitchStatusId(position);
                    if(sheSwitchStatus[position].getSheSwitchStatus() == false)
                        sheSwitchStatus[position].setSheSwitchStatus(true);
                    else
                        sheSwitchStatus[position].setSheSwitchStatus(false);

                    AppliancesUpdateResolver.updateSheSwitchStatus(context,sheSwitchStatus[position],httpResultProcessListener);
                    plugFragmentAdapter.notifyDataSetChanged();
                }
            });
            convertView.setTag(holder);
        }
        else{
            holder=(ViewHolder)convertView.getTag();
        }
        holder.text.setText(""+sheSwitchStatus[position].getSheSwitch().getSheSwitchId());
        holder.descriptionImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.icon_socket));
        if(sheSwitchStatus[position].getSheSwitchStatus() == true){
            holder.buttonImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.switch_on_normal));
        }
        else {
            holder.buttonImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.switch_off_normal));
        }
        return convertView;
    }


}