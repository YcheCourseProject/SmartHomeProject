package com.example.shems.tmp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.shems.activities.ui.ElectricalStatisticsActivity;
import com.example.shems.activities.ui.ElectricalStatisticsActivity2;
import com.example.shems.activities.ui.EventDetectionActivity;
import com.example.shems.activities.ui.LoginActivity;
import com.example.smarthome.R;

import java.util.HashMap;
import java.util.Map;

public class MainListActivity extends ListActivity {

    protected String[] items;
    protected Map<String, Intent> intents;

    protected void initIntentsMap() {
        intents = new HashMap<String, Intent>();

		/*
         * 注意： 各位写的功能使用this.putIntents(RSID, CLS)添加
		 * 参数RSID代表要在启动活动中显示的名称，CLS是你写的活动的类 参照如下例子
		 */
        putIntents(ElectricalStatisticsActivity.class);
        putIntents(EventDetectionActivity.class);
        putIntents(LoginActivity.class);
        putIntents(ElectricalStatisticsActivity2.class);
//        putIntents(ACCtrlActivity.class);
//        putIntents(LoadMonitoringActivity.class);
//        putIntents(LocationACQActivity.class);
//        putIntents(CtrlMainActivity.class);
//        putIntents(SchedulingAdviceActivity.class);
//        putIntents(SWCtrlActivity.class);
//        putIntents(TmpHumAcqActivity.class);
//        putIntents(ACScheduleDetailChartActivity.class);
//        putIntents(AirConditionerActivity.class);
//        putIntents(ConnectingActivity.class);
//        putIntents(GetRealTimePriceActivity.class);
//        putIntents(HBAChartActivity.class);
//        putIntents(HisAChartActivity.class);
//        putIntents(LoadCheckActivity.class);
//        putIntents(LoadListActivity.class);
//        putIntents(LoadListActivity.class);
//        putIntents(LogEventShowActivity.class);
//        putIntents(MainActivity.class);
//        putIntents(MenuActivity.class);
//        putIntents(ModifyParaActivity.class);
//        putIntents(ModifySQLDataActivity.class);
//        putIntents(OptimizeActivity.class);
//        putIntents(ScanActivity.class);
//        putIntents(ShowLogActivity.class);
//        putIntents(ShowSQLActivity.class);
//        putIntents(WaterHeaterOptimzieActivity.class);
//        putIntents(WHActivity.class);

    }

    protected void putIntents(Class<?>cls){
        intents.put(cls.getSimpleName(),new Intent(this,cls));
    }

    protected void putIntents(int rsid, Class<?> cls) {
        intents.put(getString(rsid), new Intent(this, cls));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_list);
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
