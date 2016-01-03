package com.xjtu.sglab.shems.model.locTolat;

import com.google.gson.annotations.SerializedName;


public class LatResult{

    private static final String FIELD_LOCATION = "location";
    private static final String FIELD_PRECISE = "precise";
    private static final String FIELD_CONFIDENCE = "confidence";
    private static final String FIELD_LEVEL = "level";


    @SerializedName(FIELD_LOCATION)
    private LatLocation mLocation;
    @SerializedName(FIELD_PRECISE)
    private int mPrecise;
    @SerializedName(FIELD_CONFIDENCE)
    private int mConfidence;
    @SerializedName(FIELD_LEVEL)
    private String mLevel;


    public LatResult(){

    }

    public void setLocation(LatLocation location) {
        mLocation = location;
    }

    public LatLocation getLocation() {
        return mLocation;
    }

    public void setPrecise(int precise) {
        mPrecise = precise;
    }

    public int getPrecise() {
        return mPrecise;
    }

    public void setConfidence(int confidence) {
        mConfidence = confidence;
    }

    public int getConfidence() {
        return mConfidence;
    }

    public void setLevel(String level) {
        mLevel = level;
    }

    public String getLevel() {
        return mLevel;
    }


}