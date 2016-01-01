package com.example.shems.models.device_optimization;

public class Node {

    int Tin;		//在某个section时刻段中的Tin是可以不同的
    Section nowsection;
    Section nextsection;
    Node next_min_node;	//对应的得出最小权重的后一个结点
    double weight;			//当前点到终点最短路径长度

    /**
     * @param nowsection
     * @param nextsection
     * @param airhelp
     * @param Tin
     */
    public Node(Section nowsection, Section nextsection,int Tin) {
        super();
        this.nowsection = nowsection;
        this.nextsection = nextsection;
        this.next_min_node=null;		//先赋值为空
        this.Tin=Tin;
        //下面找出对应的下一个next_min_node

        if(nextsection==null||nextsection.nodes==null)
        {
            this.weight=0;
        }
        else
        {
            double mincost=Double.MAX_VALUE;
            for(int i=0;i<this.nextsection.nodes.length;i++)
            {
                Node tempnode=this.nextsection.nodes[i];
                double tempcost=tempnode.weight+
                        nowsection.airhelp.getPerHourCost(this.nowsection.hour,
                                this.Tin, tempnode.Tin);
                if(mincost>tempcost)
                {
                    mincost=tempcost;
                    this.weight=tempcost;
                    this.next_min_node=tempnode;
                }
            }
        }
    }




//	下面全是局部最优的代码	
//int Tin;			//只有三种选择  target  +-1之间
//	int Tout;
//	int Ttarget;
//	double weight;
//	int hour;		//考虑好23--04这样的情况
//					//最后一段的Next指针设为空
//	Node nextNode;
//	Section section;
//	
//	public int getTin() {
//		return Tin;
//	}
//
//	public void setTin(int tin) {
//		Tin = tin;
//	}
//
//	public int getTout() {
//		return Tout;
//	}
//
//	public void setTout(int tout) {
//		Tout = tout;
//	}
//
//	public int getTtarget() {
//		return Ttarget;
//	}
//
//	public void setTtarget(int ttarget) {
//		Ttarget = ttarget;
//	}
//
//	public double getWeight() {
//		return weight;
//	}
//
//	public void setWeight(double weight) {
//		this.weight = weight;
//	}
//
//	public int getHour() {
//		return hour;
//	}
//
//	public void setHour(int hour) {
//		this.hour = hour;
//	}
//
//	public Node getNextNode() {
//		return nextNode;
//	}
//
//	public void setNextNode(Node nextNode) {
//		this.nextNode = nextNode;
//	}
//	
//
//	/**
//	 * nextNode是已经得出的最小代价的点          采用动态规划的方法
//	 * @param Target
//	 * @param nextNode
//	 */
//	Node(int hour,int Target,Node nextNode,AirConditioner help )
//	{
//		
//		this.hour=hour;
//		this.Ttarget=Target;
//		this.nextNode=nextNode;
//		
//		//下面做找最适宜的温度点，使得边weight最小
//		if(this.nextNode==null)
//			this.weight=0;
//		else
//		{
//			double min_weight=Double.MAX_VALUE;
//			int min_weight_Tin=0;
//			for(int i=0;i<3;i++)
//			{
//				Tin=this.Ttarget-1+i;
//
//				double temp=help.getPerHourCost(hour, Tin, this.nextNode.Tin);
//				Log.i("Tin", Tin+""+" cost:"+temp);
//				if(min_weight>temp)
//				{
//					min_weight=temp;
//					min_weight_Tin=Tin;
//				}
//			}
//			this.weight=this.nextNode.weight+min_weight;
//			this.Tin=min_weight_Tin;					//目的就是要求解各个Tin的值
//			
//		}
//	}

}
