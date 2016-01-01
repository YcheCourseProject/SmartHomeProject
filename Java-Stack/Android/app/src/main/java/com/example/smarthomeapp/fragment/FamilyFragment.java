package com.example.smarthomeapp.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.activities.jiazhanpei.ShowFamilylineInfo;
import com.example.smarthomeapp.activities.xuhong.ApplianceActivity;
import com.example.smarthomeapp.activities.xuhong.UIMainActivity;
import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.model.LightSensorData;
import com.example.smarthomeapp.result_jzp.SensorQueryResolver;
import com.example.smarthomeapp.util.GsonUtil;
import com.example.smarthomeapp.views.NoScorllGridView;
import com.google.gson.Gson;
import com.example.smarthomeapp.adapter.jia.SensorGridviewAdapter;
import com.example.smarthomeapp.model.FlameSensorData;
import com.example.smarthomeapp.model.GasSensorData;
import com.example.smarthomeapp.model.PlrSensorData;
import com.example.smarthomeapp.model.TemperatureSensorData;
import com.example.smarthomeapp.model.jia.FlameSensorAdapter;
import com.example.smarthomeapp.model.jia.GasSensorAdapter;
import com.example.smarthomeapp.model.jia.LightSensorAdapter;
import com.example.smarthomeapp.model.jia.PlrSensorAdapter;
import com.example.smarthomeapp.model.jia.SensorDataAdapter;
import com.example.smarthomeapp.model.jia.TemperatureSensorAdapter;

import java.util.HashSet;


/**
 * A simple {@link Fragment} subclass.
 */
public class FamilyFragment extends Fragment {
    private static final int TIME = 5000;
    private boolean flag;
    UIMainActivity uiMainActivity;
    View view;
    NoScorllGridView inDoorGridVIew;
    NoScorllGridView outDoorGridView;
    BootstrapButton familylineToBootStrapButton;
    BootstrapButton devicecontrolToBootStrapButton;

    private SensorGridviewAdapter sensorGridviewAdapter;



    class MyHttpResultProcessListener<T> implements HttpResultProcessListener {
        Class<T> clas;

        HttpResultProcessListener httpResultProcessListener = new HttpResultProcessListener() {


        @Override
        public void processing(int status, String responsString) {
            Gson gson= GsonUtil.create();

            SensorDataAdapter[] sensorDataAdapters=sensorGridviewAdapter.getSensorDataAdapters();
            if(clas.equals(LightSensorData[].class)){
                ((LightSensorAdapter)sensorDataAdapters[1]).setLightSensorData(((LightSensorData[])gson.fromJson(responsString, clas))[0]);
            }else if(clas.equals(FlameSensorData[].class)){
                ((FlameSensorAdapter)sensorDataAdapters[4]).setFlameSensorData(((FlameSensorData[]) gson.fromJson(responsString, clas))[0]);
            }else if(clas.equals(PlrSensorData[].class)){
                ((PlrSensorAdapter)sensorDataAdapters[2]).setPlrSensorData(((PlrSensorData[]) gson.fromJson(responsString, clas))[0]);
            }else if(clas.equals(TemperatureSensorData[].class)){
                ((TemperatureSensorAdapter)sensorDataAdapters[0]).setTemperatureSensorData(((TemperatureSensorData[])  gson.fromJson(responsString, clas))[0]);
            }else if(clas.equals(GasSensorData[].class)){
                ((GasSensorAdapter)sensorDataAdapters[3]).setGasSensorData(((GasSensorData[]) gson.fromJson(responsString, clas))[0]);
            }

            sensorGridviewAdapter.notifyDataSetChanged();
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
                if (flag) {
                    handler.postDelayed(this, TIME);
                    ids = new HashSet<Integer>();
                    ids.add(1);
                    SensorQueryResolver.querylightSensorData(activity, ids,
                            new MyHttpResultProcessListener<LightSensorData[]>(LightSensorData[].class));
                    SensorQueryResolver.queryflameSensorData(activity, ids,
                            new MyHttpResultProcessListener<FlameSensorData[]>(FlameSensorData[].class));
                    SensorQueryResolver.querygasSensorData(activity, ids,
                            new MyHttpResultProcessListener<GasSensorData[]>(GasSensorData[].class));
                    SensorQueryResolver.queryplrSensorData(activity, ids,
                            new MyHttpResultProcessListener<PlrSensorData[]>(PlrSensorData[].class));
                    SensorQueryResolver.querytemperatureSensorData(activity,ids,
                            new MyHttpResultProcessListener<TemperatureSensorData[]>(TemperatureSensorData[].class));
                    SensorQueryResolver.querylightSensorData(activity, ids, new MyHttpResultProcessListener<LightSensorData[]>(LightSensorData[].class));
                    SensorQueryResolver.queryflameSensorData(activity, ids, new MyHttpResultProcessListener<FlameSensorData[]>(FlameSensorData[].class));
                    SensorQueryResolver.querygasSensorData(activity, ids, new MyHttpResultProcessListener<GasSensorData[]>(GasSensorData[].class));
                    SensorQueryResolver.queryplrSensorData(activity, ids, new MyHttpResultProcessListener<PlrSensorData[]>(PlrSensorData[].class));
                    SensorQueryResolver.querytemperatureSensorData(activity,ids,new MyHttpResultProcessListener<TemperatureSensorData[]>(TemperatureSensorData[].class));
                }
            } catch (Exception e) {
            }
        }
    };



        public MyHttpResultProcessListener(Class<T> clas) {
            this.clas = clas;
        }

        @Override
        public void processing(int status, String responsString) {
            Gson gson= GsonUtil.create();

            SensorDataAdapter[] sensorDataAdapters=sensorGridviewAdapter.getSensorDataAdapters();
            if(clas.equals(LightSensorData[].class)){
                ((LightSensorAdapter)sensorDataAdapters[1]).setLightSensorData(((LightSensorData[])gson.fromJson(responsString, clas))[0]);
            }else if(clas.equals(FlameSensorData[].class)){
                ((FlameSensorAdapter)sensorDataAdapters[4]).setFlameSensorData(((FlameSensorData[]) gson.fromJson(responsString, clas))[0]);
            }else if(clas.equals(PlrSensorData[].class)){
                ((PlrSensorAdapter)sensorDataAdapters[2]).setPlrSensorData(((PlrSensorData[]) gson.fromJson(responsString, clas))[0]);
            }else if(clas.equals(TemperatureSensorData[].class)){
                ((TemperatureSensorAdapter)sensorDataAdapters[0]).setTemperatureSensorData(((TemperatureSensorData[])  gson.fromJson(responsString, clas))[0]);
            }else if(clas.equals(GasSensorData[].class)){
                ((GasSensorAdapter)sensorDataAdapters[3]).setGasSensorData(((GasSensorData[]) gson.fromJson(responsString, clas))[0]);
            }

            sensorGridviewAdapter.notifyDataSetChanged();
        }
    }
    Activity activity;
    HashSet<Integer> ids;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // handler自带方法实现定时器
            try {
                if (flag) {
                    handler.postDelayed(this, TIME);
                    ids = new HashSet<Integer>();
                    ids.add(1);
                    SensorQueryResolver.querylightSensorData(activity, ids,
                            new MyHttpResultProcessListener<LightSensorData[]>(LightSensorData[].class));
                    SensorQueryResolver.queryflameSensorData(activity, ids,
                            new MyHttpResultProcessListener<FlameSensorData[]>(FlameSensorData[].class));
                    SensorQueryResolver.querygasSensorData(activity, ids,
                            new MyHttpResultProcessListener<GasSensorData[]>(GasSensorData[].class));
                    SensorQueryResolver.queryplrSensorData(activity, ids,
                            new MyHttpResultProcessListener<PlrSensorData[]>(PlrSensorData[].class));
                    SensorQueryResolver.querytemperatureSensorData(activity,ids,
                            new MyHttpResultProcessListener<TemperatureSensorData[]>(TemperatureSensorData[].class));
                }
            } catch (Exception e) {
            }
        }
    };




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        view = inflater.inflate(R.layout.fragment_family, container, false);
        inDoorGridVIew = (NoScorllGridView) view.findViewById(R.id.grid_view_home_environment);
        outDoorGridView = (NoScorllGridView) view.findViewById(R.id.grid_view_outdoor_environment);
        familylineToBootStrapButton = (BootstrapButton)view.findViewById(R.id.familyline_to_bootstrapbutton);
        devicecontrolToBootStrapButton = (BootstrapButton)view.findViewById(R.id.devicecontrol_to_bootstrapbutton);

        familylineToBootStrapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),ShowFamilylineInfo.class);
                startActivity(intent);
            }
        });

        devicecontrolToBootStrapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(getActivity(),ApplianceActivity.class);
                startActivity(intent);
            }
        });
        GridView gridView = (GridView) view.findViewById(R.id.grid_view_outdoor_environment);

        sensorGridviewAdapter = new SensorGridviewAdapter(this.getActivity(), null);
        sensorGridviewAdapter.setSensorDataAdapters(new SensorDataAdapter[5]);
                inDoorGridVIew.setAdapter(sensorGridviewAdapter);
        sensorGridviewAdapter.getSensorDataAdapters()[0]=new TemperatureSensorAdapter(this.getActivity(),null);
        sensorGridviewAdapter.getSensorDataAdapters()[1]=new LightSensorAdapter(this.getActivity(),null);
        sensorGridviewAdapter.getSensorDataAdapters()[2]=new PlrSensorAdapter(this.getActivity(),null);
        sensorGridviewAdapter.getSensorDataAdapters()[3]=new GasSensorAdapter(this.getActivity(),null);
        sensorGridviewAdapter.getSensorDataAdapters()[4]=new FlameSensorAdapter(this.getActivity(),null);

        outDoorGridView.setAdapter(sensorGridviewAdapter);
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        uiMainActivity = ((UIMainActivity) activity);
    }

    @Override
    public void onResume() {
        super.onResume();
        flag = true;
        handler.postDelayed(runnable, 0);
    }

    @Override
    public void onPause() {
        super.onPause();
        flag = false;
    }
}

