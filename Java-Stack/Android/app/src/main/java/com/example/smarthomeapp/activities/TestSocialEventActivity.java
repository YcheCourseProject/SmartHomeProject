package com.example.smarthomeapp.activities;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.view.KeyEvent;
import android.view.Menu;
import android.widget.ListView;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.sqllite.MyDBhelper;

public class TestSocialEventActivity extends Activity {
	ListView listView;
	MyDBhelper dBhelper;
	SQLiteDatabase db;
	private void findViews() {
		 listView=(ListView) this.findViewById(R.id.listView_test);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_test_social_event);
		findViews();
		dBhelper=new MyDBhelper(this/*, "thirdparty.db", null, MyDBhelper.DB_VERSION8*/);
		db=dBhelper.getReadableDatabase();
		Cursor cursor=db.rawQuery("select *from time_event order by timestamp desc",null);
		SimpleCursorAdapter adapter=new SimpleCursorAdapter(this, R.layout.item_time_event, cursor, new String[]{"timestamp","event"}, new int[]{R.id.textView_time,R.id.textView_event},CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
		listView.setAdapter(adapter);
	
	}
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
			Intent intent = new Intent();
			intent.setClass(this, WBStatusAPIActivity.class);
			db.close();
			this.startActivity(intent);
			this.finish();
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.test_social_event, menu);
		return true;
	}

}
