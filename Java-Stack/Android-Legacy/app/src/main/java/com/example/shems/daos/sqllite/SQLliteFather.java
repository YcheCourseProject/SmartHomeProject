package com.example.shems.daos.sqllite;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//所有的要处理sqlite数据库中的某个表的类 ，都要继承这个类
public class SQLliteFather extends SQLiteOpenHelper {
    // 构造方法 要传进来参数context
    public SQLliteFather(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.i("sqllitefather", this.DATABASE_NAME);
        this.context = context;
        // Context是为了能够连接到数据库
        // 根据相对的data/data/asg/database的路径 对应的canvas
    }

    // 上下文
    private Context context;

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    // 表名和栏目名
    private String tablename;
    private String cols[];

    public String getTablename() {
        return tablename;
    }

    public void setTablename(String tablename) {
        this.tablename = tablename;
    }

    public String[] getCols() {
        return cols;
    }

    public void setCols(String[] cols) {
        this.cols = cols;
    }

    // 静态常量：
    public static String DATABASE_NAME = "asg_db"; // sql数据库的名字
    public static int DATABASE_VERSION = 1; // sql数据库的版本信息

    // 有关数据库的实例的：
    private SQLiteDatabase db;

    public SQLiteDatabase getDb() {
        return db;
    }

    public void setDb(SQLiteDatabase db) {
        this.db = db;
    }

    // 连接和断开：
    public void open() throws SQLiteException {
        if (this.getDb() == null) {
            try {
                db = this.getWritableDatabase();
                Log.i("连接到数据库", "connect db ok");
            } catch (SQLiteException ex) {
                db = this.getReadableDatabase();
                Log.i("连接到数据库", "connect db fail");
            }
        }
    }
    public void close() {
        if (db != null)
        {
            Log.i("关闭数据库","关闭数据库成功");
            db.close();
        }
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub

    }

    /**
     * 版本号在asg中没有用上 function:看是否有某个表 没有就要重新创建 (non-Javadoc)
     *
     * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase,
     *      int, int)
     */

    public void onUpgrade() {
        // TODO Auto-generated method stub
        // arg1为旧版本号 arg2为新版本号
        // TODO Auto-generated method stub
        this.open();
        String sql = "SELECT * FROM sqlite_master where type=? and name=? ";// 先看表是否存在
        Cursor tempcursor = db.rawQuery(sql, new String[] { "table",
                this.tablename });
        int count = tempcursor.getCount();
        Log.i("count", String.valueOf(count));
        if (count <= 0) {
            this.onCreate(db);
            Log.i("create table" + this.tablename, "success");
        }
    }

    public void drop() {
        this.open();
        String sql = "DROP TABLE IF EXISTS " + this.tablename;
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }
}
