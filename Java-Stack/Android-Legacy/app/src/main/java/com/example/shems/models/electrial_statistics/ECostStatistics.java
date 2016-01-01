package com.example.shems.models.electrial_statistics;

import android.content.Context;

import com.example.smarthome.R;

/**
 * 电费统计
 * Created by CHEYulin on 2015/5/16.
 */
public class ECostStatistics extends ElectricalStatistics {

    public ECostStatistics(Context context, double currentMonthVal, double formerMonthVal, double formerYearVal) {
        super(context, currentMonthVal, formerMonthVal, formerYearVal);
    }

    @Override
    public String getUnit() {
        return getContext().getString(R.string.unit_electrical_price);
    }

    @Override
    public double getOverallLevel() {
        return 85;
    }

    @Override
    public String getRankNickName() {
        return getContext().getString(R.string.e_cost_expert);
    }

    @Override
    public  String getDescription() {
        return getContext().getString(R.string.electrical_cost);
    }
}
