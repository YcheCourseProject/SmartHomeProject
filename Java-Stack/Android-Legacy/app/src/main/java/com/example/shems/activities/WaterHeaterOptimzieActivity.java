package com.example.shems.activities;

import java.text.DecimalFormat;
import java.util.Date;


import com.example.shems.ASGApplication;
import com.example.shems.util.CommonFunction;
import com.example.smarthome.R;
import com.example.shems.models.device_optimization.WaterHeater;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

public class WaterHeaterOptimzieActivity extends Activity implements
        OnClickListener {

    TimePicker timepicker_bathingtime;
    Button button_confirmTime;
    Button button_target;
    Button button_duration;
    EditText editText_target;
    EditText editText_bathingduration;

    // 跟视图逻辑有关的变量
    boolean press_target;
    boolean press_confirmTime;
    int startheathour; // 开始加热的时间 从当前的nowhour后一个小时开始
    int startdiffusehour; // 开始散热的是啊金
    int startbath; // 开始洗澡的时间

    int realduration;
    int maxduration;
    // 下面是进行实例操作的变量：
    WaterHeater waterHeater;
    double[] realtimeprice;
    double temprature_h;
    double temprature_target;
    DecimalFormat df=new DecimalFormat("0.0000");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_heater_optimzie);
        editText_target = (EditText) this.findViewById(R.id.editText_target);
        editText_bathingduration = (EditText) this
                .findViewById(R.id.editText_bathhour);
        button_confirmTime = (Button) this.findViewById(R.id.button_confirm);
        button_target = (Button) this.findViewById(R.id.button_target);
        button_duration = (Button) this.findViewById(R.id.button_duration);
        timepicker_bathingtime = (TimePicker) this
                .findViewById(R.id.timePicker_washingtime);

        this.timepicker_bathingtime.setCurrentMinute(0);

        this.timepicker_bathingtime.setIs24HourView(true);
        this.timepicker_bathingtime.setCurrentHour(22);
        this.button_confirmTime.setOnClickListener(this);
        this.button_target.setOnClickListener(this);
        this.button_duration.setOnClickListener(this);
        button_duration.setEnabled(false);
        button_target.setEnabled(false);

        this.realtimeprice = ((ASGApplication) getApplication())
                .getRealTimePrice();
        waterHeater = new WaterHeater(realtimeprice);
        // this.waterHeater.setSaveCapacity(0.1);
        Log.i("capacity", this.waterHeater.getSaveCapacity() + "");
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
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.water_heater_optimzie, menu);
        return true;
    }

    public void onClick(View view) {
        // TODO Auto-generated method stub
        try
        {
            if (view == this.button_confirmTime) {
                // button_confirmTime.setEnabled(false);
                button_target.setEnabled(true);
                Date date = new Date();
                int nowhour = date.getHours();
                int nowminute=date.getMinutes();
                if(nowminute>40)
                {
                    nowhour++;
                }
                this.startheathour = nowhour + 1; // 可以开始烧的时间从当前的后一个小时开始
                this.startdiffusehour = nowhour + 1;
                this.startbath = this.timepicker_bathingtime.getCurrentHour(); // 表示想要几点洗澡

//			Log.i("dfsdfsd", this.startbath + "");
                if (this.startbath < nowhour) {
                    this.startbath += 24; // 如果相比较小的话则要加上24
                }
                if (this.startbath == nowhour) {
                    CommonFunction.showDialog("Warning", "We need time to heat WH!!!", this);
                } else {
                    int diffuseHourNum; // 散热的小时

                    diffuseHourNum = this.startbath - this.startdiffusehour;

                    // 先算出h小时后的温度 根据散热的模型
                    this.waterHeater.setHousetemprature(26);
                    temprature_h = this.waterHeater.getWaterTempratureAfterHours(
                            WaterHeater.MAX_SET_TEMPRATURE, diffuseHourNum);

                    CommonFunction.showDialog("Hint", "After" + (diffuseHourNum+1)
                            + " hours , use it", this);
                }
            } else if (view == this.button_target) {
                // this.button_target.setEnabled(false);
                this.button_duration.setEnabled(true);
                // 下面计算出 可以洗澡的时间长度
                this.temprature_target = Double.parseDouble(this.editText_target
                        .getText().toString());
                if (temprature_target < this.waterHeater.MIN_SET_TEMPRATURE
                        || temprature_target > temprature_h) {
                    CommonFunction.showDialog("Error",
                            "Error input\n"+"Please input\n a number from" +
                                    this.waterHeater.MIN_SET_TEMPRATURE + "to"
                                    + Math.floor(temprature_h) , this);
                } else {
                    this.waterHeater.setTargetTemprature(this.temprature_target);
                    this.maxduration = (int) this.waterHeater
                            .getMaxMinute(temprature_h);
                    CommonFunction.showDialog("Hint", "Max Duration is :  "
                            + this.maxduration+"minutes", this);
                }
            } else if (view == this.button_duration) {
                // 后面开始进行优化
                int tempint = Integer.parseInt(this.editText_bathingduration
                        .getEditableText().toString());
                if (tempint > this.maxduration) {
                    CommonFunction.showDialog("Error", "input error\n Max duration time is:  "
                            + this.maxduration, this);
                } else {
                    this.realduration = tempint;
                    this.waterHeater.getBestChoice(this.startheathour,
                            this.startbath, this.realduration);
                    StringBuilder sb=new StringBuilder();
                    if(this.startdiffusehour==this.startbath)
                    {
                        CommonFunction.showDialog("Hint", "立即开始加热，加热完毕然后就可以洗澡", this);
                        return;
                    }
                    int size=Math.min(waterHeater.getTsetlist().size(), 5);
                    for(int i=0;i<size;i++)
                    {
                        sb.append("Strategy");
                        sb.append(i+1);
                        sb.append(":\n");


                        int timeindex=this.waterHeater.getPriceindexlist().get(i);

                        if(timeindex>24)
                        {
                            sb.append((timeindex-24)+":00 Tomorrow"+" Start heating\n" );
                            sb.append("Cost:$"+df.format(this.waterHeater.getCostlist().get(i)));
                            sb.append("\n");
                        }
                        else
                        {
                            sb.append(timeindex+":00 Today"+" Start heating\n");
                            sb.append("Cost:$"+df.format(this.waterHeater.getCostlist().get(i)));
                            sb.append("\n");
                        }
                        sb.append("Set temprature:\t");
                        sb.append(this.waterHeater.getTsetlist().get(i));
                        sb.append("℃");
                        sb.append("\n");
                        sb.append("\n");
                    }
                    CommonFunction
                            .showDialogWithinMaxSize("优化方案:"+"\n从最合适的到次合适的:",
                                    sb.toString(), this);
                }

            }
            // 再加一个进行重新设置的按钮

        }catch(Exception e)
        {
            CommonFunction.showDialog("Error", "error"
                    , this);
        }
    }


}
