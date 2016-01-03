package com.xjtu.sglab.shems.model.weather;

import com.google.gson.annotations.SerializedName;


public class City{

    private static final String FIELD_O3 = "o3";
    private static final String FIELD_PM10 = "pm10";
    private static final String FIELD_AQI = "aqi";
    private static final String FIELD_PM25 = "pm25";
    private static final String FIELD_CO = "co";
    private static final String FIELD_SO2 = "so2";
    private static final String FIELD_NO2 = "no2";
    private static final String FIELD_QLTY = "qlty";


    @SerializedName(FIELD_O3)
    private int mO3;
    @SerializedName(FIELD_PM10)
    private int mPm10;
    @SerializedName(FIELD_AQI)
    private int mAqi;
    @SerializedName(FIELD_PM25)
    private int mPm25;
    @SerializedName(FIELD_CO)
    private int mCo;
    @SerializedName(FIELD_SO2)
    private int mSo2;
    @SerializedName(FIELD_NO2)
    private int mNo2;
    @SerializedName(FIELD_QLTY)
    private String mQlty;


    public City(){

    }

    public void setO3(int o3) {
        mO3 = o3;
    }

    public int getO3() {
        return mO3;
    }

    public void setPm10(int pm10) {
        mPm10 = pm10;
    }

    public int getPm10() {
        return mPm10;
    }

    public void setAqi(int aqi) {
        mAqi = aqi;
    }

    public int getAqi() {
        return mAqi;
    }

    public void setPm25(int pm25) {
        mPm25 = pm25;
    }

    public int getPm25() {
        return mPm25;
    }

    public void setCo(int co) {
        mCo = co;
    }

    public int getCo() {
        return mCo;
    }

    public void setSo2(int so2) {
        mSo2 = so2;
    }

    public int getSo2() {
        return mSo2;
    }

    public void setNo2(int no2) {
        mNo2 = no2;
    }

    public int getNo2() {
        return mNo2;
    }

    public void setQlty(String qlty) {
        mQlty = qlty;
    }

    public String getQlty() {
        return mQlty;
    }

    @Override
    public String toString(){
        return "o3 = " + mO3 + ", pm10 = " + mPm10 + ", aqi = " + mAqi + ", pm25 = " + mPm25 + ", co = " + mCo + ", so2 = " + mSo2 + ", no2 = " + mNo2 + ", qlty = " + mQlty;
    }


}