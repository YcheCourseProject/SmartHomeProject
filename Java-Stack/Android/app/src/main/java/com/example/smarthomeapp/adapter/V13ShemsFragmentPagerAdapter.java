package com.example.smarthomeapp.adapter;



import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by CHEYulin on 2015/4/21.
 */
public   class V13ShemsFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> mFragments;

    public V13ShemsFragmentPagerAdapter(FragmentManager fm, List<Fragment> fragments) {
        super(fm);
        // TODO Auto-generated constructor stub
        mFragments=fragments;
    }

    @Override
    public Fragment getItem(int arg0) {
        // TODO Auto-generated method stub
        return mFragments.get(arg0);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return mFragments.size();
    }

}
