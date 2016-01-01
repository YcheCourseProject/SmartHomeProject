package com.example.smarthomeapp.dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.smarthomeapp.model.ActivityType;
import com.example.smarthomeapp.model.SocialInfo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by 家琪 on 2015/7/7.
 */
public class SocialOperateHelper extends DBHelper {

    public SocialOperateHelper(Context context) {
        super(context);
    }

    @Override
    public synchronized void  add(Object obj) {
        SocialInfo socialInfo = (SocialInfo)obj;
       ActivityType activityType = socialInfo.getActivityType();

        database.beginTransaction();
        try{

            database.execSQL("INSERT INTO social_info VALUES(NULL, ?, ?, ?, ?, ?,?)", new Object[]{
                    socialInfo.getSocialInfoId(),
                    socialInfo.getStartTime(),
                    socialInfo.getEndTime(),
                    null,
                    socialInfo.getActivitySentTime(),
                    new Timestamp(new Date().getTime())
            });
            database.execSQL(" INSERT INTO social_activity VALUES((SELECT max(social_info_id) FROM social_info), ?)", new Object[]{
                        activityType.getActivityTypeId()
               });
            database.setTransactionSuccessful();
            Log.d("SocialOperateHelper add social_info", "add ");

        }finally {
            database.endTransaction();
        }

    }

    @Override
    public void delete() {

    }

    @Override
    public void query() throws Exception {

        Cursor cursor = database.rawQuery("SELECT * FROM social_info", null);
        if (cursor.moveToFirst()){
            int index = cursor.getColumnIndex("social_info_id");
            int id = cursor.getInt(index);
            Integer intObj = Integer.valueOf(id);
            Log.e("SocialOperateHelper ADD RESULT", intObj.toString());
        }

        cursor = database.rawQuery("SELECT * FROM social_activity", null);
        if (cursor.moveToFirst()){
            int index = cursor.getColumnIndex("activity_type_id");
            int id = cursor.getInt(index);
            Integer intObj = Integer.valueOf(id);
            Log.e("social_activity ADD RESULT", intObj.toString());
        }

    }
}
