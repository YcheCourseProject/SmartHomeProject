package com.example.shems.daos.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class LoadInfoDB extends SQLiteOpenHelper {
    private final static String DATABASE_NAME="asg_db";				//sql数据库的名字
    private final static int DATABASE_VERSION=1;					//sql数据库的版本信息
    private final static String TABLE_NAME="loadinfo_table";		//负载信息的表名
    public final static String FIELD_ID="_id";
    //为了实现SimpleCursorAdaptor所比需要的
    //后面是4个关键的负载信息内容：
    public final static String FIELD_NAME="name";
    public final static String FIELD_aplow="aplow";
    public final static String FIELD_apup="apup";
    public final static String FIELD_rplow="rplow";
    public final static String FIELD_rpup="rpup";

    public LoadInfoDB(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.onUpgrade();
        //Context是为了能够连接到数据库     根据相对的data/data/asg/database的路径
    }

    @Override
    public void onCreate(SQLiteDatabase sqldb) {	//sqldb是一个句柄
        // TODO Auto-generated method stub
        StringBuilder sb=new StringBuilder("CREATE TABLE ");
        sb.append(this.TABLE_NAME);
        sb.append(" (");
        sb.append(this.FIELD_ID);
        sb.append(" INTEGER primary key autoincrement, ");
        sb.append(this.FIELD_NAME);
        sb.append(" VARCHAR(20) NOT NULL, ");
        sb.append(this.FIELD_aplow);
        sb.append(" DOUBLE NOT NULL, ");
        sb.append(this.FIELD_apup);
        sb.append(" DOUBLE NOT NULL, ");
        sb.append(this.FIELD_rplow);
        sb.append(" DOUBLE NOT NULL, ");
        sb.append(this.FIELD_rpup);
        sb.append(" DOUBLE NOT NULL)");

        sqldb.execSQL(sb.toString());			//创建表
        Log.i("cylconfirm", "数据库的表创建成功");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqldb, int arg1, int arg2) {
        //arg1为旧版本号		arg2为新版本号
        // TODO Auto-generated method stub
        String sql="SELECT * FROM sqlite_master where type=? and name=? ";
        Cursor tempcursor=sqldb.rawQuery(sql, new String[]{"table",this.TABLE_NAME});
        int count=tempcursor.getCount();
        Log.i("count",String.valueOf(count));
        if(count<=0)
        {
            this.onCreate(sqldb);
        }

    }
    public void onUpgrade() {
        //arg1为旧版本号		arg2为新版本号
        // TODO Auto-generated method stub
        SQLiteDatabase sqldb=this.getWritableDatabase(); //写句柄的个数是有限的
        String sql="SELECT * FROM sqlite_master where type=? and name=? ";
        Cursor tempcursor=sqldb.rawQuery(sql, new String[]{"table",this.TABLE_NAME});
        int count=tempcursor.getCount();
        Log.i("count",String.valueOf(count));
        if(count<=0)
        {
            this.onCreate(sqldb);
            this.insert("水壶", 1500, 1800,-15, -5);
            this.insert("风扇", 10, 35, -15, 0);
            this.insert("灯泡", 30, 50, -15, 0);
            this.insert("笔记本", 20, 50, -15, -5);
            this.insert("空调", 1500, 3000, -100,-20);
        }
        sqldb.close();
    }

    public void drop(SQLiteDatabase sqldb)
    {

        String sql="DROP TABLE IF EXISTS "+this.TABLE_NAME;
        sqldb.execSQL(sql);
    }

    public String match(double ap,double rp)
    {
        try{
            String matchedname=null;
            Cursor cursor=this.select(ap,rp);
            while(cursor.moveToNext())
            {
                matchedname=cursor.getString(0);
                break;
            }
            return matchedname;
        }
        catch(Exception e)
        {
            return null;
        }
    }
    public Cursor select(double ap,double rp)		//查找的时候根据获得的特征信息来找
    {
        //先得到一个读的句柄
        rp=rp-0.1;
        SQLiteDatabase db=this.getReadableDatabase();
        String whereclause=this.FIELD_aplow+"<? and "+this.FIELD_apup+">?"+
                " and "+this.FIELD_rplow+"<? and "+this.FIELD_rpup+">?";
        String selectionargs[]={String.valueOf(ap),String.valueOf(ap),
                String.valueOf(rp),String.valueOf(rp)};
        String columns[]={this.FIELD_NAME};
        Cursor cursor=db.query(this.TABLE_NAME,columns, whereclause,
                selectionargs, null, null, null);

        return cursor;
    }

    public Cursor select()		//查找的时候根据获得的特征信息来找
    {
        //先得到一个读的句柄
        SQLiteDatabase db=this.getReadableDatabase();

        String whereclause=null;
        String selectionargs[]=null;
        String columns[]=null;
        Cursor cursor=db.query(this.TABLE_NAME,columns, whereclause,
                selectionargs, null, null, null);

        return cursor;
    }
    public long  insert(String name,double aplow,double apup,double rplow,double rpup)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();		//存放一条记录
        cv.put(this.FIELD_NAME, name);
        cv.put(this.FIELD_aplow, aplow);
        cv.put(this.FIELD_apup, apup);
        cv.put(this.FIELD_rplow,rplow);
        cv.put(this.FIELD_rpup, rpup);
        long row=db.insert(this.TABLE_NAME, null, cv);

        return row;								//表示到目前为止有多少行条记录
    }

    public void delete(int id)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String whereClause=this.FIELD_ID+" =?";				//删除的时候要根据关键字来删除
        String []whereArgs={String.valueOf(id)};
        db.delete(this.TABLE_NAME, whereClause, whereArgs);

    }

    public void update(int id,String name,double aplow,double apup,double rplow,double rpup)
    {
        //要根据相应的关键字来找到要修改的记录
        SQLiteDatabase db=this.getWritableDatabase();
        String whereClause=this.FIELD_ID+" =?";				//更新的时候要根据关键字来找
        Log.i("更新的SQL语句",whereClause);
        String []whereArgs={String.valueOf(id)};
        Log.i("内容", whereArgs[0]);
        ContentValues cv=new ContentValues();
        cv.put(this.FIELD_NAME, name);
        cv.put(this.FIELD_aplow, aplow);
        cv.put(this.FIELD_apup, apup);
        cv.put(this.FIELD_rplow,rplow);
        cv.put(this.FIELD_rpup, rpup);
        db.update(this.TABLE_NAME, cv, whereClause, whereArgs);
    }

}
