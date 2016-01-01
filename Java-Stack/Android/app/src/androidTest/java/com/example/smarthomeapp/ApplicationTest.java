package com.example.smarthomeapp;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.test.ApplicationTestCase;

import com.example.smarthomeapp.model.AirCondition;
import com.example.smarthomeapp.model.AirConditionStatus;
import com.example.smarthomeapp.model.Curtain;
import com.example.smarthomeapp.model.CurtainStatus;
import com.example.smarthomeapp.model.Lamp;
import com.example.smarthomeapp.model.LampStatus;
import com.example.smarthomeapp.model.SheSwitch;
import com.example.smarthomeapp.model.SheSwitchStatus;
import com.example.smarthomeapp.model.WaterHeater;
import com.example.smarthomeapp.model.WaterHeaterStatus;
import com.example.smarthomeapp.result_jzp.AppliancesUpdateResolver;

import java.sql.Timestamp;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }

    public void testSaveLampInfo(){
        Context context=getContext();
        int lampid = 1;
        LampStatus lampStatus = new LampStatus();
        lampStatus.setIsControlledByUser(true);
        lampStatus.setLampStatus(2);
        Lamp lamp = new Lamp();
        lamp.setLampId(lampid);
        lampStatus.setLamp(lamp);
        lampStatus.setLampStatusRecordTime(new Timestamp(System.currentTimeMillis()));

        AppliancesUpdateResolver testLampData = new AppliancesUpdateResolver();
        testLampData.updateLampStatus(context,lampStatus,testLampData);
        Looper.loop();
    }

    public void testSaveCurtainInfo(){
        Context context=getContext();
        int curtainid = 1;
        CurtainStatus curtainStatus = new CurtainStatus();
        Curtain curtain = new Curtain();
        curtain.setCurtainId(curtainid);
        curtainStatus.setCurtain(curtain);
        curtainStatus.setCurtainStatus(10f);
        curtainStatus.setCurtainStatusRecordTime(new Timestamp(System.currentTimeMillis()));
        curtainStatus.setIsControlledByUser(false);

        AppliancesUpdateResolver testLampData = new AppliancesUpdateResolver();

        testLampData.updateCurtainStatus(context,curtainStatus,testLampData);
        Looper.loop();
    }

    public void testSheSwitchInfo(){
        Context context=getContext();
        int sheSwitchId = 1;
        SheSwitchStatus sheSwitchStatus = new SheSwitchStatus();
        SheSwitch sheSwitch = new SheSwitch();
        sheSwitch.setSheSwitchId(sheSwitchId);
        sheSwitchStatus.setSheSwitch(sheSwitch);
        sheSwitchStatus.setIsControlledByUser(true);
        sheSwitchStatus.setSheSwitchStatus(true);
        sheSwitchStatus.setSheSwitchStatusRecordTime(new Timestamp(System.currentTimeMillis()));
        AppliancesUpdateResolver testLampData = new AppliancesUpdateResolver();
        AppliancesUpdateResolver.updateSheSwitchStatus(context, sheSwitchStatus, testLampData);
        Looper.loop();
    }

    public void testSaveAirConditionInfo(){
        Context context=getContext();
        int airConditionId = 1;
        AirConditionStatus airConditionStatus = new AirConditionStatus();
        AirCondition airCondition = new AirCondition();
        airCondition.setAirConditionId(airConditionId);
        airConditionStatus.setAirCondition(airCondition);
        airConditionStatus.setAirConditionMode(1);
        airConditionStatus.setAirConditionStatusRecordTime(new Timestamp(System.currentTimeMillis()));
        airConditionStatus.setAirConditionTemperature(35f);
        airConditionStatus.setIsControlledByUser(false);

        AppliancesUpdateResolver testLampData = new AppliancesUpdateResolver();

        AppliancesUpdateResolver.updateAirConditionStatus(context, airConditionStatus, testLampData);
        Looper.loop();
    }

    public void testSaveWaterHeaterInfo(){
        Context context=getContext();
        int waterHeaterId = 1;
        WaterHeaterStatus waterHeaterStatus = new WaterHeaterStatus();
        WaterHeater waterHeater = new WaterHeater();
        waterHeater.setWaterHeaterId(waterHeaterId);
        waterHeaterStatus.setWaterHeater(waterHeater);
        waterHeaterStatus.setIsControlledByUser(true);
        waterHeaterStatus.setWaterHeaterStatusRecordTime(new Timestamp(System.currentTimeMillis()));
        waterHeaterStatus.setWaterHeaterTemperature(37f);

        AppliancesUpdateResolver testLampData = new AppliancesUpdateResolver();

        AppliancesUpdateResolver.updateWaterHeaterStatus(context,waterHeaterStatus,testLampData);
        Looper.loop();
    }
}