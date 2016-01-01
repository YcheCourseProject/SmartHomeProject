package com.example.shems.activities.ui;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.example.smarthome.R;

public class LoginActivity extends Activity {
    private ActionBar actionBar;
    private ImageView backgroundImageView;
    private BootstrapButton loginBootstrapButton;
    private BootstrapButton registerBootstrapButton;

    private void findViews(){
        backgroundImageView= (ImageView) findViewById(R.id.img_background);
        loginBootstrapButton= (BootstrapButton) findViewById(R.id.btn_login);
        registerBootstrapButton= (BootstrapButton) findViewById(R.id.btn_register);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        actionBar=getActionBar();
        actionBar.hide();
        findViews();
        if(backgroundImageView!=null)
        backgroundImageView.getBackground().setAlpha(178);
        loginBootstrapButton.setAlpha(0.7f);
        registerBootstrapButton.setAlpha(0.7f);
    }


}
