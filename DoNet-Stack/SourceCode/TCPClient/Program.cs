using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Net;
using System.Net.Sockets;
using System.Threading;
namespace TCPClient
{
    class Program
    {
        static void listenAndDoThings()
        {
            Console.WriteLine( " from thread ID: "
            + Thread.CurrentThread.ManagedThreadId);
            byte[] data = new byte[1024];
            Socket newclient = new Socket(AddressFamily.InterNetwork, SocketType.Stream, ProtocolType.Tcp);
            newclient.Bind(new IPEndPoint(IPAddress.Any, 905));

            //Console.WriteLine("please input the server ip:");
            //string ipadd = Console.ReadLine();
            //Console.WriteLine();
            //Console.WriteLine("please input the server port:");
            string ipadd = "10.0.0.154";
            int port = Convert.ToInt32("8000");

            IPEndPoint ie = new IPEndPoint(IPAddress.Parse(ipadd), port);   //服务器IP和端口

            try
            {
                newclient.Connect(ie);   //客户端想特定的服务器发送信息，所以不需要绑定本机的IP和端口
            }
            catch (SocketException e)
            {
                Console.WriteLine("unable to connect to server");
                Console.WriteLine(e.Message);
                return;
            }

            int receivedDataLebgth = newclient.Receive(data);
            string stringdata = Encoding.ASCII.GetString(data, 0, receivedDataLebgth);
            Console.WriteLine(stringdata);

            while (true)
            {
                //string input = Console.ReadLine();
                //if (input == "exit") break;
                DateTime dt = DateTime.Now;
                string str = dt.ToString();
                newclient.Send(Encoding.ASCII.GetBytes(str));
                data = new byte[1024];
                receivedDataLebgth = newclient.Receive(data);
                stringdata = Encoding.ASCII.GetString(data, 0, receivedDataLebgth);
                Thread.Sleep(500);
                Console.WriteLine(stringdata);
            }

            Console.WriteLine("disconnect from server");
            newclient.Shutdown(SocketShutdown.Both);

            newclient.Close();
        }
        static void Main(string[] args)
        {
            Console.WriteLine( "from thread ID: "
    + Thread.CurrentThread.ManagedThreadId);
            ThreadStart ts = new ThreadStart(listenAndDoThings);
            Thread t = new Thread(ts);
            t.Start();
          
        }
    }
}
