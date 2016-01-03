package com.xjtu.sglab.shems.model.baidumap;

import com.google.gson.annotations.SerializedName;
import java.util.List;


public class POIResult{

    private static final String FIELD_STATUS = "status";
    private static final String FIELD_MESSAGE = "message";
    private static final String FIELD_RESULTS = "results";
    private static final String FIELD_TOTAL = "total";


    @SerializedName(FIELD_STATUS)
    private int mStatus;
    @SerializedName(FIELD_MESSAGE)
    private String mMessage;
    @SerializedName(FIELD_RESULTS)
    private List<MapResult> mResults;
    @SerializedName(FIELD_TOTAL)
    private int mTotal;


    public POIResult(){

    }

    public void setStatus(int status) {
        mStatus = status;
    }

    public int getStatus() {
        return mStatus;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setResults(List<MapResult> results) {
        mResults = results;
    }

    public List<MapResult> getResults() {
        return mResults;
    }

    public void setTotal(int total) {
        mTotal = total;
    }

    public int getTotal() {
        return mTotal;
    }

    @Override
    public String toString(){
        return "status = " + mStatus + ", message = " + mMessage + ", results = " + mResults + ", total = " + mTotal;
    }


}