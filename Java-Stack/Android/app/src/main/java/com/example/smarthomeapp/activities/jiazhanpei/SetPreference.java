package com.example.smarthomeapp.activities.jiazhanpei;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;

import com.example.smarthomeapp.R;

/**
 * Created by 卧龙风 on 2015/7/18.
 */
public class SetPreference extends PreferenceActivity
        implements Preference.OnPreferenceClickListener {
    private CheckBoxPreference apply_gpsPreference;
    private CheckBoxPreference apply_socialPreferene;
    private EditTextPreference set_homeaddPreference;
    private ListPreference set_gps_time;
    private ListPreference set_weibo_time;
    private ListPreference set_message_time;
    private ListPreference set_carlender_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preference_setting);
        apply_gpsPreference = (CheckBoxPreference) findPreference("gps_set_preference");
        apply_socialPreferene = (CheckBoxPreference) findPreference("social_set_preference");
        set_homeaddPreference = (EditTextPreference) findPreference("homeaddress_set_preference");
        set_gps_time = (ListPreference) findPreference("gps_time_frequency_setting");
        set_weibo_time = (ListPreference) findPreference("weibo_time_frequency_setting");
        set_message_time = (ListPreference) findPreference("message_time_frequency_setting");
        set_carlender_time = (ListPreference) findPreference("carlender_time_frequency_setting");

        apply_gpsPreference.setOnPreferenceClickListener(this);
        apply_socialPreferene.setOnPreferenceClickListener(this);
        set_homeaddPreference.setOnPreferenceClickListener(this);
        set_gps_time.setOnPreferenceClickListener(this);
        set_weibo_time.setOnPreferenceClickListener(this);
        set_message_time.setOnPreferenceClickListener(this);
        set_carlender_time.setOnPreferenceClickListener(this);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        boolean apply_gpsChecked = sharedPreferences.getBoolean("gps_set_preference", false);
        boolean apply_socialChecked = sharedPreferences.getBoolean("social_set_preference", false);
    }

    private void operatePreference(Preference preference) {
        if (preference == apply_gpsPreference) {
            Log.i("hello preference", "gps CB, and isChecked = " + apply_gpsPreference.isChecked());
        } else if (preference == apply_socialPreferene) {
            Log.i("hello preference", "weibo CB ,and isChecked =" + apply_socialPreferene.isChecked());
        } else if (preference == set_homeaddPreference) {
            Log.i("hello preference", "val" +
                    "ue" + set_homeaddPreference.getText());
        } else if (preference == set_gps_time) {
            Log.i("hello preference", "gps_set_time CB,and selectValue =" +
                    set_gps_time.getValue() + ",Text =" + set_gps_time.getEntry());
        } else if (preference == set_message_time) {
            Log.i("hello preference", "set_message_time CB,and selectValue =" +
                    set_message_time.getValue() + ",Text =" + set_message_time.getEntry());
        } else if (preference == set_weibo_time) {
            Log.i("hello preference", "set_weibo_time CB,and selectValue =" +
                    set_weibo_time.getValue() + ",Text =" + set_weibo_time.getEntry());
        } else if (preference == set_carlender_time) {
            Log.i("hello preference", "set_carlender_time CB,and selectValue =" +
                    set_carlender_time.getValue() + ",Text =" + set_carlender_time.getEntry());
        }
    }

    @Override


    public boolean onPreferenceClick(Preference preference){
       Log.i("hello preference", "onPreferenceClick----->" + String.valueOf(preference.getKey()));
        // 对控件进行操作
        operatePreference(preference);
        return false;
    }


}
