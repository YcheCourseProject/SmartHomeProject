package com.example.smarthomeapp.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.adapter.jia.CurtainFragmentAdapter;
import com.example.smarthomeapp.listener.HttpResultProcessListener;
import com.example.smarthomeapp.model.CurtainStatus;
import com.example.smarthomeapp.result_jzp.AppliancesQueryResolver;
import com.example.smarthomeapp.util.GsonUtil;
import com.example.smarthomeapp.views.NoScorllGridView;
import com.google.gson.Gson;

import java.util.HashSet;


/**
 * A simple {@link Fragment} subclass.
 */
public class CurtainFragment extends Fragment {
    private static final int TIME = 5000;
    private boolean flag;
    View view;
    NoScorllGridView gridView;
    CurtainFragmentAdapter curtainFragmentAdapter;;
    public CurtainFragment() {
        // Required empty public constructor
    }
    HttpResultProcessListener httpResultProcessListener = new HttpResultProcessListener() {
        @Override
        public void processing(int status, String responsString) {
            Gson gson = GsonUtil.create();
            CurtainStatus[] curtainStatuses = gson.fromJson(responsString,CurtainStatus[].class);
            curtainFragmentAdapter.setCurtainStatuses(curtainStatuses);
            curtainFragmentAdapter.notifyDataSetChanged();
        }
    };

    Activity activity;
    HashSet<Integer> ids;
    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                if (flag) {
                    handler.postDelayed(this, TIME);
                    ids = new HashSet<Integer>();
                    ids.add(1);
                    ids.add(2);
                    AppliancesQueryResolver.queryCurtainStatusArray(activity, ids, httpResultProcessListener);
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_curtain, container, false);
        gridView = (NoScorllGridView)view.findViewById(R.id.list_curtain);
        return view;
}

    @Override
    public void onResume() {
        super.onResume();
        flag = true;
        handler.postDelayed(runnable,0);
        curtainFragmentAdapter = new CurtainFragmentAdapter(this.getActivity()
            ,null,new boolean[]{true,true});
        gridView.setAdapter(curtainFragmentAdapter);

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
