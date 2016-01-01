package com.example.smarthomeapp.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;


import com.example.smarthomeapp.R;
import com.example.smarthomeapp.adapter.AirconditionerFragmentAdapter;
import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.model.AirConditionStatus;
import com.example.smarthomeapp.result_jzp.AppliancesQueryResolver;
import com.example.smarthomeapp.util.GsonUtil;
import com.google.gson.Gson;

import java.util.HashSet;


/**
 * A simple {@link Fragment} subclass.
 */

public class AirconditionerFrgament extends Fragment{
    private static final int TIME=5000;
    private boolean flag;
    ExpandableListView expandableListView;
    View view;
    HttpResultProcessListener httpResultProcessListener = new HttpResultProcessListener() {
        @Override
        public void processing(int status, String responsString) {
            Gson gson= GsonUtil.create();
            AirConditionStatus[] airConditionStatuses=gson.fromJson(responsString, AirConditionStatus[].class);
            airconditionerFragmentAdapter.setAirConditionStatusArray(airConditionStatuses);
            airconditionerFragmentAdapter.notifyDataSetChanged();
        }
    };
    Activity activity;
    HashSet<Integer> ids;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                if(flag)
                {
                    handler.postDelayed(this, TIME);
                    ids=new HashSet<Integer>();
                    ids.add(1);
                    ids.add(2);
                    AppliancesQueryResolver.queryAirConditionStatusArray(activity,ids,httpResultProcessListener);
                }
            } catch (Exception e) {
            }
        }
    };
    AirconditionerFragmentAdapter airconditionerFragmentAdapter;
    public AirconditionerFrgament() {
        // Required empty public constructor
    }

    private void findViews() {
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandlist_aircondition);
//        AirConditionStatus[] airConditionStatuses=new AirConditionStatus[2];
//
//        AirConditionStatus airConditionStatus=new AirConditionStatus();
//        airConditionStatus.setAirConditionMode(1);//
//        AirCondition airCondition=new AirCondition();
//        airCondition.setAirConditionId(1);
//        airConditionStatus.setAirCondition(airCondition);
//        airConditionStatus.setAirConditionTemperature(10f);
//        airConditionStatuses[0]=airConditionStatus;
//
//        airConditionStatus=new AirConditionStatus();
//        airConditionStatus.setAirConditionMode(2);
//        airCondition=new AirCondition();
//        airCondition.setAirConditionId(2);
//        airConditionStatus.setAirCondition(airCondition);
//        airConditionStatus.setAirConditionTemperature(15f);
//        airConditionStatuses[1]=airConditionStatus;


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_airconditioner, container, false);
        findViews();

        return view;
    }


    @Override
    public void onResume() {
        super.onResume();
       // timer.schedule(task,2000,10000);
        flag=true;
        handler.postDelayed(runnable, 0); //每隔5s执行
        airconditionerFragmentAdapter=new AirconditionerFragmentAdapter(this.getActivity(),null,new boolean[]{true,true});
        expandableListView.setAdapter(airconditionerFragmentAdapter);
        expandableListView.setGroupIndicator(null);
    }

    @Override
    public void onPause() {
        super.onPause();
        flag=false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity=activity;
    }
}
