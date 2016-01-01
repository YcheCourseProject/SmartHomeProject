package com.example.shems.activities.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.smarthome.R;

public class MyActionBarActivity extends FragmentActivity {
    private ActionBar actionBar;
    private String actionBarTitle = null;
    TextView titleTextView;

    public void setActionBarTitle(String actionBarTitle) {
        if (titleTextView != null)
            titleTextView.setText(actionBarTitle);
    }

    protected void initActionBar() {
        //设置自定义
        ActionBar actionBar = getActionBar();
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.item_actionbar);
        titleTextView = (TextView) findViewById(R.id.txt_hint_title);
        if (actionBarTitle != null)
            titleTextView.setText(actionBarTitle);
        ImageButton button = (ImageButton) findViewById(R.id.btn_back);
        final Activity activity = this;
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_action_bar);
        initActionBar();
    }


}
