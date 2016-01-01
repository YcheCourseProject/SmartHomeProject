package com.example.smarthomeapp.adapter.jia;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.model.ElectricityInfo;

/**
 * Created by 卧龙风 on 2015/7/26.
 */
 public class LineExpandableListAdapter extends BaseExpandableListAdapter {
    private ElectricityInfo[] familyline_value ;
    private Context context;


    public LineExpandableListAdapter(Context context) {
        this.context = context;

    }

    public LineExpandableListAdapter(ElectricityInfo[] familyline_value, Context context) {
        this.familyline_value = familyline_value;
        this.context = context;
    }

    public void setFamilyline_value(ElectricityInfo[] familyline_value) {
        this.familyline_value = familyline_value;
    }

    @Override
    public int getGroupCount() {
        if(familyline_value==null){
            return 0;
        }
        else
        return familyline_value.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 3;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return familyline_value[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if(childPosition == 0){
            return familyline_value[groupPosition].getActivePower();
        }else if(childPosition == 1){
            return familyline_value[groupPosition].getReactivePower();
        }else {
            return familyline_value[groupPosition].getTotalConsumeEnergy();
        }
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    class GroupViewHolder{
        ImageView imageView;
        TextView groupTitleTextView;
        TextView groupDetailTextView;
    }
    class ChildViewHolder{
        ImageView imageView;
        TextView childTitleTextView;
        TextView childDetailTextView;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder;
        if(convertView==null) {
            groupViewHolder=new GroupViewHolder();
            convertView =  LayoutInflater.from(context).inflate(R.layout.familyline_group_item, null);
            groupViewHolder.groupTitleTextView= (TextView) convertView.findViewById(R.id.familyline_type);
            groupViewHolder.groupDetailTextView = (TextView) convertView.findViewById(R.id.familyline_tails);
            groupViewHolder.imageView = (ImageView) convertView.findViewById(R.id.family_group_status);

            convertView.setTag(groupViewHolder);
        }else{
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }

        groupViewHolder.groupTitleTextView.setText(familyline_value[groupPosition]
                .getElectricityMeter().getCircuitLine().getCircuitLineId().toString());
        groupViewHolder.groupDetailTextView.setText(familyline_value[groupPosition]
                .getElectricityMeter().getCircuitLine().getCircuitLineDescription());
        if(isExpanded == true){
            groupViewHolder.imageView.setBackgroundResource(R.drawable.down_arrow);
        }else if(isExpanded == false){
            groupViewHolder.imageView.setBackgroundResource(R.drawable.right_arrow);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder;
        if(convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView =  LayoutInflater.from(context).inflate(R.layout.familyline_child_item, null);
            childViewHolder.childDetailTextView = (TextView)convertView.findViewById(R.id.familyline_child_tails);
            childViewHolder.childTitleTextView = (TextView)convertView.findViewById(R.id.familyline_child_type);
            childViewHolder.imageView = (ImageView) convertView.findViewById(R.id.image_child);

            convertView.setTag(childViewHolder);
        }
        else{
            childViewHolder = (ChildViewHolder)convertView.getTag();
        }

        if(childPosition == 0){
            childViewHolder.childTitleTextView.setText(context.getString(R.string.familyline_activepower));
            childViewHolder.childDetailTextView.setText(familyline_value[groupPosition].getActivePower().toString()+"w");
            childViewHolder.imageView.setBackgroundResource(R.drawable.activepower);
        }else if(childPosition == 1){
            childViewHolder.childTitleTextView.setText(context.getString(R.string.familyline_reactivepower));
            childViewHolder.childDetailTextView.setText(familyline_value[groupPosition].getReactivePower().toString()+"Var");
            childViewHolder.imageView.setBackgroundResource(R.drawable.reactivepower);
        }else if(childPosition == 2){
            childViewHolder.childTitleTextView.setText(context.getString(R.string.familyline_consumption));
            childViewHolder.childDetailTextView.setText(familyline_value[groupPosition].getTotalConsumeEnergy().toString()+"J");
            childViewHolder.imageView.setBackgroundResource(R.drawable.consumption);
        }

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
};
