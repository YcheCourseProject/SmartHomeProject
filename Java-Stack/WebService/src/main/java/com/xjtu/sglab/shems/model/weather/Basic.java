package com.xjtu.sglab.shems.model.weather;

import com.google.gson.annotations.SerializedName;


public class Basic{

    private static final String FIELD_ID = "id";
    private static final String FIELD_CITY = "city";
    private static final String FIELD_LON = "lon";
    private static final String FIELD_CNTY = "cnty";
    private static final String FIELD_LAT = "lat";
    private static final String FIELD_UPDATE = "update";


    @SerializedName(FIELD_ID)
    private String mId;
    @SerializedName(FIELD_CITY)
    private String mCity;
    @SerializedName(FIELD_LON)
    private double mLon;
    @SerializedName(FIELD_CNTY)
    private String mCnty;
    @SerializedName(FIELD_LAT)
    private double mLat;
    @SerializedName(FIELD_UPDATE)
    private Update mUpdate;


    public Basic(){

    }

    public void setId(String id) {
        mId = id;
    }

    public String getId() {
        return mId;
    }

    public void setCity(String city) {
        mCity = city;
    }

    public String getCity() {
        return mCity;
    }

    public void setLon(double lon) {
        mLon = lon;
    }

    public double getLon() {
        return mLon;
    }

    public void setCnty(String cnty) {
        mCnty = cnty;
    }

    public String getCnty() {
        return mCnty;
    }

    public void setLat(double lat) {
        mLat = lat;
    }

    public double getLat() {
        return mLat;
    }

    public void setUpdate(Update update) {
        mUpdate = update;
    }

    public Update getUpdate() {
        return mUpdate;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof Basic){
            return ((Basic) obj).getId() == mId;
        }
        return false;
    }

    @Override
    public int hashCode(){
        return mId.hashCode();
    }

    @Override
    public String toString(){
        return "id = " + mId + ", city = " + mCity + ", lon = " + mLon + ", cnty = " + mCnty + ", lat = " + mLat + ", update = " + mUpdate;
    }


}