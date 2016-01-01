package com.example.smarthomeapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smarthomeapp.R;

/**
 * Created by acer on 2015/7/17.
 */
public class ShemsGridViewAdapter extends BaseAdapter{
    Drawable[]menuDrawables;
    String [] menuDescription;
    Context context;
    LayoutInflater layoutInflater;
    int []selectedStatus;


    public int[] getSelectedStatus() {
        return selectedStatus;
    }

    public void setSelectedStatus(int[] selectedStatus) {
        this.selectedStatus = selectedStatus;
    }

    public ShemsGridViewAdapter(Drawable[] menuDrawables, String[] menuDescription, Context context) {
        this.menuDrawables = menuDrawables;
        this.menuDescription = menuDescription;
        this.context = context;
        this.layoutInflater=LayoutInflater.from(this.context);
        this.selectedStatus=new int[]{1,0,0,0};
    }

    @Override
    public int getCount() {
        return menuDrawables.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder=new ViewHolder();
            convertView = layoutInflater.inflate(R.layout.item_ui_main_bottom, null);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.image_view_main_ui);
            viewHolder.textView=(TextView) convertView.findViewById(R.id.text_view_main_ui);
            viewHolder.linearLayout=(LinearLayout)convertView.findViewById(R.id.linear_layout_item_main_ui);
            convertView.setTag(viewHolder);
        } else {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setImageDrawable(menuDrawables[position]);
        viewHolder.textView.setText(menuDescription[position]);

        switch (position){
            case 0:
                if(selectedStatus[position]==1)
                    viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_home));
                else
                    viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_home_unfocus));
                break;
            case 1:
                if(selectedStatus[position]==1)
                    viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_user));
                else
                    viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_user_unfocus));
                break;
            case 2:
                if(selectedStatus[position]==1)
                    viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_statistics));
                else
                    viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_statistics_unfocus));
                break;
            case 3:
                if(selectedStatus[position]==1)
                    viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_setting));
                else
                    viewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.menu_setting_unfocus));
                break;
            default:
                break;
        }


        if(selectedStatus[position]==1){
            int color=context.getResources().getColor(R.color.actionbar);
            viewHolder.textView.setTextColor(color);
        }
        else {
            int color=context.getResources().getColor(R.color.lightgrey);
            viewHolder.textView.setTextColor(color);
        }
        return convertView;
    }
    class ViewHolder{
        public ImageView imageView;
        public TextView textView;
        public LinearLayout linearLayout;
    }
}
