package com.xjtu.sglab.shems.model.baidumap;

import com.google.gson.annotations.SerializedName;


public class DetailInfo{

    private static final String FIELD_TYPE = "type";
    private static final String FIELD_DISTANCE = "distance";
    private static final String FIELD_ENVIRONMENT_RATING = "environment_rating";
    private static final String FIELD_IMAGE_NUM = "image_num";
    private static final String FIELD_SERVICE_RATING = "service_rating";
    private static final String FIELD_OVERALL_RATING = "overall_rating";
    private static final String FIELD_DETAIL_URL = "detail_url";
    private static final String FIELD_TAG = "tag";


    @SerializedName(FIELD_TYPE)
    private String mType;
    @SerializedName(FIELD_DISTANCE)
    private int mDistance;
    @SerializedName(FIELD_ENVIRONMENT_RATING)
    private double mEnvironmentRating;
    @SerializedName(FIELD_IMAGE_NUM)
    private int mImageNum;
    @SerializedName(FIELD_SERVICE_RATING)
    private double mServiceRating;
    @SerializedName(FIELD_OVERALL_RATING)
    private double mOverallRating;
    @SerializedName(FIELD_DETAIL_URL)
    private String mDetailUrl;
    @SerializedName(FIELD_TAG)
    private String mTag;


    public DetailInfo(){

    }

    public void setType(String type) {
        mType = type;
    }

    public String getType() {
        return mType;
    }

    public void setDistance(int distance) {
        mDistance = distance;
    }

    public int getDistance() {
        return mDistance;
    }

    public void setEnvironmentRating(int environmentRating) {
        mEnvironmentRating = environmentRating;
    }

    public double getEnvironmentRating() {
        return mEnvironmentRating;
    }

    public void setImageNum(int imageNum) {
        mImageNum = imageNum;
    }

    public int getImageNum() {
        return mImageNum;
    }

    public void setServiceRating(int serviceRating) {
        mServiceRating = serviceRating;
    }

    public double getServiceRating() {
        return mServiceRating;
    }

    public void setOverallRating(double overallRating) {
        mOverallRating = overallRating;
    }

    public double getOverallRating() {
        return mOverallRating;
    }

    public void setDetailUrl(String detailUrl) {
        mDetailUrl = detailUrl;
    }

    public String getDetailUrl() {
        return mDetailUrl;
    }

    public void setTag(String tag) {
        mTag = tag;
    }

    public String getTag() {
        return mTag;
    }

    @Override
    public String toString(){
        return "type = " + mType + ", distance = " + mDistance + ", environmentRating = " + mEnvironmentRating + ", imageNum = " + mImageNum + ", serviceRating = " + mServiceRating + ", overallRating = " + mOverallRating + ", detailUrl = " + mDetailUrl + ", tag = " + mTag;
    }


}