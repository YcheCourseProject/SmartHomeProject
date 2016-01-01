package com.example.shems.models.device_optimization;

public class Section {
    public static final int NODENUM=5;
    int Ttarget;
    int hour;		//当前的时刻
    AirConditioner airhelp;
    Node[]nodes=new Node[NODENUM];		//点数
    Node mincost_node;
    Section nextSection;
    /**
     * @param nextSection
     * @param airhelp
     */
    public Section(Section nextSection,AirConditioner airhelp,int hour) {
        super();
        this.airhelp=airhelp;
        Ttarget =this.airhelp.Ttarget;

        this.hour=hour;
        this.nextSection = nextSection;
        //后面是对Node数组进行的操作：
        nodes[0]=new Node(this, nextSection,Ttarget-2);
        nodes[1]=new Node(this, nextSection,Ttarget-1);
        nodes[2]=new Node(this, nextSection,Ttarget);
        nodes[3]=new Node(this, nextSection,Ttarget+1);
        nodes[4]=new Node(this, nextSection,Ttarget+2);

        double mincost=Double.MAX_VALUE;
        for(int i=0;i<NODENUM;i++)
        {
            double tempcost=nodes[i].weight;
            if(tempcost<mincost)
            {
                this.mincost_node=nodes[i];
                mincost=tempcost;
            }
        }

    }

}
