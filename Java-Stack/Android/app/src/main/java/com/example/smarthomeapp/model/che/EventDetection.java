package com.example.smarthomeapp.model.che;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;


import com.example.smarthomeapp.R;

import java.util.Date;
import java.util.List;

/**
 * Created by CHEYulin on 2015/5/17.
 */
public class EventDetection {
    Context context;
    Date happenDate;
    String[] eventSources;
    String eventName;
    String eventDescription;
    List<Device> deviceList;

    public Context getContext() {
        return context;
    }

    public Date getHappenDate() {
        return happenDate;
    }

    public String[] getEventSources() {
        return eventSources;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public List<Device> getDeviceList() {
        return deviceList;
    }

    private Drawable getEventSourceDrawable(String eventSource){
        Log.d(getClass().getSimpleName(), eventSource);
        if(eventSource.contains(context.getString(R.string.gps)))
        {
            return context.getResources().getDrawable(R.drawable.location);
        }
        else if(eventSource.contains(context.getString(R.string.hand_ring))){
            return context.getResources().getDrawable(R.drawable.hand_ring);
        }
        else if(eventSource.contains(context.getString(R.string.weibo))){
            return context.getResources().getDrawable(R.drawable.weibo);
        }
        else if(eventSource.contains(context.getString(R.string.alarm_clock))){
            return context.getResources().getDrawable(R.drawable.alarm);
        }
        else if(eventSource.contains(getContext().getString(R.string.api_bai_du_map))){
            return  context.getResources().getDrawable(R.drawable.map);
        }
        else
            return null;
    }

    public Drawable[] getEventSourcesDrawables(){
        if(eventSources!=null&&eventSources.length>0){
            Drawable[] drawables=new Drawable[eventSources.length];
            for(int i=0;i<eventSources.length;i++){
                drawables[i]=getEventSourceDrawable(eventSources[i]);
            }
            return  drawables;
        }
        else
             return null;
    }

    public Drawable getFirstEventSourceDrawable(){
        Drawable[] drawables=getEventSourcesDrawables();
        if(drawables!=null){
            return drawables[0];
        }
        else
            return null;
    }

    public EventDetection(Context context, Date happenDate, String[] eventSources, String eventName, String eventDescription, List<Device> deviceList) {
        this.context = context;
        this.happenDate = happenDate;
        this.eventSources = eventSources;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.deviceList = deviceList;
    }
}
