using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Runtime.InteropServices;
using System.IO;
using System.Net;
using System.Net.Sockets;
using SensorDataAcq.Models;
using SensorDataAcq.Services;


namespace SensorDataAcq
{
    class Program
    {
        #region TCPServer
        static System.ComponentModel.IContainer components = null;
        static private tcpServer.TcpServer tcpServer1;
        static private PublicSQL publicSQL=new PublicSQL(Constants.DB_USER_NAME,Constants.DB_USER_PASSWD,Constants.DB_NAME);
        static private void openTcpPort()
        {
            tcpServer1.Close();
            tcpServer1.Port = 8080;
            tcpServer1.Open();           
        }
        public static void logData(bool sent, string text)
        {
            String tempstr = "";
            DateTime dt = DateTime.Now;
            if (text.Length > 10)
            {
                SensorData sensorData = new SensorData();
                String[] strs = text.Split(new char[] { ',' });
                string temprature = strs[0];
                string humidity = strs[1];
                string light = strs[2];
                string gas = strs[3];
                string fire = strs[4];
                string humanstatus = strs[5];
                sensorData.TemperatureData = Double.Parse(temprature.Split(new char[] { ':' })[1]);
                sensorData.HumidityData = Double.Parse(humidity.Split(new char[] { ':' })[1]);
                sensorData.LightData = Double.Parse(light.Split(new char[] { ':' })[1]);
                sensorData.GasData = Double.Parse(gas.Split(new char[] { ':' })[1]);
                sensorData.FireData = Double.Parse(fire.Split(new char[] { ':' })[1]);
                sensorData.HumanStatus = Int16.Parse(humanstatus.Split(new char[] { ':' })[1]);
                sensorData.DateTime = dt.ToString();
                tempstr = temprature + ";" + humidity + ";" + light + ";" + gas + ";" + fire + ";" + humanstatus;
                Console.WriteLine(tempstr);
                try
                { 
                publicSQL.DataOperate(sensorData, PublicSQL.MODE_INSERT);
                    }
                catch
                {
                    Console.WriteLine("2DB error");
                }
            }

        }
        protected static byte[] readStream(TcpClient client)
        {
            NetworkStream stream = client.GetStream();
            if (stream.DataAvailable)
            {
                byte[] data = new byte[client.Available];

                int bytesRead = 0;
                try
                {
                    bytesRead = stream.Read(data, 0, data.Length);
                }
                catch (IOException)
                {
                }

                if (bytesRead < data.Length)
                {
                    byte[] lastData = data;
                    data = new byte[bytesRead];
                    Array.ConstrainedCopy(lastData, 0, data, 0, bytesRead);
                }
                return data;
            }
            return null;
        }
        private static void tcpServer1_OnDataAvailable(tcpServer.TcpServerConnection connection)
        {
            if (connection == null)
                return;
            else
                if (connection.Socket == null)
                    return;
            byte[] data = readStream(connection.Socket);

            if (data != null)
            {
                string dataStr = Encoding.ASCII.GetString(data);
 
                logData(false, dataStr);
  

                data = null;
            }
        }
        private static void tcpServer1_OnConnect(tcpServer.TcpServerConnection connection)
        {
            //invokeDelegate setText = () => lblConnected.Text = tcpServer1.Connections.Count.ToString();
            Console.WriteLine("connect count:" + tcpServer1.Connections.Count.ToString());
            //Invoke(setText);
        }
        private static void Dispose()
        {
            if ((components != null))
            {
                components.Dispose();
            }

        }

        public delegate bool ControlCtrlDelegate(int CtrlType);
        [DllImport("kernel32.dll")]
        private static extern bool SetConsoleCtrlHandler(ControlCtrlDelegate HandlerRoutine, bool Add);
        private static ControlCtrlDelegate cancelHandler = new ControlCtrlDelegate(HandlerRoutine);
        public static bool HandlerRoutine(int CtrlType)
        {

            if (tcpServer1 != null)
                tcpServer1.Close();

            switch (CtrlType)
            {

                case 0:
                    Console.WriteLine("Press Any Key Exit"); //Ctrl+C关闭  
                    break;
                case 2:
                    Console.WriteLine("Press Any Key Exit");//按控制台关闭按钮关闭  
                    break;

            }
            Console.ReadLine();
            return true;
        }
        #endregion

        static void Main(string[] args)
        {
            Console.WriteLine("Start Listening Port:8080...");
            SetConsoleCtrlHandler(cancelHandler, true);
            components = new System.ComponentModel.Container();
            tcpServer1 = new tcpServer.TcpServer(components);
            tcpServer1.IdleTime = 50;
            tcpServer1.IsOpen = false;
            tcpServer1.MaxCallbackThreads = 100;
            tcpServer1.MaxSendAttempts = 3;
            tcpServer1.Port = -1;
            tcpServer1.VerifyConnectionInterval = 0;
            tcpServer1.OnConnect += new tcpServer.tcpServerConnectionChanged(tcpServer1_OnConnect);
            tcpServer1.OnDataAvailable += new tcpServer.tcpServerConnectionChanged(tcpServer1_OnDataAvailable);
            openTcpPort();

        }
    }
}
