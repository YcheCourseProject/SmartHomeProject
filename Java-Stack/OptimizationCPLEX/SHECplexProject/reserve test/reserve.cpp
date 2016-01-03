#include <ilcplex/ilocplex.h>
#include <fstream>
#include <math.h>
#include <ilconcert/ilomodel.h>
#include <iostream>
#include<cmath>
//#include<iostream.h>
#define TOUT "E:/data/Tout.txt"
#define PRICE "E:/data/price.txt"
#define RAC "E:/data/Rac.txt"
#define RWH "E:/data/Rwh.txt"
#define STATES "E:/data/States.txt"
#define Result_qac "E:/data/Result_qac.txt"
#define Result_qwh "E:/data/Result_qwh.txt"
#define Result_Tac "E:/data/Result_Tac.txt"
#define Result_Twh "E:/data/Result_Twh.txt"


ILOSTLBEGIN

	typedef IloArray<IloNumArray> Matrix2;                       
typedef IloArray<Matrix2> Matrix3;
typedef IloArray<Matrix3> Matrix4;
typedef IloArray<IloNumVarArray> FloatMatrix2;
typedef IloArray<FloatMatrix2> FloatMatrix3;

//**********调度问题已知参数，定义全局变量************
IloInt uniNum;                                               //设备个数
IloInt cycle;                                                 //调度周期
IloInt amount;
float alfa=0.12;   //净受入电量
//IloNumArray BatchNum;                                        //每台设备的生产批次
//IloNumArray MostBatch;                                       //最大的批次

//**********读取系统数据函数**************************

//************************程序主函数部分**********************
int main()
{

	IloEnv env;

	try
	{	
		IloModel model(env);
		IloTimer timer(env);
		timer.start();

		int Tpriod,k,i,j,tk;
		float tdeta,e=0.9,COPA=18,qacmax=0.6,cm=0.07,theta=0.004,qwhmax=2,lastTac,lastTwh;
		int  Kperiod;
		Tpriod=24;
		tdeta=0.5;
		Kperiod=Tpriod/tdeta;
		///数据读入
		IloNumArray Tout(env,Kperiod+1); 
		IloNumArray price(env,Kperiod+1); 
		IloNumArray States(env,3+1); 
		Matrix2 Rac(env,4+1); 
		Matrix2 Rwh(env,4+1); 


		for(i=1;i<4+1;i++)
		{
			Rac[i]=IloNumArray(env,4+1);
		}
		for(i=1;i<4+1;i++)
		{
			Rwh[i]=IloNumArray(env,4+1);
		}
		//read Tout
		ifstream fin1(TOUT,ios::in);
		if(!fin1) env.out()<<"problem with file:"<<TOUT<<endl;
		for(i=1;i<Kperiod+1;i++)
		{
			fin1>>Tout[i];

		}
		fin1.close();

		//price
		ifstream fin2(PRICE,ios::in);
		if(!fin2) env.out()<<"problem with file:"<<PRICE<<endl;
		for(i=1;i<Kperiod+1;i++)
		{
			fin2>>price[i];

		}
		fin2.close();

		//Rac

		ifstream fin3(RAC,ios::in);
		if(!fin3) env.out()<<"problem with file:"<<RAC<<endl;
		for(i=1;i<4+1;i++)
		{	
			for(j=1;j<4+1;j++)
			{
				fin3>>Rac[i][j];
			}
		}
		fin3.close();

		//Rwh

		ifstream fin4(RWH,ios::in);
		if(!fin4) env.out()<<"problem with file:"<<RWH<<endl;
		for(i=1;i<4+1;i++)
		{	
			for(j=1;j<4+1;j++)
			{
				fin4>>Rwh[i][j];
			}
		}
		fin4.close();

		ifstream fin5(STATES,ios::in);
		if(!fin5) env.out()<<"problem with file:"<<STATES<<endl;
		for(i=1;i<3+1;i++)
		{
			fin5>>States[i];

		}
		fin5.close();


		IloNumVarArray qac(env,Kperiod+1,0,qacmax,ILOFLOAT); 
		IloNumVarArray STac(env,Kperiod+1,0,99,ILOFLOAT); 
		IloNumVarArray qwh(env,Kperiod+1,0,qacmax,ILOFLOAT); 
		IloNumVarArray STwh(env,Kperiod+1,0,99,ILOFLOAT); 

		//约束
		tk=States[1];
		lastTac=States[2];
		lastTwh=States[3];

		model.add(STac[tk]==lastTac);
		model.add(STwh[tk]==lastTwh);

		for( k=tk;k<Kperiod;k++)
		{
			model.add(STac[k+1]==e*STac[k]+(1-e)*Tout[k]-COPA*qac[k]*tdeta);
		}
		for(k=tk;k<Kperiod;k++)
		{
			model.add(cm*(STwh[k+1]-STwh[k])==qwh[k]*tdeta-theta*(STwh[k]-STac[k]));
		}



		for(i=1;i<=4;i++)
		{
			if(Rac[i][1]>0)
			{
				for(j=Rac[i][1];j<=Rac[i][2];j++)
				{
					model.add(Rac[i][3]-Rac[i][4]<=STac[j]<=Rac[i][3]+Rac[i][4]);
				}
			}
		}

		for(i=1;i<=4;i++)
		{
			if(Rwh[i][1]>0)
			{
				for(j=Rwh[i][1];j<=Rwh[i][2];j++)
				{
					model.add(Rwh[i][3]-Rwh[i][4]<=STwh[j]<=Rwh[i][3]+Rwh[i][4]);
				}
			}
		}
		IloExpr obj(env);
		IloExpr sum(env);
		for(i=tk;i<=Kperiod;i++)
		{
			sum+=price[i]*qac[i]+price[i]*qwh[i];
		}

		IloObjective objective = IloMinimize(env, obj+sum);// 
		model.add(objective);

		IloCplex cplex(model);
		cplex.setParam(cplex.EpGap,0.01);

		//		cplex.setParam(cplex.PreInd,0);
		cplex.extract(model);
		IloCplex::AutoAlg ;
		cplex.solve();

		env.out() << "Solution status = " << cplex.getStatus() << endl;
		env.out() << "Solution value  = " << cplex.getObjValue() << endl;
		env.out() << "solution time   = " <<timer.getTime()<<endl;
		env.out() << "EpGap           = " <<cplex.getParam(cplex.EpGap)<<endl;


		ofstream tfile1(Result_qac,ios::out);
		if(!tfile1)
			cout<<"cannot open 'result.dat'"<<endl;

		for(i=tk;i<=Kperiod;i++)
		{
			tfile1<<cplex.getValue(qac[i]);
			tfile1<<endl;

		}

		tfile1.close();

		ofstream tfile2(Result_qwh,ios::out);
		if(!tfile2)
			cout<<"cannot open 'result.dat'"<<endl;

		for(i=tk;i<=Kperiod;i++)
		{
			tfile2<<cplex.getValue(qwh[i]);
			tfile2<<endl;

		}

		tfile2.close();

		ofstream tfile3(Result_Twh,ios::out);
		if(!tfile3)
			cout<<"cannot open 'result.dat'"<<endl;

		for(i=tk;i<=Kperiod;i++)
		{
			tfile3<<cplex.getValue(STwh[i]);
			tfile3<<endl;

		}

		tfile3.close();

		ofstream tfile4(Result_Tac,ios::out);
		if(!tfile4)
			cout<<"cannot open 'result.dat'"<<endl;

		for(i=tk;i<=Kperiod;i++)
		{
			tfile4<<cplex.getValue(STac[i]);
			tfile4<<endl;

		}

		tfile4.close();

	}

	catch (...) 
	{
		cerr << "Unknown exception caught" << endl;
	}
	//IloInt taoyan;
	//cin>>taoyan;

	env.end();
	//system("pause"); 

	return 0;

}  // END main
