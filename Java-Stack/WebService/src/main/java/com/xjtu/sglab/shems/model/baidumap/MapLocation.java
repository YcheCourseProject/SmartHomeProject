package com.xjtu.sglab.shems.model.baidumap;

import com.google.gson.annotations.SerializedName;


public class MapLocation{

    private static final String FIELD_LAT = "lat";
    private static final String FIELD_LNG = "lng";


    @SerializedName(FIELD_LAT)
    private double mLat;
    @SerializedName(FIELD_LNG)
    private double mLng;


    public MapLocation(){

    }

    public void setLat(double lat) {
        mLat = lat;
    }

    public double getLat() {
        return mLat;
    }

    public void setLng(double lng) {
        mLng = lng;
    }

    public double getLng() {
        return mLng;
    }

    @Override
    public String toString(){
        return "lat = " + mLat + ", lng = " + mLng;
    }


}