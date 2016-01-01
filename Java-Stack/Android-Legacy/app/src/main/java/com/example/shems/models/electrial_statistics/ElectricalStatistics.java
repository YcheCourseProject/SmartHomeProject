package com.example.shems.models.electrial_statistics;

import android.content.Context;

/**
 * Created by CHEYulin on 2015/5/16.
 */
public abstract class ElectricalStatistics {
    private Context context;
    private double currentMonthVal;
    private double formerMonthVal;
    private double formerYearVal;

    public Context getContext() {
        return context;
    }

    public double getCurrentMonthVal() {
        return currentMonthVal;
    }

    public double getFormerMonthVal() {
        return formerMonthVal;
    }

    public double getFormerYearVal() {
        return formerYearVal;
    }

    protected ElectricalStatistics(Context context, double currentMonthVal, double formerMonthVal, double formerYearVal) {
        this.context = context;
        this.currentMonthVal = currentMonthVal;
        this.formerMonthVal = formerMonthVal;
        this.formerYearVal = formerYearVal;
    }

    /**
     * 与上个月相比的变化
     * @return
     */
    final public ChangeRate getChangeRateByMonth(){
        return calChangeRate(formerMonthVal,currentMonthVal);
    }

    /**
     * 与去年同月相比的变化
     * @return
     */
    final public ChangeRate getChangeRateByYear(){
        return calChangeRate(formerYearVal,currentMonthVal);
    }

    /**
     * 由两个比较的值，算出存储变化的对象ChangeRate
     * @param formerVal
     * @param currentVal
     * @return ChangeRate
     */
    private ChangeRate calChangeRate(double formerVal,double currentVal){
        boolean isIncrease;
        if(formerVal<currentVal)
            isIncrease=true;
        else
            isIncrease=false;
        double changeRate=Math.abs(currentVal-formerVal)/formerVal;
        return  new ChangeRate(isIncrease,changeRate);
    }

    /**
     * 获取单位字符串
     * @return
     */
    public abstract String getUnit();

    /**
     * 获取总体水平，值从0到100
     * @return
     */
    public abstract double getOverallLevel();

    /**
     * 获取排名对应的昵称
     * @return
     */
    public abstract String getRankNickName();

    /**
     * 获取相应的描述
     * @return
     */
    public abstract String getDescription();
    /**
     * 用来传递变化率
     */
    public class ChangeRate{
        private boolean isIncrease;
        private double changeRate;

        public ChangeRate(boolean isIncrease, double changeRate) {
            this.isIncrease = isIncrease;
            this.changeRate = changeRate;
        }

        public boolean isIncrease() {
            return isIncrease;
        }

        public double getChangeRate() {
            return changeRate;
        }
    }
}
