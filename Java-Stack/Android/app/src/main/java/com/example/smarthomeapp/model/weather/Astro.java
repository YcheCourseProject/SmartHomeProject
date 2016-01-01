package com.example.smarthomeapp.model.weather;

import com.google.gson.annotations.SerializedName;


public class Astro{

    private static final String FIELD_SR = "sr";
    private static final String FIELD_SS = "ss";


    @SerializedName(FIELD_SR)
    private String mSr;
    @SerializedName(FIELD_SS)
    private String mSs;


    public Astro(){

    }

    public void setSr(String sr) {
        mSr = sr;
    }

    public String getSr() {
        return mSr;
    }

    public void setSs(String ss) {
        mSs = ss;
    }

    public String getSs() {
        return mSs;
    }

    @Override
    public String toString(){
        return "sr = " + mSr + ", ss = " + mSs;
    }


}