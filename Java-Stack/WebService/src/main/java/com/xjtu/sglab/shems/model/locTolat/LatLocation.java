package com.xjtu.sglab.shems.model.locTolat;

import com.google.gson.annotations.SerializedName;


public class LatLocation{

    private static final String FIELD_LAT = "lat";
    private static final String FIELD_LNG = "lng";


    @SerializedName(FIELD_LAT)
    private float mLat;
    @SerializedName(FIELD_LNG)
    private float mLng;


    public LatLocation(){

    }

    public void setLat(float lat) {
        mLat = lat;
    }

    public float getLat() {
        return mLat;
    }

    public void setLng(float lng) {
        mLng = lng;
    }

    public float getLng() {
        return mLng;
    }


}