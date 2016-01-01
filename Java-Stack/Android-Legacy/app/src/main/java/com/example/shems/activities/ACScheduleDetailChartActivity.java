package com.example.shems.activities;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.PointStyle;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import com.example.shems.util.algorithm.Algorithm_AC;
import com.example.shems.views.AbstractDemoChart;
import com.example.shems.views.TableView;
import com.example.smarthome.R;


import android.os.Bundle;
import android.R.integer;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

public class ACScheduleDetailChartActivity extends Activity {
    AbstractDemoChart adChart = new AbstractDemoChart();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduling_chart);
        paintSchedulingChart();
    }

    public void paintSchedulingChart() {
        Algorithm_AC.run();
        double optimalPolicy[] = Algorithm_AC.BasePolicy;
        Algorithm_AC.runwithoutscheduling();
        double formerPolicy[] = Algorithm_AC.formerPolicy;
        for(int i=0;i<optimalPolicy.length;i++)
        {
            DecimalFormat df=new DecimalFormat(".0f");
            optimalPolicy[i]=Double.parseDouble(df.format(optimalPolicy[i]));
            formerPolicy[i]=Double.parseDouble(df.format(formerPolicy[i]));
        }
        List<double[]> values = new ArrayList<double[]>();
        values.add(formerPolicy);
        values.add(optimalPolicy);
        XYMultipleSeriesDataset dataset = adChart.buildBarDataset(new String[] {
                "Qformer", "Qoptimized" }, values);
        int[] colors = { Color.BLUE, Color.RED };
        XYMultipleSeriesRenderer renderer = adChart.buildBarRenderer(colors);
        Type type = Type.DEFAULT;

        // renderer.setZoomEnabled(false);//怎么失效了----使用下面的方式
        renderer.setZoomEnabled(false, false);// 成功控制--嘿嘿
        adChart.setChartSettings(renderer, "AC Scheduling Comparison", "Time",
                "Energy/kWh", 0, 24, 0, 1.2, Color.BLACK, Color.BLACK);
        renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
        renderer.getSeriesRendererAt(1).setDisplayChartValues(true);
        renderer.setXLabels(0);// 设置x轴上的下标数量
        renderer.setYLabels(10); // 设置y轴上的下标数量
        renderer.setXLabelsAlign(Align.RIGHT);
        renderer.setYLabelsAlign(Align.LEFT);// y轴 数字表示在坐标还是右边
        renderer.setPanEnabled(false, false);// 设置是否允许平移
        //renderer.setLegendHeight(BIND_IMPORTANT);
        for (int i = 1; i < 25; i++) {
            renderer.addXTextLabel(i, String.valueOf(i));
        }
        NumberFormat nnf = NumberFormat.getNumberInstance();
        nnf.setMinimumIntegerDigits(1);
        nnf.setMaximumFractionDigits(2);
        for(int i=0;i<renderer.getSeriesRenderers().length;i++)
            renderer.getSeriesRenderers()[0].setChartValuesFormat(nnf);
        // renderer.addXTextLabel(2.0, "220kv电力线");// 在指定坐标处显示文字
        // renderer.addXTextLabel(3.0, "220kv电力线");// 在指定坐标处显示文字
        // renderer.clearXTextLabels();//清除 labels
        // renderer.setZoomRate(2.1f);//设置放缩比
        renderer.setBarSpacing(0.2f);// 设置柱状的间距
        // renderer.setLabelsTextSize(30);//设置坐标轴上数字的大小
        renderer.setXLabelsAngle(30.0f);// 设置文字旋转角度 对文字顺时针旋转
        renderer.setXLabelsPadding(10);// 设置文字和轴的距离
        renderer.setFitLegend(true);// 调整合适的位置
        renderer.setLegendTextSize(40f);

        renderer.setChartValuesTextSize(20f);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;// 宽度height = dm.heightPixels ;//高度
        GraphicalView graphicalView = ChartFactory.getBarChartView(
                getBaseContext(), dataset, renderer, type);// 柱状图
        LinearLayout layout = (LinearLayout) findViewById(R.id.ACscheulingDes);
        layout.removeAllViews();
        layout.setBackgroundColor(Color.WHITE);
        layout.addView(graphicalView, new LayoutParams(
                LayoutParams.FILL_PARENT, height / 2));
        String[] strs = new String[4];
        strs[0] = "Former Cost";
        strs[1] = "Optimized Cost";
        DecimalFormat df = new DecimalFormat(".00");
        strs[2] = "￥" + df.format(Algorithm_AC.costwithoutscheuling);
        strs[3] = "￥" + df.format(Algorithm_AC.costwithscheuling);

        TableView table = new TableView(this, 2, 2, strs);
        layout = (LinearLayout) findViewById(R.id.AC_Cost_Description);
        layout.removeAllViews();
        layout.setBackgroundColor(Color.WHITE);

        layout.addView(table, new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT));
    }

    public void paintDynamicPrice() {


        double []realtimeprice=Algorithm_AC.RealTimePrice;
        double []temp=new double[realtimeprice.length];

        List<double[]> values = new ArrayList<double[]>();
        values.add(realtimeprice);

        // values.add(new double[]{2,3,4});
        XYMultipleSeriesDataset dataset = adChart.buildBarDataset(new String[] {
                "Dynamic Price" }, values);
        int[] colors = { Color.BLUE};
        XYMultipleSeriesRenderer renderer = adChart.buildBarRenderer(colors);
        Type type = Type.DEFAULT;

        // renderer.setZoomEnabled(false);//怎么失效了----使用下面的方式
        renderer.setZoomEnabled(false, false);// 成功控制--嘿嘿
        adChart.setChartSettings(renderer, "Dynamic Price", "Time/h",
                "Dynamic Price￥/kWh", 0, 24, 0, 1.5, Color.BLACK, Color.BLACK);
        renderer.getSeriesRendererAt(0).setDisplayChartValues(false);
        // renderer.getSeriesRendererAt(1).setDisplayChartValues(true);
        renderer.setXLabels(0);// 设置x轴上的下标数量
        renderer.setYLabels(10); // 设置y轴上的下标数量
        renderer.setXLabelsAlign(Align.RIGHT);
        renderer.setYLabelsAlign(Align.LEFT);// y轴 数字表示在坐标还是右边
        renderer.setPanEnabled(false, false);// 设置是否允许平移
        for (int i = 1; i < 25; i++) {
            renderer.addXTextLabel(i, String.valueOf(i));
        }
        // renderer.addXTextLabel(2.0, "220kv电力线");// 在指定坐标处显示文字
        // renderer.addXTextLabel(3.0, "220kv电力线");// 在指定坐标处显示文字
        // renderer.clearXTextLabels();//清除 labels
        // renderer.setZoomRate(2.1f);//设置放缩比
        renderer.setBarSpacing(1f);// 设置柱状的间距
        // renderer.setLabelsTextSize(30);//设置坐标轴上数字的大小
        renderer.setXLabelsAngle(30.0f);// 设置文字旋转角度 对文字顺时针旋转
        renderer.setXLabelsPadding(10);// 设置文字和轴的距离
        //renderer.setFitLegend(true);// 调整合适的位置
        renderer.setXLabelsPadding(3f);
        renderer.setLegendTextSize(30f);

        renderer.setChartValuesTextSize(30f);
        renderer.setBarSpacing(0.5f);// 设置柱状的间距
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;// 宽度height = dm.heightPixels ;//高度
        GraphicalView graphicalView = ChartFactory.getBarChartView(
                getBaseContext(), dataset, renderer, type);// 柱状图
        LinearLayout layout = (LinearLayout) findViewById(R.id.ACscheulingDes);
        layout.removeAllViews();
        layout.setBackgroundColor(Color.WHITE);
        layout.addView(graphicalView, new LayoutParams(
                LayoutParams.FILL_PARENT, (int) (height*0.8)));

        layout = (LinearLayout) findViewById(R.id.AC_Cost_Description);
        layout.removeAllViews();

    }
    public void paintTin()
    {
        String[] titles = new String[] { "TinFormer", "TinOptimized"};
        double []tinOptimal=Algorithm_AC.T_in;
        double []tinFormer=Algorithm_AC.T_in_former;
        List x = new ArrayList();
        List y = new ArrayList();

        double t[]=new double[24];
        for(int i=0;i<24;i++)
        {
            t[i]=i;
        }
        x.add(t);
        x.add(t);

        y.add(tinFormer);
        y.add(tinOptimal);

        XYMultipleSeriesDataset dataset =adChart.buildDataset(titles, x, y);

        int[] colors = new int[] { Color.BLUE, Color.RED};
        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE, PointStyle.DIAMOND};
        XYMultipleSeriesRenderer renderer = adChart.buildRenderer(colors, styles);

        adChart.setChartSettings(renderer, "indoor-Temprature", "Time/h", "Temprature/℃", 0, 24, 19, 32, Color.BLACK, Color.BLACK);
        renderer.setMarginsColor(Color.WHITE);
        renderer.setBackgroundColor(Color.WHITE);
        renderer.setXLabelsColor(Color.BLACK);
        renderer.setYLabelsColor(0, Color.BLACK);
        renderer.setZoomEnabled(false, false);// 成功控制--嘿嘿
        renderer.setPanEnabled(false, false);// 设置是否允许平移
        renderer.setAxisTitleTextSize(20f);
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        int height = dm.heightPixels;// 宽度height = dm.heightPixels ;//高度
        View chart = ChartFactory.getCubeLineChartView(this, dataset, renderer,0.3f);
        LinearLayout layout = (LinearLayout) findViewById(R.id.ACscheulingDes);
        layout.removeAllViews();
        layout.setBackgroundColor(Color.WHITE);
        layout.addView(chart, new LayoutParams(
                LayoutParams.FILL_PARENT, (int)(height*0.8)));

        layout = (LinearLayout) findViewById(R.id.AC_Cost_Description);
        layout.removeAllViews();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.scheduling_chart, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_scheduling:
                paintSchedulingChart();
                return true;
            case R.id.action_dynamic_price:
                paintDynamicPrice();
                return true;
            case R.id.action_tin:
                paintTin();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            Intent intent = new Intent();
//            intent.setClass(this, AirConditionerActivity.class);
//            this.startActivity(intent);
//            this.finish();
//        }
//        return super.onKeyDown(keyCode, event);
//
//    }

}
