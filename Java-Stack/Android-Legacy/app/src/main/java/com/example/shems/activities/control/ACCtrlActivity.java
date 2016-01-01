package com.example.shems.activities.control;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.shems.daos.control.ACctrl;
import com.example.shems.daos.control.TempHumidAcq;
import com.example.smarthome.R;

public class ACCtrlActivity extends Activity {
	ACctrl aCctrl = new ACctrl();
	boolean ac_is_On = false;
	boolean acqflag = true;
	ImageButton onOffButton;
	ImageButton tUpButton;
	ImageButton tDownButton;
	ImageView modeImageView;
	TextView tempratureTextView;
	TextView humidityTextView;
	EditText tempraturEditText;
	Handler handler;
	ACctrl.AC_MODE acMode = ACctrl.AC_MODE.WARM;
	Spinner modeSpinner;
	int temperature = 21;
	boolean firstIn;

	private void findViews() {

		onOffButton = (ImageButton) this.findViewById(R.id.imgbtn_ac_on_off);
		tUpButton = (ImageButton) this.findViewById(R.id.imgbtn_upT);
		tDownButton = (ImageButton) this.findViewById(R.id.imgbtn_downT);
		modeImageView = (ImageView) this.findViewById(R.id.imgview_mode);
		tempraturEditText = (EditText) this
				.findViewById(R.id.editxt_temperature);
		modeSpinner = (Spinner) this.findViewById(R.id.spinner_mode);
		tempratureTextView = (TextView) this
				.findViewById(R.id.txt_ac_temperature);
		humidityTextView = (TextView) this.findViewById(R.id.txt_ac_humidity);
	}

	private void setModeImage() {
		switch (acMode) {
		case COLD:
			modeImageView.setImageResource(R.drawable.ac_mode_cool);
			break;
		case WARM:
			modeImageView.setImageResource(R.drawable.ac_mode_hot);
			break;
		case VENTILATION:
			modeImageView.setImageResource(R.drawable.ac_mode_ventilate);
			break;
		case DEHYDRATION:
			modeImageView.setImageResource(R.drawable.ac_mode_arefaction);
			break;
		}

	}

	private void UpdateOnOffButton() {
		if (ac_is_On == false)
			onOffButton.setImageResource(R.drawable.switch_off_normal);
		else
			onOffButton.setImageResource(R.drawable.switch_on_normal);
	}

	public class SpinnerData {
		private ACctrl.AC_MODE value = ACctrl.AC_MODE.WARM;
		private String text = "";

		public SpinnerData(ACctrl.AC_MODE _value, String _text) {
			value = _value;
			text = _text;
		}

		@Override
		public String toString() {

			return text;
		}

		public ACctrl.AC_MODE getValue() {
			return value;
		}

		public String getText() {
			return text;
		}
	}

	// 锟斤拷锟斤拷慕峁癸拷锟斤拷锟斤拷锟斤拷锟斤拷裕锟絭alue锟斤拷锟斤拷锟斤拷锟街碉拷锟絫ext锟斤拷锟斤拷锟斤拷示锟斤拷锟矫伙拷锟斤拷锟斤拷荨锟�
	//
	// 锟斤拷锟芥创锟斤拷spinner锟斤拷锟斤拷锟绞碉拷锟襟定碉拷spinner锟较ｏ拷
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		acqflag = false;
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		acqflag = true;
		AcqThread acqThread = new AcqThread();
		acqThread.start();
	}

	private void Bind() {
		List<SpinnerData> lst = new ArrayList<SpinnerData>();
		SpinnerData c = new SpinnerData(ACctrl.AC_MODE.COLD, "Cool");
		lst.add(c);
		c = new SpinnerData(ACctrl.AC_MODE.WARM, "Warm");
		lst.add(c);
		c = new SpinnerData(ACctrl.AC_MODE.VENTILATION, "Ventilate");
		lst.add(c);
		c = new SpinnerData(ACctrl.AC_MODE.DEHYDRATION, "Dehydrate ");
		lst.add(c);
		ArrayAdapter<SpinnerData> Adapter = new ArrayAdapter<SpinnerData>(this,
				android.R.layout.simple_spinner_item, lst);
		Adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		modeSpinner.setAdapter(Adapter);

		class customedListener implements OnItemSelectedListener {
//			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				
				if (firstIn == true) {
					firstIn = false;
					return;
				}
				ac_is_On=true;
				UpdateOnOffButton();
				switch (arg2) {

				case 0:
					acMode = ACctrl.AC_MODE.COLD;
					break;
				case 1:
					acMode = ACctrl.AC_MODE.WARM;
					break;
				case 2:
					acMode = ACctrl.AC_MODE.VENTILATION;
					break;
				case 3:
					acMode = ACctrl.AC_MODE.DEHYDRATION;
					break;
				}
				setModeImage();
				ChangSettingThread changSettingThread = new ChangSettingThread();
				changSettingThread.start();
				// Toast.makeText(getApplicationContext(), sInfo,
				// Toast.LENGTH_LONG).show();
			}

//			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		}
		modeSpinner.setOnItemSelectedListener(new customedListener());

	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_acctrl);
		firstIn = true;
		findViews();
		UpdateOnOffButton();
		setModeImage();
		Bind();
		tempraturEditText.setFocusableInTouchMode(false);
		modeSpinner.setSelection(1, true);
		onOffButton.setOnClickListener(new OnClickListener() {
			//@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				SwitchACThread swacThread = new SwitchACThread();
				swacThread.start();
				ac_is_On = !ac_is_On;
				UpdateOnOffButton();
			}
		});
		tDownButton.setOnClickListener(new OnClickListener() {

//			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ac_is_On=true;
				UpdateOnOffButton();
				if (temperature > 16)
					temperature--;
				tempraturEditText.setText(String.valueOf(temperature));
				ChangSettingThread changThread = new ChangSettingThread();
				changThread.start();
			}
		});
		tUpButton.setOnClickListener(new OnClickListener() {

//			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ac_is_On=true;
				UpdateOnOffButton();
				if (temperature < 30)
					temperature++;
				tempraturEditText.setText(String.valueOf(temperature));
				ChangSettingThread changThread = new ChangSettingThread();
				changThread.start();
			}
		});

		handler = new Handler() {
			public void handleMessage(Message message) {
				Bundle dataBundle = message.getData();
				if (dataBundle.getString("temperature") != null) {
					tempratureTextView.setText("Temperature:\n"
							+ dataBundle.getString("temperature") + "℃");
				}
				if (dataBundle.getString("humidity") != null) {
					humidityTextView.setText("Humidity:\n"
							+ dataBundle.getString("humidity") + "%");
				}
			}
		};
		AcqThread acqThread = new AcqThread();
		acqThread.start();
	}

//	@Override
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

	class SwitchACThread extends Thread {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			if (ac_is_On == true) {
				ACctrl.OffAC();
			} else {
				ACctrl.onAC();
			}
			super.run();
		}

	}

	class ChangSettingThread extends Thread {
		public void run() {
			// TODO Auto-generated method stub
			ACctrl.setACTemperatureMode(temperature, acMode);
		}
	}

	class AcqThread extends Thread {

		@Override
		public void run() {
//			Log.i("锟組锟斤拷锟斤拷", "锟絎锟絡通訊");
			// TODO Auto-generated method stub
			String temprature;
			String humidity;
			try {
				StringBuffer sb = new StringBuffer();
				Socket clientSocket = new Socket(
						InetAddress.getByName(TempHumidAcq.TMP_HUM_SERVER_IP),
						TempHumidAcq.TMP_HUM_PORT);
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
						Bundle bundle = new Bundle();
						bundle.putString("temperature", temprature);
						bundle.putString("humidity", humidity);
						Message msg = new Message();
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
