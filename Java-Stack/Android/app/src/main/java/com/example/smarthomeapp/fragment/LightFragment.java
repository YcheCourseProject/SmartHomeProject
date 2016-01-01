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
import com.example.smarthomeapp.adapter.LightFragmentAdapter;
import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.model.Lamp;
import com.example.smarthomeapp.model.LampStatus;
import com.example.smarthomeapp.result_jzp.AppliancesQueryResolver;
import com.example.smarthomeapp.result_jzp.AppliancesUpdateResolver;
import com.example.smarthomeapp.util.GsonUtil;
import com.google.gson.Gson;

import java.util.HashSet;

/**
 * A simple {@link Fragment} subclass.
 */
public class LightFragment extends Fragment {
    private static final int TIME = 5000;
    private boolean flag;
    View view;
    ListView listView;
    LightFragmentAdapter lightFragmentAdapter;

    public LightFragment() {

    }
    HttpResultProcessListener httpResultProcessListener = new HttpResultProcessListener() {
        @Override
        public void processing(int status, String responsString) {
            Gson gson = GsonUtil.create();
            LampStatus[] lampStatuses = gson.fromJson(responsString
                    , LampStatus[].class);
            lightFragmentAdapter.setLightStatusesArray(lampStatuses);
            lightFragmentAdapter.notifyDataSetChanged();

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
                    ids.add(3);
                    AppliancesQueryResolver.queryLampStatusArray(
                        activity,ids,httpResultProcessListener);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };
    private void findViews() {
       /* LampStatus lampStatus = new LampStatus();
        LampStatus[] lampStatuses = new LampStatus[3];
        lampStatuses[0] = lampStatus;
        lampStatuses[0].setLampStatus(0);
        Lamp lamp = new Lamp();
        lamp.setLampId(1);
        lampStatuses[0].setLamp(lamp);

        lampStatus = new LampStatus();
        lampStatuses[1] = lampStatus;
        lampStatuses[1].setLampStatus(0);
        lamp = new Lamp();
        lamp.setLampId(2);
        lampStatuses[1].setLamp(lamp);

        lampStatus = new LampStatus();
        lampStatuses[2] = lampStatus;
        lampStatuses[2].setLampStatus(0);
        lamp = new Lamp();
        lamp.setLampId(3);
        lampStatuses[2].setLamp(lamp);
*/
        listView = (ListView) view.findViewById(R.id.list_light);
      //  lightFragmentAdapter = new LightFragmentAdapter(this.getActivity(), ,new boolean[]{true,true,true});
      //  listView.setAdapter(lightFragmentAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_light, container, false);
        findViews();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        flag = true;
        handler.postDelayed(runnable,0);
        lightFragmentAdapter = new LightFragmentAdapter(this.getActivity(),
                null,new boolean[]{true,true,true});
        listView.setAdapter(lightFragmentAdapter);

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
