package com.example.smarthomeapp.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.model.che.Device;
import com.example.smarthomeapp.model.che.EventDetection;
import com.example.smarthomeapp.views.SubListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Yulin on 2015/7/21.
 */

public class EventExpandableAdapter extends BaseExpandableListAdapter {
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

    public EventExpandableAdapter(List<EventDetection> eventDetectionList, Context context) {
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
