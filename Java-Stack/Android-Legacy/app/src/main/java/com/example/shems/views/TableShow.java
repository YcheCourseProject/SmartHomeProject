package com.example.shems.views;

import com.example.smarthome.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.LinearLayout;

public class TableShow extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_table_show);
		String [] strs=new String[25];
		for(int i=0;i<strs.length;i++)
		{
			strs[i]=String.valueOf(i);
		}
		TableView table = new TableView(this, 5, 5,strs);
		LinearLayout layout=(LinearLayout)findViewById(R.id.DIY_TABLE);
		layout.removeAllViews();
		layout.addView(table);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.table_show, menu);
		return true;
	}

}
