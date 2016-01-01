package com.example.shems.models.device_optimization;

import android.util.Log;

public class AirConditioner {

    public static final double INTERVAL=3600;		//时间间隔为1小时
    public static final double H_GS_WALL=1;			//墙的传热系数
    public static final double H_GS_WINDOW=8.7;		//窗户的传热系数
    public static final double AREA_WINDOW=1.5*1.8;//每个窗户面积
    public static final double AREA_WALL=3*4;//每个墙面面积
    public static final double AP_PERSON=75;	//人的散热
    public static final double PERSON_NUM=2;
    public static final double COP=3;		//制冷比例系数
    public static final double C=1012;	//空气比热容
    public static final double M_AIR=16*2.7*1.184;//空气的质量
    public static final double TOUT=32;
    public static final double PARAM_COMFORT=0.001;	//系数大小待定
    public static final double UNIT_KWH=1000*3600;		//能量的单位要化成千瓦时

    int starttime;			//0到23
    int endtime;			//1到47
    int Ttarget;
    double []realtime_prices;
    int Tin[];
    //Node headnode;
    /**
     * @param starttime
     * @param endtime
     * @param Ttarget
     * @param prices
     */
    public AirConditioner(int starttime,int endtime,int Ttarget,double[]prices)
    {
        this.starttime=starttime;
        this.endtime=endtime;
        this.realtime_prices=prices;
        this.Ttarget=Ttarget;
    }

    public double getQp(int starttime)
    {
        return PERSON_NUM*AP_PERSON*INTERVAL/UNIT_KWH;
    }
    public double getQlight(int starttime)
    //只有晚上开始开   认为是从17到24共7个小时
    //认为是80W
    {
        if((starttime>16)&&(starttime<24))
        {
            return INTERVAL*80/UNIT_KWH;
        }
        else
            return 0;
    }

    public double getQwindow(double Tout,double Tin)
    {
        return H_GS_WINDOW*AREA_WINDOW*(Tout-Tin)*INTERVAL/UNIT_KWH;
    }
    public double getQwall(double Tout,double Tin)
    {
        return H_GS_WALL*AREA_WALL*(Tout-Tin)*INTERVAL*6/UNIT_KWH;
    }

    public double getQequipment(int starttime)
    {
        if(starttime>1&&starttime<8)
        {
            return 30*INTERVAL/UNIT_KWH;
        }
        else if(starttime>18&&starttime<23)
        {
            return 200*INTERVAL/UNIT_KWH;
        }
        else
        {
            return 100*INTERVAL/UNIT_KWH;
        }
    }

    public double calQi(int TinNow,int TinNext,int starttime,double ToutNow)
    {
        double rightformat=this.getQequipment(starttime)+this.getQlight(starttime)
                +this.getQp(starttime)+this.getQwall(ToutNow, TinNow)
                +this.getQwindow(ToutNow, TinNow);
        double Ei=C*M_AIR*(TinNext-TinNow)/UNIT_KWH;
        double qi=Math.abs((Ei-rightformat)/COP);
        return qi;
    }

    public double getPerHourCost(int hour,int TinFormer,int TinNext)
    {
        double cost=0;
        while(hour>23)
        {
            hour-=24;
        }

        cost+=this.realtime_prices[hour]*this.calQi(TinFormer, TinNext, hour, TOUT);
        Log.i("cost1:",""+cost);
        cost+=PARAM_COMFORT*Math.abs(TinFormer-this.Ttarget);
        Log.i("all",""+cost);
//		Log.i("hour", hour+"  cost:"+cost);
        return cost;
    }

    public int[]getSchemeTins()
    {
        int size=this.endtime-this.starttime;
        this.Tin=new int[size];
        Section laterSection=null;
        Section earlierSection=null;
        //先构造Section
        for(int i=endtime-1;i>starttime-1;i--)
        {
            laterSection=earlierSection;
            earlierSection=new Section(laterSection, this,i);
        }

        //然后已经构造出来一个有序的数据结构,进行遍历
        Node headnode=earlierSection.mincost_node;
        int i=0;
        while(true)
        {
            Tin[i]=headnode.Tin;
            headnode=headnode.next_min_node;
            i++;
            if(headnode==null)
            {
                break;
            }
        }
        return this.Tin;
    }
//后面是局部最优的代码	
//public int[] createRoadAndGetTins()
//	{
//		Node laterNode=null;
//		Node earlierNode=null;
//		int size=this.endtime-this.starttime-1;		//最后一个小时的温度无法优化
//		this.Tin=new int[size];
//		for(int i=endtime-1;i>starttime-1;i--)
//		{
//			earlierNode=new Node(i,this.Ttarget,laterNode,this);
//			laterNode=earlierNode;
//		}
//		for(int i=0;i<size;i++)
//		{
//			this.Tin[i]=earlierNode.getTin();
//			earlierNode=earlierNode.getNextNode();
//			
//		}
//		return this.Tin;
//	}
//	public void createTin(int size)		//来形成一个递归调用  调用时候的cost
//	{
//		
//	}
//	public double getMinCost(int starttime,int endtime)
//	{
//		int size=endtime-starttime;
//		this.Tin=new int[size];
//		for(int i=0;i<size;i++)
//		{
//			 //下面做那个size个嵌套的循环
//			
//		}
//	}

}
