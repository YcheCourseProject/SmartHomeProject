package com.example.shems.activities;

import java.util.ArrayList;


import com.example.shems.ASGApplication;
import com.example.shems.util.CommonFunction;
import com.example.shems.util.algorithm.Algorithm_AC;
import com.example.shems.views.TableView;
import com.example.smarthome.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.Layout;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

public class AirConditionerActivity extends Activity implements OnClickListener {

    Button button_air_cal;

    TableView tableView;
    ArrayList<String> schemelist = new ArrayList<String>();
    EditText edittext_target;
    double[] prices;
    int starttime;
    int endtime;
    int Ttarget;
    StringBuffer show = new StringBuffer();
    ASGApplication application;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            Intent intent = new Intent();
            intent.setClass(this, GetRealTimePriceActivity.class);
            this.startActivity(intent);
            this.finish();
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_conditioner);

        this.button_air_cal = (Button) this.findViewById(R.id.button_air_cal);
        this.button_air_cal.setOnClickListener(this);
        // this.button_air_cal.setFocusable(true);
        // this.button_air_cal.setFocusableInTouchMode(true);
        this.prices = ((ASGApplication) getApplication()).getRealTimePrice();
        this.edittext_target = (EditText) this
                .findViewById(R.id.editText_target);
        this.edittext_target.setText("24");
        LinearLayout layout = (LinearLayout) findViewById(R.id.achba);
        int achba[] = Algorithm_AC.HumanBehavior;
        tableView = new TableView(this, 4, 6, achba);
        layout.removeAllViews();
        layout.setBackgroundColor(Color.WHITE);
        layout.addView(tableView, new LayoutParams(LayoutParams.FILL_PARENT,
                LayoutParams.FILL_PARENT));
        this.application=(ASGApplication) this.getApplication();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.air_conditioner, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_ac_schedulingdetails:
                Intent intent=new Intent();
                intent.setClass(this, ACScheduleDetailChartActivity.class);
                this.startActivity(intent);
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClick(View view) {
        // TODO Auto-generated method stub

        if (view == this.button_air_cal) {

            Algorithm_AC.run();
//				application.setHumanBavior(tableView.savehba);
            double[] Tin = Algorithm_AC.T_in;
            ArrayList<Integer> startlist = new ArrayList<Integer>();
            ArrayList<Integer> endlist = new ArrayList<Integer>();
            startlist.add(0);
            int curstart = 0;
            for (int i = 1; i < Tin.length; i++) {
                if ((int) Tin[i] == (int) Tin[curstart])
                    continue;
                else {
                    endlist.add(i);
                    if(i==Tin.length-1)
                        break;
                    else {
                        startlist.add(i+1);
                        i++;
                    }
                }
            }
            if(startlist.size()>endlist.size())
            {
                startlist.remove(startlist.size()-1);
            }
            StringBuilder sbBuilder=new StringBuilder();

            for(int i=0;i<startlist.size();i++)
            {
                sbBuilder.append(startlist.get(i)).append(":00-");
                sbBuilder.append(endlist.get(i)).append(":00").
                        append("\n").append("setTemprature:").
                        append((int)Tin[startlist.get(i)]).
                        append("℃").append("\n");
            }
            CommonFunction.showDialogWithinMaxSize("AC Scheduling Strategy", sbBuilder.toString(), this);

        }

    }

}
