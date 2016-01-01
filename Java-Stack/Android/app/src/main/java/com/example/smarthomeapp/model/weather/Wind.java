package com.example.smarthomeapp.model.weather;

import com.google.gson.annotations.SerializedName;


public class Wind{

    private static final String FIELD_DIR = "dir";
    private static final String FIELD_DEG = "deg";
    private static final String FIELD_SC = "sc";
    private static final String FIELD_SPD = "spd";


    @SerializedName(FIELD_DIR)
    private String mDir;
    @SerializedName(FIELD_DEG)
    private int mDeg;
    @SerializedName(FIELD_SC)
    private String mSc;
    @SerializedName(FIELD_SPD)
    private int mSpd;


    public Wind(){

    }

    public void setDir(String dir) {
        mDir = dir;
    }

    public String getDir() {
        return mDir;
    }

    public void setDeg(int deg) {
        mDeg = deg;
    }

    public int getDeg() {
        return mDeg;
    }

    public void setSc(String sc) {
        mSc = sc;
    }

    public String getSc() {
        return mSc;
    }

    public void setSpd(int spd) {
        mSpd = spd;
    }

    public int getSpd() {
        return mSpd;
    }

    @Override
    public String toString(){
        return "dir = " + mDir + ", deg = " + mDeg + ", sc = " + mSc + ", spd = " + mSpd;
    }


}