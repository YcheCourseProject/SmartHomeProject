package com.example.shems.activities.ui;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shems.models.event_detection.AirConditioner;
import com.example.shems.models.event_detection.Curtain;
import com.example.shems.models.event_detection.Device;
import com.example.shems.models.event_detection.DrinkerMachine;
import com.example.shems.models.event_detection.EggBoiler;
import com.example.shems.models.event_detection.EventDetection;
import com.example.shems.models.event_detection.Lamp;
import com.example.shems.models.event_detection.WaterHeater;
import com.example.shems.views.SubListView;
import com.example.smarthome.R;


import java.io.DataOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 *
 */
public class EventDetectionActivity extends MyActionBarActivity {
    private ExpandableListView expandableListView;
    private MyExpandableAdapter myExpandableAdapter;
    private List<EventDetection> eventDetectionList;
    private boolean isInsertData = true;
    private int insertCount = -1;
    private Handler handler = new Handler();
    private Runnable insertDataRunnable = new Runnable() {
        public void run() {
            if (isInsertData) {
                Log.d("Runnable", "delay_in");
                updateListViewData();
                handler.postDelayed(this, 10000);
                Log.d("Runnable", "delay_over");
                if (insertCount > 4) {
                    isInsertData = false;
                }
            }
        }
    };

    private void findViews() {
        expandableListView = (ExpandableListView) findViewById(R.id.expandable_view_event_detection);
    }

    private void changeSystemTime(EventDetection eventDetection) {
        Date date=eventDetection.getHappenDate();
        try {
            Process process = Runtime.getRuntime().exec("su");
            String datetime = "20150521."+date.getHours()+date.getMinutes()+date.getSeconds(); //测试的设置的时间【时间格式 yyyyMMdd.HHmmss】
            DataOutputStream os = new DataOutputStream(process.getOutputStream());
            os.writeBytes("setprop persist.sys.timezone GMT-8\n");
            os.writeBytes("/system/bin/date -s " + datetime + "\n");
            os.writeBytes("clock -w\n");
            os.writeBytes("exit\n");
            os.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 用来更新ExpandableListView里面的数据
     */
    private void updateListViewData() {

        eventDetectionList = myExpandableAdapter.getEventDetectionList();
        if (eventDetectionList == null) {
            eventDetectionList = new LinkedList<EventDetection>();
            myExpandableAdapter.setEventDetectionList(eventDetectionList);
        }
        List<Device> deviceList;
        switch (insertCount) {
            case -1:
                break;
            case 0:
                Date date = new Date();
                date.setHours(6);
                date.setMinutes(48);
                String[] eventSources = new String[]{getString(R.string.hand_ring)};
                String eventDescription = getString(R.string.awake_description);
                String eventName = getString(R.string.simple_awake);
                deviceList = new ArrayList<Device>();
                deviceList.add(new Lamp(this));
                deviceList.add(new EggBoiler(this));
                deviceList.add(new Curtain(this));
                EventDetection eventDetection = new EventDetection(this, date, eventSources, eventName, eventDescription, deviceList);
                eventDetectionList.add(0, eventDetection);
                break;
            case 1:
                date = new Date();
                date.setHours(8);
                date.setMinutes(10);
                eventSources = new String[]{getString(R.string.gps)};
                eventDescription = getString(R.string.go_out_home);
                eventName = getString(R.string.simple_go_out_home);
                deviceList = new ArrayList<Device>();
                deviceList.add(new AirConditioner(this));
                deviceList.add(new DrinkerMachine(this));
                eventDetection = new EventDetection(this, date, eventSources, eventName, eventDescription, deviceList);
                eventDetectionList.add(0, eventDetection);
                break;
            case 2:
                date = new Date();
                date.setHours(8);
                date.setMinutes(22);
                eventSources = new String[]{getString(R.string.gps), getString(R.string.api_bai_du_map)};
                eventDescription = getString(R.string.traffic_jam);
                eventName = getString(R.string.simple_traffic_jam);
                deviceList = new ArrayList<Device>();
                deviceList.add(new AirConditioner(this));
                deviceList.add(new DrinkerMachine(this));
                eventDetection = new EventDetection(this, date, eventSources, eventName, eventDescription, deviceList);
                eventDetectionList.add(0, eventDetection);
                break;
            case 3:
                date = new Date();
                date.setHours(11);
                date.setMinutes(32);
                eventSources = new String[]{getString(R.string.weibo)};
                eventDescription = getString(R.string.social_plan_sports);
                eventName = getString(R.string.simple_plan_sports);
                deviceList = new ArrayList<Device>();
                deviceList.add(new WaterHeater(this));
                eventDetection = new EventDetection(this, date, eventSources, eventName, eventDescription, deviceList);
                eventDetectionList.add(0, eventDetection);
                break;
            case 4:
                date = new Date();
                date.setHours(14);
                date.setMinutes(47);
                eventSources = new String[]{getString(R.string.hand_ring)};
                eventDescription = getString(R.string.play_sports);
                eventName = getString(R.string.simple_play_sports);
                deviceList = new ArrayList<Device>();
                deviceList.add(new WaterHeater(this));
                deviceList.add(new AirConditioner(this));
                eventDetection = new EventDetection(this, date, eventSources, eventName, eventDescription, deviceList);
                eventDetectionList.add(0, eventDetection);
                break;
        }
        insertCount++;
        if(insertCount>0)
            changeSystemTime(eventDetectionList.get(0));
        myExpandableAdapter.notifyDataSetChanged();
    }

    private void initListViewData() {
        eventDetectionList = new LinkedList<EventDetection>();
        Date date = new Date();
        date.setHours(6);
        date.setMinutes(48);
        String[] eventSources = new String[]{getString(R.string.hand_ring)};
        String eventDescription = getString(R.string.awake_description);
        String eventName = getString(R.string.simple_awake);
        List<Device> deviceList = new ArrayList<Device>();
        deviceList.add(new Lamp(this));
        EventDetection eventDetection = new EventDetection(this, date, eventSources, eventName, eventDescription, deviceList);
        eventDetectionList.add(0, eventDetection);

        date = new Date();
        date.setHours(8);
        date.setMinutes(10);
        eventSources = new String[]{getString(R.string.gps)};
        eventDescription = getString(R.string.go_out_home);
        eventName = getString(R.string.simple_go_out_home);
        deviceList = new ArrayList<Device>();
        deviceList.add(new AirConditioner(this));
        deviceList.add(new DrinkerMachine(this));
        eventDetection = new EventDetection(this, date, eventSources, eventName, eventDescription, deviceList);
        eventDetectionList.add(0, eventDetection);

        date = new Date();
        date.setHours(8);
        date.setMinutes(22);
        eventSources = new String[]{getString(R.string.gps), getString(R.string.api_bai_du_map)};
        eventDescription = getString(R.string.traffic_jam);
        eventName = getString(R.string.simple_traffic_jam);
        deviceList = new ArrayList<Device>();
        deviceList.add(new AirConditioner(this));
        deviceList.add(new DrinkerMachine(this));
        eventDetection = new EventDetection(this, date, eventSources, eventName, eventDescription, deviceList);
        eventDetectionList.add(0, eventDetection);

        date = new Date();
        date.setHours(11);
        date.setMinutes(32);
        eventSources = new String[]{getString(R.string.weibo)};
        eventDescription = getString(R.string.social_plan_sports);
        eventName = getString(R.string.simple_plan_sports);
        deviceList = new ArrayList<Device>();
        deviceList.add(new WaterHeater(this));
        eventDetection = new EventDetection(this, date, eventSources, eventName, eventDescription, deviceList);
        eventDetectionList.add(0, eventDetection);

        date = new Date();
        date.setHours(14);
        date.setMinutes(47);
        eventSources = new String[]{getString(R.string.hand_ring)};
        eventDescription = getString(R.string.play_sports);
        eventName = getString(R.string.simple_play_sports);
        deviceList = new ArrayList<Device>();
        deviceList.add(new WaterHeater(this));
        deviceList.add(new AirConditioner(this));
        eventDetection = new EventDetection(this, date, eventSources, eventName, eventDescription, deviceList);
        eventDetectionList.add(0, eventDetection);

    }

    private void setAdapters() {
//        initListViewData();
        myExpandableAdapter = new MyExpandableAdapter(eventDetectionList, this);
        expandableListView.setAdapter(myExpandableAdapter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setActionBarTitle(getString(R.string.title_activity_event_detection));
        setContentView(R.layout.activity_event_detection);
        findViews();
        expandableListView.setGroupIndicator(null);
        setAdapters();
    }

    /**
     * 在获取焦点后定时更新ExpandableListview条目
     */
    @Override
    protected void onResume() {
        super.onResume();
        handler.post(insertDataRunnable);
    }


    /**
     * 事件检测ListView的ExpandableListAdapter
     */
    private class MyExpandableAdapter extends BaseExpandableListAdapter {
        private final int CHILDREN_COUNT = 1;
        private LayoutInflater layoutInflater;
        private List<EventDetection> eventDetectionList;
        private Context context;

        public List<EventDetection> getEventDetectionList() {
            return eventDetectionList;
        }

        public void setEventDetectionList(List<EventDetection> eventDetectionList) {
            this.eventDetectionList = eventDetectionList;
        }

        private MyExpandableAdapter(List<EventDetection> eventDetectionList, Context context) {
            this.layoutInflater = LayoutInflater.from(context);
            this.eventDetectionList = eventDetectionList;
            this.context = context;
        }

        public int getGroupCount() {

            if (eventDetectionList != null) {
                Log.d(getClass().getSimpleName(), "getGroupCount" + eventDetectionList.size());
                return eventDetectionList.size();
            } else
                return 0;
        }

        public int getChildrenCount(int groupPosition) {
            if (eventDetectionList != null)
                return CHILDREN_COUNT;
            else
                return 0;
        }

        public Object getGroup(int groupPosition) {
            return eventDetectionList.get(groupPosition);
        }

        public Object getChild(int groupPosition, int childPosition) {
            return eventDetectionList.get(groupPosition);
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        public long getChildId(int groupPosition, int childPosition) {
            int sum = 0;
            for (int i = 0; i < groupPosition; i++) {
                sum += getChildrenCount(i);
            }
            return sum + childPosition;
        }

        public boolean hasStableIds() {
            return false;
        }

        private String getFormatDateString(Date date) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
            simpleDateFormat.applyPattern("HH:mm a");
            return simpleDateFormat.format(date);
        }

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            Log.d(getClass().getSimpleName(), "GroupView");
            EventDetection eventDetection = eventDetectionList.get(groupPosition);
            GroupViewHolder groupViewHolder;
            View view = convertView;
            if (view == null) {
                groupViewHolder = new GroupViewHolder();
                view = layoutInflater.inflate(R.layout.item_group_event, null);
                groupViewHolder.hintEventTypeTextView = (TextView) view.findViewById(R.id.txt_group_event_description);
                groupViewHolder.dateTextView = (TextView) view.findViewById(R.id.txt_hint_date);
                groupViewHolder.hintSourceTypeImageView = (ImageView) view.findViewById(R.id.img_group_hint_source_type);
                groupViewHolder.hintExpandImageView = (ImageView) view.findViewById(R.id.img_group_hint);
                view.setTag(groupViewHolder);
            } else
                groupViewHolder = (GroupViewHolder) view.getTag();
            if (isExpanded == true) {
                groupViewHolder.hintExpandImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.down_arrow));
            } else {
                groupViewHolder.hintExpandImageView.setImageDrawable(context.getResources().getDrawable(R.drawable.right_arrow));
            }
            groupViewHolder.dateTextView.setText(getFormatDateString(eventDetection.getHappenDate()));
            groupViewHolder.hintEventTypeTextView.setText(eventDetection.getEventName());
            groupViewHolder.hintSourceTypeImageView.setImageDrawable(eventDetection.getFirstEventSourceDrawable());
            return view;
        }

        class GroupViewHolder {
            TextView dateTextView;
            TextView hintEventTypeTextView;
            ImageView hintSourceTypeImageView;
            ImageView hintExpandImageView;
        }

        private class MyDevicesListViewAdapter extends BaseAdapter {
            List<Device> deviceList;

            public List<Device> getDeviceList() {
                return deviceList;
            }

            private MyDevicesListViewAdapter(int groupPosition) {
                this.deviceList = eventDetectionList.get(groupPosition).getDeviceList();
            }

            public int getCount() {
                if (deviceList != null) {
                    Log.d(getClass().getSimpleName(), "count" + deviceList.size());
                    return deviceList.size();
                } else
                    return 0;
            }

            public Object getItem(int position) {
                return deviceList.get(position);
            }

            public long getItemId(int position) {
                return position;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                ViewHolder viewHolder;
                if (view == null) {
                    view = layoutInflater.inflate(R.layout.item_devices_listview, null);
                    viewHolder = new ViewHolder();
                    viewHolder.hintTextView = (TextView) view.findViewById(R.id.txt_hint_description);
                    viewHolder.deviceNameTextView = (TextView) view.findViewById(R.id.txt_device_name);
                    viewHolder.devicePictureImageView = (ImageView) view.findViewById(R.id.img_device_picture);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }
                Device device = deviceList.get(position);
                Log.d(getClass().getSimpleName(), "" + position);
                if (position == 0)
                    viewHolder.hintTextView.setText(context.getString(R.string.linked_devices));
                else
                    viewHolder.hintTextView.setText("");
                viewHolder.deviceNameTextView.setText(device.getDeviceName());
                viewHolder.devicePictureImageView.setImageDrawable(device.getDevicePicture());
                return view;
            }

            class ViewHolder {
                TextView hintTextView;
                TextView deviceNameTextView;
                ImageView devicePictureImageView;
            }
        }

        private class MyEventSourcesViewAdapter extends BaseAdapter {
            String[] eventSources;
            Drawable[] eventSourcePictures;

            private MyEventSourcesViewAdapter(int groupPosition) {
                this.eventSources = eventDetectionList.get(groupPosition).getEventSources();
                this.eventSourcePictures = eventDetectionList.get(groupPosition).getEventSourcesDrawables();
            }

            public int getCount() {
                return eventSources.length;
            }

            public Object getItem(int position) {
                return eventSources[position];
            }

            public long getItemId(int position) {
                return position;
            }

            public View getView(int position, View convertView, ViewGroup parent) {
                View view = convertView;
                ViewHolder viewHolder;
                if (view == null) {
                    view = layoutInflater.inflate(R.layout.item_event_source_listview, null);
                    viewHolder = new ViewHolder();
                    viewHolder.hintTextView = (TextView) view.findViewById(R.id.txt_hint_description);
                    viewHolder.eventTypeTextView = (TextView) view.findViewById(R.id.txt_event_type_name);
                    viewHolder.eventTypePictureImageView = (ImageView) view.findViewById(R.id.img_event_type_picture);
                    view.setTag(viewHolder);
                } else {
                    viewHolder = (ViewHolder) view.getTag();
                }

                if (position == 0)
                    viewHolder.hintTextView.setText(context.getString(R.string.event_source));
                else
                    viewHolder.hintTextView.setText("");
                viewHolder.eventTypeTextView.setText(eventSources[position]);
                viewHolder.eventTypePictureImageView.setImageDrawable(eventSourcePictures[position]);
                return view;
            }

            class ViewHolder {
                TextView hintTextView;
                TextView eventTypeTextView;
                ImageView eventTypePictureImageView;
            }
        }

        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            EventDetection eventDetection = eventDetectionList.get(groupPosition);
            ChildViewHolder childViewHolder;
            View view = convertView;
            if (view == null) {
                childViewHolder = new ChildViewHolder();
                view = layoutInflater.inflate(R.layout.item_child_event, null);
                childViewHolder.eventSourceListView = (SubListView) view.findViewById(R.id.list_view_event_source);
                childViewHolder.eventDescriptionTextView = (TextView) view.findViewById(R.id.txt_event_detail_description);
                childViewHolder.deviceListView = (SubListView) view.findViewById(R.id.list_view_devices);
                view.setTag(childViewHolder);

            } else
                childViewHolder = (ChildViewHolder) view.getTag();
            childViewHolder.eventDescriptionTextView.setText(eventDetection.getEventDescription());
            childViewHolder.eventSourceListView.setAdapter(new MyEventSourcesViewAdapter(groupPosition));
            final MyDevicesListViewAdapter myDevicesListViewAdapter = new MyDevicesListViewAdapter(groupPosition);
            childViewHolder.deviceListView.setAdapter(myDevicesListViewAdapter);
            childViewHolder.deviceListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    myDevicesListViewAdapter.getDeviceList().get(position).getDetailDialog(new Object());
                }
            });
            return view;
        }

        class ChildViewHolder {
            SubListView eventSourceListView;
            TextView eventDescriptionTextView;
            SubListView deviceListView;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }

}
