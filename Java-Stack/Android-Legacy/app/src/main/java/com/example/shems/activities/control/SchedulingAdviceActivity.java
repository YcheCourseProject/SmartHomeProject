package com.example.shems.activities.control;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;

import com.example.shems.util.UserDefined;
import com.example.shems.views.AbstractDemoChart;
import com.example.shems.views.TableView2;
import com.example.smarthome.R;


import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class SchedulingAdviceActivity extends Activity {
	AbstractDemoChart adChart = new AbstractDemoChart();
	GraphicalView graphicalView;
	LinearLayout layout_pricechart;
	LinearLayout layout_schedulingtable;
	LinearLayout layout_wholescheduiling;
	TableView2 tableView;
	Spinner regionSpinner; 
	private ArrayAdapter<String> adapter;
	
	double[] costs = new double[24];
	double[] e_prices = { 0.02016, 0.01834, 0.01813, 0.01789, 0.01862, 0.02106,
			0.02524, 0.02676, 0.02755, 0.02798, 0.02672, 0.02604, 0.02556,
			0.02497, 0.02474, 0.02504, 0.03054, 0.03036, 0.02843, 0.02693,
			0.02606, 0.02422, 0.02339, 0.02085, };

//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		// TODO Auto-generated method stub
//		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//			Intent intent = new Intent();
//			intent.setClass(this, CtrlMainActivity.class);
//			this.startActivity(intent);
//			this.finish();
//		}
//		return super.onKeyDown(keyCode, event);
//	}
	private void paintPrices() {

		String[] titles = { "Day-ahead E-Price" };
		List<double[]> values = new ArrayList<double[]>();
		values.add(e_prices);
		XYMultipleSeriesDataset dataset = adChart.buildBarDataset(titles,
				values);
		int[] colors = { Color.BLUE };
		XYMultipleSeriesRenderer renderer = adChart.buildBarRenderer(colors);
		Type type = Type.DEFAULT;
		renderer.setZoomEnabled(false, false);// �ɹ�����--�ٺ�
		adChart.setChartSettings(renderer,
				"Tomorrow Electric Price /Penny", "Time/o'clock",
				"", 0, 25, 0,0.036*100, Color.BLACK, Color.BLACK);
		renderer.setChartTitleTextSize(50.0f);
		renderer.setAxisTitleTextSize(30.0f);
		renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
		renderer.setXLabels(0);// ����x���ϵ��±�����
		renderer.setYLabels(10); // ����y���ϵ��±�����
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);// y�� ���ֱ�ʾ�����껹���ұ�
		renderer.setDisplayChartValues(false);
		renderer.setPanEnabled(true,false );// �����Ƿ�����ƽ��
		for (int i = 0; i < 25; i++) {
			if(i%3==0)
				renderer.addXTextLabel(i, String.valueOf(i));
		}
		renderer.setBarSpacing(0.1f);// ������״�ļ��
		renderer.setXLabelsAngle(0.0f);// ����������ת�Ƕ� ������˳ʱ����ת
		renderer.setXLabelsPadding(10);// �������ֺ���ľ���
		renderer.setFitLegend(true);// �������ʵ�λ��
		renderer.setMargins(new int[]{50,50,50,50});		
		renderer.setXLabelsColor(Color.BLACK);
		renderer.setYLabelsColor(0, Color.BLACK);
		renderer.setLegendTextSize(30f);
	 
		graphicalView = ChartFactory.getBarChartView(getBaseContext(), dataset,
				renderer, type);// ��״ͼ
	
		layout_pricechart.removeAllViews();
		layout_pricechart.setBackgroundColor(Color.WHITE);
		layout_pricechart.addView(graphicalView, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	}

	private void initSpinner()
	{
		String[]regionsStrings={"illinois"};
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,regionsStrings);      
        //���������б�ķ��
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);  
        
        regionSpinner.setAdapter(adapter);        
        regionSpinner.setVisibility(View.VISIBLE);
	}
	private void paintNotes()
	{
		
		LayoutInflater flater = LayoutInflater.from(this);
		LinearLayout layout = (LinearLayout) flater.inflate(R.layout.item_description_imageandtxt, null);
		layout_wholescheduiling.removeAllViews();
		TextView imageTextView=(TextView) layout.findViewById(R.id.textView_image);
		TextView desTextView=(TextView) layout.findViewById(R.id.textView_description);
		for(int i=0;i< UserDefined.strings.length;i++)
		{
			imageTextView.setBackgroundColor(Color.rgb(UserDefined.rgbs[i][0], UserDefined.rgbs[i][1], UserDefined.rgbs[i][2]));
			desTextView.setText(UserDefined.strings[i]);
			layout_wholescheduiling.addView(layout,i);
		 
			layout = (LinearLayout) flater.inflate(R.layout.item_description_imageandtxt, null);
			  imageTextView=(TextView) layout.findViewById(R.id.textView_image);
			  desTextView=(TextView) layout.findViewById(R.id.textView_description);
		}

	}
	private void findViews() {

		layout_schedulingtable = (LinearLayout) this
				.findViewById(R.id.layout_schedulingtable);
		layout_pricechart = (LinearLayout) this
				.findViewById(R.id.layout_pricecurve);
		layout_wholescheduiling=(LinearLayout) this.findViewById(R.id.layout_wholescheduling);
		regionSpinner=(Spinner) this.findViewById(R.id.Spinner_Region_Name);
	}

	private void initViews() {
		initSpinner();
		for(int i=0;i<e_prices.length;i++)
		{
			e_prices[i]*=100;
		}
		paintPrices();
		for (int i = 0; i < 18; i++) {
			costs[i] = -1;		//former impossible points
		}
		for(int i=18;i<24;i++)
		{
			costs[i]=-2;		//latter impossible points
		}
		costs[14]=0.055;
		costs[15]=0.052;
		costs[16]=0.051;
		costs[17]=-1;
		costs[18]=0.057;	//demand time
		tableView = new TableView2(this, 4, 6, costs);
		layout_schedulingtable.removeAllViews();
		layout_schedulingtable.setBackgroundColor(Color.WHITE);
		layout_schedulingtable.addView(tableView, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
		paintNotes();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_scheduling_advice);
		findViews();
		initViews();
	 
	}


}
