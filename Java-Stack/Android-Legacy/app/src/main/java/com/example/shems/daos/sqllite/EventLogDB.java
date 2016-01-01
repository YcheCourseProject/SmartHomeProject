package com.example.shems.daos.sqllite;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;



public class EventLogDB extends SQLliteFather {

    static final String EVENT_HAPPEN_TIME="event_happen_time";   //格式用long形式因为那个形式是唯一的
    static final String IDENTIFIED_APPLIANCE="identified_appliance_name";
    static final String EVENT_STATUS="event_status";

    static final String TABLE_NAME_EVENT_LOG="table_EventLog";	//in   或者是  out
    public static final String STATUS_IN="in";
    public static final String STATUS_OUT="out";



    public EventLogDB(Context context) {
        super(context);
        //全部在构造函数里确定各个栏目(column)的值      和表的名字
        this.setTablename(TABLE_NAME_EVENT_LOG);
        this.setCols(new String[]{EVENT_HAPPEN_TIME,IDENTIFIED_APPLIANCE,EVENT_STATUS});				//设置存的内容名字
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        super.onCreate(db);
        StringBuilder sb=new StringBuilder("CREATE TABLE ");
        sb.append(this.getTablename());
        sb.append(" (");
        sb.append(this.getCols()[0]);
        sb.append(" INTEGER primary key, ");	//这里的时间戳一定不同因为存的log中的时间是不同的
        sb.append(this.getCols()[1]);
        sb.append(" DOUBLE NOT NULL, ");
        sb.append(this.getCols()[2]);
        sb.append(" DOUBLE NOT NULL)");
        this.getDb().execSQL(sb.toString());			//创建表
        Log.i("create table", " event_log 表创建成功");
    }

    public long insert(long time,String identified_appliance,String event_status)		//插入一条规整的记录
    {
        this.open();			//首先要打开           才可以getDB
        ContentValues contentvalues;
        contentvalues=new ContentValues();		//存放一条记录
        //把一条记录的内容包装一下：
        contentvalues.put(this.getCols()[0], time);
        contentvalues.put(this.getCols()[1], identified_appliance);
        contentvalues.put(this.getCols()[2], event_status);
        long recordnum=this.getDb().insert(this.getTablename(), null,contentvalues);
//		this.close();			//插入完毕后要关闭				
        return recordnum;
    }

    /**
     * @param itemname		表示对应的设备名称
     * @param trueForASC
     * @param startTimeStamp
     * @return
     */
    private Cursor selectItemOrderAndTimeStamp(String itemname,boolean trueForASC,long startTimeStamp)		//顺序就是true参数
    {
        this.open();
        Cursor cursor=null;
        String whereclause=EVENT_HAPPEN_TIME+">? and "+itemname+"=? ";				//截止日期以前的东西
        String selectionargs[]={EVENT_HAPPEN_TIME,IDENTIFIED_APPLIANCE};
        String columns[]=this.getCols();
        String orderbyClause=null;
        if(trueForASC==true)
        {
            orderbyClause=this.getCols()[0]+" ASC";
        }
        else
        {
            orderbyClause=this.getCols()[0]+" DESC";
        }
        cursor=this.getDb().query(this.getTablename(),columns, whereclause,
                selectionargs, null, null, orderbyClause);
//		this.close();

        return cursor;
    }
    /**
     * @param itemname
     * @param startTimeStamp
     * @return     毫秒级别的一个时间使用长度的List
     */
    public List<Long> SeekCertainDeviceForUseTime(String itemname,long startTimeStamp)		//返回时间段的一个数组
    {
        Cursor cursor=this.selectItemOrderAndTimeStamp(itemname, true, startTimeStamp);
        //队列维持逻辑
        Queue<Long> inTimeStampQue=new ArrayDeque<Long>();		//记录存储的接入时间
//		Queue<Long> outTimeStampQue=new ArrayDeque<Long>();		//记录存储的移除时间                
        List<Long> timelist=new ArrayList();
        //用来作为temp存储
        long temptimeStamp;
        String tempstatus;
        while(cursor.moveToLast())
        {
            temptimeStamp=cursor.getLong(0);
            tempstatus=cursor.getString(1);
            if(tempstatus==STATUS_IN)
            {
                int quenum=inTimeStampQue.size();
                if(quenum==0)
                {
                    inTimeStampQue.add(temptimeStamp);
                }
                if(quenum==1)
                {
                    inTimeStampQue.remove();
                    inTimeStampQue.add(temptimeStamp);
                }
            }
            if(tempstatus==STATUS_OUT)
            {
                if(inTimeStampQue.size()==1)
                {
                    long inTimeStamp=inTimeStampQue.poll();
                    long timeInterval=temptimeStamp-inTimeStamp;			//毫秒级别的
                    timelist.add(timeInterval);
                }
            }
        }
        return timelist;
    }

}
