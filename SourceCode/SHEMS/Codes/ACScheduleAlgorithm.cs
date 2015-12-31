using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SHEMS.Codes
{
    class ACScheduleAlgorithm
    {
  	/*parameter for transition function*/
	static double e =0.93; // system inertia
	static double cop = 2.5;
	static double A = 0.12465; // thermal conductivity
	/*parameter for transition function*/
	public static double []formerPolicy=new double[24];
	static double Comfort_penalty = 50; //parameter for cost function

	public static double []BasePolicy = new double [24];
	static double [] T_out= new double[]{25,25,25,23,24,25,27,
	                   30,34,36,40,42,43,
	                   41,42,40,38,36,32,
	                   32,32,32,32,30
	                  };
	public static double  []T_in = new double[24];
	public static double []T_in_former=new double[24];
	public static int []T_need = new int[]{24,24,24,24,24,24,24,
	                  24,24,24,24,24,24,
	                  24,24,24,24,24,24,
	                  24,24,24,24,24
	                 };
	public static double []RealTimePrice = new double[]{0.391,0.391,0.391,0.391,0.391,0.391,0.391,
	                           1.194,1.194,1.194,1.194,
	                           0.781,0.781,0.781,0.781,0.781,0.781,0.781,0.781,
	                           1.194,1.194,1.194,1.194,
	                           0.391
	                          };
	public static int []HumanBehavior = new int[]{
	0,0,0,0,0,0,
	0,0,1,1,1,1,
	0,0,1,1,1,1,
	0,0,0,0,0,0
	};
	static double []T_out_true = new double[]{25,25,25,23,24,25,27,
	                        30,34,36,40,42,43,
	                        41,42,40,38,36,32,
	                        32,32,32,32,30
	                       };
	public static int []HumanBehavior_true = new int[]{
		0,0,0,0,0,0,
		0,0,1,1,1,1,
		0,0,1,1,1,1,
		0,0,0,0,0,0
	};

	static double Q_current = 0;
	static double Q_min = 10000000;
	static double A_op = 0;
	public static double costwithscheuling=0;
	public static double costwithoutscheuling=0;
	
	static double Cost_electricity(int k, double Action)
	{
	    return Action * RealTimePrice[k];
	}
	static double Cost_ComfortPenalty(int k)
	{
	    double H = 0;
	    if(Math.Abs(T_in[k] - T_need[k]) <= 1)
	        H = 0;
	    else
	        H = Math.Abs(T_in[k] - T_need[k]) - 1;
	    return H * 5000 * HumanBehavior[k];
	}
	static double Cost(int k,double Action)
	{
	    return Cost_electricity(k,Action) + Cost_ComfortPenalty(k);
	}
	public static void  run()
	{	
		HumanBehavior_true=HumanBehavior;
	    T_in[0] = T_out[0];
	    Base_Policy(0,T_out[0],BasePolicy,T_in);
	    double Action;
	    double Future_cost;
	    for(int i=0;i<5;i++)
	    {
	    	BasePolicy[i]=0;
	    }
	    for(int i = 4; i < 23; i++)
	    {
	        A_op = BasePolicy[i];
	        Q_min = 100000;
	        Future_cost = 0;
	        Action = 0;
	        for(int j = 0; j < 100; j++)
	        {
	            Future_cost = 0;
	            Action += 0.01;
	            T_in[i+1] = e * T_in[i] + (1-e) * (T_out[i] - cop * Action / A);
	            Base_Policy(i+1,T_in[i+1],BasePolicy,T_in);
	            for(int k =i+1; k < 24; k++)
	            {
	                Future_cost += Cost(k,BasePolicy[k]);
	            }
	            Q_current = Cost(i,Action) + Future_cost;
	            if(Q_current < Q_min)
	            {
	                Q_min = Q_current;
	                A_op = Action;
	            }
	        }
	        if(A_op != BasePolicy[i])
	        {
	            if(A_op >= 0.99)
	                A_op = 1;
	            if( A_op <= 0.01)
	                A_op = 0;
	            BasePolicy[i] = A_op;
	            T_in[i+1] = e * T_in[i] + (1-e) * (T_out[i] - cop * A_op / A);
	            Base_Policy(i+1,T_in[i+1],BasePolicy,T_in);
	        }
	        else
	        {
	            T_in[i+1] = e * T_in[i] + (1-e) * (T_out[i] - cop * BasePolicy[i] / A);
	            Base_Policy(i+1,T_in[i+1],BasePolicy,T_in);
	        }
	    }
	    costwithscheuling=calCostWithScheduling(BasePolicy);
	}

	static double calCostWithScheduling(double []basepolicy)
	{
		double cost=0;
		for(int i=0;i<basepolicy.Length;i++)
		{
			cost+=basepolicy[i]*RealTimePrice[i];
		}
		return cost;
	}
	static double getTnext(int i,double q)
	{
		return e*T_in[i]+(1-e)*(T_out[i]-cop*q/A);
	}
	static double getTnextForTinFormer(int i,double q)
	{
		return e*T_in_former[i]+(1-e)*(T_out[i]-cop*q/A);
	}
	static double getQwithoutScheduling(double Tformer,double Tlatter,double Tout)
	{
		return (Tout-(Tlatter-e*Tformer)/(1-e))*A/cop;
	}
	public static void runwithoutscheduling()
	{
		costwithoutscheuling=calFormerCost();
	}
	static double calFormerCost()
	{
		double cost=0;
		T_in_former[0]=25;
		Base_Policy(0,25,formerPolicy,T_in_former);	
		for(int i=0;i<24;i++)
		{
			cost+=RealTimePrice[i]*formerPolicy[i]; 			 
		}
		return cost;
	}
	static double calCostWithoutScheduling()
	{

		T_in_former[0]=T_out[0];
		T_in_former[0]=getTnextForTinFormer(0, 0);//初始
		formerPolicy[0]=0;
		for(int i=1;i<24;i++)
		{
			if(HumanBehavior[i]==0)
			{
				T_in_former[i]=getTnextForTinFormer(i-1, 0);
			}
			else {
				if(getTnextForTinFormer(i-1,1)>24)
				{
					formerPolicy[i-1]=1;
					T_in_former[i]=getTnextForTinFormer(i-1, 1);
				}
				else if(getTnextForTinFormer(i-1, 0)<24)
				{
					formerPolicy[i-1]=0;
					T_in_former[i]=getTnextForTinFormer(i-1, 0);
				}
				else {
					formerPolicy[i-1]=getQwithoutScheduling(T_in_former[i-1], 24, T_out[i-1]);	
					T_in_former[i]=24;
				}
			}
		}
		double cost=0;
		for(int i=0;i<24;i++)
		{
			cost+=RealTimePrice[i]*formerPolicy[i];
	 
		}
		return cost;
	}
	static void Base_Policy(int k, double K_state,double []basepolicy,double[]Tin)
	{
	    for(int i = k; i < 23 ; i++)
	    {
	        if(HumanBehavior[i] == 0)
	        {
	        	basepolicy[i] = 0;
	        	Tin[i+1] = e * Tin[i] + (1 - e) * T_out[i];
	        }
	        else
	        {
	            if(Tin[i] < 23)
	            {
	                double BasePolicy_temp;
	                BasePolicy_temp = A / cop * (T_out[i] - (25 - Tin[i] * e) / (1 - e));
	                if(BasePolicy_temp < 0)
	                {
	                	Tin[i+1] = Tin[i] * e + (1 - e) * T_out[i];
	                	basepolicy[i] = 0;
	                }
	                else
	                {
	                	basepolicy[i] = BasePolicy_temp;
	                    Tin[i+1] = 25;
	                }
	            }
	            if(Tin[i] > 25)
	            {
	                double BasePolicy_temp;
	                BasePolicy_temp = A / cop * (T_out[i] - (25 - Tin[i] * e) / (1 - e));
	                if(BasePolicy_temp > 1)
	                {
	                	Tin[i+1] = Tin[i] * e + (1 - e) * (T_out[i] - cop * 1 / A);
	                	basepolicy[i] = 1;
	                }
	                else
	                {
	                	basepolicy[i] = BasePolicy_temp;
	                    Tin[i+1] = 25;
	                }
	            }
	            if(Tin[i] >= 23 && Tin[i] <= 25)
	            {
	                double BasePolicy_temp;
	                BasePolicy_temp = A / cop * (T_out[i] - (25 - Tin[i] * e) / (1 - e));
	                if(BasePolicy_temp < 0)
	                {
	                	Tin[i+1] = Tin[i] * e + (1 - e) * T_out[i];
	                	basepolicy[i] = 0;
	                }
	                else
	                {
	                	basepolicy[i] = BasePolicy_temp;
	                    Tin[i+1] = 25;
	                }
	            }
	        }
	    }
	}
    static void Base_Policy_Advanced(int k, double K_state, double[] basepolicy, double[] Tin)
    {
        for (int i = k; i < 23; i++)
        {
            if (HumanBehavior[i] == 0)
            {
                basepolicy[i] = 0;
                Tin[i + 1] = e * Tin[i] + (1 - e) * T_out[i];
            }
            else
            {
                if (Tin[i] <= 24)
                {
                    double BasePolicy_temp;
                    BasePolicy_temp = A / cop * (T_out[i] - (24 - Tin[i] * e) / (1 - e));
                    if (BasePolicy_temp < 0)
                    {
                        Tin[i + 1] = Tin[i] * e + (1 - e) * T_out[i];
                        basepolicy[i] = 0;
                    }
                    else
                    {
                        basepolicy[i] = BasePolicy_temp;
                        Tin[i + 1] = 25;
                    }
                }
                else if (Tin[i] > 24)
                {
                    double BasePolicy_temp;
                    BasePolicy_temp = A / cop * (T_out[i] - (24 - Tin[i] * e) / (1 - e));
                    if (BasePolicy_temp > 1)
                    {
                        Tin[i + 1] = Tin[i] * e + (1 - e) * (T_out[i] - cop * 1 / A);
                        basepolicy[i] = 1;
                    }
                    else
                    {
                        basepolicy[i] = BasePolicy_temp;
                        Tin[i + 1] = 24;
                    }
                }

            }
        }
    }


    }
}
