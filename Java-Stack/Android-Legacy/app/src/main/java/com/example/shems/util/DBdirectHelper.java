package com.example.shems.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import com.example.smarthome.*;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * 从
 */
public class DBdirectHelper {
    private static final String DATABASE_PATH =  "/data/data/com.example.smarthome/databases";
    private static final int DATABASE_VERSION = 0;

    private static final String DATABASE_NAME = "testtest.db";

    private static String outFileName = DATABASE_PATH + "/" + DATABASE_NAME;

    private Context context;
    private SQLiteDatabase database;

    public SQLiteDatabase getDatabase() {
        return database;
    }

    public void setDatabase(SQLiteDatabase database) {
        this.database = database;
    }

    public DBdirectHelper(Context context) {
        this.context = context;
        File file = new File(outFileName);
        if (file.exists()) {
            database = SQLiteDatabase.openOrCreateDatabase(outFileName, null);
            if (database.getVersion() != DATABASE_VERSION) {
                database.close();
                file.delete();
            }
        }
        try {
            buildDatabase();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void buildDatabase() throws Exception{
        InputStream myInput = context.getResources().openRawResource(R.raw.testtest);
        File file = new File(outFileName);
        Log.i("dbpath", DATABASE_PATH);
        File dir = new File(DATABASE_PATH);
        if (!dir.exists()) {
            if (!dir.mkdir()) {
                Log.i("dbpath", DATABASE_PATH);
                throw new Exception("创建失败");
            }
        }

        if (!file.exists()) {
            try {
                OutputStream myOutput = new FileOutputStream(outFileName);

                byte[] buffer = new byte[1024];
                int length;
                while ((length = myInput.read(buffer))>0){
                    myOutput.write(buffer, 0, length);
                }
                myOutput.close();
                myInput.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}
