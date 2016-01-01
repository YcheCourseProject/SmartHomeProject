package com.example.shems.activities;


import com.example.smarthome.R;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class ConnectingActivity extends FatherActivity {

    int devicetype;
    boolean mutex = false;
    public static int SEMONS_HARMONIC_CURRENT_ID = 11001;

    TextView textViewL1_N;
    TextView title0;

    ImageButton pushbutton7;

    // 用于显示谐波电流的控件
    TextView harCurViews[] = new TextView[9];
    // 用于做效果的控件
    ImageButton harCurIbutton15;
    // false表示还没有展开
    boolean harcurbool1 = false;
    // 1表示 一整块比如说1次谐波的所有控件，11表示所有采集到的数据的控件
    TableLayout tablelayout_harcur_1;
    // 1表示整个的层次结构，后面表示对应的1、3、5次谐波相应的值
    TableRow tablerow_harcur_1;
    TableLayout tablelayout7;

    Handler mHandler;

    // Bundle mybundle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hba_item);


        // 设置文本颜色(如透明色：Color.TRANSPARENT)
        // textViewL1_N.setTextColor(Color.MAGENTA);
        mHandler = new Handler() {
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
            }
        };


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    public class hbaItemListAdapter extends BaseAdapter {

        public int getCount() {
            // TODO Auto-generated method stub
            return 0;
        }

        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return null;
        }

        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        public View getView(int arg0, View arg1, ViewGroup arg2) {
            // TODO Auto-generated method stub
            return null;
        }

    }


}
