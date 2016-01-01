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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

/**
 * Created by 家琪 on 2015/7/10.
 * 获取手机短信
 */
public class GetSMS {

    private static GetSMS getSMS;
    private static  Context context;
    private static  int interval = 120000;

    private static final String SMS_URI_ALL = "content://sms/"; // 所有短信
    private static final String SMS_URI_INBOX = "content://sms/inbox"; // 收信箱
    private static final String SMS_URI_SEND = "content://sms/sent"; // 发信箱
    private static final String SMS_URI_DRAFT = "content://sms/draft"; // 草稿箱

    //私有构造方法
    private GetSMS(Context c){
        context = c;
    }

    //单例模式
    public static GetSMS getInstance(Context c){
        if(getSMS != null && context.equals(c)){
            return getSMS;
        }else{
            return getSMS = new GetSMS(c);
        }
    }

    //设置时间间隔
    public static void setInterval(int inter){
        interval = inter;
    }

    public static int getInterval(){
        return interval;
    }

    public ArrayList<SocialInfo> getSMS(){

        ArrayList<SocialInfo> socialList = new ArrayList<SocialInfo>();
        String name;
        String phoneNumber;
        String smsbody;
        String date;
        String type;
        int nameColumn;
        int phoneNumberColumn;
        int smsbodyColumn;
        int dateColumn ;
        int typeColumn ;
        try {
            ContentResolver cr = context.getContentResolver();
            String[] projection = new String[] { "_id", "address", "person",
                    "body", "date", "type" };
            Uri uri = Uri.parse(SMS_URI_ALL);

            //设置时间间隔
            Date date1 = new Date();
            long millSeconds1 = date1.getTime();
            Timestamp timestamp = new Timestamp(millSeconds1);
            long millSeconds2 = millSeconds1 -  interval;
            Timestamp timestamp1 = new Timestamp(millSeconds2);
            String selection = " date BETWEEN " + timestamp.toString() + " AND " + timestamp1.toString();

            Cursor cur = cr.query(uri, projection, selection, null, "date desc");
            if (cur.moveToFirst()) {
                nameColumn = cur.getColumnIndex("person");// 姓名
                phoneNumberColumn = cur.getColumnIndex("address");// 手机号
                smsbodyColumn = cur.getColumnIndex("body");// 短信内容
                dateColumn = cur.getColumnIndex("date");// 日期
                typeColumn = cur.getColumnIndex("type");// 收发类型 1表示接受 2表示发送
                do {
                    name = cur.getString(nameColumn);
                    phoneNumber = cur.getString(phoneNumberColumn);
                    smsbody = cur.getString(smsbodyColumn);
                    SimpleDateFormat dateFormat = new SimpleDateFormat(
                            "yyyy-MM-dd hh:mm:ss");
                    Date d = new Date(Long.parseLong(cur.getString(dateColumn)));
                    date = dateFormat.format(d);
                    int typeId = cur.getInt(typeColumn);
                    Log.d("GET SMS",  String.valueOf(typeId));
                    if (typeId == 1) {
                        type = "接收";

                    } else if (typeId == 2) {
                        type = "发送";
                    } else {
                        type = "";
                    }
                    //语意分析
                    ArrayList<String[]> results = EventDetection.detection(
                            smsbody, date, 0);
                    if (results.size() > 0) {
                        //默认为单事件， result中一条事件分析结果
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
                } while (cur.moveToNext());
                return socialList;
            } else {
                return null;
            }
        } catch (SQLiteException ex) {
            Log.d("SQLiteException in getSmsInPhone", ex.getMessage());
        }
        return null;
    }

}
