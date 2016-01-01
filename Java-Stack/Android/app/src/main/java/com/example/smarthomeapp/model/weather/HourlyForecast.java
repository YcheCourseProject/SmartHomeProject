package com.example.smarthomeapp.model.weather;

import com.google.gson.annotations.SerializedName;


public class HourlyForecast{

    private static final String FIELD_WIND = "wind";
    private static final String FIELD_POP = "pop";
    private static final String FIELD_HUM = "hum";
    private static final String FIELD_TMP = "tmp";
    private static final String FIELD_DATE = "date";
    private static final String FIELD_PRES = "pres";


    @SerializedName(FIELD_WIND)
    private Wind mWind;
    @SerializedName(FIELD_POP)
    private int mPop;
    @SerializedName(FIELD_HUM)
    private int mHum;
    @SerializedName(FIELD_TMP)
    private int mTmp;
    @SerializedName(FIELD_DATE)
    private String mDate;
    @SerializedName(FIELD_PRES)
    private int mPre;


    public HourlyForecast(){

    }

    public void setWind(Wind wind) {
        mWind = wind;
    }

    public Wind getWind() {
        return mWind;
    }

    public void setPop(int pop) {
        mPop = pop;
    }

    public int getPop() {
        return mPop;
    }

    public void setHum(int hum) {
        mHum = hum;
    }

    public int getHum() {
        return mHum;
    }

    public void setTmp(int tmp) {
        mTmp = tmp;
    }

    public int getTmp() {
        return mTmp;
    }

    public void setDate(String date) {
        mDate = date;
    }

    public String getDate() {
        return mDate;
    }

    public void setPre(int pre) {
        mPre = pre;
    }

    public int getPre() {
        return mPre;
    }

    @Override
    public String toString(){
        return "wind = " + mWind + ", pop = " + mPop + ", hum = " + mHum + ", tmp = " + mTmp + ", date = " + mDate + ", pre = " + mPre;
    }


}