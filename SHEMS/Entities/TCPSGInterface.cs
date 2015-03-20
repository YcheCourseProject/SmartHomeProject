using System;
using System.Collections.Generic;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Linq;
using System.Text;
using System.Net;
//异步操作
using System.Threading.Tasks;
using System.Threading;
using Windows.Storage.Streams;
using Windows.Networking.Sockets;
using Windows.Networking;
using Windows.UI.Popups;

namespace SHEMS.Entities
{
    class TCPSGInterface//TCP，用于读取电表数据的简单接口类
    {
        SynchronizationContext context;
        StreamSocketListener listener;
        StreamSocket clientSocket;
        DataWriter writer;
        private String meter_ipaddr;
        private static String SEVER_PORT = "502";
        //public TcpClient client;
        /// <summary>
        /// 构造函数
        /// </summary>
        public TCPSGInterface(String meter_ipaddr, SynchronizationContext context)//参数为电表的IP地址
        {
            this.meter_ipaddr = meter_ipaddr;
            this.context = context;
            //this.client = new TcpClient(meter_ipaddr, 502);
        }

        /// <summary>
        /// Simple Modbus TCP master read holding registers
        /// Modbus协议函数功能3，读取保持寄存器(16位)
        /// startAddress：起始偏移量（从0开始计数）
        /// numInputs：读取的寄存器个数
        /// holdregs：存放结果用的数组
        /// 读回结果以UINT8为单位存放在数组里(以通信过程中的字节流顺序为单位存放)
        /// </summary>
        /// 

        //private async void startListener()
        //{
        //    listener = new StreamSocketListener();
        //    if (listener != null)
        //    {
        //        await new MessageDialog("监听已经启动了").ShowAsync();
        //        return;
        //    }
        //    listener = new StreamSocketListener();
        //    listener.ConnectionReceived += OnConnection;
        //    try
        //    {
        //        await listener.BindServiceNameAsync("22112");
        //        await new MessageDialog("正在监听中").ShowAsync();
        //    }
        //    catch (Exception exception)
        //    {
        //        listener = null;
        //        if (SocketError.GetStatus(exception.HResult) == SocketErrorStatus.Unknown)
        //        {
        //            throw;
        //        }

        //    }
        //}
        //private async void OnConnection(StreamSocketListener sender, StreamSocketListenerConnectionReceivedEventArgs args)
        //{
        //    DataReader reader = new DataReader(args.Socket.InputStream);
        //    try
        //    {
        //        while (true)
        //        {
        //            uint sizeFieldCount = await reader.LoadAsync(sizeof(byte));
        //            if (sizeFieldCount != sizeof(uint))
        //            {
        //                return;
        //            }
        //            uint stringLength = reader.ReadUInt32();
        //            uint actualStringLength = await reader.LoadAsync(stringLength);
        //            if (stringLength != actualStringLength)
        //            {
        //                return;
        //            }
        //            string msg = reader.ReadString(actualStringLength);


        //        }
        //    }
        //    catch (Exception exception)
        //    {
        //        if (SocketError.GetStatus(exception.HResult) == SocketErrorStatus.Unknown)
        //        {
        //            throw;
        //        }
        //    }
        //}
        //private async void connectSocket()
        //{
        //    if (clientSocket != null)
        //    {
        //        await new MessageDialog("已经连接了Socket").ShowAsync();
        //        return;
        //    }
        //    HostName hostName = null;
        //    string message = "";
        //    try
        //    {
        //        hostName = new HostName("localhost");
        //    }
        //    catch (ArgumentException)
        //    {
        //        message = "主机名不可用";
        //    }
        //    if (message != "")
        //    {
        //        await new MessageDialog(message).ShowAsync();
        //        return;
        //    }
        //    clientSocket = new StreamSocket();
        //    try
        //    {
        //        await clientSocket.ConnectAsync(hostName, "22112");
        //        await new MessageDialog("连接成功").ShowAsync();
        //    }
        //    catch (Exception exception)
        //    {
        //        if (SocketError.GetStatus(exception.HResult) == SocketErrorStatus.Unknown)
        //        {
        //            throw;
        //        }
        //    }
        //}
        //private void closeSocket()
        //{
        //    if (writer != null)
        //    {
        //        writer.DetachStream();
        //        writer.Dispose();
        //        writer = null;
        //    }
        //    if (clientSocket != null)
        //    {
        //        clientSocket.Dispose();
        //        clientSocket = null;
        //    }
        //    if (listener != null)
        //    {
        //        listener.Dispose();
        //        listener = null;
        //    }
        //}
        //private async void sendMsg()
        //{
        //    if (listener == null)
        //    {
        //        await new MessageDialog("监听未启动").ShowAsync();
        //        return;
        //    }
        //    if (clientSocket == null)
        //    {
        //        await new MessageDialog("未连接Socket").ShowAsync();
        //        return;
        //    }
        //    if (writer == null)
        //    {
        //        writer = new DataWriter(clientSocket.OutputStream);
        //    }

        //    writer.WriteUInt32(writer.MeasureString(stringToSend));
        //    writer.WriteString(stringToSend);
        //    try
        //    {
        //        await writer.StoreAsync();
        //        await new MessageDialog("发送成功").ShowAsync();
        //    }
        //    catch (Exception exception)
        //    {
        //        if (SocketError.GetStatus(exception.HResult) == SocketErrorStatus.Unknown)
        //        {
        //            throw;
        //        }
        //    }
        //}
        public async Task<byte[]> ReadHoldingRegisters(ushort startAddress, ushort numInputs, byte[] bholdregs)
        {

            try
            {
                if(clientSocket==null)
                { 
                HostName serverHost = new HostName(this.meter_ipaddr);
                clientSocket = new StreamSocket();
                await clientSocket.ConnectAsync(serverHost, SEVER_PORT);      
                }
                DataWriter writer;
                writer = new DataWriter(clientSocket.OutputStream);
                byte[] tempbytes = getSendReadHoldingRegisternumsPackage(startAddress, numInputs);
                writer.WriteBytes(tempbytes);
                try
                {
                    await writer.StoreAsync();
                    //context.Post((s) =>
                    //{

                    //    //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  
                    //    //await new MessageDialog("发送成功").ShowAsync();
                    //}, null);               
                }
                catch (Exception exception)
                {
                    if (SocketError.GetStatus(exception.HResult) == SocketErrorStatus.Unknown)
                    {
                        throw;
                    }
                }
                DataReader reader = new DataReader(clientSocket.InputStream);
                try
                {
                    byte[] bytes = new byte[9];
                    await reader.LoadAsync(9);
                    reader.ReadBytes(bytes);
                    uint length = bytes[8];
                    if (length == 0)
                        return null;
                    await reader.LoadAsync(length);
                    bholdregs = new byte[length];
                    reader.ReadBytes(bholdregs);
                }
                catch (Exception exception)
                {
                    if (SocketError.GetStatus(exception.HResult) == SocketErrorStatus.Unknown)
                    {
                        throw;
                    }
                }
                if (writer != null)
                {
                    writer.DetachStream();
                    writer.Dispose();
                    writer = null;
                }
                if (clientSocket != null)
                {
                    clientSocket.Dispose();
                    clientSocket = null;
                }     
            }
            catch(Exception exception)
            {
                if (SocketError.GetStatus(exception.HResult) == SocketErrorStatus.Unknown)
                {
                    throw;
                }
            }
            return bholdregs;
        }
        /// <summary>
        /// 析构函数
        /// </summary>
        ~TCPSGInterface()
        {
            //client.Close();
        }
        /// <summary>
        /// 关闭TCP连接，断开于电表的通信
        /// </summary>
        public void close()
        {
            //client.Close();
        }

        /// <summary>
        /// 将UINT16的数组的每一个UINT16分割为两个UINT8，存放到UINT8的数组里面
        /// </summary>
        public void ushort2byte(ushort[] a, byte[] b)
        {
            for (int i = 0; i < a.GetLength(0); i++)
            {
                byte[] c = BitConverter.GetBytes(a[i]);
                b[2 * i] = c[1];
                b[2 * i + 1] = c[0];
            }
        }

        public static uint getHex(int hexbytesnum)
        {
            uint retInt = 0xffffffff;
            int changebit = (4 - hexbytesnum) * 8;
            if (changebit > 0)
            {
                retInt = retInt >> changebit;
            }
            return (uint)retInt;
        }
        public static byte[] int2bytes(uint operatedinteger, int bytesnum) // 构建包的时候用
        {
            // 一个Int 4个Byte

            int index = 0;
            operatedinteger = operatedinteger & (getHex(bytesnum));
            byte[] bytes = new byte[bytesnum];
            for (int i = 0; i < bytesnum; i++)
            {
                uint tempinteger = operatedinteger;
                // 得先向左移位 后到顶再向右移位到底才行
                int leftchangebitnum = (4 - bytesnum + i) * 8;
                tempinteger = tempinteger << leftchangebitnum;
                int rightchangebitnum = 24;

                tempinteger = tempinteger >> rightchangebitnum;
                bytes[index] = (byte)((tempinteger));
                index++;

            }
            return bytes;
        }
        public byte[] getSendReadHoldingRegisternumsPackage(uint referencenum, uint wordcount)// 构建手机发的包 function code 3
        {
            byte functioncode = 3;
            uint length = 1 + 1 + 2 + 2;
            uint unitID = 1;
            int index = 0; // sendbytes index
            byte[] sendbytes = new byte[2 + 2 + 2 + 1 + 1 + 2 + 2];
            byte[] tranidbytes = int2bytes(0, 2); // transaction id
            // 为了简单处理固定为0x00
            // 0x00
            byte[] protoclbytes = int2bytes(0, 2);// modbus协议 固定0x00
            // 0x00
            byte[] unitIdbytes = int2bytes(unitID, 1);
            byte[] lengthbytes = int2bytes(length, 2);
            byte[] referencenumbytes = int2bytes(referencenum, 2);
            byte[] wordcountbytes = int2bytes(wordcount, 2);

            sendbytes[index] = tranidbytes[0];
            index++; // transaction id 2Bytes
            sendbytes[index] = tranidbytes[1];
            index++;
            sendbytes[index] = protoclbytes[0];
            index++; // protocol id 2Bytes
            sendbytes[index] = protoclbytes[1];
            index++;
            sendbytes[index] = lengthbytes[0];
            index++; // length 2Bytes
            sendbytes[index] = lengthbytes[1];
            index++;
            sendbytes[index] = unitIdbytes[0];
            index++; // unit id 1Bytes

            sendbytes[index] = functioncode;
            index++; // function 1Bytes
            sendbytes[index] = referencenumbytes[0];
            index++; // reference number 2Bytes
            sendbytes[index] = referencenumbytes[1];
            index++;
            sendbytes[index] = wordcountbytes[0];
            index++; // word count 2Bytes
            sendbytes[index] = wordcountbytes[1];
            index++;

            return sendbytes;
        }

    }
}
