package com.example.smarthomeapp.adapter;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.model.AirConditionStatus;
import com.example.smarthomeapp.model.WaterHeaterStatus;
import com.example.smarthomeapp.result_jzp.AppliancesUpdateResolver;
import com.example.smarthomeapp.util.AsyncHttpUtil;
import com.triggertrap.seekarc.SeekArc;

/**
 * Created by acer on 2015/7/21.
 */
public class HeaterFragmentAdapter extends BaseExpandableListAdapter {
    private static final int CHILDREN_COUNT = 1;
    private static final int MIN_TEMPERATURE = 0;
    private static final int MAX_TEMPERATURE = 100;
    private Context context;
    //private AirConditionStatus[] airConditionStatusArray;
    private WaterHeaterStatus[] waterHeaterStatusesArray;
    private boolean[] isON;
    HeaterFragmentAdapter heaterFragmentAdapter = this;

    private HttpResultProcessListener httpResultProcessListener = new HttpResultProcessListener() {
        @Override
        public void processing(int status, String responsString) {
            if(status == 201)
                Toast.makeText(context,"控制热水器成功",Toast.LENGTH_SHORT).show();
        }
    };
    public void setWaterHeaterStatusesArray(WaterHeaterStatus[] waterHeaterStatusesArray){
        this.waterHeaterStatusesArray = waterHeaterStatusesArray;
    }

    public HeaterFragmentAdapter(Context context, WaterHeaterStatus[] waterHeaterStatusesArray, boolean[] isON) {
        this.context = context;
        this.waterHeaterStatusesArray = waterHeaterStatusesArray;
        this.isON = isON;
    }

    @Override
    public int getGroupCount() {
        if(waterHeaterStatusesArray == null){
            return 0;
        }
        return waterHeaterStatusesArray.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(waterHeaterStatusesArray == null){
            return 0;
        }
        return CHILDREN_COUNT;
    }

    @Override
    public Object getGroup(int groupPosition) {
        if(waterHeaterStatusesArray == null){
            return null;
        }else
        return waterHeaterStatusesArray[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if(waterHeaterStatusesArray == null){
            return null;
        }else
        return waterHeaterStatusesArray[groupPosition];
    }

    @Override
    public long getGroupId(int groupPosition) { return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return groupPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        GroupViewHolder groupViewHolder = null;
        if (convertView == null) {
            groupViewHolder = new GroupViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_heater, null);
            groupViewHolder.groupHeaterIDText = (TextView) convertView.findViewById(R.id.txt_group_heater_id);
            groupViewHolder.groupHintImage = (ImageView) convertView.findViewById(R.id.img_group_electrical_statistics_hint);
            groupViewHolder.groupHeaterStatusText = (TextView) convertView.findViewById(R.id.textHeatertemp);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        WaterHeaterStatus heaterConditionStatus = waterHeaterStatusesArray[groupPosition];
        groupViewHolder.groupHeaterIDText.setText(context.getString(R.string.current_id) + waterHeaterStatusesArray[groupPosition].getWaterHeater().getWaterHeaterId());
        groupViewHolder.groupHeaterStatusText.setText(context.getString(R.string.set_temp) + waterHeaterStatusesArray[groupPosition].getWaterHeaterTemperature()
                + context.getString(R.string.temp_symbol));

        if (isExpanded == true) {
            groupViewHolder.groupHintImage.setImageDrawable(context.getResources().getDrawable(R.drawable.down_arrow));
        } else {
            groupViewHolder.groupHintImage.setImageDrawable(context.getResources().getDrawable(R.drawable.right_arrow));
        }
        return convertView;
    }

    class GroupViewHolder {
        TextView groupHeaterIDText;
        TextView groupHeaterStatusText;
        ImageView groupHintImage;

    }

    class MySeekArcListener implements SeekArc.OnSeekArcChangeListener {
        ChildViewHolder childViewHolder;
        private int groupPosition;

        public MySeekArcListener(ChildViewHolder childViewHolder, int groupPosition) {
            this.childViewHolder = childViewHolder;
            this.groupPosition = groupPosition;
        }

        @Override
        public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
            childViewHolder.heaterTemp.setText("" + progress);
            waterHeaterStatusesArray[groupPosition].setWaterHeaterTemperature((float) progress);
            AppliancesUpdateResolver.updateWaterHeaterStatus(context,waterHeaterStatusesArray[groupPosition]
                    ,httpResultProcessListener);
            notifyDataSetChanged();
        }

        @Override
        public void onStartTrackingTouch(SeekArc seekArc) {

        }

        @Override
        public void onStopTrackingTouch(SeekArc seekArc) {

        }
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder childViewHolder = null;
        if (convertView == null) {
            childViewHolder = new ChildViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child_heater, null);
            childViewHolder.childswitchkey = (ImageView) convertView.findViewById(R.id.switchkeyimage);
            childViewHolder.childcurrenttemp = (TextView) convertView.findViewById(R.id.heater_current_temp);
            childViewHolder.seekArc = (com.triggertrap.seekarc.SeekArc) convertView.findViewById(R.id.heaterseekArc);
            childViewHolder.heaterTemp = (TextView) convertView.findViewById(R.id.heaterTemp);
            waterHeaterStatusesArray[0].setWaterHeaterTemperature(0f);//7 25
            waterHeaterStatusesArray[1].setWaterHeaterTemperature(0f);//7 25
            convertView.setTag(childViewHolder);
            class MySeekArcListener implements SeekArc.OnSeekArcChangeListener{
                ChildViewHolder childViewHolder;

                public MySeekArcListener(ChildViewHolder childViewHolder) {
                    this.childViewHolder = childViewHolder;
                }

                @Override
                public void onProgressChanged(SeekArc seekArc, int progress, boolean fromUser) {
                    childViewHolder.heaterTemp.setText("" + progress);///////////////////
                }

                @Override
                public void onStartTrackingTouch(SeekArc seekArc) {

                }

                @Override
                public void onStopTrackingTouch(SeekArc seekArc) {

                }
            }
            childViewHolder.seekArc.setOnSeekArcChangeListener(new MySeekArcListener(childViewHolder));
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }


        MySeekArcListener mySeekArcListener = new MySeekArcListener(childViewHolder, groupPosition);
        childViewHolder.seekArc.setOnSeekArcChangeListener(mySeekArcListener);
        childViewHolder.childswitchkey.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // isON[groupPosition] = !isON[groupPosition];
               // if(waterHeaterStatusesArray[groupPosition].getWaterHeaterTemperature()==0)
               // heaterFragmentAdapter.notifyDataSetChanged();
            }
        });
        WaterHeaterStatus heaterConditionStatus = waterHeaterStatusesArray[groupPosition];
        //if (isON[groupPosition] == true)//7 25
        if(waterHeaterStatusesArray[groupPosition].getWaterHeaterTemperature()==0f)//7 25
        {
            childViewHolder.childswitchkey.setImageDrawable(context.getResources().getDrawable(R.drawable.switch_off_normal));
        } else
            childViewHolder.childswitchkey.setImageDrawable(context.getResources().getDrawable(R.drawable.switch_on_normal));
        return convertView;
    }

    class ChildViewHolder {
        ImageView childswitchkey;
        TextView heaterTemp;
        TextView childcurrenttemp;
        com.triggertrap.seekarc.SeekArc seekArc;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
