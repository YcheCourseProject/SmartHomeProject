package com.example.shems.models.electrial_statistics;

import android.content.Context;

import com.example.smarthome.R;

/**
 * CO2排放统计
 * Created by CHEYulin on 2015/5/16.
 */
public class CO2EmissionStatistics extends ElectricalStatistics{

    public CO2EmissionStatistics(Context context, double currentMonthVal, double formerMonthVal, double formerYearVal) {
        super(context, currentMonthVal, formerMonthVal, formerYearVal);
    }

    @Override
    public String getUnit() {
        return getContext().getString(R.string.unit_co2_emission);
    }

    @Override
    public double getOverallLevel() {
        return 70;
    }

    @Override
    public String getRankNickName() {
        return getContext().getString(R.string.emission_reduction_expert);
    }

    @Override
    public String getDescription() {
        return getContext().getString(R.string.carbon_dioxide_emission);
    }
}
