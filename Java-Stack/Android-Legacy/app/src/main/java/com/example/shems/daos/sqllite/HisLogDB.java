package com.example.shems.daos.sqllite;
import java.util.Date;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;

public class HisLogDB extends SQLliteFather{

    //	Long timestamp;
    static final String LONG_TIME="time";		//记录日志的时间
    static final String FLOAT_AP="instant_ap";//表示瞬时有功
    static final String	FLOAT_RP="instant_rp";//表示瞬时无功
    static final String FLOAT_PF="instant_pf";//表示瞬时功率因数

    public HisLogDB(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        this.setTablename("table_HisLog");
        this.setCols(new String[]{LONG_TIME,FLOAT_AP,FLOAT_RP,FLOAT_PF});		//设置存的内容名字
        this.onUpgrade();
    }

    @Override
    public void drop( ) {
        // TODO Auto-generated method stub
        super.drop();
    }
    public void dropAndCreadte()		//扔掉再重新创建
    {
        this.drop();
        this.onCreate();
    }

    public void onCreate() {
        //sqldb是一个句柄
        // TODO Auto-generated method stub
        StringBuilder sb=new StringBuilder("CREATE TABLE ");
        sb.append(this.getTablename());
        sb.append(" (");
        sb.append(this.getCols()[0]);
        sb.append(" INTEGER primary key, ");	//这里的时间戳一定不同因为存的log中的时间是不同的
        sb.append(this.getCols()[1]);
        sb.append(" DOUBLE NOT NULL, ");
        sb.append(this.getCols()[2]);
        sb.append(" DOUBLE NOT NULL, ");
        sb.append(this.getCols()[3]);
        sb.append(" DOUBLE NOT NULL)");
        this.getDb().execSQL(sb.toString());			//创建表
        Log.i("cylconfirm_createTable", "数据库的表创建成功");
    }

    @Override
    public synchronized void close() {
        // TODO Auto-generated method stub
        super.close();
    }

    @Override
    public void onUpgrade() {
        // TODO Auto-generated method stub
        this.open();
        String sql = "SELECT * FROM sqlite_master where type=? and name=? ";// 先看表是否存在
        Cursor tempcursor = this.getDb().rawQuery(sql, new String[] { "table",
                this.getTablename() });
        int count = tempcursor.getCount();
        Log.i("count", String.valueOf(count));
        if (count <= 0) {
            this.onCreate();
            Log.i("create table" + this.getTablename(), "success");
        }
    }




    public boolean judgeIfExists(Long timestamp)
    {
        this.open();
        String whereclause=LONG_TIME+"=?";
        String selectionargs[]={""+timestamp};
        String columns[]=this.getCols();
        String orderbyClause=this.getCols()[0]+" DESC";		//这样就是从最近到endTimeStamp
        Cursor cursor=this.getDb().query(this.getTablename(),columns, whereclause,
                selectionargs, null, null, null);
        if(cursor.getCount()>0)
            return true;
        else
            return false;
    }
    public long getMaxTimeStamp()
    {
        this.open();
        long maxtimestamp=0;
        Cursor cursor=this.selectAllForTimeStamp();
        while (cursor.moveToNext()) {
            String temptimestamp=cursor.getString(0);
            long l=Long.parseLong(temptimestamp);
            if(l>maxtimestamp)
                maxtimestamp=l;
        }
        return maxtimestamp;
        //有可能返回了0
    }
    public boolean  insert(Date time,float ap,float rp,float power_factor)
    {
        this.open();
        //Log.i("insert method", time.toLocaleString()+" "+ap);
        //如果成功插入就返回true  否则返回false
        //Long nowDBMaxTimeStamp=this.getMaxTimeStamp();
        Long timestamp=time.getTime();
        //先查询再插入
        Log.i("into insert", time.toLocaleString());
        if(this.judgeIfExists(timestamp)==false)
        {
            ContentValues contentvalues;
            contentvalues=new ContentValues();		//存放一条记录
            //把一条记录的内容包装一下：
            contentvalues.put(this.getCols()[0], timestamp);
            contentvalues.put(this.getCols()[1], ap);
            contentvalues.put(this.getCols()[2], rp);
            contentvalues.put(this.getCols()[3], power_factor);
            this.getDb().insert(this.getTablename(), null,contentvalues);
            return true;

        }
        else return false;
        //表示到目前为止有多少行条记录
    }
    /**
     * 获得从现在到结束的之前结点的所有记录
     * @param endTimeStamp
     * @return
     */

    public Cursor select(long endTimeStamp)		//查找的时候根据获得的特征信息来找
    {
        this.open();
        //先得到一个读的句柄
        Log.i("endTimeStamp", endTimeStamp+"");
        Log.i("enddate", new Date(endTimeStamp).toLocaleString());
        String whereclause=LONG_TIME+">? ";
        String selectionargs[]={""+endTimeStamp};
        String columns[]=this.getCols();
        String orderbyClause=this.getCols()[0]+" DESC";		//这样就是从最近到endTimeStamp
        Cursor cursor=this.getDb().query(this.getTablename(),columns, whereclause,
                selectionargs, null, null, orderbyClause);
        return cursor;
    }
    public Cursor selectAllForTimeStamp()		//查找的时候根据获得的特征信息来找
    {
        this.open();
        String whereclause=null;
        String selectionargs[]=null;
        String columns[]=this.getCols();
        String orderbyClause=this.getCols()[0]+" DESC";
        Cursor cursor=this.getDb().query(this.getTablename(),columns, whereclause,
                selectionargs, null, null, orderbyClause);
        return cursor;
    }

    public Cursor selectOrderByASC()		//    然后升序来找
    {
        this.open();
        String whereclause=null;
        String selectionargs[]=null;
        String columns[]=this.getCols();
        String orderbyClause=this.getCols()[0]+" ASC";
        Cursor cursor=this.getDb().query(this.getTablename(),columns, whereclause,
                selectionargs, null, null, orderbyClause);
        return cursor;
    }

}
