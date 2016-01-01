package com.example.smarthomeapp;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.test.ApplicationTestCase;

import com.example.smarthomeapp.result_jzp.AppliancesQueryResolver;

import java.util.HashSet;

/**
 * Created by 卧龙风 on 2015/7/24.
 */
public class QueryApplianceResolver extends ApplicationTestCase<Application> {
    public QueryApplianceResolver() {
        super(Application.class);
    }

    public void testLampStatusInfo() {
        Context context = getContext();
        HashSet<Integer> idSet = new HashSet<Integer>();
        idSet.add(1);
        idSet.add(2);
        AppliancesQueryResolver testLampStatus = new AppliancesQueryResolver();
        testLampStatus.queryLampStatusArray(context, idSet, testLampStatus);
        Looper.loop();
    }

    public void testCurtainStatusInfo() {
        Context context = getContext();
        HashSet<Integer> idSet = new HashSet<Integer>();
        idSet.add(1);
        idSet.add(2);
        AppliancesQueryResolver testCurtainStatus = new AppliancesQueryResolver();
        testCurtainStatus.queryCurtainStatusArray(context, idSet, testCurtainStatus);
        Looper.loop();
    }

    public void testSheSwitchStatusInfo() {
        Context context = getContext();
        HashSet<Integer> idSet = new HashSet<Integer>();
        idSet.add(1);
        idSet.add(2);
        AppliancesQueryResolver testSheSwitchStatus = new AppliancesQueryResolver();
        testSheSwitchStatus.querySheSwitchStatusArray(context, idSet, testSheSwitchStatus);
        Looper.loop();
    }

    public void testAirConditionStatusInfo() {
        Context context = getContext();
        HashSet<Integer> idSet = new HashSet<Integer>();
        idSet.add(1);
        idSet.add(2);
        AppliancesQueryResolver testAirConditionStatus = new AppliancesQueryResolver();
        testAirConditionStatus.queryAirConditionStatusArray(context, idSet, testAirConditionStatus);
        Looper.loop();
    }

    public void testWaterHeaterStatusInfo() {
        Context context = getContext();
        HashSet<Integer> idSet = new HashSet<Integer>();
        idSet.add(1);
        idSet.add(2);
        AppliancesQueryResolver testWaterHeaterStatus = new AppliancesQueryResolver();
        testWaterHeaterStatus.queryWaterHeaterStatusArray(context, idSet, testWaterHeaterStatus);
        Looper.loop();
    }



}
