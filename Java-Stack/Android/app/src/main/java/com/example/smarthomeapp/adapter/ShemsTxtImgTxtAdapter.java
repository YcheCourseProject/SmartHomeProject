package com.example.smarthomeapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smarthomeapp.R;

/**
 * Created by Yulin on 2015/7/21.
 */
public class ShemsTxtImgTxtAdapter extends BaseAdapter {
    Context context;
    String[] descriptionStrList;
    String[] valueStrLIst;
    Drawable[] drawables;

    public ShemsTxtImgTxtAdapter(Context context, String[] descriptionStrList, String[] valueStrLIst, Drawable[] drawables) {
        this.context = context;
        this.descriptionStrList = descriptionStrList;
        this.valueStrLIst = valueStrLIst;
        this.drawables = drawables;
    }

    @Override
    public int getCount() {
        return descriptionStrList.length;
    }

    @Override
    public Object getItem(int position) {
        return valueStrLIst[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
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
            convertView= LayoutInflater.from(context).inflate(R.layout.item_txt_img_txt,null);
            viewHolder.valueTextView= (TextView) convertView.findViewById(R.id.txt_value);
            viewHolder.descriptionTextView= (TextView) convertView.findViewById(R.id.txt_description);
            viewHolder.imageView= (ImageView) convertView.findViewById(R.id.img_description);
            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.valueTextView.setText(valueStrLIst[position]);
        viewHolder.descriptionTextView.setText(descriptionStrList[position]);
        viewHolder.imageView.setImageDrawable(drawables[position]);
        return  convertView;
    }
}
