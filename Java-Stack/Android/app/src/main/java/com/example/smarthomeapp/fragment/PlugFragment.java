package com.example.smarthomeapp.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.adapter.PlugFragmentAdapter;
import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.model.SheSwitchStatus;
import com.example.smarthomeapp.result_jzp.AppliancesQueryResolver;
import com.example.smarthomeapp.util.GsonUtil;
import com.google.gson.Gson;

import java.util.HashSet;


/**
 * A simple {@link Fragment} subclass.
 */

public class PlugFragment extends Fragment {
    private static final int TIME = 5000;
    private boolean flag;
    View view;
    ListView listView;
    PlugFragmentAdapter plugFragmentAdapter;

    public PlugFragment() {

    }
    HttpResultProcessListener httpResultProcessListener = new HttpResultProcessListener() {
        @Override
        public void processing(int status, String responsString) {
            Gson gson = GsonUtil.create();
            SheSwitchStatus[] sheSwitchStatuses = gson.fromJson(responsString,
                    SheSwitchStatus[].class);
            plugFragmentAdapter.setSheSwitchStatus(sheSwitchStatuses);
            plugFragmentAdapter.notifyDataSetChanged();
        }
    };
    Activity activity;
    HashSet<Integer> ids;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try{
                if(flag){
                    handler.postDelayed(this,TIME);
                    ids = new HashSet<Integer>();
                    ids.add(1);
                    ids.add(2);
                    AppliancesQueryResolver.querySheSwitchStatusArray(
                            activity, ids, httpResultProcessListener);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };
    private void findViews() {
        /*SheSwitchStatus sheSwitchStatus = new SheSwitchStatus();
        SheSwitchStatus sheSwitchStatuses[] = new SheSwitchStatus[3];
        sheSwitchStatuses[0] = sheSwitchStatus;
        sheSwitchStatuses[0].setSheSwitchStatus(false);
        SheSwitch sheSwitch = new SheSwitch();
        sheSwitch.setSheSwitchId(1);
        sheSwitchStatus.setSheSwitch(sheSwitch);

        sheSwitchStatus = new SheSwitchStatus();
        sheSwitchStatuses[1] = sheSwitchStatus;
        sheSwitchStatuses[1].setSheSwitchStatus(false);
        sheSwitch = new SheSwitch();
        sheSwitch.setSheSwitchId(2);
        sheSwitchStatuses[1].setSheSwitch(sheSwitch);

        sheSwitchStatus = new SheSwitchStatus();
        sheSwitchStatuses[2] = sheSwitchStatus;
        sheSwitchStatuses[2].setSheSwitchStatus(false);
        sheSwitch = new SheSwitch();
        sheSwitch.setSheSwitchId(3);
        sheSwitchStatuses[2].setSheSwitch(sheSwitch);*/

        listView = (ListView) view.findViewById(R.id.list_plug);
        //plugFragmentAdapter = new PlugFragmentAdapter(this.getActivity(), sheSwitchStatuses);
        //listView.setAdapter(plugFragmentAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_plug, container, false);
        findViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        flag = true;
        handler.postDelayed(runnable,0);
        plugFragmentAdapter = new PlugFragmentAdapter(this.getActivity(), null,new boolean[]{true,true});
        listView.setAdapter(plugFragmentAdapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        flag = false;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.activity = activity;
    }
}
