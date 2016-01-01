package com.example.smarthomeapp.model.weather;

import com.google.gson.annotations.SerializedName;
import java.util.List;


public class HeWeatherDataService30{

    private static final String FIELD_HOURLY_FORECAST = "hourly_forecast";
    private static final String FIELD_STATUS = "status";
    private static final String FIELD_SUGGESTION = "suggestion";
    private static final String FIELD_AQI = "aqi";
    private static final String FIELD_NOW = "now";
    private static final String FIELD_BASIC = "basic";
    private static final String FIELD_DAILY_FORECAST = "daily_forecast";


    @SerializedName(FIELD_HOURLY_FORECAST)
    private List<HourlyForecast> mHourlyForecasts;
    @SerializedName(FIELD_STATUS)
    private String mStatus;
    @SerializedName(FIELD_SUGGESTION)
    private Suggestion mSuggestion;
    @SerializedName(FIELD_AQI)
    private Aqi mAqi;
    @SerializedName(FIELD_NOW)
    private Now mNow;
    @SerializedName(FIELD_BASIC)
    private Basic mBasic;
    @SerializedName(FIELD_DAILY_FORECAST)
    private List<DailyForecast> mDailyForecasts;


    public HeWeatherDataService30(){

    }

    public void setHourlyForecasts(List<HourlyForecast> hourlyForecasts) {
        mHourlyForecasts = hourlyForecasts;
    }

    public List<HourlyForecast> getHourlyForecasts() {
        return mHourlyForecasts;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public String getStatus() {
        return mStatus;
    }

    public void setSuggestion(Suggestion suggestion) {
        mSuggestion = suggestion;
    }

    public Suggestion getSuggestion() {
        return mSuggestion;
    }

    public void setAqi(Aqi aqi) {
        mAqi = aqi;
    }

    public Aqi getAqi() {
        return mAqi;
    }

    public void setNow(Now now) {
        mNow = now;
    }

    public Now getNow() {
        return mNow;
    }

    public void setBasic(Basic basic) {
        mBasic = basic;
    }

    public Basic getBasic() {
        return mBasic;
    }

    public void setDailyForecasts(List<DailyForecast> dailyForecasts) {
        mDailyForecasts = dailyForecasts;
    }

    public List<DailyForecast> getDailyForecasts() {
        return mDailyForecasts;
    }

    @Override
    public String toString(){
        return "hourlyForecasts = " + mHourlyForecasts + ", status = " + mStatus + ", suggestion = " + mSuggestion + ", aqi = " + mAqi + ", now = " + mNow + ", basic = " + mBasic + ", dailyForecasts = " + mDailyForecasts;
    }


}