package com.xjtu.sglab.shems.model.locTolat;

import com.google.gson.annotations.SerializedName;


public class LngLatResult{

    private static final String FIELD_STATUS = "status";
    private static final String FIELD_RESULT = "result";


    @SerializedName(FIELD_STATUS)
    private int mStatus;
    @SerializedName(FIELD_RESULT)
    private LatResult mResult;


    public LngLatResult(){

    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setResult(LatResult result) {
        mResult = result;
    }

    public LatResult getResult() {
        return mResult;
    }


}