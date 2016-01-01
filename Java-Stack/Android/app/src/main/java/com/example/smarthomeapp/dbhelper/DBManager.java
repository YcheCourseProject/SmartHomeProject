package com.example.smarthomeapp.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

import com.example.smarthomeapp.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by 家琪 on 2015/7/8.
 */
public class DBManager {

    private static String TAG0 = "DBManager";

    private final int BUFFER_SIZE=40000;
    public static final String DB_NAME = "smart_home_app.db";
    public static final String PACKAGE_NAME="com.example.smarthomeapp";
    public static final String DB_PATH ="/data"
            + Environment.getDataDirectory().getAbsolutePath()+"/"
            +PACKAGE_NAME;
    private SQLiteDatabase database;
    private Context context;

    public DBManager(Context context){
        this.context=context;
    }

    public SQLiteDatabase getDatabase(){
        return database;
    }

    public void setDatabase(SQLiteDatabase database){
        this.database=database;
    }

    public SQLiteDatabase openDatabase(){
        System.out.println(DB_PATH+"/"+DB_NAME);
        this.database = this.openDatabase(DB_PATH + "/" + DB_NAME);
        Log.d(TAG0, "initialDatabase");
        return database;

    }
    private SQLiteDatabase openDatabase(String dbfile){
        try{
            if(!(new File(dbfile).exists())){
                InputStream is=this.context.getResources().openRawResource(
                        R.raw.smart_home_app);
                FileOutputStream fos = new FileOutputStream(dbfile);
                byte[] buffer = new byte[BUFFER_SIZE];
                int count = 0;
                while ((count = is.read(buffer))>0){
                    fos.write(buffer,0,count);
                }
                fos.close();
                is.close();

            }
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,null);
            Log.d(TAG0, "initialDatabase");
            return db;
        }catch (FileNotFoundException e) {
            Log.d(TAG0,"File is not found");
            e.printStackTrace();
        }catch (IOException e){
            Log.d(TAG0,"IO exception");
            e.printStackTrace();
        }
        return null;
    }
    public void closeDatabase(){

        this.database.close();
        Log.d(TAG0, "close Database");
    }

}
