package com.example.smarthomeapp.activities;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.activities.jiazhanpei.SetPreference;
import com.example.smarthomeapp.activities.jiazhanpei.ShowEnvironmentData;

import com.example.smarthomeapp.activities.jiazhanpei.ShowFamilylineInfo;
import com.example.smarthomeapp.activities.xuhong.ApplianceActivity;
import com.example.smarthomeapp.activities.xuhong.ContrastElectricityActivity;
import com.example.smarthomeapp.activities.xuhong.StatisticComplaindegreeActivity;
import com.example.smarthomeapp.activities.xuhong.ApplianceActivity;
import com.example.smarthomeapp.activities.xuhong.ContrastElectricityActivity;
import com.example.smarthomeapp.activities.xuhong.StatisticComplaindegreeActivity;
import com.example.smarthomeapp.activities.xuhong.ApplianceActivity;
import com.example.smarthomeapp.activities.xuhong.ContrastElectricityActivity;
import com.example.smarthomeapp.activities.xuhong.StatisticComplaindegreeActivity;
import com.example.smarthomeapp.activities.xuhong.UIMainActivity;

import java.util.HashMap;
import java.util.Map;


/**
 * 用来测试各个activity功能用的
 * @author Jingkai Tang
 * @version 1.0
 * @email jingkaitang@gmail.com
 * @date 2015.04.10 19:11
 */
public class MainListViewActivity extends ListActivity {

    protected String[] items;
    protected Map<String, Intent> intents;


    protected void initIntentsMap() {
        intents = new HashMap<String, Intent>();
		/*
         * 注意： 各位写的功能使用this.putIntents(RSID, CLS)添加
		 * 参数RSID代表要在启动活动中显示的名称，CLS是你写的活动的类 参照如下例子
		 */
        this.putIntents(R.string.title_activity_gps_band, MainActivity.class);
        this.putIntents(R.string.title_activity_social, WBStatusAPIActivity.class);
        this.putIntents(R.string.title_activity_service_test, ServiceTestActivity.class);
        this.putIntents(R.string.applicant_airconditioner, ApplianceActivity.class);
        this.putIntents(R.string.title_activity_statistic__complaindegree, StatisticComplaindegreeActivity.class);
        this.putIntents(R.string.actvity_contrast_electricity,ContrastElectricityActivity.class);
        this.putIntents(R.string.title_activity_ui__main, UIMainActivity.class);
        this.putIntents(R.string.title_preference, SetPreference.class);
        this.putIntents(R.string.title_gridview, ShowEnvironmentData.class);
        this.putIntents(R.string.title_activity_service_test, ServiceTestActivity.class);
        this.putIntents(R.string.title_activity_ui__main, UIMainActivity.class);
        this.putIntents(R.string.title_familyline, ShowFamilylineInfo.class);
  }



    protected void putIntents(int rsid, Class<?> cls) {
        intents.put(getString(rsid), new Intent(this, cls));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview);
        initIntentsMap();
        items = intents.keySet().toArray(new String[0]);
        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        startActivity(intents.get(items[position]));
    }
}
