package com.xjtu.sglab.shems.model.weather;

import com.google.gson.annotations.SerializedName;


public class Tmp{

    private static final String FIELD_MAX = "max";
    private static final String FIELD_MIN = "min";


    @SerializedName(FIELD_MAX)
    private int mMax;
    @SerializedName(FIELD_MIN)
    private int mMin;


    public Tmp(){

    }

    public void setMax(int max) {
        mMax = max;
    }

    public int getMax() {
        return mMax;
    }

    public void setMin(int min) {
        mMin = min;
    }

    public int getMin() {
        return mMin;
    }

    @Override
    public String toString(){
        return "max = " + mMax + ", min = " + mMin;
    }


}