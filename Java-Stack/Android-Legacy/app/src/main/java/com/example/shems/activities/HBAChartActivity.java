package com.example.shems.activities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;
import org.achartengine.renderer.XYMultipleSeriesRenderer;


import com.example.shems.ASGApplication;
import com.example.shems.activities.MenuActivity;
import com.example.shems.daos.sqllite3.DBManager;
import com.example.shems.util.algorithm.Algorithm_AC;
import com.example.shems.views.AbstractDemoChart;
import com.example.smarthome.R;



import android.os.Bundle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class HBAChartActivity extends Activity {
    // 柱状图~~~~~~~~~~~~~~~~~~~~~
    AbstractDemoChart adChart = new AbstractDemoChart();
    GridView gridview_functions;
    GraphicalView graphicalView;
    double THRESHOLD_RATIO = 0.3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zhu_chart);
        // 创建一个获取R中内容的对象
        Resources resources = this.getResources();
        String funarr[] = resources.getStringArray(R.array.Menu_Function_Items);

        gridview_functions=(GridView) this.findViewById(R.id.gridView_functions2);
        this.gridview_functions.setAdapter(new ImageAdpter(this, funarr));
        this.gridview_functions.setNumColumns(2);// 设置每行列数
        this.gridview_functions.setGravity(Gravity.CENTER);// 位置居中
        this.gridview_functions.setVerticalSpacing(50);// 垂直间隔
        this.gridview_functions.setHorizontalSpacing(10);// 水平间隔

        this.gridview_functions
                .setOnItemClickListener(new OnItemClickListener() {
                    public void onItemClick(AdapterView<?> parent, View view,
                                            int position, long id) {
                        // TODO Auto-generated method stub
                        switch (position) {
                            case 0: {

                            }

                        }
                    }
                });
        paintEnergyDivision();

    }

    private void saveHumanBehavior(double[] humanBehavior) {
        ASGApplication application = (ASGApplication) this.getApplication();
        application.setHumanBavior(humanBehavior);
    }

    private void paintHBA() {
        DBManager dbManager = new DBManager(this); // 一定要放这里才能读到上下文
        String[] titles = { "June" };
        List<double[]> values = new ArrayList<double[]>();
        double[] tempdoubles = new double[24];
        int[] hba = new int[24];
        double maxcount = 0;
        HashMap<Integer, Integer> hashMap = dbManager.queryHumanBehavior();
        for (int i = 0; i < hashMap.size(); i++) {
            tempdoubles[i] = hashMap.get(i);
            if (tempdoubles[i] > maxcount) {
                maxcount = tempdoubles[i];
            }
        }
        for (int i = 0; i < tempdoubles.length; i++) {
            if (tempdoubles[i] / maxcount > THRESHOLD_RATIO) {
                hba[i] = 1;
            } else {
                hba[i] = 0;
            }
        }
        Algorithm_AC.HumanBehavior=hba;
        //saveHumanBehavior(hba);
        values.add(tempdoubles);
        // values.add(new double[]{2,3,4});
        XYMultipleSeriesDataset dataset = adChart.buildBarDataset(titles,
                values);
        int[] colors = { Color.BLUE };
        XYMultipleSeriesRenderer renderer = adChart.buildBarRenderer(colors);
        Type type = Type.DEFAULT;

        // renderer.setZoomEnabled(false);//怎么失效了----使用下面的方式
        renderer.setZoomEnabled(false, false);// 成功控制--嘿嘿
        adChart.setChartSettings(renderer, "Human Electric Pattern Analysis For AC",
                "Time/h", "Minutes/m", 0, 25, 0, 1600, Color.BLACK, Color.BLACK);
        renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
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
        renderer.setFitLegend(true);// 调整合适的位置

        renderer.setMarginsColor(Color.WHITE);
        renderer.setBackgroundColor(Color.WHITE);
        renderer.setXLabelsColor(Color.BLACK);
        renderer.setYLabelsColor(0, Color.BLACK);
        renderer.setLegendTextSize(40f);
        graphicalView = ChartFactory.getBarChartView(getBaseContext(), dataset,
                renderer, type);// 柱状图
        LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
        layout.removeAllViews();
        layout.setBackgroundColor(Color.WHITE);
        layout.addView(graphicalView, new LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    }

    private void paintEnergyDivision() {
        DBManager dbManager = new DBManager(this); // 一定要放这里才能读到上下文
        HashMap<String, Double> hashMap = dbManager.queryElecUsageDivision();
        Iterator iter = hashMap.entrySet().iterator();
        int arrsize = hashMap.size();
        double[] values = new double[arrsize+1];
        String[] legends = new String[arrsize+1];
        int index = 0;
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            if (key.equals("MeterLine3"))
            {
                key = "AC";
                Double val = (Double) entry.getValue();
                values[index] = val*0.6;
                legends[index] = key;
                values[index+1]=val*0.4;
                legends[index+1]="WH";
                index+=2;
            }
            else if (key.equals("MeterLine2"))
            {
                key = "refrigerator";
                Double val = (Double) entry.getValue();
                values[index] = val;
                legends[index] = key;
                index++;
            }
            else {
                key = "others";
                Double val = (Double) entry.getValue();
                values[index] = val;
                legends[index] = key;
                index++;
            }

        }
        int[] colors = { Color.BLUE, Color.GREEN, Color.RED,Color.MAGENTA };
        CategorySeries dataset = adChart.buildCategoryDataset(
                "Electric Division", legends, values);

        DefaultRenderer renderer = adChart.buildCategoryRenderer(colors);
        renderer.setChartTitle("Electric Division");

        renderer.setPanEnabled(false);// 设置是否允许平移
        renderer.setZoomEnabled(false);// 成功控制--嘿嘿
        renderer.setFitLegend(true);
        renderer.setAxesColor(Color.BLACK);
        renderer.setLabelsColor(Color.BLACK);
        renderer.setZoomRate(0.5f);
        View pieChartView = ChartFactory.getPieChartView(this, dataset,
                renderer);
        LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
        layout.setBackgroundColor(Color.WHITE);
        layout.removeAllViews();
        // layout.setBackgroundColor(Color.WHITE);
        layout.addView(pieChartView, new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT));

    }

    private void paintEConsumption() {
        String[] titles = { "Cost" };
        DBManager dbManager = new DBManager(this); // 一定要放这里才能读到上下文
        HashMap<Integer, Double> hashMap = dbManager.queryMonthlyUsage();
        List<double[]> values = new ArrayList<double[]>();
        int count = 0;
        for (int i = 1; i < 13; i++) {
            Double tempDouble = hashMap.get(i);
            if (tempDouble != null) {
                count++;
            }
        }
        double[] tempdoubles = new double[count];
        int[] months = new int[count];
        int index = 0;
        int minmonth = 1;
        int maxmmonth = 1;
        double maxq = 0;
        for (int i = 1; i < 13; i++) {
            Double tempDouble = hashMap.get(i);
            boolean isfirst = false;
            if (tempDouble != null) {
                if (maxq < tempDouble)
                    maxq = tempDouble;
                if (isfirst == false) {
                    minmonth = i;
                    isfirst = true;
                }
                tempdoubles[index] = tempDouble.doubleValue();
                months[index] = i;
                index++;
                maxmmonth = i;
            }
        }

        values.add(tempdoubles);
        // values.add(new double[]{2,3,4});
        XYMultipleSeriesDataset dataset = adChart.buildBarDataset(titles,
                values);
        int[] colors = { Color.BLUE };
        XYMultipleSeriesRenderer renderer = adChart.buildBarRenderer(colors);
        Type type = Type.DEFAULT;

        NumberFormat nnf = NumberFormat.getNumberInstance();
        nnf.setMinimumIntegerDigits(1);
        nnf.setMaximumFractionDigits(2);
        for (int i = 0; i < renderer.getSeriesRenderers().length; i++)
            renderer.getSeriesRenderers()[0].setChartValuesFormat(nnf);

        // renderer.setZoomEnabled(false);//怎么失效了----使用下面的方式
        renderer.setZoomEnabled(false, false);// 成功控制--嘿嘿
        adChart.setChartSettings(renderer, "Human Monthly Energy Analysis",
                "Month", "Power/kWh", 0, 5, 0, 200, Color.BLACK, Color.BLACK);
        renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
        // renderer.getSeriesRendererAt(1).setDisplayChartValues(true);
        renderer.setXLabels(0);// 设置x轴上的下标数量
        renderer.setYLabels(10); // 设置y轴上的下标数量
        renderer.setXLabelsAlign(Align.RIGHT);
        renderer.setYLabelsAlign(Align.LEFT);// y轴 数字表示在坐标还是右边
        renderer.setPanEnabled(false, false);// 设置是否允许平移
        for (int i = 0; i < index; i++) {
            renderer.addXTextLabel(i + 1, String.valueOf(months[i]));
        }
        // renderer.addXTextLabel(2.0, "220kv电力线");// 在指定坐标处显示文字
        // renderer.addXTextLabel(3.0, "220kv电力线");// 在指定坐标处显示文字
        // renderer.clearXTextLabels();//清除 labels
        // renderer.setZoomRate(2.1f);//设置放缩比
        renderer.setBarSpacing(2f);// 设置柱状的间距
        // renderer.setLabelsTextSize(30);//设置坐标轴上数字的大小
        renderer.setXLabelsAngle(30.0f);// 设置文字旋转角度 对文字顺时针旋转
        renderer.setXLabelsPadding(10);// 设置文字和轴的距离
        renderer.setFitLegend(true);// 调整合适的位置
        renderer.setLegendTextSize(40f);
        graphicalView = ChartFactory.getBarChartView(getBaseContext(), dataset,
                renderer, type);// 柱状图
        LinearLayout layout = (LinearLayout) findViewById(R.id.chart);
        layout.removeAllViews();
        layout.setBackgroundColor(Color.WHITE);
        layout.addView(graphicalView, new LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.zhu_chart, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_energydivision:
                paintEnergyDivision();
                return true;
            case R.id.action_hba:
                paintHBA();
                return true;
            case R.id.action_energyconsumption:
                paintEConsumption();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View v) {
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { // 按下的如果是BACK，同时没有重复
//            // do something here
//            Intent intent = new Intent();
//            intent.setClass(this, MenuActivity.class);
//            this.finish();
//            this.startActivity(intent);
//            return true;
//        } else {
//            return false;
//        }
//        // return super.onKeyDown(keyCode, event);
//    }

    private class ImageAdpter extends BaseAdapter {
        private Context context;
        private int width = 100;
        private int height = 100;
        private Integer[] images = {
                // R.drawable.menu_devicesetting,
                R.drawable.menu_onlineidentify,
                R.drawable.menu_offlineidentify, R.drawable.optimizemenu,
                R.drawable.menu_loadinfo,
                // R.drawable.menu_about_us
        };

        private String[] texts; // 对应图片的文本内容

        public ImageAdpter(Context context, String[] str) {
            this.context = context;
            texts = str;
        }

        public int getCount() {
            return images.length;
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int id) {
            return id;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout linearLayout;
            linearLayout = new LinearLayout(this.context);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            linearLayout.setGravity(Gravity.CENTER);
            ImageView iv = new ImageView(this.context);
            iv.setImageResource(images[position]);
            iv.setLayoutParams(new AbsListView.LayoutParams(width, height));

            linearLayout.addView(iv); // 添加照片的VIEW

            TextView tv = new TextView(this.context);
            tv.setText(texts[position]);
            tv.setTextColor(Color.BLACK);
            tv.setTextSize(15);
            tv.setGravity(Gravity.CENTER_HORIZONTAL);
            tv.setPadding(0, 20, 0, 30);
            linearLayout.addView(tv); // 添加文字的VIEW
            return linearLayout;

        }

    }

}
