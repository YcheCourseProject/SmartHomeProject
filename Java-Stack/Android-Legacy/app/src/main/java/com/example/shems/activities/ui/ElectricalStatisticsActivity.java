package com.example.shems.activities.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.example.shems.fragments.ElectricalStatisticsFragment;
import com.example.smarthome.R;

import java.util.ArrayList;
import java.util.List;

public class ElectricalStatisticsActivity extends MyActionBarActivity {

    private ViewPager viewPager;
    private PagerSlidingTabStrip pagerSlidingTabStrip;
    private Context context;
    private List<Fragment> fragmentList=new ArrayList<Fragment>(4);

    private void findViews() {
        viewPager= (ViewPager) findViewById(R.id.viewpager_electrical_statistics);
        pagerSlidingTabStrip= (PagerSlidingTabStrip) findViewById(R.id.tabs_electrical_statistics);
    }

    private void initFragmentsAndViewPager(){
        MyFragmentPagerAdapter myFragmentPagerAdapter=new MyFragmentPagerAdapter(getSupportFragmentManager());
        for(int i=0;i<myFragmentPagerAdapter.getCount();i++)
            fragmentList.add(new ElectricalStatisticsFragment());
        viewPager.setAdapter(myFragmentPagerAdapter);
        pagerSlidingTabStrip.setViewPager(viewPager);
        pagerSlidingTabStrip.setIndicatorColor(getResources().getColor(R.color.actionbar));
    }

    class MyFragmentPagerAdapter extends FragmentPagerAdapter{

        private final String[] TITLES={context.getString(R.string.electrical_consumption),context.getString(R.string.electrical_cost),
        context.getString(R.string.unit_electrical_cost),context.getString(R.string.carbon_dioxide_emission)};

        public MyFragmentPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLES[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getCount() {
            return TITLES.length;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setActionBarTitle(getString(R.string.title_activity_electrical_statistics));
        setContentView(R.layout.activity_electrical_statistics);
        context=this;
        findViews();
        initFragmentsAndViewPager();
    }


}
