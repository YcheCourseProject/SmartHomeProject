package com.example.smarthomeapp;

import android.app.Application;
import android.content.Context;
import android.os.Looper;
import android.test.ApplicationTestCase;

import com.example.smarthomeapp.result_jzp.MeterQueryResolver;
import com.example.smarthomeapp.result_jzp.SensorQueryResolver;

import java.util.HashSet;


/**
 * Created by 卧龙风 on 2015/7/23.
 */
public class QuerySensorResolverTest extends ApplicationTestCase<Application> {
    public QuerySensorResolverTest() {
        super(Application.class);
    }

    public void testGasSensorInfo() {
        Context context = getContext();
        HashSet<Integer> idSet = new HashSet<Integer>();
        idSet.add(1);
        //idSet.add(2);
        SensorQueryResolver testGasSensorData = new SensorQueryResolver();
        testGasSensorData.querygasSensorData(context, idSet, testGasSensorData);
        Looper.loop();
    }

    public void testlightSensorInfo() {
        Context context = getContext();
        HashSet<Integer> idSet = new HashSet<Integer>();
        idSet.add(1);
       // idSet.add(2);
        SensorQueryResolver testlightSensor = new SensorQueryResolver();
        testlightSensor.querylightSensorData(context, idSet, testlightSensor);
        Looper.loop();
    }

    public void testflameSensorInfo() {
        Context context = getContext();
        HashSet<Integer> idSet = new HashSet<Integer>();
        idSet.add(1);
        //idSet.add(2);
        SensorQueryResolver testflameSensor = new SensorQueryResolver();
        testflameSensor.queryflameSensorData(context, idSet, testflameSensor);
        Looper.loop();
    }

    public void testplrSensorInfo() {
        Context context = getContext();
        HashSet<Integer> idSet = new HashSet<Integer>();
        idSet.add(1);
        //idSet.add(2);
        SensorQueryResolver testplrSensor = new SensorQueryResolver();
        testplrSensor.queryplrSensorData(context, idSet, testplrSensor);
        Looper.loop();
    }

    public void testtemperatureSensorInfo() {
        Context context = getContext();
        HashSet<Integer> idSet = new HashSet<Integer>();
        idSet.add(1);
        //idSet.add(2);
        SensorQueryResolver testtemperatureSensor = new SensorQueryResolver();
        testtemperatureSensor.querytemperatureSensorData(context, idSet, testtemperatureSensor);
        Looper.loop();
    }

    public void testMeterInfo() {
        Context context = getContext();
        HashSet<Integer> idSet = new HashSet<Integer>();
        idSet.add(1);
       // idSet.add(2);
        MeterQueryResolver meter = new MeterQueryResolver();
        meter.queryMeterData(context, idSet, meter);
        Looper.loop();
    }


}
