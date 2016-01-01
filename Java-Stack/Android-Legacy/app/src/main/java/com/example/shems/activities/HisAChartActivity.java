package com.example.shems.activities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.TimeSeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;


import com.example.shems.daos.sqllite.HisLogDB;
import com.example.smarthome.R;

import android.os.Bundle;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HisAChartActivity extends FatherActivity {

    final static int POINT_LENGTH=700;
    LinearLayout LinearLayout_Achart;
    Cursor cursor;
    HisLogDB logdb;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //首先:get db data:
        logdb=new HisLogDB(this);
        cursor=logdb.selectAllForTimeStamp();
        List<Date> timelist=new ArrayList<Date>();
        List<Float> aplist=new ArrayList<Float>();
        List<Float> rplist=new ArrayList<Float>();

        Log.i("info", "cursor");
        int cursorcount=cursor.getCount();
        while(cursor.moveToNext())
        {
            long long_time=cursor.getLong(0);
            //Log.i("pos", cursor.getPosition()+"");
            timelist.add(new Date(long_time));
            //Log.i("date", new Date(long_time).toLocaleString());
            aplist.add(cursor.getFloat(1));
            rplist.add(cursor.getFloat(2));

            //Log.i("flaot", cursor.getFloat(1)+"");
        }
        if(cursorcount>POINT_LENGTH)
            cursorcount=POINT_LENGTH;
        //然后：首先获得数据
        Date[] dates0=new Date[cursorcount];
        double[] doubles_ap=new double[cursorcount];
        double[] doubles_rp=new double[cursorcount];
        for(int i=0;i<cursorcount;i++)
        {
            dates0[i]=timelist.get(i);
            doubles_ap[i]=aplist.get(i);
            doubles_rp[i]=rplist.get(i);
        }
        List timell=new ArrayList();
        List datall=new ArrayList();
        timell.add(dates0);
        timell.add(dates0);
        datall.add(doubles_ap);
        datall.add(doubles_rp);
        XYMultipleSeriesDataset dataset=this.buildDateDataset(new String[]{"ap","rp"}, timell, datall);
        //然后样式
        PointStyle[] styles = new PointStyle[] { PointStyle.CIRCLE,PointStyle.CIRCLE};
        int[] colors = new int[] { Color.RED,Color.GREEN };

        XYMultipleSeriesRenderer renderer=this.buildRenderer(colors, styles, false);
        renderer.setXLabels(10);
        renderer.setYLabels(10);
        renderer.setShowGrid(true);
        renderer.setXLabelsAlign(Align.CENTER);
        renderer.setYLabelsAlign(Align.RIGHT);
        //然后坐标的显示

        this.setChartSettings(renderer, "Load info Chart", "Date /m/d", "Active Power/W", dates0[dates0.length-1].getTime(), dates0[0].getTime(), -50, 50 , Color.WHITE, Color.WHITE);

        //Log.i("form fail", "fail");
        View chart=ChartFactory.getTimeChartView(this, dataset, renderer,  "M/d HH:mm") ;
        setContentView(chart);


    }

    protected XYMultipleSeriesDataset buildDataset(String[] titles,
                                                   List xValues,
                                                   List yValues)
    {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length; //有几条线
        for (int i = 0; i < length; i++)
        {
            XYSeries series = new XYSeries(titles[i]); //根据每条线的名称创建
            double[] xV = (double[]) xValues.get(i); //获取第i条线的数据
            double[] yV = (double[]) yValues.get(i);
            int seriesLength = xV.length; //有几个点

            for (int k = 0; k < seriesLength; k++) //每条线里有几个点
            {
                series.add(xV[k], yV[k]);
            }
            dataset.addSeries(series);
        }
        return dataset;
    }

    protected XYMultipleSeriesRenderer buildRenderer(int[] colors, PointStyle[] styles, boolean fill)
    {
        XYMultipleSeriesRenderer renderer = new XYMultipleSeriesRenderer();
        int length = colors.length;
        for (int i = 0; i < length; i++)
        {
            XYSeriesRenderer r = new XYSeriesRenderer();
            r.setColor(colors[i]);
            r.setPointStyle(styles[i]);
            r.setFillPoints(fill);
            renderer.addSeriesRenderer(r);
        }
        return renderer;
    }
    protected void setChartSettings(XYMultipleSeriesRenderer renderer, String title,
                                    String xTitle,String yTitle, double xMin,
                                    double xMax, double yMin, double yMax,
                                    int axesColor,int labelsColor)
    {
        renderer.setChartTitle(title);
        renderer.setLegendTextSize(15);
        renderer.setXTitle(xTitle);
        renderer.setYTitle(yTitle);
        renderer.setXAxisMin(xMin);
        renderer.setXAxisMax(xMax);
        renderer.setYAxisMin(yMin);
        renderer.setYAxisMax(yMax);
        renderer.setAxesColor(axesColor);
        renderer.setLabelsColor(labelsColor);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.his_achart, menu);

        return true;
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if(keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
//        {
//            Intent intent=new Intent();
//            intent.setClass(this, ShowLogActivity.class);
//            this.startActivity(intent);
//            this.finish();
//        }
//        return super.onKeyDown(keyCode, event);
//
//    }

    protected XYMultipleSeriesDataset buildDateDataset(String[] titles, List<Date[]> xValues,
                                                       List<double[]> yValues) {
        XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
        int length = titles.length;
        for (int i = 0; i < length; i++) {

            TimeSeries series = new TimeSeries(titles[i]);
            Date[] xV = xValues.get(i);
            double[] yV = yValues.get(i);
            int seriesLength = xV.length;
            Log.i("info", xV.length+"");
            Log.i("pointer",(series==null)+"");
            for (int k = 0; k < seriesLength; k++) {
                Log.i("pointer??",(series==null)+""+xV[k]+" "+yV[k]);
                series.add(xV[k], yV[k]);
            }
            dataset.addSeries(series);
        }
        Log.i("kao", "kao");
        return dataset;
    }
}
