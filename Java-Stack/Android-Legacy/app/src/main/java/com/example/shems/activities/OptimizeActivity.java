package com.example.shems.activities;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import com.example.shems.ASGApplication;
import com.example.shems.util.CommonFunction;
import com.example.smarthome.R;



import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

public class OptimizeActivity extends Activity implements OnClickListener {
    EditText editText_duration;
    TimePicker timepicker_start;
    TimePicker timepicker_end;
    EditText editText_ap;
    Button button_cal;
    RadioButton radioButton_interuptable;
    RadioButton radioButton_noninteruptable;

    boolean check_interupt;
    boolean check_noninterupt;
    double duration;
    TextView textView_showoptimize;
    static final int startThreshhold = 0;
    static final int endThreshhold = 24;
    int start_a;
    int end_b;
    static final double baseparam = 1.1;
    static final double weight_for_tolerance = 0.7; // 等待成本的系数
    static final int k = 100;
    private double ap = 100; // 表示100W
    double realtime_price[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_optimize);
        realtime_price = ((ASGApplication) getApplication()).getRealTimePrice();
        editText_duration = (EditText) this.findViewById(R.id.editText_usetime);
        this.editText_ap=(EditText) this.findViewById(R.id.editText_ap);
        radioButton_interuptable = (RadioButton) this
                .findViewById(R.id.radioButton_interuptable);
        radioButton_interuptable.setOnClickListener(this);
        this.radioButton_noninteruptable = (RadioButton) this
                .findViewById(R.id.radioButton_noninteruptable);
        this.radioButton_noninteruptable.setOnClickListener(this);
        button_cal = (Button) this.findViewById(R.id.button_optimize_cal);
        button_cal.setOnClickListener(this);
        textView_showoptimize = (TextView) this
                .findViewById(R.id.textView_show_optimize);

        this.timepicker_start=(TimePicker)this.findViewById(R.id.timePicker_commonstart);
        this.timepicker_end=(TimePicker)this.findViewById(R.id.timePicker_commomend);
        this.timepicker_start.setIs24HourView(true);
        this.timepicker_end.setIs24HourView(true);
        this.timepicker_start.setCurrentHour(0);
        this.timepicker_end.setCurrentHour(23);
    }

    // 得到24段中每一段的价格 利用贪心算法选出最优的
    private List<Integer> getScheme() {
        List<Integer> int_list = new ArrayList<Integer>();

        TreeSet<Double> treeset = new TreeSet<Double>();
        double[] regions_size24_prices = new double[24];

        int index=0;
        if(!(this.end_b>this.start_a))
        {
            this.end_b+=24;
        }
        for (int i = this.start_a; i < this.end_b; i++) {
            if(i>23)
                index=i-24;
            else
                index=i;
            double realprice = this.realtime_price[index] * ap;
            double cost = weight_for_tolerance * this.getWaitingCost(i-this.start_a) * ap;
            regions_size24_prices[index] = realprice + cost; // every region cost
            Log.i("realprice", realprice + " ;" + cost);
            treeset.add(regions_size24_prices[index]);
        }
        for (int i = 0; i < Math.ceil(this.duration); i++) {
            Double temp = treeset.pollFirst();
            for (int j = 0; j < 24; j++) {
                if (regions_size24_prices[j] == temp.doubleValue()) {
                    int_list.add(j);
                    break;
                }
            }

        }
        return int_list;
    }

    private double nonInteruptcost(int index) {
        double sum = 0;
        double num = Math.ceil(this.duration);
        for (int i = 0; i < num; i++) {
            double realprice = this.realtime_price[i + index] * ap;
            double cost = weight_for_tolerance * this.getWaitingCost(i + index)
                    * ap;
            sum += realprice + cost;

        }
        return sum;
    }

    private int getNonInterupt() {
        double min = Double.MAX_VALUE;
        int index = this.start_a;
        for (int i = this.start_a; i < this.end_b ; i++) {

            //退出的情况
            if ( (i + this.duration )> this.end_b) {
                break;
            }
            double temp = nonInteruptcost(i);
            if (temp < min) {
                min = temp;
                index = i;
                Log.i("temp", temp+"");
            }
        }
        return index;
    }

    private double getWaitingCost(int realstarttime) {
        assert (realstarttime > startThreshhold - 1 && realstarttime < endThreshhold + 1); // 保证在1到24的值
        if (realstarttime > this.start_a - 1 && realstarttime < this.end_b + 1)
            // 大于等于a 小于等于b
            return Math.pow(1.1, realstarttime) / k;
        else
            return 0;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.optimize, menu);
        return true;
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            Intent intent = new Intent();
//            intent.setClass(this, GetRealTimePriceActivity.class);
//            this.startActivity(intent);
//            this.finish();
//        }
//        return super.onKeyDown(keyCode, event);
//
//    }
    public void onClick(View view) {
        // TODO Auto-generated method stub
        try
        {
            if (view == this.button_cal) {
                String str_duration = this.editText_duration.getText().toString().trim();
                String str_ap=this.editText_ap.getText().toString().trim();
                if(this.editText_duration.getText()==null||this.editText_duration.getText().equals("")||this.editText_ap.getText()==null||this.editText_ap.getText().equals(""))
                {
                    CommonFunction.showDialog("Error Info", "Input is null ", this);
                    return;
                }
                if (str_duration.equalsIgnoreCase("")||str_ap.equalsIgnoreCase("")|| Double.parseDouble(str_duration)>24)
                {
                    CommonFunction.showDialog("Error Info", "Input type is error ", this);
                }
                else {
                    this.duration = Double.parseDouble(str_duration);
                    this.ap=Double.parseDouble(str_ap);
                    this.end_b=this.timepicker_end.getCurrentHour();
                    this.start_a=this.timepicker_start.getCurrentHour();
                    //判断是否符合时间
                    int tempduration=0;
                    if(!(this.end_b>this.start_a))
                    {
                        tempduration=this.end_b-this.start_a+24;
                    }
                    else
                    {
                        tempduration=this.end_b-this.start_a;
                    }
                    if(tempduration<this.duration)
                    {
                        CommonFunction.showDialog("Error Info", "Duration should be\n smaller than time interval", this);
                        return;
                    }


                    if (this.check_interupt == true) {
                        Log.i("into", "into");
                        List<Integer> list_show = this.getScheme();
                        StringBuilder sb = new StringBuilder();
                        sb.append("Usage Time :"+"\n");
                        for (int i = 0; i < list_show.size(); i++) {
                            Log.i("listshow", list_show.get(i) + "");
                            sb.append("\nFrom"+list_show.get(i) + ":00 "+"To "+(list_show.get(i)+1)+":00\n");
                        }
//					this.textView_showoptimize.setText(sb.toString());
                        CommonFunction.showDialog("Interruptable Load Scheduling", sb.toString(), this);
                    } else if (this.check_noninterupt == true) {
                        StringBuilder sb = new StringBuilder();
                        int start = this.getNonInterupt();
                        double duration = Math.ceil(this.duration);
//					for (int i = 0; i < duration; i++) {

                        sb.append("Usage Time  "+":\nFrom"+(start + 0)+ ":00 "+"To "+((int)(start + duration))+":00\n");
//					}

//					this.textView_showoptimize.setText(sb.toString());
                        CommonFunction.showDialog("Uninterruptable Load Scheduling", sb.toString(), this);

                    }
                }

            } else if (view == this.radioButton_interuptable) {
                this.check_interupt = true;
                this.check_noninterupt = false;
                this.radioButton_noninteruptable.setChecked(false);
            } else if (view == this.radioButton_noninteruptable) {
                this.check_interupt = false;
                this.check_noninterupt = true;
                this.radioButton_interuptable.setChecked(false);
            }
        }catch(Exception e)
        {
            CommonFunction.showDialog("Error Info","Input type is error", this);
        }
    }


}
