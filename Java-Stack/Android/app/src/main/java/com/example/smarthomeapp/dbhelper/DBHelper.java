package com.example.smarthomeapp.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by 家琪 on 2015/7/8.
 */
public abstract class DBHelper {

    protected DBManager dBManager;
    protected SQLiteDatabase database;

    public DBHelper(Context context){
        dBManager = new DBManager(context);
        database = dBManager.openDatabase();
        //database = SQLiteDatabase.openOrCreateDatabase(DBManger.DB_PATH + "/" + DBManger.DB_NAME, null);
        Log.e("DBHelper  constructor", "initial");
    }

    public abstract void add(Object obj);

    public abstract  void query() throws Exception;

    public abstract void delete();

}
