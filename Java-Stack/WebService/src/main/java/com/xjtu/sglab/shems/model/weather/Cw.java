package com.xjtu.sglab.shems.model.weather;

import com.google.gson.annotations.SerializedName;


public class Cw{

    private static final String FIELD_BRF = "brf";
    private static final String FIELD_TXT = "txt";


    @SerializedName(FIELD_BRF)
    private String mBrf;
    @SerializedName(FIELD_TXT)
    private String mTxt;


    public Cw(){

    }

    public void setBrf(String brf) {
        mBrf = brf;
    }

    public String getBrf() {
        return mBrf;
    }

    public void setTxt(String txt) {
        mTxt = txt;
    }

    public String getTxt() {
        return mTxt;
    }

    @Override
    public String toString(){
        return "brf = " + mBrf + ", txt = " + mTxt;
    }


}