package com.xjtu.sglab.shems.model.weather;

import com.google.gson.annotations.SerializedName;
import java.util.List;


public class HeFengWeatherService{

    private static final String FIELD_HE_WEATHER_DATA_SERVICE = "HeWeather data service 3.0";


    @SerializedName(FIELD_HE_WEATHER_DATA_SERVICE)
    private List<HeWeatherDataService30> mHeWeatherDataService30s;


    public HeFengWeatherService(){

    }

    public void setHeWeatherDataService30s(List<HeWeatherDataService30> heWeatherDataService30s) {
        mHeWeatherDataService30s = heWeatherDataService30s;
    }

    public List<HeWeatherDataService30> getHeWeatherDataService30s() {
        return mHeWeatherDataService30s;
    }

    @Override
    public String toString(){
        return "heWeatherDataService30s = " + mHeWeatherDataService30s;
    }


}