package com.example.shems.activities.control;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;



import android.location.Criteria;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.shems.daos.sqllite.MyDBhelper;
import com.example.smarthome.R;

public class LocationACQActivity extends Activity {

	MyDBhelper dBhelper;
	SQLiteDatabase db;
	final static double endlongitude=108.98127042;
	final static double endlatitude=34.24634268;
	static double COUNT=0;
	private LocationManager locationManager;
	private TextView longitudeTextView;
	private TextView latitudeTextView;
	private TextView speedTextView;
	private TextView timestampTextView;
	private TextView distanceTextView;
	private void findViews() {
		longitudeTextView=(TextView) this.findViewById(R.id.textView_longitude);
		latitudeTextView=(TextView) this.findViewById(R.id.textView_latitude);
		speedTextView=(TextView) this.findViewById(R.id.textView_speed);
		timestampTextView=(TextView) this.findViewById(R.id.textView_timestamp);
		distanceTextView=(TextView) this.findViewById(R.id.textView_distance);
	}
	private void updateViews(Location location)
	{
		if(location!=null)
		{
		longitudeTextView.setText("longitude:"+String.valueOf(location.getLongitude()));
		latitudeTextView.setText("latitude:"+String.valueOf(location.getLatitude()));
		speedTextView.setText("speed"+String.valueOf(location.getSpeed()));
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmss");
		timestampTextView.setText( new Date(location.getTime()).toLocaleString());
		float []saveddata=new float[1];
		Location.distanceBetween(location.getLatitude(), location.getLongitude(), endlatitude, endlongitude, saveddata);
		COUNT++;
		distanceTextView.setText("count"+COUNT+";"+String.valueOf(saveddata[0]));
		String sqlString="insert into gps_data ([timestamp],[longitude],[latitude],[speed]) values ('"+location.getTime()+"','"+location.getLongitude()+"','"+location.getLatitude()+"','"+location.getSpeed()+"')";
		db.execSQL(sqlString);
		}
		else {
			distanceTextView.setText("暂无数据");
		}
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location_acq);
		findViews() ;
//		Criteria criteria=new Criteria();
//		criteria.setCostAllowed(false);
//		criteria.setSpeedRequired(true);
//		locationManager.getBestProvider(criteria, true);
		dBhelper=new MyDBhelper(this, "thirdparty.db", null, MyDBhelper.DB_VERSION);
		db=dBhelper.getWritableDatabase();
		locationManager=(LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 5f, new LocationListener() {

			public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
				// TODO Auto-generated method stub
			 
				
			}
			
			public void onProviderEnabled(String arg0) {
				// TODO Auto-generated method stub
				updateViews(locationManager.getLastKnownLocation(arg0));
			}
			
			public void onProviderDisabled(String arg0) {
				// TODO Auto-generated method stub
				
			}
			
			public void onLocationChanged(Location arg0) {
				// TODO Auto-generated method stub
				updateViews(arg0);
			}
		});
//		Location location=locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//		updateViews(location);
	}

	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		db.close();
	}


}
