using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using MeterInterface;
using System.Threading;
using System.Data;
using System.Data.SqlClient;
using System.Timers;
using System.IO;
using System.Net;
using System.Net.Sockets;
 
using System.Collections;
using System.Runtime.InteropServices;
//.......
using MeterDataAcq.Services;
using MeterDataAcq.Models;
using MeterDataAcq;
namespace ConsoleApplicationDataAcquire
{
    class Program
    {
        #region tcpserver
        public static Thread myThread;       //声明一个线程实例
        public static Socket newsock;        //声明一个Socket实例
        public static Socket server1;
        public static Socket Client;
        public static Hashtable _sessionTable;
        public static IPEndPoint localEP;
        public static EndPoint remote;
        public static int setPort;
        #endregion

        static bool RunFlag = true;
        public TCPSGInterface TcpMeter;
        public TCPAmmeterData MeterDataAccessor;
        public CSmartMeterData SmartMeterData;
        public static PublicSQL publicSQL;
        
        public static String UID = Constants.DB_USER_NAME;
        public static String PASSWD = Constants.DB_USER_PASSWD;
        public static String DB = Constants.DB_NAME;

        bool error=false;
 
        Program()
        {
            connect();
         
        }
        void connect()
        {
            try
            {
                //电表连接
                TcpMeter = new TCPSGInterface(Constants.METER_IP);
                MeterDataAccessor = new TCPAmmeterData(TcpMeter);
                SmartMeterData = new CSmartMeterData();
                //数据库连接
                publicSQL = new PublicSQL(UID, PASSWD, DB);
                error = false;
            }
            catch (Exception e)
            {
                error = true;
                //Console.WriteLine("{0}", e.ToString());
                Console.WriteLine("Connect Fail");
            }
        }
        
        void Data2mssql()
        {
            if (error)
            {
                Console.WriteLine("Something error happened");
                connect();
                return;
            }
            try
            {
                //从电表读取数据,并解析到对象                
                SmartMeterData.getActive_Energy(MeterDataAccessor.read_active_Energy());
                SmartMeterData.getActive_Power(MeterDataAccessor.read_active_Power());
                SmartMeterData.getReactive_Power(MeterDataAccessor.read_Reactive_Power());
                DateTime datetime = DateTime.Now;
                CSmartMeterDataInfo datainfo=SmartMeterData.smartMeterData;
                UserfulMeterData userfulMeterData = new UserfulMeterData(datainfo.Total_Active_Power_65, datainfo.Reactive_Power_Total_67, datainfo.Active_Energy_Import_Tariff_1_801, datetime);
                publicSQL.DataOperate(userfulMeterData, PublicSQL.MODE_INSERT);
                string tempstr = "---GetData:" + "\nTime:" +
                    userfulMeterData.DateTime + "\nActivePower:" +
                    String.Format("{0:F}", userfulMeterData.ActivePower) +
                    "W\tReactivePower:" +
                    String.Format("{0:F}", userfulMeterData.ReactivePower) +
                    "Var\nEnergy:" + String.Format("{0:F}", userfulMeterData.ActiveEnergy)+"\n";
                Console.WriteLine(tempstr);
            }
            catch (Exception e)
            {
                Console.WriteLine("{0}", e.ToString());
                connect();
            }
        
        }
 
        



        /// <summary>
        /// 程序入口
        /// </summary>
        /// <param name="args"></param>
        static void Main(string[] args)
        {
            Console.WriteLine("SmartMemte DataAcquire Program");
            Console.WriteLine("waiting......");
            Program a = new Program();
            Console.WriteLine("<Start>");

          
            while (RunFlag)
            {
                
                a.Data2mssql();
                System.Threading.Thread.Sleep(1000);
            }
        
        }


    }
}

 