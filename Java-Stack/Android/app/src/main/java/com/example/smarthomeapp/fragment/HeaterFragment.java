package com.example.smarthomeapp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.adapter.HeaterFragmentAdapter;
import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.model.WaterHeater;
import com.example.smarthomeapp.model.WaterHeaterStatus;
import com.example.smarthomeapp.util.GsonUtil;
import com.google.gson.Gson;

/**
 * A simple {@link Fragment} subclass.
 */
public class HeaterFragment extends Fragment {
    private static final int TIME=5000;
    private boolean flag;
    ExpandableListView expandableListView;
    View view;
    HeaterFragmentAdapter heaterFragmentAdapter;

    public HeaterFragment() {
        // Required empty public constructor
    }

    HttpResultProcessListener httpResultProcessListener = new HttpResultProcessListener() {
        @Override
        public void processing(int status, String responsString) {
            Gson gson = GsonUtil.create();
            WaterHeaterStatus[] waterHeaterStatuses = gson.fromJson(responsString,
                    WaterHeaterStatus[].class);

        }
    };
private void findViews(){
    expandableListView=(ExpandableListView) view.findViewById(R.id.expandlist_heater);

    WaterHeaterStatus waterHeaterStatuses[]=new WaterHeaterStatus[2];
    WaterHeaterStatus waterHeaterStatus=new WaterHeaterStatus();
    WaterHeater waterHeater=new WaterHeater();
    waterHeater.setWaterHeaterId(1);
    waterHeaterStatus.setWaterHeater(waterHeater);
    waterHeaterStatus.setWaterHeaterTemperature(50f);
    waterHeaterStatuses[0]=waterHeaterStatus;

     waterHeaterStatus=new WaterHeaterStatus();
     waterHeater=new WaterHeater();
    waterHeater.setWaterHeaterId(2);
    waterHeaterStatus.setWaterHeater(waterHeater);
    waterHeaterStatus.setWaterHeaterTemperature(70f);
    waterHeaterStatuses[1]=waterHeaterStatus;

    heaterFragmentAdapter=new HeaterFragmentAdapter(this.getActivity(),waterHeaterStatuses,new boolean[]{true,true});
    expandableListView.setAdapter(heaterFragmentAdapter);
    expandableListView.setGroupIndicator(null);
}
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_heater, container, false);
        findViews();
        // Inflate the layout for this fragment
        return view;
    }


}
