package com.example.shems.models.electrial_statistics;

import android.content.Context;

import com.example.smarthome.R;

/**
 * 用电量统计
 * Created by CHEYulin on 2015/5/16.
 */
public class EConsumptionStatistics extends ElectricalStatistics{

    public EConsumptionStatistics(Context context, double currentMonthVal, double formerMonthVal, double formerYearVal) {
        super(context, currentMonthVal, formerMonthVal, formerYearVal);
    }

    @Override
    public String getUnit() {
        return getContext().getString(R.string.unit_electrical_consumption);
    }

    @Override
    public double getOverallLevel() {
        return 60;
    }

    @Override
    public String getRankNickName() {
        return getContext().getString(R.string.e_consumption_expert);
    }

    @Override
    public String getDescription() {
        return getContext().getString(R.string.electrical_consumption);
    }
}
