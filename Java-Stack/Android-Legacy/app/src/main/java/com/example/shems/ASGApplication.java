package com.example.shems;
import android.app.Application;

import com.example.shems.daos.meters.Connector;
import com.example.shems.daos.meters.Meter;

/**
 * 这是一个全局变量的共享类，在所有的activity中传递共享信息
 * @author Administrator
 *
 */
public class ASGApplication extends Application{
    Connector connector = null;
    Meter meter = null;
    double  realTimePrice[]=new double[24];
    double  humanBavior[]=new double[24];
    public double[] getHumanBavior() {
        return humanBavior;
    }

    public void setHumanBavior(double[] humanBavior) {
        this.humanBavior = humanBavior;
    }

    public void setConnector(Connector connector){
        this.connector = connector;
    }

    public Connector getConnector(){
        return this.connector;
    }

    public Meter getMeter(){
        return this.meter;
    }

    public double[] getRealTimePrice() {
        return realTimePrice;
    }

    public void setRealTimePrice(double[] realTimePrice) {
        this.realTimePrice = realTimePrice;
    }

    public void setMeter(Meter meter){
        this.meter = meter;
    }
}
