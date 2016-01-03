package com.xjtu.sglab.shems.model.weather;

import com.google.gson.annotations.SerializedName;


public class Update{

    private static final String FIELD_UTC = "utc";
    private static final String FIELD_LOC = "loc";


    @SerializedName(FIELD_UTC)
    private String mUtc;
    @SerializedName(FIELD_LOC)
    private String mLoc;


    public Update(){

    }

    public void setUtc(String utc) {
        mUtc = utc;
    }

    public String getUtc() {
        return mUtc;
    }

    public void setLoc(String loc) {
        mLoc = loc;
    }

    public String getLoc() {
        return mLoc;
    }

    @Override
    public String toString(){
        return "utc = " + mUtc + ", loc = " + mLoc;
    }


}