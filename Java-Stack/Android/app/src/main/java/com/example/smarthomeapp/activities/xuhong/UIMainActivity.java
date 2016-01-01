package com.example.smarthomeapp.activities.xuhong;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.smarthomeapp.R;
import com.example.smarthomeapp.adapter.V13ShemsFragmentPagerAdapter;
import com.example.smarthomeapp.adapter.ShemsGridViewAdapter;
import com.example.smarthomeapp.fragment.FamilyFragment;
import com.example.smarthomeapp.fragment.PersonalFragment;
import com.example.smarthomeapp.fragment.StatisticFragment;
import com.example.smarthomeapp.fragment.ToolsFragment;

import java.util.ArrayList;
import java.util.List;

public class UIMainActivity extends Activity {

    private List<Fragment> fragmentsList;
    private ActionBar actionBar;
    private TextView actionBarTextView;
    private ImageButton actionBarImageButton;
    private ViewPager viewPager;
    private GridView gridView;
    private ShemsGridViewAdapter gridViewAdapter;

    public void setActionBarText(String string){
        actionBarTextView.setText(string);
    }

    private void initActionBar() {
        //�����Զ���
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.item_actionbar);
        actionBarTextView = (TextView) findViewById(R.id.txt_hint_title);
        actionBarTextView.setText(R.string.title_activity_ui__main);
        ImageButton button = (ImageButton) findViewById(R.id.btn_back);

        button.setImageDrawable(getResources().getDrawable(R.drawable.icon_smarthome));

    }
    private void findViews() {
        actionBar = getActionBar();
        gridView=(GridView)findViewById(R.id.grid_view_main_ui);
        viewPager= (ViewPager) findViewById(R.id.view_pager_mainui);
    }


    private void initViewPager(){
        fragmentsList = new ArrayList<>();
        fragmentsList.add(new FamilyFragment());
        fragmentsList.add(new PersonalFragment());
        fragmentsList.add(new StatisticFragment());
        fragmentsList.add(new ToolsFragment());
        viewPager.setAdapter(new V13ShemsFragmentPagerAdapter(getFragmentManager(), fragmentsList));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                int[] status = gridViewAdapter.getSelectedStatus();
                for (int j = 0; j < 4; j++) {
                    status[j] = 0;
                }
                status[i] = 1;
                gridViewAdapter.setSelectedStatus(status);
                gridViewAdapter.notifyDataSetChanged();
                switch (i){
                    case 0:
                        setActionBarText(getString(R.string.title_home));
                        break;
                    case 1:
                        setActionBarText(getString(R.string.title_personal));
                        break;
                    case 2:
                        setActionBarText(getString(R.string.title_statistics));
                        break;
                    case 3:
                        setActionBarText(getString(R.string.title_tool));
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_ui_main);
        findViews();
        initActionBar();
        initViewPager();
        Drawable[] drawables=new Drawable[4];
        drawables[0]=getResources().getDrawable(R.drawable.family1);
        drawables[1]=getResources().getDrawable(R.drawable.menu_user);
        drawables[2]=getResources().getDrawable(R.drawable.menu_user);
        drawables[3]=getResources().getDrawable(R.drawable.menu_user);

        gridViewAdapter=new ShemsGridViewAdapter(drawables, new String[]{getString(R.string.title_home),
                getString(R.string.title_personal),getString(R.string.title_statistics),
        getString(R.string.title_tool)},this);
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                viewPager.setCurrentItem(position);
            }
        });
    }



}
