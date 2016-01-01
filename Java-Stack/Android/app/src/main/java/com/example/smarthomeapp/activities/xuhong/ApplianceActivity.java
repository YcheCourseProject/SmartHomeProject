package com.example.smarthomeapp.activities.xuhong;

import android.app.ActionBar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;
import com.example.smarthomeapp.R;
import com.example.smarthomeapp.fragment.AirconditionerFrgament;
import com.example.smarthomeapp.fragment.CurtainFragment;
import com.example.smarthomeapp.fragment.HeaterFragment;
import com.example.smarthomeapp.fragment.LightFragment;
import com.example.smarthomeapp.fragment.PlugFragment;


import java.util.ArrayList;
import java.util.List;

public class ApplianceActivity extends FragmentActivity {
    private ActionBar actionBar;
    private TextView actionBarTextView;
    private ViewPager viewPager;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private List<Fragment> fragmentsList;
    private SHEMSPagerTitleAdapter shemsPagerTitleAdapter;

    public void setActionBarText(String string){
        actionBarTextView.setText(string);
    }

    private void findViews() {
        actionBar = getActionBar();
        viewPager = (ViewPager) findViewById(R.id.viewpager_appliance_control);
        pagerSlidingTabStrip = (PagerSlidingTabStrip) findViewById(R.id.tab_appliance_control);
    }

    class SHEMSPagerTitleAdapter extends FragmentPagerAdapter {
        private final String[] TITLES = {getString(R.string.applicant_airconditioner), getString(R.string.appliccant_heater),
                getString(R.string.applicant_light), getString(R.string.applicant_plug), getString(R.string.applicant_curtain)
        };

        public SHEMSPagerTitleAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentsList.get(position);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }

    private void initActionBar() {
        //�����Զ���
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.item_actionbar);
        actionBarTextView = (TextView) findViewById(R.id.txt_hint_title);
        ImageButton button = (ImageButton) findViewById(R.id.btn_back);
        button.setImageDrawable(getResources().getDrawable(R.drawable.icon_smarthome));
    }

    private void initViewPager(){
        fragmentsList = new ArrayList<>();
        fragmentsList.add(new AirconditionerFrgament());
        fragmentsList.add(new HeaterFragment());
        fragmentsList.add(new LightFragment());
        fragmentsList.add(new PlugFragment());
        fragmentsList.add(new CurtainFragment());


        viewPager.setAdapter(new SHEMSPagerTitleAdapter(getSupportFragmentManager()));
        pagerSlidingTabStrip.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                switch (i) {
                    case 0:
                        setActionBarText(getString(R.string.applicant_airconditioner));
                        break;
                    case 1:
                        setActionBarText(getString(R.string.appliccant_heater));
                        break;
                    case 2:
                        setActionBarText(getString(R.string.applicant_light));
                        break;
                    case 3:
                        setActionBarText(getString(R.string.applicant_plug));
                        break;
                    case 4:
                        setActionBarText(getString(R.string.applicant_curtain));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        pagerSlidingTabStrip.setViewPager(viewPager);
        pagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.actionbar));

        viewPager.setCurrentItem(0);

   }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appliance);
        findViews();
        initViewPager();
        initActionBar();

    }

}
