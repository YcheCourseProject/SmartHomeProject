package com.example.shems.models.device_optimization;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

public class WaterHeater {

    //所有的数据都使用国际标准单位  kg  k  j  m
    public static double KWH=3600*1000;
    public static double c=4.187*Math.pow(10, 3);	//比热容
    public static double L=1;			//热水器长度   70L的大概长度  已经接近于最大了
    public static double landa=0.07;		//保温层热导率
    public static double d2=0.4;		//热水器最外直径
    public static double d1=0.3;		//热水器内直径
    public static double d3=0.05;	//保温层的厚度
    public static double flowspeed=7*Math.pow(10, -3);		//单位 立方米每分钟
    public static double eficiency=0.8;		//0.7-0.95之间
    public static double param_divide=3.412/1.8;		//一个要除以的参数
    public static double defalut_bathtime=15;
    public static double density_water=1000;
    public static double MAX_SET_TEMPRATURE=80;
    public static double TEMPRATURE_WATER_IN=20;
    public static double MIN_SET_TEMPRATURE=35;
    public static double T=3600;
    public static double param_comfort=Math.pow(10, 4)/KWH;
    public  static double THRESHOLD_DIFFUSE=3;

    private double bath_duration;				//目标的洗澡时间
    private double targetTemprature;	//目标期望的水温
    private double  saveCapacity;		//储水容量
    private double []realtimeprice;		//实时的电价    //对应了实时的0-23开始的时刻点的电价
    private double housetemprature;

    private double Tset;			//最优化的
    private int priceindex;			//最优化的
    private List<Integer>Tsetlist;
    private List<Integer> priceindexlist;
    private List<Double> costlist;

    public List<Integer> getTsetlist() {
        return Tsetlist;
    }
    public void setTsetlist(List<Integer> tsetlist) {
        Tsetlist = tsetlist;
    }
    public List<Integer> getPriceindexlist() {
        return priceindexlist;
    }
    public void setPriceindexlist(List<Integer> priceindexlist) {
        this.priceindexlist = priceindexlist;
    }
    public List<Double> getCostlist() {
        return costlist;
    }
    public void setCostlist(List<Double> costlist) {
        this.costlist = costlist;
    }
    public double getHousetemprature() {
        return housetemprature;
    }
    public void setHousetemprature(double housetemprature) {
        this.housetemprature = housetemprature;
    }
    public double getTset() {
        return Tset;
    }
    public void setTset(double tset) {
        Tset = tset;
    }
    public int getPriceindex() {
        return priceindex;
    }
    public void setPriceindex(int priceindex) {
        this.priceindex = priceindex;
    }
    public double getBath_duration() {
        return bath_duration;
    }
    public void setBath_duration(double bath_duration) {
        this.bath_duration = bath_duration;
    }
    public double getTargetTemprature() {
        return targetTemprature;
    }
    public void setTargetTemprature(double targetTemprature) {
        this.targetTemprature = targetTemprature;
    }
    public double[] getRealtimeprice() {
        return realtimeprice;
    }
    public void setRealtimeprice(double[] realtimeprice) {
        this.realtimeprice = realtimeprice;
    }
    public double getSaveCapacity() {
        return saveCapacity;
    }
    public void setSaveCapacity(double saveCapacity) {
        this.saveCapacity = saveCapacity;
    }



    public WaterHeater(double []realtimeprice) {
        super();
        this.setSaveCapacity(Math.PI*L*d1*d1/4*2);		//给出了热水器的储水容量
        this.realtimeprice=realtimeprice;
        this.housetemprature=26;
        Tsetlist=new ArrayList<Integer>();
        priceindexlist=new ArrayList<Integer>();
        this.costlist=new ArrayList<Double>();
    }


    /**
     * @param nowTemprature
     * @param houseTemprature
     * @return  根据Twater(t)   得到 Twater(t+1)
     */
    public double getNextTemprature(double nowTemprature,double houseTemprature)
    {
        double rightformat=L*(nowTemprature-houseTemprature);
        rightformat*=3600;
        rightformat/=1/(2*Math.PI*landa);
        rightformat/=Math.log(d2/d1);
        double difference=rightformat/(c*this.getSaveCapacity()*density_water);
        return nowTemprature-difference;
    }


    /**
     * @param setTemprature
     * @param hournum
     * @return
     */
    public double getWaterTempratureAfterHours(double setTemprature,int hournum)
    {
        double retTemprature=setTemprature;
        for(int i=0;i<hournum;i++)			//如果为0，或者为负值就不变
        {
            retTemprature=this.getNextTemprature(retTemprature, this.housetemprature);
        }
        return retTemprature;
    }


    /**
     * @param nowTemprature
     * @param houseTemprature
     * @return  获得上一个小时的水温
     */
    public double getPreviousTemprature(double nowTemprature,double houseTemprature)
    {
        double retTemprature;
        double param=1/(2*Math.PI*landa)*Math.log(d2/d1);
        double m=this.getSaveCapacity()*this.density_water;
        double mediateParam=c*m*param/(L*T);
        retTemprature=mediateParam*nowTemprature-houseTemprature;
        retTemprature=retTemprature/mediateParam+1;
        return retTemprature;
    }

    /**
     * 获得最大的可以洗澡的时间
     * @param h_temprature
     * @return
     */
    public double getMaxMinute(double h_temprature)
    {
        double retDuration;
        double leftformat=this.getSaveCapacity()*(h_temprature-this.getTargetTemprature());
//		Log.i("leftformat", leftformat+"");
        double volumesum=leftformat/(this.getTargetTemprature()-TEMPRATURE_WATER_IN);
//		Log.i("leftformatd", ""+(this.getTargetTemprature()-TEMPRATURE_WATER_IN));
        volumesum+=this.getSaveCapacity();
//		Log.i("volume", volumesum+"");
        retDuration= Math.floor(volumesum/this.flowspeed);
        return retDuration;
    }




    /**
     * 获取某个时间和   设定温度    对应的cost
     * @param startheathour
     * @param Tset
     * @param bathehour
     * @return
     */
    public double getCost(int startheathour,double Tset,int bathehour)		//会有大于等于24的然后需要做-24的处理
    {
        int index;
        index=startheathour;
        if(index>23)
        {
            index-=24;
        }


        //h表示散热的时间
        int h=bathehour-(startheathour+1);
        double consume_energy=(Tset-this.TEMPRATURE_WATER_IN)
                *c*this.getSaveCapacity()*this.density_water
                /(this.eficiency*this.param_divide)/KWH;
        double rawcost=consume_energy*this.realtimeprice[index];
        double Temprature_h=this.getWaterTempratureAfterHours(Tset, h);
        if(Temprature_h<this.targetTemprature-THRESHOLD_DIFFUSE)	//如果散热到了某个限度了
        {
            return -1;
        }
        double comfortcost=this.param_comfort*(Temprature_h);
        return rawcost+comfortcost;
    }

    private void clearList()
    {
        this.priceindexlist.clear();
        this.costlist.clear();
        this.Tsetlist.clear();
    }
    private void listExchangeBetweenIAndJ(int former_i,int next_j,List  list)
    {
        if(former_i==next_j)
            return;
        Object tempobj;
        tempobj=list.get(former_i);
        list.set(former_i, list.get(next_j));
        list.set(next_j, tempobj);
    }
    private void  OrderedTwoList()
    {
        double mincost=Double.MAX_VALUE;
        int minindex=0;
        for(int j=0;j<this.costlist.size();j++)
        {
            for(int i=j;i<this.costlist.size()-1;i++)
            {
                if(mincost>this.costlist.get(i))
                {
                    mincost=this.costlist.get(i);
                    minindex=i;
                }
                //之后是进行交换的工作:

            }
            this.listExchangeBetweenIAndJ(minindex, j, this.costlist);
            this.listExchangeBetweenIAndJ(minindex, j, this.priceindexlist);
            this.listExchangeBetweenIAndJ(minindex, j, this.Tsetlist);
            mincost=Double.MAX_VALUE;
            minindex=j+1;
        }


    }
    public void getBestChoice(int startheat,int startbathe,int bathtime)			//可以开始加热的时刻        和一定要开始洗澡的时刻
    {
        this.clearList();
        double mincost=Double.MAX_VALUE;
        //方式1：
//		for(int Tset=(int) Math.floor(this.targetTemprature);Tset<80;Tset++)
//		{
//			Log.i("target", this.targetTemprature+"");
//			for(int heattime=startbathe-1;heattime>startheat-1;heattime--)
//			{
//				double cost=this.getCost(heattime, Tset, startbathe);
//				if(cost==-1||bathtime>this.getMaxMinute(this.getWaterTempratureAfterHours(Tset, startbathe-(heattime+1))))
//					{
//					int temp=startbathe-(heattime+1);
//					Log.i("break", this.getPriceindex()+"!!"+ temp);
//					break;
//
//					}
//				if(mincost>cost)
//					{
//					mincost=cost;
//					this.setPriceindex(heattime);
//					this.setTset(Tset);
//					Log.i("cost", cost+"!!"+this.getPriceindex());
//					}
//			}
//		}
        for(int heattime=startbathe-1;heattime>startheat-1;heattime--)
        {
            boolean judgeifok=false;			//判断对应的这个时间是否有可行的温度
            int internal_index=heattime;
            int internalTset=80;
            double internal_mincost=Double.MAX_VALUE;
            for(int Tset=80;Tset>(int)Math.floor(this.targetTemprature);Tset--)	//找出最合适的internal_Tset
            {

                //每一个时刻都找一个最省钱的温度就行
                double cost=this.getCost(heattime, Tset, startbathe);
                if(cost==-1||bathtime>this.getMaxMinute(this.getWaterTempratureAfterHours(Tset, startbathe-(heattime+1))))
                {
                    int temp=startbathe-(heattime+1);
                    Log.i("break", this.getPriceindex()+"!!"+ temp);
                    break;
                }
                if(internal_mincost>cost)
                {
                    judgeifok=true;
                    internal_mincost=cost;
                    internalTset=Tset;
                }

            }
            if(judgeifok==true)
            {
                judgeifok=false;
                //下面要进行插入到List里面去
                this.priceindexlist.add(internal_index);
                this.Tsetlist.add(internalTset);
                this.costlist.add(internal_mincost);
            }
        }
        this.OrderedTwoList();
    }




}
