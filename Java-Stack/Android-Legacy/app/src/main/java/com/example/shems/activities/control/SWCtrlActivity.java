package com.example.shems.activities.control;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

import com.example.shems.daos.control.SWCtrl;
import com.example.smarthome.R;

public class SWCtrlActivity extends Activity {
	boolean sw_is_On=false;
	ImageButton onOffButton;
 
	private void findViews()
	{
		onOffButton=(ImageButton)this.findViewById(R.id.imgbtn_sw);
	 
	}
	private void changeOnOffButton()
	{
		if(sw_is_On==false)
			onOffButton.setImageResource(R.drawable.switch_off_normal);
		else
			onOffButton.setImageResource(R.drawable.switch_on_normal);
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
		setContentView(R.layout.activity_swctrl);
		findViews();
		onOffButton.setOnClickListener(new OnClickListener() {		
//			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SwitchThread swacThread=new SwitchThread();
				swacThread.start();
				sw_is_On=!sw_is_On;
				changeOnOffButton();				
			}
		});
	}

	class SwitchThread extends Thread
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			if(sw_is_On==true)				
			{
				for(int i=0;i<255;i++)
				{
				SWCtrl.switchOn(SWCtrl.SW_SERVER_IP + i);
				}
				}
			else {
				for(int i=0;i<255;i++)
				{
				SWCtrl.switchOff(SWCtrl.SW_SERVER_IP+i);
				}
			}
			
		}
		
	}
}
