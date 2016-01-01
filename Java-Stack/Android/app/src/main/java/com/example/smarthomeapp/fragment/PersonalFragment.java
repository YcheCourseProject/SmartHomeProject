package com.example.smarthomeapp.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.activities.xuhong.UIMainActivity;
import com.example.smarthomeapp.adapter.EventExpandableAdapter;
import com.example.smarthomeapp.adapter.ShemsTxtImgTxtAdapter;
import com.example.smarthomeapp.model.che.AirConditioner;
import com.example.smarthomeapp.model.che.Device;
import com.example.smarthomeapp.model.che.DrinkerMachine;
import com.example.smarthomeapp.model.che.EventDetection;
import com.example.smarthomeapp.model.che.Lamp;
import com.example.smarthomeapp.model.che.WaterHeater;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class PersonalFragment extends Fragment {
    UIMainActivity uiMainActivity;
    private ExpandableListView expandableListView;
    private EventExpandableAdapter eventExpandableAdapter;
    private List<EventDetection> eventDetectionList;
    private Spinner spinner;
    private View view;
    private ListView socialListView;
    private ListView movingStatusListView;
    private ShemsTxtImgTxtAdapter socialAdapter;
    private ShemsTxtImgTxtAdapter movingStatusAdapter;


    public PersonalFragment() {
        // Required empty public constructor
    }

    private void findViews() {
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandable_view_event_detection);
        spinner = (Spinner) view.findViewById(R.id.spinner_event_time);
        socialListView= (ListView) view.findViewById(R.id.listview_social_status);
        movingStatusListView= (ListView) view.findViewById(R.id.listview_moving_status);
    }

    private void initListViewData() {
        eventDetectionList = new LinkedList<EventDetection>();
        Date date = new Date();
        date.setHours(6);
        date.setMinutes(48);
        String[] eventSources = new String[]{getString(R.string.hand_ring)};
        String eventDescription = getString(R.string.awake_description);
        String eventName = getString(R.string.simple_awake);
        List<Device> deviceList = new ArrayList<Device>();
        deviceList.add(new Lamp(this.getActivity()));
        EventDetection eventDetection = new EventDetection(this.getActivity(), date, eventSources, eventName, eventDescription, deviceList);
        eventDetectionList.add(0, eventDetection);

        date = new Date();
        date.setHours(8);
        date.setMinutes(10);
        eventSources = new String[]{getString(R.string.gps)};
        eventDescription = getString(R.string.go_out_home);
        eventName = getString(R.string.simple_go_out_home);
        deviceList = new ArrayList<Device>();
        deviceList.add(new AirConditioner(this.getActivity()));
        deviceList.add(new DrinkerMachine(this.getActivity()));
        eventDetection = new EventDetection(this.getActivity(), date, eventSources, eventName, eventDescription, deviceList);
        eventDetectionList.add(0, eventDetection);

        date = new Date();
        date.setHours(8);
        date.setMinutes(22);
        eventSources = new String[]{getString(R.string.gps), getString(R.string.api_bai_du_map)};
        eventDescription = getString(R.string.traffic_jam);
        eventName = getString(R.string.simple_traffic_jam);
        deviceList = new ArrayList<Device>();
        deviceList.add(new AirConditioner(this.getActivity()));
        deviceList.add(new DrinkerMachine(this.getActivity()));
        eventDetection = new EventDetection(this.getActivity(), date, eventSources, eventName, eventDescription, deviceList);
        eventDetectionList.add(0, eventDetection);

        date = new Date();
        date.setHours(11);
        date.setMinutes(32);
        eventSources = new String[]{getString(R.string.weibo)};
        eventDescription = getString(R.string.social_plan_sports);
        eventName = getString(R.string.simple_plan_sports);
        deviceList = new ArrayList<Device>();
        deviceList.add(new WaterHeater(this.getActivity()));
        eventDetection = new EventDetection(this.getActivity(), date, eventSources, eventName, eventDescription, deviceList);
        eventDetectionList.add(0, eventDetection);

        date = new Date();
        date.setHours(14);
        date.setMinutes(47);
        eventSources = new String[]{getString(R.string.hand_ring)};
        eventDescription = getString(R.string.play_sports);
        eventName = getString(R.string.simple_play_sports);
        deviceList = new ArrayList<Device>();
        deviceList.add(new WaterHeater(this.getActivity()));
        deviceList.add(new AirConditioner(this.getActivity()));
        eventDetection = new EventDetection(this.getActivity(), date, eventSources, eventName, eventDescription, deviceList);
        eventDetectionList.add(0, eventDetection);

    }

    private void setAdapters() {
        eventExpandableAdapter = new EventExpandableAdapter(eventDetectionList, this.getActivity());
        expandableListView.setAdapter(eventExpandableAdapter);
        String[] stringList = new String[]{"1h", "3h", "6h", "24h"};
        ArrayAdapter<String> myaAdapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, stringList);
        spinner.setAdapter(myaAdapter);
        String [] descriptionStrs=getActivity().getResources().getStringArray(R.array.social_status_array);
        Context context=getActivity();
        socialAdapter=new ShemsTxtImgTxtAdapter(getActivity(),descriptionStrs,new String[]{"1","2","3"},
                new Drawable[]{context.getResources().getDrawable(R.drawable.weibo),context.getResources().getDrawable(R.drawable.weixin),context.getResources().getDrawable(R.drawable.map)});
        socialListView.setAdapter(socialAdapter);
        socialListView.setDivider(null);
        descriptionStrs=getActivity().getResources().getStringArray(R.array.moving_status_array);
        movingStatusAdapter=new ShemsTxtImgTxtAdapter(getActivity(),descriptionStrs,new String[]{"1","2","3","4"},
                new Drawable[]{context.getResources().getDrawable(R.drawable.weibo),context.getResources().getDrawable(R.drawable.weixin),
                        context.getResources().getDrawable(R.drawable.temperature),context.getResources().getDrawable(R.drawable.map)});
        movingStatusListView.setAdapter(movingStatusAdapter);
        movingStatusListView.setDivider(null);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_personal, container, false);
        findViews();
        initListViewData();
        expandableListView.setGroupIndicator(null);
        setAdapters();
        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        uiMainActivity = ((UIMainActivity) activity);
    }



}
