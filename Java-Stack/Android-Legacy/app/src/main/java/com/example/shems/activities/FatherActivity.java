package com.example.shems.activities;



import com.example.shems.daos.meters.Connector;
import com.example.shems.daos.meters.Meter;
import com.example.smarthome.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

/**
 * this is the father for all the activities
 *
 * @author Owen
 *
 */
public class FatherActivity extends Activity {

    Connector connector = null;
    Meter meter = null;

    private long mExitTime;// 按下屏幕退出键的时间点

//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            if ((System.currentTimeMillis() - mExitTime) > 2000) {
//                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
//                mExitTime = System.currentTimeMillis();
//
//            } else {
//                connector = ((ASGApplication)getApplication()).getConnector();
//                //meter = ((ASGApplication)getApplication()).getMeter();
//                if(connector != null){
//                    connector.disconnect();
//                    ((ASGApplication)getApplication()).setConnector(null);
//                }
//                System.exit(0);
//            }
//            return true;
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_father);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_father, menu);
        return true;
    }
}
