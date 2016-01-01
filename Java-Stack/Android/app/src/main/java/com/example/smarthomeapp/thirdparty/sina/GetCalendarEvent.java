package com.example.smarthomeapp.thirdparty.sina;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;

import com.example.smarthomeapp.model.ActivityType;
import com.example.smarthomeapp.model.SocialInfo;
import com.example.smarthomeapp.model.SocialSource;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * Created by 家琪 on 2015/7/10.
 */
public class GetCalendarEvent {

    private static Context context;
    private static GetCalendarEvent getCalendarEvent;
    private static  int interval = 12000;

    private static final String calanderEventURL = "content://com.android.calendar/events";

    private GetCalendarEvent(Context c){
        context = c;
    }

    public static GetCalendarEvent getInstance(Context c){
        if(getCalendarEvent != null && context.equals(c)){
            return getCalendarEvent;
        }else{
            return getCalendarEvent = new GetCalendarEvent(c);
        }
    }

    public static void setInterval(int inter){
        interval = inter;
    }

    public static int getInterval(){
        return interval;
    }

    public ArrayList<SocialInfo> getCalendarEvent(){
        ArrayList<SocialInfo> socialList = new ArrayList<SocialInfo>();
        String eventTitle;
        String eventTime1;
        String eventTime2;
        try {
            ContentResolver cr = context.getContentResolver();

            //设置时间间隔
            Date date1 = new Date();
            long millSeconds1 = date1.getTime();
            Timestamp timestamp = new Timestamp(millSeconds1);
            long millSeconds2 = millSeconds1 -  interval;
            Timestamp timestamp1 = new Timestamp(millSeconds2);
            String selection = " dstart BETWEEN " + timestamp.toString() + " AND " + timestamp1.toString();

            Cursor eventCursor = cr.query(Uri.parse(calanderEventURL), null, selection, null, null);
            if(eventCursor.moveToFirst()){
                do{
                    eventTitle = eventCursor.getString(eventCursor.getColumnIndex("title"));
                    eventTime1 = eventCursor.getString(eventCursor.getColumnIndex("dtstart"));
                    eventTime2 = eventCursor.getString(eventCursor.getColumnIndex("dtend"));
                    String startTimeStamp = eventTime1.substring(0,10 );
                    long startEventTime = Long.parseLong(startTimeStamp);
                    String startTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(startEventTime*1000));
                    String endTimeStamp = eventTime2.substring(0,10 );
                    long endEventTime = Long.parseLong(endTimeStamp);
                    String endTime = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(endEventTime*1000));

                    ArrayList<String[]> results = EventDetection.detection(
                            eventTitle, startTime, 0);
                    if (results.size() > 0) {

                        String[] result = (String[]) results.get(0);

                        Integer activity_type_id = Integer.valueOf(result[0]);

                        SocialInfo socialInfo = new SocialInfo();
                        SocialSource socialSource = new SocialSource();
                        socialSource.setSourceTypeId(1);
                        socialInfo.setStartTime(result[1]);
                        socialInfo.setEndTime(result[2]);

                        ActivityType activityType = new ActivityType();
                        activityType.setActivityTypeId(activity_type_id);
                        socialInfo.setActivityType(activityType);

                        socialInfo.setSocialSource(socialSource);
                        socialInfo.setActivitySentTime(new Timestamp(System.currentTimeMillis()));
                        socialList.add(socialInfo);
                    }
                }while(eventCursor.moveToNext());
                return socialList;
            }else{
                return null;
            }
        }catch (SQLiteException ex) {
            Log.d("GetCalendarException", ex.getMessage());
        }
        return null;
    }

}
