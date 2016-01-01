package com.example.smarthomeapp.sqllite;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MyDBhelper extends SQLiteOpenHelper {
	public static final String DATABASENAME = "eventrecord.db";
	public static final int DB_VERSION = 1;
	private static final String TABLENAME = "socialrecord";
	private Context context;
	public MyDBhelper(Context context) {
		super(context, DATABASENAME, null, DB_VERSION);
		this.context=context;
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		final String CREATE_TABLE_SQL = "CREATE TABLE socialrecord ([startTime]  TEXT NOT NULL unique,[endTime] ,[messageId] TEXT NOT NULL,[messageContent] TEXT);";
		db.execSQL(CREATE_TABLE_SQL);
		Toast.makeText(this.context, "create_success", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		final String DROP_TABLE_SQL = "DROP TABLE if exists socialrecord;";
		db.execSQL(DROP_TABLE_SQL);
		final String CREATE_TABLE_SQL = "CREATE TABLE socialrecord ([startTime] TEXT NOT NULL unique ,[endTime] ,[messageId] TEXT NOT NULL,[messageContent] TEXT);";
		db.execSQL(CREATE_TABLE_SQL);
		Toast.makeText(this.context, "update_success", Toast.LENGTH_SHORT).show();
	}
}
// public static final int DB_VERSION=5;
// final String
// CREATE_TABLE_SQL="CREATE TABLE [socialrecord] ([text]  TEXT NOT NULL,[time]  TEXT NOT NULL,[username]  TEXT,PRIMARY KEY ([time] ASC));";
// final String
// CREATE_TABLE_SQL2="CREATE TABLE [key_event] ([event]  TEXT NOT NULL,[description]  TEXT,[key]  TEXT);";
// final String
// INSERT_SQL="INSERT INTO [key_event] VALUES ('打篮球', '改变空调、热水器需求，时间', '篮球');";
// final String
// INSERT_SQL2="INSERT INTO [key_event] VALUES ('心情不好', '改变空调需求', '心情好差');";
// final String
// INSERT_SQL3="INSERT INTO [key_event] VALUES ('回家', '改变时间', '回家');";
// final String
// INSERT_SQL4="INSERT INTO [key_event] VALUES ('聚餐', '改变时间', '聚餐');";
// final String
// CREATE_TABLE_SQL3="CREATE TABLE [time_event] ([event]  TEXT,[timestamp]  TEXT NOT NULL,[_id] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL);";
// final String DROP_TABLE_SQL="DROP TABLE IF EXISTS [time_event];";
// final String
// CREATE_TABLE_SQL4="CREATE TABLE [gps_data] ([timestamp]  INTEGER NOT NULL,[longitude]  REAL,[latitude]  REAL,[speed]  REAL,[_id]  INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL);";
// public MyDBhelper(Context context, String name, CursorFactory factory,
// int version) {
// super(context, name, factory, version);
// // TODO Auto-generated constructor stub
// }
//
// @Override
// public void onCreate(SQLiteDatabase db) {
// // TODO Auto-generated method stub
// //第一次使用数据库时，自动建表
// db.execSQL(CREATE_TABLE_SQL);
// db.execSQL(CREATE_TABLE_SQL2);
// db.execSQL(CREATE_TABLE_SQL3);
// db.execSQL(INSERT_SQL);
// db.execSQL(INSERT_SQL2);
// db.execSQL(INSERT_SQL3);
// db.execSQL(INSERT_SQL4);
// db.execSQL(CREATE_TABLE_SQL4);
// }
//
// @Override
// public void onUpgrade(SQLiteDatabase db, int versionOld, int versionNew) {
// // TODO Auto-generated method stub
// // if(versionNew==2)
// // {
// // db.execSQL(CREATE_TABLE_SQL2);
// // db.execSQL(CREATE_TABLE_SQL3);
// // db.execSQL(INSERT_SQL);
// // }
// //// else
// // db.execSQL(INSERT_SQL2);
// // db.execSQL(INSERT_SQL3);
// // db.execSQL(INSERT_SQL4);
// // db.execSQL(DROP_TABLE_SQL);
// // db.execSQL(CREATE_TABLE_SQL3);
// db.execSQL(CREATE_TABLE_SQL4);
// }
//
