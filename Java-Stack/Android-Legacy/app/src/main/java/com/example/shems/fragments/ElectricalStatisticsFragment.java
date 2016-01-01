package com.example.shems.fragments;


import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.smarthome.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class ElectricalStatisticsFragment extends Fragment {
    private Context context;
    private View view;
    private BarChart statisticsBarChart;
    private TextView comparisonLastYearTextView;
    private TextView comparisonLastMonthTextView;
    private TextView comparisonUsersTextView;
    private TextView hintTypeTextView;
    private TextView hintNickNameTextView;
    private RatingBar ratingBar;

    public ElectricalStatisticsFragment() {
        // Required empty public constructor
    }

    private void findViews() {
        this.statisticsBarChart = (BarChart) view.findViewById(R.id.chart_statistics);
        this.comparisonLastMonthTextView= (TextView) view.findViewById(R.id.txt_hint_comparison_last_month);
        this.comparisonLastYearTextView= (TextView) view.findViewById(R.id.txt_hint_comparison_last_year);
        this.comparisonUsersTextView= (TextView) view.findViewById(R.id.txt_hint_comparison_users);
        this.hintTypeTextView= (TextView) view.findViewById(R.id.txt_hint_statistics_type);
        this.hintNickNameTextView= (TextView) view.findViewById(R.id.txt_hint_comparison_nickname);
        this.ratingBar= (RatingBar) view.findViewById(R.id.rating_bar_electrical_statistics);
    }

    private void initBartChart() {
        statisticsBarChart.setDescription("");
        // scaling can now only be done on x- and y-axis separately
        statisticsBarChart.setPinchZoom(false);
        statisticsBarChart.setDrawBarShadow(false);
        statisticsBarChart.setDrawGridBackground(false);

        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");

        Legend l = statisticsBarChart.getLegend();
        l.setEnabled(false);
//        l.setPosition(Legend.LegendPosition.BELOW_CHART_LEFT);
//        l.setTypeface(tf);
//        l.setTextSize(12f);

        XAxis xl = statisticsBarChart.getXAxis();
        xl.setPosition(XAxis.XAxisPosition.BOTTOM);
        xl.setTypeface(tf);
        xl.setTextSize(12f);
        xl.setDrawGridLines(false);
        xl.setLabelsToSkip(0);

        statisticsBarChart.getAxisLeft().setEnabled(false);
        statisticsBarChart.getAxisRight().setEnabled(false);
    }

    private void bindData2BarChart() {
        ArrayList<String> xValList = new ArrayList<String>();
        xValList.add(getString(R.string.last_year));
        xValList.add(getString(R.string.last_month));
        xValList.add(getString(R.string.current_month));
        ArrayList<BarEntry> yValFormerMonthList = new ArrayList<BarEntry>();
        int i = 0;
        yValFormerMonthList.add(new BarEntry(1, i));
        i++;
        yValFormerMonthList.add(new BarEntry(2, i));
        i++;
        yValFormerMonthList.add(new BarEntry(3, i));

        BarDataSet setFormer = new BarDataSet(yValFormerMonthList, getString(R.string.txt_former_month));
        setFormer.setBarSpacePercent(40f);
        setFormer.setColors(new int[]{Color.rgb(255, 249, 133),Color.rgb(196, 255, 134),Color.rgb(136,235,255)});

        ArrayList<BarDataSet> dataSets = new ArrayList<BarDataSet>();
        dataSets.add(setFormer);

        BarData data = new BarData(xValList, dataSets);

        data.setValueTextSize(12f);


        data.setGroupSpace(80f);
        Typeface tf = Typeface.createFromAsset(getActivity().getAssets(), "OpenSans-Regular.ttf");
        data.setValueTypeface(tf);

        statisticsBarChart.setData(data);
        statisticsBarChart.invalidate();
    }

    private void initComparisonTextView(TextView textView){
        int start;
        int end;
        SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder();
        end=spannableStringBuilder.length();
        spannableStringBuilder.append(context.getString(R.string.hint_increase));
        start=end;
        end=spannableStringBuilder.length();
        spannableStringBuilder.setSpan(new ForegroundColorSpan(Color.RED),start,end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append("20" ).append(context.getString(R.string.unit_percent));
        start=end;
        end=spannableStringBuilder.length();
        spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(50),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableStringBuilder);
    }

    private void initComparisonUsersTextView(double percentage){
        int start;
        int end;
        String[]strings=context.getString(R.string.hint_beat_xxx_users).split("X+");
        SpannableStringBuilder spannableStringBuilder=new SpannableStringBuilder(strings[0]);
        end=spannableStringBuilder.length();
        spannableStringBuilder.append(String.valueOf(percentage)).append(context.getString(R.string.unit_percent));
        start=end;
        end=spannableStringBuilder.length();
        spannableStringBuilder.setSpan(new StyleSpan(Typeface.BOLD),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.setSpan(new AbsoluteSizeSpan(50),start,end,Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableStringBuilder.append(strings[1]);
        comparisonUsersTextView.setText(spannableStringBuilder);
    }

    private void initTextViews(){
        initComparisonTextView(comparisonLastYearTextView);
        initComparisonTextView(comparisonLastMonthTextView);
        initComparisonUsersTextView(19.5);
        hintTypeTextView.setText(context.getString(R.string.your_home)+context.getString(R.string.electrical_consumption));
        hintNickNameTextView.setText("节能达人");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_electrical_statistics, container, false);
        context=getActivity();
        findViews();
        initBartChart();
        bindData2BarChart();
        initTextViews();
        ratingBar.setRating(  ratingBar.getNumStars()*0.7f);
        return view;

    }

//    public interface IGetElectricalStatistics{
//        String getBarGraphTitle();
//        BarDataSet getBarDataSet();
//        double getDefeatPercentNumber();
//        String getNickName();
//
//    }

}
