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
    class TmpHumidCtrl
    {
        public static String TMP_HUM_PORT = "8000";
        public static String TMP_HUM_SERVER_IP = "10.0.0.152";
        static StreamSocket clientSocket = null;
  
        private static async void receiveMsg()
        {
            clientSocket = new StreamSocket();
            try
            {
                HostName serverHost = new HostName(TMP_HUM_SERVER_IP);
                await clientSocket.ConnectAsync(serverHost, TMP_HUM_PORT);
            }
            catch
            {
                clientSocket.Dispose();
                clientSocket = null;
            }
            try
            {
                String sb = "";
                String temprature;
                String humidity;
                DataReader reader = new DataReader(clientSocket.InputStream);
                int tempint;
                while(true)
                {
                    tempint = reader.ReadByte();
                    if(tempint=='%')
                    {
                        char []ch=new char[]{':'};
                        String [] tempstrs=sb.Split(ch);
                        temprature = tempstrs[1].Substring(1, 6);
                        humidity = tempstrs[1].Substring(8, 12);
                        sb = "";
                    }
                    else
                    {
                        sb = sb + (char)tempint;
                    }
                }
            }
            catch(Exception exception)
            {
               
               receiveMsg();
            }
        }

        public static void disTmpHumid()
        {
         
            receiveMsg();
        }
    }
}
