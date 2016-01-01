package com.example.smarthomeapp.activities.jiazhanpei;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.example.smarthomeapp.R;

import java.util.ArrayList;
import java.util.HashMap;


/**
 * Created by 卧龙风 on 2015/7/20.
 */
public class ShowEnvironmentData extends Activity{
    private int[] image={
            R.drawable.light,R.drawable.air,
            R.drawable.fire,R.drawable.humidity,
            R.drawable.temperature_measure,R.drawable.human
    };
    private String[] type = {"光照","天然气","火警系数",
            "湿度","温度","在家状态"};
    private String[] value = {"1","2","3",
            "4","5","6"};

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.activity_ui_environment);
        GridView gridView = (GridView)findViewById(R.id.gridview_environment);
        ArrayList<HashMap<String,Object>> imagelist = new
                ArrayList<HashMap<String,Object>>();
        for(int i=0;i<6;i++){
            HashMap<String,Object> map = new HashMap<String,Object>();
            map.put("image",image[i]);
            map.put("type",type[i]);
            map.put("value",value[i]);
            imagelist.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,
                imagelist,R.layout.environment_item,
                new String[]{"image","type","value"},
                new int[]{R.id.item_image_environment,R.id.item_environment_type,R.id.item_environment_showdata});
        gridView.setAdapter(simpleAdapter);
    }

 }
