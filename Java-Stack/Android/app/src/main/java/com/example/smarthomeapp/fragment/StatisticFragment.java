package com.example.smarthomeapp.fragment;


import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.activities.xuhong.UIMainActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatisticFragment extends Fragment {
    UIMainActivity uiMainActivity;

    public StatisticFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistic, container, false);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        uiMainActivity= ((UIMainActivity)activity);
    }



}
