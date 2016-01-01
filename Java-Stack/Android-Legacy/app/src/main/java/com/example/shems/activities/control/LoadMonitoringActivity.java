package com.example.shems.activities.control;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.GraphicalView;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint.Align;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.shems.util.UserDefined;
import com.example.shems.views.AbstractDemoChart;
import com.example.smarthome.R;

public class LoadMonitoringActivity extends Activity {
	AbstractDemoChart adChart = new AbstractDemoChart();
	LinearLayout loadMonitorLayout;
	LinearLayout loadCharacterLayout;
	LinearLayout loadnotesLayout;
	GraphicalView graphicalView;
	double[] ac = { 2.9, 12.1, 12, 2.2, 11, 16.9, 16.5, 10.8, 0.8, 11.7, 11.9,
			8.2, 4.7, 3.5, 8.2, 8.2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 6.5, 5.6, 12, 11.9, 0, 16.6, 0, 18.2, 0, 13.3,
			12.4, 12.7, };
	double[] wh = { 0, 0, 0, 0, 17, 5.1, 
			        0, 0, 0, 0, 22.3, 0,
			        23.2, 1.8, 0, 0,0, 29.5, 
			        0, 0, 0, 0, 0, 22.4, 
			        0, 0, 0, 22.5, 0, 0, 
			        0, 0, 0, 0, 112.5,11.9,
			        15, 0, 0, 0, 0, 0, 
			        0, 27, 0, 0, 0, 22.5, };
	double[] dish_washer = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1.1,
			13.7, 16.6, 9.2, 2.4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 4.4, 12.2, 17, 9.4, 0, 0, 0, 0, };
	double[] clothes_washer = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
			0, 0, 0, 0, 7.36, 7.89, 0, 0, 0, 0, };

	private double[] change48to24(double[]temp)
	{
		double []tempdouble=new double[temp.length/4];
		for(int i=0;i<tempdouble.length;i++)
		{
			tempdouble[i]=temp[4*i]+temp[4*i+1]+temp[4*i+2]+temp[4*i+3];
		}
		return tempdouble;
	}
	private void findViews() {
		loadMonitorLayout = (LinearLayout) this
				.findViewById(R.id.layout_loadmonitoring);
		loadCharacterLayout = (LinearLayout) this
				.findViewById(R.id.layout_loadcharacteristics);
		loadnotesLayout = (LinearLayout) this
				.findViewById(R.id.layout_notesforNIML);
	}

	private void initViews() {
		paintCurve();
		loadCharacterLayout.removeAllViews();
		LayoutInflater layoutInflater = LayoutInflater.from(this);
		for (int i = 0; i < 4; i++) {
			LinearLayout layout = (LinearLayout) layoutInflater.inflate(
					R.layout.item_loadcharacteristics, null);
			TextView textView0 = (TextView) layout
					.findViewById(R.id.textView_loadname);
			TextView textView1 = (TextView) layout
					.findViewById(R.id.textView_loadcharacteristic_image);
			ImageView imageView = (ImageView) layout
					.findViewById(R.id.imageView_load);			
			if (i == 0) {
				textView0.setText("Air-Conditioner");
				textView1.setBackgroundColor(Color.RED);
				imageView.setImageResource(R.drawable.airconditioner);
				
			} else if (i == 1) {
				textView0.setText("Water-Heater");
				textView1.setBackgroundColor(Color.RED);
				imageView.setImageResource(R.drawable.waterheater);
			}
			else if(i==2)
			{
				textView0.setText("Dish-Washer");
				textView1.setBackgroundColor(Color.rgb(UserDefined.rgb1[0], UserDefined.rgb1[1], UserDefined.rgb1[2]));
				imageView.setImageResource(R.drawable.dishwasher);
			}
			else {
				textView0.setText("Cloth-Washer");
				textView1.setBackgroundColor(Color.rgb(UserDefined.rgb1[0], UserDefined.rgb1[1], UserDefined.rgb1[2]));
				imageView.setImageResource(R.drawable.clothwasher);
			}
		
			loadCharacterLayout.addView(layout);
		}
		paintNotes();
	}

	private void paintNotes() {

		LayoutInflater flater = LayoutInflater.from(this);

		for (int i = 0; i < UserDefined.strings2.length; i++) {
			LinearLayout layout = (LinearLayout) flater.inflate(
					R.layout.item_description_imageandtxt, null);
			TextView imageTextView = (TextView) layout
					.findViewById(R.id.textView_image);
			TextView desTextView = (TextView) layout
					.findViewById(R.id.textView_description);
			imageTextView.setBackgroundColor(Color.rgb(UserDefined.rgbs2[i][0],
					UserDefined.rgbs2[i][1], UserDefined.rgbs2[i][2]));
			desTextView.setText(UserDefined.strings2[i]);
			loadnotesLayout.addView(layout, i);
		}

	}

	private void paintCurve() {
		for (int i = 0; i < ac.length; i++) {
			ac[i] /= 60.0;
			wh[i] /= 60.0;
			dish_washer[i] /= 60.0;
			clothes_washer[i] /= 60.0;
		}
		ac=change48to24(ac);
		wh=change48to24(wh);
		dish_washer=change48to24(dish_washer);
		clothes_washer=change48to24(clothes_washer);
		String[] titles = { "AC", "WH", "DW", "WM" };
		List<double[]> values = new ArrayList<double[]>();
		values.add(ac);
		values.add(wh);
		values.add(dish_washer);
		values.add(clothes_washer);
		XYMultipleSeriesDataset dataset = adChart.buildBarDataset(titles,
				values);
		int[] colors = { Color.RED, Color.GREEN, Color.BLUE, Color.CYAN };
		XYMultipleSeriesRenderer renderer = adChart.buildBarRenderer(colors);
		Type type = Type.DEFAULT;
		renderer.setZoomEnabled(false, false);// �ɹ�����--�ٺ�
		adChart.setChartSettings(renderer,
				"Loads Operation Statistics	/KWH", "Time/O'clock", "",
				0, 12 + 1, 0, 2.5, Color.BLACK, Color.BLACK);
		renderer.getSeriesRendererAt(0).setDisplayChartValues(true);
		renderer.setXLabels(0);// ����x���ϵ��±�����
		renderer.setYLabels(6); // ����y���ϵ��±�����
		renderer.setXLabelsAlign(Align.RIGHT);
		renderer.setYLabelsAlign(Align.RIGHT);// y�� ���ֱ�ʾ�����껹���ұ�
		renderer.setDisplayChartValues(false);
		renderer.setChartTitleTextSize(50.0f);
		renderer.setAxisTitleTextSize(30.0f);
		
		renderer.setPanEnabled(true, false);// �����Ƿ�����ƽ��
		 for (int i = 0; i < 25; i++) {
			 if((i+4)%2==0)
			 renderer.addXTextLabel(i, String.valueOf(i*2));
		 }
		renderer.setBarSpacing(0.0f);// ������״�ļ��
		renderer.setXLabelsAngle(0.0f);// ����������ת�Ƕ� ������˳ʱ����ת
		renderer.setXLabelsPadding(20);// �������ֺ���ľ���
		renderer.setFitLegend(true);// �������ʵ�λ��
		renderer.setMargins(new int[] { 50, 50, 50, 50 });
		// renderer.setMarginsColor(renderer);

		renderer.setXLabelsColor(Color.BLACK);
		renderer.setYLabelsColor(0, Color.BLACK);
		renderer.setLegendTextSize(40f);

		graphicalView = ChartFactory.getBarChartView(getBaseContext(), dataset,
				renderer, type);// ��״ͼ
		// graphicalView.setBackgroundColor(Color.WHITE);
		loadMonitorLayout.removeAllViews();
		loadMonitorLayout.setBackgroundColor(Color.WHITE);
		loadMonitorLayout.addView(graphicalView, new LayoutParams(
				LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	}

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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_load_monitoring);
		findViews();
		initViews();
	}


}
