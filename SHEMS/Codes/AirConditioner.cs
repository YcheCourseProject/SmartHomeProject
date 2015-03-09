using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;


using Windows.Storage.Streams;
using Windows.Networking.Sockets;
using Windows.Networking;


namespace SHEMS.entities
{
    class AirConditioner
    {
        static public int temperature = 21;
        static AC_MODE ac_mode = AC_MODE.WARM;
        static string HOST_IP = "10.0.0.151";
        static string SEVER_PORT = "60000";
        static byte AC_CODE_ON = (byte)0x55;
        static byte AC_CODE_OFF = (byte)0x80;
        static public float COMFORT_TEMPERATURE = 21;
        static public float COMFORT_RESTRAIN_BOUND = 0.5f;
        static public bool isACOn = false;
         public enum AC_MODE
        {
            COLD, WARM, VENTILATION, DEHYDRATION
        }
        //必须要每次重新连套接字，否则的话要考虑连接中断的问题
        private static async void setACSettings(byte sendbyte)
        {
            StreamSocket clientSocket = null;
            try
            {
                HostName serverHost = new HostName(HOST_IP);
                clientSocket = new StreamSocket();
                await clientSocket.ConnectAsync(serverHost, SEVER_PORT);
                DataWriter writer = new DataWriter(clientSocket.OutputStream);
                writer.WriteByte(sendbyte);
                await writer.StoreAsync();
                writer.DetachStream();
                writer.Dispose();
                clientSocket.Dispose();
                clientSocket = null;
            }
            catch
            {
                clientSocket.Dispose();
                clientSocket = null;
            }
        }
       
        public static void downTemperature()
        {
            if (temperature > 16)
            {
                temperature--;
                setACTemperatureMode(temperature, ac_mode);
            }
        }
        public static void upTemperature()
        {
            if (temperature < 30)
            {
                temperature++;
                setACTemperatureMode(temperature, ac_mode);
            }
        }
        public static void setTemperatureWithComfortT()
        {
            setACTemperatureMode((int)AirConditioner.COMFORT_TEMPERATURE, ac_mode);
            temperature = (int)COMFORT_TEMPERATURE;
        }
        public static void setACMode(AC_MODE mode)
        {
            setACTemperatureMode(temperature, mode);
            ac_mode = mode;
        }
        public static void setACTemperatureMode(int temperature, AC_MODE mode)
        {
            byte temp;
            switch (mode)
            {
                case AC_MODE.COLD:
                    temp = (byte)0x40;
                    break;
                case AC_MODE.WARM:
                    temp = (byte)0x50;
                    break;
                case AC_MODE.VENTILATION:
                    temp = (byte)0x60;
                    break;
                case AC_MODE.DEHYDRATION:
                    temp = (byte)0x70;
                    break;
                default:
                    temp = (byte)0x40;
                    break;
            }
            if (temperature < 16 || temperature > 30)
                temperature = 21;
            temp = (byte)(temp + (temperature - 16));
            setACSettings(temp);
    
        }
        public static void onAC()
        {
            setACSettings(AC_CODE_ON);
        }
        public static void OffAC()
        {
            setACSettings(AC_CODE_OFF);
        }
    }
}
