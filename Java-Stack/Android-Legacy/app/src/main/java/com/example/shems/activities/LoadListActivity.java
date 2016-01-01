package com.example.shems.activities;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Queue;


import com.example.shems.daos.sqllite.EventLogDB;
import com.example.shems.daos.sqllite.HisLogDB;
import com.example.shems.daos.sqllite.LoadInfoDB;
import com.example.smarthome.R;

import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

public class LoadListActivity extends ListActivity  {
    EventLogDB db_eventlog;
    HisLogDB db_hislog;
    LoadInfoDB db_loadinfo;
    Cursor cursor;
    Button button_loadevents;
    private List<String> loadeventslist = null;
    static final float THRESHOLD = 0.1f;
    static final int WINDOW_NUM=5;			//滑动平均滞后的时间      为WINDOW_NUM
    static final float AP_THRESHOLD=10;
    static final float BASE_POWER_THRESHOLD=5;
    final Context context=this;
    final Activity activity=this;
    //滑动平均窗口越大    滞后的时间越长
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_list);
        db_eventlog=new EventLogDB(this);
        db_eventlog.onUpgrade();		//如果不存在就创建
//		button_loadevents=(Button) this.findViewById(R.id.button_loadeventview);
//		button_loadevents.setOnClickListener(new View.OnClickListener()
//		{
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//				Intent intent=new Intent();
//				intent.setClass(context, LogEventShowActivity.class);
//				context.startActivity(intent);
//				activity.finish();
//			}			
//		});
        db_hislog = new HisLogDB(this);
        cursor = db_hislog.selectOrderByASC(); // 先获得数据
// 		this.handleHisLogDBMethodNormal(cursor); // 进行负载识别
        this.handleHisLogDBMethodWindow(cursor);
    }

//    @Override
//    public boolean onKeyDown(int keyCode, KeyEvent event) {
//        // TODO Auto-generated method stub
//        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//            Intent intent = new Intent();
//            intent.setClass(this, ShowLogActivity.class);
//            this.startActivity(intent);
//            this.finish();
//        }
//        return super.onKeyDown(keyCode, event);
//    }

    private class LoadData {
        float ap;
        float rp;
        float power_factor;
        Date time;

        public LoadData(float ap, float rp, float power_factor,Long time) {
            super();
            this.ap = ap;
            this.rp = rp;
            this.power_factor = power_factor;
            this.time=new Date(time);
        }

        public Date getTime() {
            return time;
        }

        public void setTime(Date time) {
            this.time = time;
        }

        public float getAp() {
            return ap;
        }

        public void setAp(float ap) {
            this.ap = ap;
        }

        public float getRp() {
            return rp;
        }

        public void setRp(float rp) {
            this.rp = rp;
        }

        public float getPower_factor() {
            return power_factor;
        }

        public void setPower_factor(float power_factor) {
            this.power_factor = power_factor;
        }

    }


    private class WindowOperator // 用来方便做平滑处理的类
    {
        /**
         * @author cyl 得到平滑处理后的数据点
         *
         */
        Queue<Float> apque;
        Queue<Float> rpque;
        float ap_average;
        float rp_average;
        Queue<Date>dateque;

        WindowOperator()
        {
            apque=new ArrayDeque<Float>();
            rpque=new ArrayDeque<Float>();
            dateque=new ArrayDeque<Date>();
            ap_average=0;
            rp_average=0;
        }

        WindowOperator(WindowOperator windowOperator)
        {
            this.ap_average=windowOperator.ap_average;
            this.rp_average=windowOperator.rp_average;
            this.apque=windowOperator.apque;
            this.rpque=windowOperator.rpque;
            this.dateque=windowOperator.dateque;

        }
        public LoadData addData(float ap,float rp,float power_factor,Long time)
        {
            if(apque.size()<WINDOW_NUM)
            {
                apque.add(ap);
                rpque.add(rp);
                dateque.add(new Date(time));
                int nowsize=apque.size();
                this.ap_average=(this.ap_average*(nowsize-1)+ap)/nowsize;
                this.rp_average=(this.rp_average*(nowsize-1)+rp)/nowsize;
                //Log.i("apque_size",""+apque.size());
                if(apque.size()==WINDOW_NUM)
                    return new LoadData(this.ap_average,this.rp_average,power_factor,time);
                else
                    return null;			//表示开始的处理还不够对应的平滑点数
            }
            if(apque.size()==WINDOW_NUM)
            {
                this.ap_average=(this.ap_average*WINDOW_NUM-(Float)apque.poll()+ap)/WINDOW_NUM;
                this.rp_average=(this.rp_average*WINDOW_NUM-(Float)rpque.poll()+rp)/WINDOW_NUM;
                apque.add(ap);
                rpque.add(rp);
                dateque.add(new Date(time));
                assert(apque.size()<WINDOW_NUM+1);		//断言
                return new LoadData(this.ap_average,this.rp_average,power_factor,time);
            }
            else
                return null;			//出问题的时候
        }
    }
    private class DataRegion // 表示5个点的一个区间 用来平滑一段的作用
    {
        /**
         * 最原始的算法的时候用的
         */
        List<Date> datelist;
        List<Float> aplist;
        List<Float> rplist;

        public DataRegion() {
            super();
            this.datelist = new ArrayList<Date>();
            this.aplist = new ArrayList<Float>();
            this.rplist = new ArrayList<Float>();
            // TODO Auto-generated constructor stub
        }

        public void addData(Long date, float ap, float rp) // cursor中读来的是Long类型的
        {
            datelist.add(new Date(date));
            aplist.add(ap);
            rplist.add(rp);
        }

        public float getAverageAp() {
            assert (aplist.size() > 0);
            float ret = 0;
            for (int i = 0; i < aplist.size(); i++) {
                ret += aplist.get(i);
            }
            ret = ret / aplist.size();
            return ret; // 表示该区间的一个稳态值 可以用来消除波动
        }

        public float getAverageRp() {
            assert (rplist.size() > 0);
            float ret = 0;
            for (int i = 0; i < rplist.size(); i++) {
                ret += rplist.get(i);
            }
            ret = ret / rplist.size();
            return ret; // 表示该区间的一个稳态值 可以用来消除波动
        }

        public Date getdate() // 返回最小的date 秒级别的差距不用关心
        {
            return datelist.get(0);
        }

        public int getRegionSize() {
            return aplist.size();
        }
    }

    /**
     * 利用窗口处理后的数据
     * 用来判断是否存在一个状态改变的方法
     * @param former
     * @param latter
     * @param window_operator
     * @return
     */
    private boolean whetherPosibleEvent(LoadData former,LoadData latter,WindowOperator window_operator)
    {
        if(window_operator.apque.size()==WINDOW_NUM)				//窗口正确形成的时候
        {
            float ap_change=latter.getAp()-former.getAp();
            float rp_change=latter.getRp()-former.getRp();
            float ap_rate_change_abs=0;
            float rp_rate_change_abs=0;
            if(Math.abs(former.getAp())>BASE_POWER_THRESHOLD)				//如果比较的             基础的值比较大才选择使用
            {
                ap_rate_change_abs=ap_change/former.getAp();
            }
            if(Math.abs(former.getRp())>BASE_POWER_THRESHOLD)
            {
                rp_rate_change_abs=rp_change/former.getRp();
            }
            if(Math.abs(ap_rate_change_abs)>THRESHOLD||Math.abs(rp_rate_change_abs)>THRESHOLD)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;						//窗口不形成  不能给权限进行分析
        }
    }

    private boolean ConfirmEvent(LoadData former,LoadData latter,WindowOperator window_operator,float threshold)
    {
        if(window_operator.apque.size()==WINDOW_NUM)				//窗口正确形成的时候
        {
            float ap_change=latter.getAp()-former.getAp();
            float rp_change=latter.getRp()-former.getRp();
            float ap_rate_change_abs=0;
            float rp_rate_change_abs=0;
            if(Math.abs(former.getAp())>BASE_POWER_THRESHOLD)				//如果比较的             基础的值比较大才选择使用
            {
                ap_rate_change_abs=ap_change/former.getAp();
            }
            if(Math.abs(former.getRp())>BASE_POWER_THRESHOLD)
            {
                rp_rate_change_abs=rp_change/former.getRp();
            }
            if(Math.abs(ap_rate_change_abs)>threshold||Math.abs(rp_rate_change_abs)>threshold)
            {
                Log.i("change", ap_change+"   ;"+rp_change);
                Log.i("changeRate", ap_rate_change_abs+"  ;"+rp_rate_change_abs);
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            return false;						//窗口不形成  不能给权限进行分析
        }
    }

    private void handleHisLogDBMethodWindow(Cursor cursor) {
        loadeventslist=new ArrayList<String>();
        WindowOperator window_operator=new WindowOperator();
        LoadData former=null;
        LoadData latter=null;
        Queue<LoadData> loadDataQue;
        loadDataQue=new ArrayDeque();			//主要用来存储出现可疑点的
        db_eventlog=new EventLogDB(this);
        LoadInfoDB db_loginfo=new LoadInfoDB(this);
        while (cursor.moveToNext()) {
            long time=cursor.getLong(0);
            float ap=cursor.getFloat(1);
            float rp=cursor.getFloat(2);
            float power_factor=cursor.getFloat(3);
            //Log.i("aprp", ap+";"+rp);
            //Log.i("?", "?");
            former=latter;					//原来的相对在后面的的点     到了在前面的点的位置
            latter=window_operator.addData(ap, rp, power_factor, time);

            //后面是找是否有可疑点            然后再进行查找
            if(former!=null)
            {
                //Log.i("former", former.getTime().toLocaleString()+";"+"ap:"+(former.getAp())+";rp:"+(former.getRp()));
                //Log.i("latter", latter.getTime().toLocaleString()+";"+"ap:"+(latter.getAp())+";rp:"+(latter.getRp()));
                if(this.whetherPosibleEvent(former, latter, window_operator))		//可疑点
                {
                    //参考
                    LoadData reference=former;			//作为一个参考后面进行参考
                    LoadData possibleInOrOut=latter;			//可疑点
                    LoadData thelast=latter;				//就是可疑点      和之后要检测的点
                    WindowOperator tempOperator=new WindowOperator(window_operator);		//用来存储突变的点
                    int cursormovetime=0;						//记录游标到底移动了多少
                    for(int move=0;move<WINDOW_NUM-1;move++)		//之后往后走WINDOW_NUM-1   个 点看看  进行一个确认的过程  因为我们的变化基本是一个突变的过程
                    {



                        //第一步，先拿数据：           记得游标在正常结束的时候也要进行回退
                        if(cursor.moveToNext())
                        {
                            cursormovetime++;
                            Log.i("cursor movetime", "move"+move+" ;cursortime"+cursormovetime);
                            time=cursor.getLong(0);
                            ap=cursor.getFloat(1);
                            rp=cursor.getFloat(2);
                            power_factor=cursor.getFloat(3);
                            thelast=tempOperator.addData(ap, rp, power_factor, time);		//做平滑处理
                        }
                        else
                        {
                            for(int j=0;j<cursormovetime;j++)
                            {
                                cursor.moveToPrevious();			//游标回退
                                cursormovetime=0;
                            }
                        }

                        //第二步比较判断
                        if( ConfirmEvent(reference,thelast,tempOperator,THRESHOLD+cursormovetime*0.1f)==false)
                        {
                            for(int j=0;j<cursormovetime;j++)
                            {
                                cursor.moveToPrevious();			//游标回退
                            }
                            cursormovetime=0;
                            break;												//否决的话就不做下去了
                        }

                        else
                        {
                            //第三步：如果做到了最后一个判断
                            if(move==WINDOW_NUM-2)					//到了最后的把关点的判断正确就要进行显示或者存数据库的相关操作
                            {
                                Log.i("oki", cursormovetime+"");
                                //游标不应该回退
//							for(int j=0;j<cursormovetime;j++)
//							{
//								cursor.moveToPrevious();			//游标回退             
//							}		

                                String tempstr=null;
                                boolean trueForIn=thelast.getAp()>reference.getAp();
                                String event_status=null;
                                String identified_appliance=null;

//							db_loadinfo=new LoadInfoDB(this);
                                float loadap=Math.abs(thelast.getAp()-reference.getAp());
                                float loadrp=-Math.abs(thelast.getRp()-reference.getRp());
                                //先做一个匹配的检验    要从loginfo中匹配到才算ok

                                if(trueForIn)
                                {
                                    tempstr="Load detected：in\n";
                                    event_status=EventLogDB.STATUS_IN;
                                }
                                else
                                {
                                    tempstr="Load detected: out\n ";
                                    event_status=EventLogDB.STATUS_OUT;
                                }

                                if(db_loginfo.match(loadap, loadrp)!=null)
                                {
                                    identified_appliance=db_loginfo.match(loadap, loadrp);
                                    db_eventlog.insert(possibleInOrOut.getTime().getTime(), identified_appliance, event_status);
                                    loadeventslist.add(tempstr+possibleInOrOut.getTime().
                                            toLocaleString()+"\n"+"ap:"+loadap+"W\nrp:"+loadrp+"Var"+"\n"+"maybe:"+identified_appliance);
                                }
                                else
                                {
                                    loadeventslist.add(tempstr+possibleInOrOut.getTime().
                                            toLocaleString()+"\n"+"ap:"+loadap+"W\nrp:"+loadrp+"Var");
                                }
//							db_eventlog.insert(new Date().getTime(), "fdsfsd", "in");
//							loadeventslist.add(tempstr+possibleInOrOut.getTime().
//							toLocaleString()+"\n"+"ap:"+loadap+"W\nrp:"+loadrp+"Var");							

                            }

                        }
                    }

                }

            }
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.item_load_events, loadeventslist);
        setListAdapter(adapter);
    }


    private void handleHisLogDBMethodNormal(Cursor cursor) {
        loadeventslist = new ArrayList<String>();
        // 适配器的部分：
        DataRegion temp_data_region = null;
        // 设想分成N多份搞成平均值的情况 利用稳态来进行判断
        int pointnum = 5; // 表示份数
        int index = 0;
        List<DataRegion> region_list = new ArrayList<DataRegion>();

        while (cursor.moveToNext()) {

            if (index % 5 == 0) {
                temp_data_region = new DataRegion();
                if (index != 0) {
                    region_list.add(temp_data_region);

                }
            }
            Long date = cursor.getLong(0);
            Float ap = cursor.getFloat(1);
            Float rp = cursor.getFloat(2);
            temp_data_region.addData(date, ap, rp);
            index++;
        }

        // 已经获得了各个区域的均值
        for (int j = 0; j < region_list.size() - 1; j++) {
            float former_average_power = region_list.get(j).getAverageAp();
            float latter_average_power = region_list.get(j + 1).getAverageAp();
            float former_average_rp = region_list.get(j).getAverageRp();
            float latter_average_rp = region_list.get(j + 1).getAverageRp();
            float rate = Math.abs((latter_average_power - former_average_power)
                    / former_average_power);
            if (rate > THRESHOLD) {
                float changrp = latter_average_rp - former_average_rp;
                String item = region_list.get(j + 1).getdate().toLocaleString()
                        + "\n" + (latter_average_power - former_average_power)
                        + "W" + "\n" + changrp + "var";
                loadeventslist.add(item);
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.item_load_events, loadeventslist);
        setListAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.load_list, menu);
        return true;
    }





}
