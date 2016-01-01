package com.example.shems.daos.sqllite3;


import java.util.HashMap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.shems.util.DBdirectHelper;

public class DBManager {
    private DBdirectHelper helper;
    private SQLiteDatabase db;

    public DBManager(Context context) {
        helper = new DBdirectHelper(context);
        // 因为getWritableDatabase内部调用了mContext.openOrCreateDatabase(mName, 0,
        // mFactory);
        // 所以要确保context已初始化,我们可以把实例化DBManager的步骤放在Activity的onCreate里
        db = helper.getDatabase();
    }

    public HashMap<Integer, Integer> queryHumanBehavior() {
        HashMap<Integer, Integer> hashMap=new HashMap<Integer, Integer>();
        StringBuilder sBuilder=new StringBuilder();
        sBuilder.append("SELECT count(H.Sub_metering_3),Time ").append("FROM HousePowerConsumption H ")
                .append("WHERE Date LIKE '%/7/2007' and H.Sub_metering_3>5 GROUP BY Time");
        Cursor c = db.rawQuery(sBuilder.toString(), null);
        int hour=0;
        int count[]=new int[24];
        while (c.moveToNext()) {
            String [] strs=c.getString(1).split(":");
            String tempString=String.valueOf(hour);
            if(tempString.length()==1)
                tempString="0"+tempString;
            if(strs[0].equals(tempString))
            {
                count[hour]+=c.getInt(0);
                Log.i("tempstr",tempString);
            }
            else {
                hashMap.put(hour, count[hour]);
                hour++;
            }

        }
        c.close();
        return hashMap;
    }

    public HashMap<Integer, Double>queryMonthlyUsage()
    {
        HashMap<Integer, Double> hashMap=new HashMap<Integer, Double>();
        StringBuilder sBuilder=new StringBuilder();
        sBuilder.append("SELECT sum(H.Sub_metering_3),Date ").append("FROM HousePowerConsumption H ")
                .append("WHERE (Date LIKE '%/7/2007' OR  Date LIKE '%/8/2007' ")
                .append("Or Date like '%/9/2007' or Date LIKE'%/10/2007')and H.Sub_metering_3>5 GROUP BY Date ")
                .append("ORDER BY Date ASC");
        Cursor c = db.rawQuery(sBuilder.toString(), null);
        double sum[]=new double[12];
        for(int i=0;i<sum.length;i++)
        {
            sum[i]=-1;
        }
        while (c.moveToNext()) {
            String [] strs=c.getString(1).split("/");
            sum[(Integer.parseInt(strs[1]))-1]=c.getDouble(0)*(1.0/60);
        }
        for(int i=0;i<12;i++)
        {
            if(sum[i]!=-1)
            {
                int month=i+1;
                hashMap.put(month, sum[i]);
            }
        }
        c.close();
        return hashMap;
    }
    public HashMap<String, Double>queryElecUsageDivision()
    {
        HashMap<String, Double> hashMap=new HashMap<String, Double>();
        StringBuilder sBuilder=new StringBuilder();
        sBuilder.append("SELECT sum(H.Sub_metering_1) AS sum1,").append("sum(H.Sub_metering_2) AS sum2,sum(H.Sub_metering_3) AS sum3 ")
                .append("FROM HousePowerConsumption H ")
                .append("WHERE Date LIKE '%/7/2007' ");
        Cursor c = db.rawQuery(sBuilder.toString(), null);
        c.moveToNext();
        for(int i=0;i<c.getColumnCount();i++)
        {
            hashMap.put("MeterLine"+(i+1), c.getDouble(i));
        }
        c.close();
        return hashMap;
    }

}
