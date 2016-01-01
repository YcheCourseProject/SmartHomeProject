package com.example.shems.activities.control;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;

import com.example.shems.daos.control.TempHumidAcq;
import com.example.smarthome.R;

public class TmpHumAcqActivity extends Activity {
	TextView temperatureTextView;
	TextView humidityTextView;
	Handler handler;
	
	boolean acqflag=true;
	private void findViews() {
		temperatureTextView = (TextView) this.findViewById(R.id.txt_temperature);
		humidityTextView=(TextView)this.findViewById(R.id.txt_humid);
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tmp_hum_acq);
		findViews();
		handler=new Handler()
		{
			public void handleMessage(Message message)
			{
				Bundle dataBundle=message.getData();
				if(dataBundle.getString("temperature")!=null)
				{
					temperatureTextView.setText("Temperature:\n"+dataBundle.getString("temperature")+"��");
				}
				if(dataBundle.getString("humidity")!=null)
				{
					humidityTextView.setText("Humidity:\n"+dataBundle.getString("humidity")+"%");
				}
			}
		};
		AcqThread acqThread=new AcqThread();
		acqThread.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		acqflag=false;
	}
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		// TODO Auto-generated method stub
//		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//			acqflag=false;
//			Intent intent = new Intent();
//			intent.setClass(this, CtrlMainActivity.class);
//			this.startActivity(intent);
//			this.finish();
//		}
//		return super.onKeyDown(keyCode, event);
//	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		acqflag=true;
		AcqThread acqThread=new AcqThread();
		acqThread.start();
	}


	class AcqThread extends Thread
	{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String temprature;
			String humidity;
			try {
				StringBuffer sb = new StringBuffer();
				Socket clientSocket = new Socket(
						InetAddress.getByName(TempHumidAcq.TMP_HUM_SERVER_IP), TempHumidAcq.TMP_HUM_PORT);
				BufferedReader infromServer = new BufferedReader(
						new InputStreamReader(clientSocket.getInputStream()));
				int tempint;
				while (acqflag) {
					tempint = infromServer.read();
					if (tempint == '%') {
						String tempstr = sb.toString();
						System.out.println(tempstr);
						String tempstrs[] = tempstr.split(":");
						temprature = tempstrs[1].substring(2, 6);
						System.out.println(tempstrs[1]);
						humidity = tempstrs[1].substring(9, 13);
						Bundle bundle=new Bundle();
						bundle.putString("temperature", temprature);
						bundle.putString("humidity", humidity);
						Message msg=new Message();
						msg.setData(bundle);
						handler.sendMessage(msg);
						sb = new StringBuffer();
					} else
						sb.append((char) (tempint));

				}
			} catch (Exception e) {
				Log.e("Fail", "TmpHumidFail");
				// TODO: handle exception
			}
		
	}
	}
}
