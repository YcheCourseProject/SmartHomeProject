package com.example.shems.models.electrical_prices;

import java.util.Date;

import android.util.Log;

public class RegionPrice            //拿到的是一个时间段的各个地区的实时电价
// 对于有可能踩不到电价也就是needmodify==true情况要注意
{

    public float[] prices;
    public Date date;            //表示的是电价所在的时间
    public boolean needmodify;
    String[] regions = new String[]{
            RealTimePrice.region1, RealTimePrice.region2,
            RealTimePrice.region3, RealTimePrice.region4,
            RealTimePrice.region5, RealTimePrice.region6,
            RealTimePrice.region7, RealTimePrice.region8,
            RealTimePrice.region9};

    void init() {
        prices = new float[9];
        date = new Date();
        date = new Date(date.getTime() / 1000 * 1000);        //除去毫秒的影响

    }

    public RegionPrice(Date date) {
        this.init();
        this.date = date;
        needmodify = false;
    }

    public void add(float[] prices) {
        if (prices[0] == 0) {
            needmodify = true;
        }
        this.prices = prices;

    }

    public RegionPrice(float[] prices, Date date) {
        this.init();
        this.date = date;
        needmodify = false;
        this.add(prices);
    }

    public void show() {
        Log.i("date", this.date.toLocaleString());
        for (int i = 0; i < 9; i++) {
            Log.i("prices", "" + prices[i]);
        }
    }

}
