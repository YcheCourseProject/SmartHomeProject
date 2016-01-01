package com.example.shems.models.electrial_statistics;

import android.content.Context;

import com.example.smarthome.R;

/**
 * 单位电价统计
 * Created by CHEYulin on 2015/5/16.
 */
public class EAverageCostStatistics extends ElectricalStatistics {

    public EAverageCostStatistics(Context context, double currentMonthVal, double formerMonthVal, double formerYearVal) {
        super(context, currentMonthVal, formerMonthVal, formerYearVal);
    }

    @Override
    public String getUnit() {
        return getContext().getString(R.string.unit_e_price_per_kwh);
    }

    @Override
    public double getOverallLevel() {
        return 90;
    }

    @Override
    public  String getRankNickName() {
        return getContext().getString(R.string.e_cost_expert);
    }

    @Override
    public String getDescription() {
        return getContext().getString(R.string.unit_electrical_cost);
    }
}
