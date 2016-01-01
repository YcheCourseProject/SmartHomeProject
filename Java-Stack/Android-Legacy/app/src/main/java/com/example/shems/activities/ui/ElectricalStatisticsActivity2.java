package com.example.shems.activities.ui;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.shems.models.electrial_statistics.CO2EmissionStatistics;
import com.example.shems.models.electrial_statistics.EAverageCostStatistics;
import com.example.shems.models.electrial_statistics.EConsumptionStatistics;
import com.example.shems.models.electrial_statistics.ECostStatistics;
import com.example.shems.models.electrial_statistics.ElectricalStatistics;
import com.example.smarthome.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ElectricalStatisticsActivity2 extends MyActionBarActivity {
    public static int RATE_DOLLAR2RMB=6;
    ExpandableListView expandableListView;

    private void findViews() {
        this.expandableListView = (ExpandableListView) findViewById(R.id.expandable_view_electrical_statistics);
    }

    private boolean isZh() {
        Locale locale = getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.endsWith("zh"))
            return true;
        else
            return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_electrical_statistics_activity2);
        setActionBarTitle(getString(R.string.title_activity_electrical_statistics));
        findViews();
        List<ElectricalStatistics> electricalStatisticList = new ArrayList<ElectricalStatistics>(4);
        double lastYearECost=20.6;
        double lastYearEConsumption=408;
        double lastYearUnitCost=0.0504;
        double lastYearCO2Emission=lastYearEConsumption*0.9;

        double rate1=1+0.1*Math.random();
        double rate2=1+0.1*Math.random();
        double lastMonthECost=lastYearECost*rate1;
        double lastMonthEConsumption=lastYearEConsumption*rate2;
        double lastMonthUnitCost=rate1/rate2*lastYearUnitCost;
        double lastMonthCO2Emission=lastMonthEConsumption*0.9;

        double currentECost= 18.6;
        double currentEConsumption=495;
        double currentUnitCost=0.0376;
        double currentCO2Emission=Math.min(lastYearCO2Emission,lastMonthCO2Emission)-60.5;
        ElectricalStatistics electricalStatistics = new EConsumptionStatistics(this, currentEConsumption, lastMonthEConsumption, lastYearEConsumption);
        electricalStatisticList.add(electricalStatistics);

        if (isZh()) {
            electricalStatistics = new ECostStatistics(this, currentECost*RATE_DOLLAR2RMB, lastMonthECost*RATE_DOLLAR2RMB, lastYearECost*RATE_DOLLAR2RMB);
            electricalStatisticList.add(electricalStatistics);
            electricalStatistics = new EAverageCostStatistics(this,currentUnitCost*RATE_DOLLAR2RMB,lastMonthUnitCost*RATE_DOLLAR2RMB, lastYearUnitCost*RATE_DOLLAR2RMB);
            electricalStatisticList.add(electricalStatistics);
        }
        else {
            electricalStatistics = new ECostStatistics(this,currentECost, lastMonthECost,lastYearECost);
            electricalStatisticList.add(electricalStatistics);
            electricalStatistics = new EAverageCostStatistics(this,currentUnitCost, lastMonthUnitCost, lastYearUnitCost);
            electricalStatisticList.add(electricalStatistics);
        }

        electricalStatistics = new CO2EmissionStatistics(this, currentCO2Emission,lastMonthCO2Emission,lastYearCO2Emission);
        electricalStatisticList.add(electricalStatistics);

        MyExpandableAdapter myExpandableAdapter = new MyExpandableAdapter(electricalStatisticList, this);
        expandableListView.setAdapter(myExpandableAdapter);
        expandableListView.setGroupIndicator(null);
    }

    private class MyExpandableAdapter extends BaseExpandableListAdapter {
        private static final int CHILDREN_COUNT = 1;
        private LayoutInflater layoutInflater;
        private List<ElectricalStatistics> electricalStatisticsList;
        private Context context;

        private MyExpandableAdapter(List<ElectricalStatistics> electricalStatisticsList, Context context) {
            this.layoutInflater = LayoutInflater.from(context);
            this.electricalStatisticsList = electricalStatisticsList;
            this.context = context;
        }

        public int getGroupCount() {
            return electricalStatisticsList.size();
        }

        public int getChildrenCount(int groupPosition) {
            return CHILDREN_COUNT;
        }

        public Object getGroup(int groupPosition) {
            return electricalStatisticsList.get(groupPosition);
        }

        public Object getChild(int groupPosition, int childPosition) {
            return electricalStatisticsList.get(groupPosition);
        }

        public long getGroupId(int groupPosition) {
            return groupPosition;
        }

        public long getChildId(int groupPosition, int childPosition) {
            return groupPosition - 1 + childPosition;
        }

        public boolean hasStableIds() {
            return false;
        }

        private String getFormatDouble(double doubleNumber) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            return decimalFormat.format(doubleNumber) + "\b\b";
        }

        private String getPercentageString(double doubleNUmber) {
            return getFormatDouble(doubleNUmber * 100) + "%";
        }

        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
            GroupViewHolder groupViewHolder;
            View view = convertView;
            if (view == null) {
                groupViewHolder = new GroupViewHolder();
                view = layoutInflater.inflate(R.layout.item_group_electrical_statistics, null);
                groupViewHolder.descriptionTextView = (TextView) view.findViewById(R.id.txt_group_electrical_statistics_description);
                groupViewHolder.descriptionValTextView = (TextView) view.findViewById(R.id.txt_group_description_value);
                groupViewHolder.rankNickNameTextView = (TextView) view.findViewById(R.id.txt_group_nickname);
                groupViewHolder.imageView = (ImageView) view.findViewById(R.id.img_group_electrical_statistics_hint);
                view.setTag(groupViewHolder);
            } else {
                groupViewHolder = (GroupViewHolder) view.getTag();
            }

            if (isExpanded == true) {
                groupViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.down_arrow));
            } else {
                groupViewHolder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.right_arrow));
            }
            groupViewHolder.descriptionTextView.setText(electricalStatisticsList.get(groupPosition).getDescription());
            groupViewHolder.descriptionValTextView.setText(getFormatDouble(electricalStatisticsList.get(groupPosition).getCurrentMonthVal()) + electricalStatisticsList.get(groupPosition).getUnit());
            groupViewHolder.rankNickNameTextView.setText("" + electricalStatisticsList.get(groupPosition).getRankNickName());
            return view;
        }

        class GroupViewHolder {
            TextView descriptionTextView;
            TextView descriptionValTextView;
            TextView rankNickNameTextView;
            ImageView imageView;
        }

        private void initBartChart(BarChart statisticsBarChart) {
            statisticsBarChart.setDescription("");
            // scaling can now only be done on x- and y-axis separately
            statisticsBarChart.setPinchZoom(false);
            statisticsBarChart.setDrawBarShadow(false);
            statisticsBarChart.setDrawGridBackground(false);

            Typeface tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");

            Legend l = statisticsBarChart.getLegend();
            l.setEnabled(false);

            XAxis xl = statisticsBarChart.getXAxis();
            xl.setPosition(XAxis.XAxisPosition.BOTTOM);
            xl.setTypeface(tf);
            xl.setTextSize(12f);
            xl.setDrawGridLines(false);
            xl.setLabelsToSkip(0);

            statisticsBarChart.getAxisLeft().setEnabled(false);
            statisticsBarChart.getAxisRight().setEnabled(false);
        }

        private void bindData2BarChart(BarChart statisticsBarChart, int groupPosition) {
            ArrayList<String> xValList = new ArrayList<String>();
            xValList.add(getString(R.string.last_year));
            xValList.add(getString(R.string.last_month));
            xValList.add(getString(R.string.current_month));
            ArrayList<BarEntry> yValFormerMonthList = new ArrayList<BarEntry>();
            int i = 0;
            yValFormerMonthList.add(new BarEntry((float) electricalStatisticsList.get(groupPosition).getFormerYearVal(), i));
            i++;
            yValFormerMonthList.add(new BarEntry((float) electricalStatisticsList.get(groupPosition).getFormerMonthVal(), i));
            i++;
            yValFormerMonthList.add(new BarEntry((float) electricalStatisticsList.get(groupPosition).getCurrentMonthVal(), i));

            BarDataSet setFormer = new BarDataSet(yValFormerMonthList, electricalStatisticsList.get(groupPosition).getDescription());
            setFormer.setBarSpacePercent(40f);
            setFormer.setColors(new int[]{Color.rgb(255, 249, 133), Color.rgb(196, 255, 134), Color.rgb(136, 235, 255)});

            ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
            dataSets.add(setFormer);
            BarData data = new BarData(xValList, dataSets);

            data.setValueTextSize(12f);
            data.setGroupSpace(80f);
            Typeface tf = Typeface.createFromAsset(context.getAssets(), "OpenSans-Regular.ttf");
            data.setValueTypeface(tf);

            statisticsBarChart.setData(data);
            statisticsBarChart.invalidate();
        }

        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
            ChildViewHolder childViewHolder;
            View view = convertView;
            if (view == null) {
                childViewHolder = new ChildViewHolder();
                view = layoutInflater.inflate(R.layout.item_child_electrical_statistics, null);
                childViewHolder.barChart = (BarChart) view.findViewById(R.id.chart_statistics);
                childViewHolder.barChartTitleTextView = (TextView) view.findViewById(R.id.txt_chart_title);
                childViewHolder.descriptionTextView = (TextView) view.findViewById(R.id.txt_hint_statistics_type);
                childViewHolder.yearChangeTextView = (TextView) view.findViewById(R.id.txt_hint_comparison_last_year);
                childViewHolder.monthChangeTextView = (TextView) view.findViewById(R.id.txt_hint_comparison_last_month);
                childViewHolder.ratingBar = (RatingBar) view.findViewById(R.id.rating_bar_electrical_statistics);
                view.setTag(childViewHolder);
            } else {
                childViewHolder = (ChildViewHolder) view.getTag();
            }
            initBartChart(childViewHolder.barChart);
            bindData2BarChart(childViewHolder.barChart, groupPosition);
            ElectricalStatistics electricalStatistics = electricalStatisticsList.get(groupPosition);
            childViewHolder.barChartTitleTextView.setText(electricalStatistics.getDescription());
            childViewHolder.descriptionTextView.setText(context.getString(R.string.your_home) + " " + electricalStatistics.getDescription());
            if (electricalStatistics.getChangeRateByMonth().isIncrease())
                childViewHolder.monthChangeTextView.setText(context.getString(R.string.hint_increase) + getPercentageString(electricalStatistics.getChangeRateByMonth().getChangeRate()));
            else
                childViewHolder.monthChangeTextView.setText(context.getString(R.string.hint_decrease) + getPercentageString(electricalStatistics.getChangeRateByMonth().getChangeRate()));
            if (electricalStatistics.getChangeRateByYear().isIncrease())
                childViewHolder.yearChangeTextView.setText(context.getString(R.string.hint_increase) + getPercentageString(electricalStatistics.getChangeRateByYear().getChangeRate()));
            else
                childViewHolder.yearChangeTextView.setText(context.getString(R.string.hint_decrease) + getPercentageString(electricalStatistics.getChangeRateByYear().getChangeRate()));
            childViewHolder.ratingBar.setRating((float) electricalStatistics.getOverallLevel() / 100 * childViewHolder.ratingBar.getNumStars());
            return view;
        }

        class ChildViewHolder {
            BarChart barChart;
            TextView barChartTitleTextView;
            TextView descriptionTextView;
            TextView yearChangeTextView;
            TextView monthChangeTextView;
            RatingBar ratingBar;
        }

        public boolean isChildSelectable(int groupPosition, int childPosition) {
            return false;
        }
    }

}
