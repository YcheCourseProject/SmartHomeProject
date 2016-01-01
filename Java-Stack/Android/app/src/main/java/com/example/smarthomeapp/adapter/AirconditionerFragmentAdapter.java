package com.example.smarthomeapp.adapter;

/**
 * Created by acer on 2015/7/20.
 */
import java.util.ArrayList;
import java.util.List;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnticipateInterpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.smarthomeapp.Constants;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.model.AirConditionStatus;
import com.example.smarthomeapp.result_jzp.AppliancesUpdateResolver;
import com.example.smarthomeapp.util.AnimatorUtils;
import com.ogaclejapan.arclayout.ArcLayout;
import com.triggertrap.seekarc.SeekArc;

public class AirconditionerFragmentAdapter extends BaseExpandableListAdapter {
    private static final int MIN_TEMPERATURE=16;
    private static final int MAX_TEMPERATURE=44;
    private static final int CHILDREN_COUNT = 1;
    private Context context;
    private AirConditionStatus[] airConditionStatusArray;
    private boolean[] isON;
    private AirconditionerFragmentAdapter airconditionerFragmentAdapter=this;
    private TextView mSeekArcProgress;
    private HttpResultProcessListener httpResultProcessListener=new HttpResultProcessListener() {
        @Override
        public void processing(int status, String responsString) {
            if(status==201)
                Toast.makeText(context,"控制成功",Toast.LENGTH_SHORT).show();
        }
    };
    public void setAirConditionStatusArray(AirConditionStatus[] airConditionStatusArray) {
        this.airConditionStatusArray = airConditionStatusArray;
    }

    public AirconditionerFragmentAdapter(Context context, AirConditionStatus[] airConditionStatusArray, boolean[] isON) {
        this.context = context;
        this.airConditionStatusArray = airConditionStatusArray;
        this.isON = isON;
    }

    @Override
    public int getGroupCount() {

        if(airConditionStatusArray==null)
            return 0;
            else

        return airConditionStatusArray.length;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        if(airConditionStatusArray==null)
            return 0;
        else return CHILDREN_COUNT;
    }

    @Override
    public Object getGroup(int groupPosition) {

        if(airConditionStatusArray==null)
            return null;
        else
        return airConditionStatusArray[groupPosition];
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        if(airConditionStatusArray==null)
            return null;
        else
        return airConditionStatusArray[groupPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_group_aircondition, null);
            groupViewHolder.groupACIDText = (TextView) convertView.findViewById(R.id.txt_group_airconditioner_id);
            groupViewHolder.groupHintImage = (ImageView) convertView.findViewById(R.id.img_group_electrical_statistics_hint);
            groupViewHolder.groupACStatusText = (TextView) convertView.findViewById(R.id.textACcurrentStatus);
            convertView.setTag(groupViewHolder);
        } else {
            groupViewHolder = (GroupViewHolder) convertView.getTag();
        }
        groupViewHolder.groupACIDText.setText("设备编号：" + airConditionStatusArray[groupPosition].getAirCondition().getAirConditionId());
        groupViewHolder.groupACStatusText.setText("模式:" + airConditionStatusArray[groupPosition].getAirConditionMode() + " " + context.getString(R.string.set_temp) + airConditionStatusArray[groupPosition].getAirConditionTemperature() + context.getString(R.string.temp_symbol));

        if (isExpanded == true) {
            groupViewHolder.groupHintImage.setImageDrawable(context.getResources().getDrawable(R.drawable.down_arrow));
        } else {
            groupViewHolder.groupHintImage.setImageDrawable(context.getResources().getDrawable(R.drawable.right_arrow));
        }
        return convertView;
    }

    class GroupViewHolder {
        TextView groupACIDText;
        TextView groupACStatusText;
        ImageView groupHintImage;
    }

    @SuppressWarnings("NewApi")
    private void showMenu(ArcLayout arcLayout,View modelHint) {
        arcLayout.setVisibility(View.VISIBLE);
        List<Animator> animList = new ArrayList<>();

        for (int i = 0, len = arcLayout.getChildCount(); i < len; i++) {
            animList.add(createShowItemAnimator(arcLayout.getChildAt(i),modelHint));
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(400);
        animSet.setInterpolator(new OvershootInterpolator());
        animSet.playTogether(animList);
        animSet.start();
    }

    @SuppressWarnings("NewApi")
    private void hideMenu(ArcLayout arcLayout,View modelHint) {
        arcLayout.setVisibility(View.INVISIBLE);
        List<Animator> animList = new ArrayList<>();

        for (int i = arcLayout.getChildCount() - 1; i >= 0; i--) {
            animList.add(createHideItemAnimator(arcLayout.getChildAt(i),modelHint));
        }

        AnimatorSet animSet = new AnimatorSet();
        animSet.setDuration(400);
        animSet.setInterpolator(new AnticipateInterpolator());
        animSet.playTogether(animList);
        animSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });
        animSet.start();

    }

    private Animator createShowItemAnimator(View item,View modelHint) {

        float dx = modelHint.getX() - item.getX();
        float dy = modelHint.getY() - item.getY();

        item.setRotation(0f);
        item.setTranslationX(dx);
        item.setTranslationY(dy);

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.rotation(0f, 720f),
                AnimatorUtils.translationX(dx, 0f),
                AnimatorUtils.translationY(dy, 0f)
        );

        return anim;
    }

    private Animator createHideItemAnimator(final View item,View modelHint) {

        float dx = modelHint.getX() - item.getX();
        float dy = modelHint.getY() - item.getY();

        Animator anim = ObjectAnimator.ofPropertyValuesHolder(
                item,
                AnimatorUtils.rotation(720f, 0f),
                AnimatorUtils.translationX(0f, dx),
                AnimatorUtils.translationY(0f, dy)
        );

        anim.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                item.setTranslationX(0f);
                item.setTranslationY(0f);
            }
        });

        return anim;
    }

    class MyOnClickListener implements View.OnClickListener {
        private ChildViewHolder childViewHolder;
        private int groupPosition;
        public MyOnClickListener(ChildViewHolder childViewHolder,int groupPosition) {
            this.childViewHolder = childViewHolder;
            this.groupPosition=groupPosition;
        }

        @Override
        public void onClick(View v) {
            if (v == childViewHolder.modelhint) {
                v.setSelected(!v.isSelected());
                if(v.isSelected())
                    showMenu(childViewHolder.childArc,childViewHolder.modelhint);
                else
                    hideMenu(childViewHolder.childArc,childViewHolder.modelhint);
            } else if (v == childViewHolder.childswitchkey) {
               // isON[groupPosition] = !isON[groupPosition];
                if(airConditionStatusArray [groupPosition].getAirConditionMode()==5)//7 25
                airConditionStatusArray[groupPosition].setAirConditionMode(6);//7 25
                else if(airConditionStatusArray[groupPosition].getAirConditionMode()==6)//7 25
                    airConditionStatusArray[groupPosition].setAirConditionMode(5);// 7 25
            } else {
                childViewHolder.modelhint.setSelected(false);
                hideMenu(childViewHolder.childArc, childViewHolder.modelhint);
                if (v == childViewHolder.model_cool) {
                    airConditionStatusArray[groupPosition].setAirConditionMode(Constants.AirMode.MODE_COOL);

                } else if (v == childViewHolder.model_dry) {
                    airConditionStatusArray[groupPosition].setAirConditionMode(Constants.AirMode.MODE_DRY);
                } else if (v == childViewHolder.model_humidity) {
                    airConditionStatusArray[groupPosition].setAirConditionMode(Constants.AirMode.MODE_HUMIDITY);
                } else if (v == childViewHolder.model_wind) {
                    airConditionStatusArray[groupPosition].setAirConditionMode(Constants.AirMode.MODE_WIND);
                }

                airConditionStatusArray[groupPosition].setAirConditionStatusId(null);
                AppliancesUpdateResolver.updateAirConditionStatus(context,airConditionStatusArray[groupPosition],httpResultProcessListener);
            }
            notifyDataSetChanged();
        }
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

            float temperature = (float) (progress / 100.0 * (MAX_TEMPERATURE - MIN_TEMPERATURE) + MIN_TEMPERATURE);
            airConditionStatusArray[groupPosition].setAirConditionTemperature(temperature);
            AppliancesUpdateResolver.updateAirConditionStatus(context, airConditionStatusArray[groupPosition], httpResultProcessListener);
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
            convertView = LayoutInflater.from(context).inflate(R.layout.item_child_aircondition, null);
            childViewHolder.childswitchkey = (ImageView) convertView.findViewById(R.id.switchkeyimage);
            childViewHolder.childACmode = (ImageView) convertView.findViewById(R.id.airconditioner_mode_image);///////
            childViewHolder.childcurrenttemp = (TextView) convertView.findViewById(R.id.airconditioner_current_temp);
            childViewHolder.childArc = (com.ogaclejapan.arclayout.ArcLayout) convertView.findViewById(R.id.arc_layout);
            childViewHolder.seekArc = (com.triggertrap.seekarc.SeekArc) convertView.findViewById(R.id.seekArc);
            childViewHolder.modelhint = (Button) convertView.findViewById(R.id.modelhint);
            childViewHolder.menuLayout = (FrameLayout) convertView.findViewById(R.id.menu_layout);
            childViewHolder.mSeekArcProgress = (TextView) convertView.findViewById(R.id.seekark_progress);
            childViewHolder.childArc.setVisibility(View.INVISIBLE);
            childViewHolder.model_cool = ((ImageButton) convertView.findViewById(R.id.model_cool));
            childViewHolder.model_humidity = (ImageButton) convertView.findViewById(R.id.model_humidity);
            childViewHolder.model_dry = (ImageButton) convertView.findViewById(R.id.model_dry);
            childViewHolder.model_wind = (ImageButton) convertView.findViewById(R.id.model_wind);
            childViewHolder.childArc.setVisibility(View.INVISIBLE);
            airConditionStatusArray[0].setAirConditionMode(6);//7 25设置空调初态
            airConditionStatusArray[1].setAirConditionMode(6);//7 25设置空调初态

            convertView.setTag(childViewHolder);
        } else {
            childViewHolder = (ChildViewHolder) convertView.getTag();
        }
        MyOnClickListener myOnClickListener = new MyOnClickListener(childViewHolder,groupPosition);
        childViewHolder.model_cool.setOnClickListener(myOnClickListener);
        childViewHolder.model_dry.setOnClickListener(myOnClickListener);
        childViewHolder.model_humidity.setOnClickListener(myOnClickListener);
        childViewHolder.model_wind.setOnClickListener(myOnClickListener);
        childViewHolder.modelhint.setOnClickListener(myOnClickListener);
        childViewHolder.childswitchkey.setOnClickListener(myOnClickListener);
        MySeekArcListener mySeekArcListener=new MySeekArcListener(childViewHolder,groupPosition);
        childViewHolder.seekArc.setOnSeekArcChangeListener(mySeekArcListener);
        switch (airConditionStatusArray[groupPosition].getAirConditionMode()) {
            case Constants.AirMode.MODE_COOL:
                childViewHolder.childACmode.setImageDrawable(context.getResources().getDrawable(R.drawable.ac_mode_cool));
                break;
            case Constants.AirMode.MODE_HUMIDITY:
                childViewHolder.childACmode.setImageDrawable(context.getResources().getDrawable(R.drawable.ac_mode_arefaction));
                break;
            case Constants.AirMode.MODE_DRY:
                childViewHolder.childACmode.setImageDrawable(context.getResources().getDrawable(R.drawable.ac_mode_hot));
                break;
            case Constants.AirMode.MODE_WIND:
                childViewHolder.childACmode.setImageDrawable(context.getResources().getDrawable(R.drawable.ac_mode_ventilate));
                break;
        }

        if ( airConditionStatusArray[groupPosition].getAirConditionMode()==5) {
            childViewHolder.childswitchkey.setImageDrawable(context.getResources().getDrawable(R.drawable.switch_on_normal));
        } else if(airConditionStatusArray[groupPosition].getAirConditionMode()==6)//7 25
            childViewHolder.childswitchkey.setImageDrawable(context.getResources().getDrawable(R.drawable.switch_off_normal));
        childViewHolder.seekArc.setProgress((int)((airConditionStatusArray[groupPosition].getAirConditionTemperature()-MIN_TEMPERATURE)/(MAX_TEMPERATURE-MIN_TEMPERATURE)*100));
        childViewHolder.mSeekArcProgress.setText("" + airConditionStatusArray[groupPosition].getAirConditionTemperature());
        return convertView;
    }

    class ChildViewHolder {
        FrameLayout menuLayout;
        ImageView childswitchkey;
        ImageView childACmode;
        TextView childcurrenttemp;
        com.ogaclejapan.arclayout.ArcLayout childArc;
        com.triggertrap.seekarc.SeekArc seekArc;
        Button modelhint;
        TextView mSeekArcProgress;
        ImageButton model_cool;
        ImageButton model_humidity;
        ImageButton model_dry;
        ImageButton model_wind;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}
