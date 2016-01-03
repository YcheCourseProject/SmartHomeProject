package com.xjtu.sglab.shems.model.weather;

import com.google.gson.annotations.SerializedName;


public class Aqi{

    private static final String FIELD_CITY = "city";


    @SerializedName(FIELD_CITY)
    private City mCity;


    public Aqi(){

    }

    public void setCity(City city) {
        mCity = city;
    }

    public City getCity() {
        return mCity;
    }

    @Override
    public String toString(){
        return "city = " + mCity;
    }


}