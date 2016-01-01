package com.example.shems.activities;

import com.example.shems.ASGApplication;
import com.example.shems.views.TableView;

import com.example.smarthome.R;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.Layout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.LinearLayout;

public class WHActivity extends Activity {
	ASGApplication application;
	TableView tableView;
	EditText et_duration;
	EditText et_temprature;
	EditText et_bathtime;
	double[] prices;
	Layout layout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_wh);
		LinearLayout layout=(LinearLayout) this.findViewById(R.id.wh_pattern);
		et_bathtime=(EditText) this.findViewById(R.id.editText_bathhour);
		et_temprature=(EditText) this.findViewById(R.id.editText_target);
		et_duration=(EditText) this.findViewById(R.id.EditText_duration);
		et_bathtime.setText(String.valueOf(22));
		et_temprature.setText(String.valueOf(50));
		et_duration.setText(String.valueOf(30));
		application=(ASGApplication) getApplication();
		
//		double []whHba=application.getHumanBavior();		
		int[]whHba={0,0,0,0,0,0,
				1,0,0,0,0,0,
				0,0,0,0,0,0,
				0,0,1,0,0,0};
		tableView = new TableView(this, 4, 6, whHba);
		layout.removeAllViews();
		layout.setBackgroundColor(Color.WHITE);
		layout.addView(tableView, new LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.FILL_PARENT));
	}
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		// TODO Auto-generated method stub
//		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//			Intent intent = new Intent();
//			intent.setClass(this, GetRealTimePriceActivity.class);
//			this.startActivity(intent);
//			this.finish();
//		}
//		return super.onKeyDown(keyCode, event);
//
//	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wh, menu);
		return true;
	}

}
