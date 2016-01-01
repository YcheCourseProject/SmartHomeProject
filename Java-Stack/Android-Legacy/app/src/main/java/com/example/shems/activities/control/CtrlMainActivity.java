package com.example.shems.activities.control;

 


import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.smarthome.R;

public class CtrlMainActivity extends Activity {
	ImageButton menu2acButton;
	ImageButton menu2swButton;
	ImageButton menu2tempratureButton;
	Button loadmonitorButton;
	Button scheduleButton;
	private void findViews() {
     	menu2acButton = (ImageButton) this.findViewById(R.id.imgbtn_menu_ac);
		menu2swButton = (ImageButton) this.findViewById(R.id.imagebtn_menu_sw);
		menu2tempratureButton = (ImageButton) this.findViewById(R.id.imgbtn_menu_temperature);
		loadmonitorButton=(Button) this.findViewById(R.id.button_loadmonitor);
		scheduleButton=(Button) this.findViewById(R.id.button_schedule);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main2);
		findViews();
		menu2acButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(CtrlMainActivity.this, ACCtrlActivity.class);
				 startActivity(intent);
				 finish();
			}
		});
		menu2swButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(CtrlMainActivity.this, SWCtrlActivity.class);
				 startActivity(intent);
				 finish();
			}
		});
		menu2tempratureButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(CtrlMainActivity.this, TmpHumAcqActivity.class);
				 startActivity(intent);
				 finish();
			}
		});
		loadmonitorButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(CtrlMainActivity.this, LoadMonitoringActivity.class);
				 startActivity(intent);
				 finish();
			}
		});
	scheduleButton.setOnClickListener(new OnClickListener() {
			
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent=new Intent();
				intent.setClass(CtrlMainActivity.this, SchedulingAdviceActivity.class);
				 startActivity(intent);
				 finish();
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
