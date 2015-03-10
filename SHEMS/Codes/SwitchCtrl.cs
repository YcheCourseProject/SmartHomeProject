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
    class SwitchCtrl
    {
        static public float COMFORT_HUMID= 35;
        static public float COMFORT_RESTRAIN_BOUND = 5;
        static public bool isSwitchOn = false;
        static byte[] ON_SWITCH_BYTES = { (byte) 0x5a, (byte) 0xa5, (byte) 0xaa,
		(byte) 0x55, (byte) 0x5a, (byte) 0xa5, (byte) 0xaa, (byte) 0x55,
		(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x65,
		(byte) 0xcf, (byte) 0x00, (byte) 0x00, (byte) 0x11, (byte) 0x27,
		(byte) 0x6a, (byte) 0x00, (byte) 0x92, (byte) 0x80, (byte) 0x90,
		(byte) 0x1f, (byte) 0x11, (byte) 0x0d, (byte) 0x43, (byte) 0xb4,
		(byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xb2,
		(byte) 0xbe, (byte) 0x00, (byte) 0x00, (byte) 0x7f, (byte) 0xe8,
		(byte) 0xe7, (byte) 0x85, (byte) 0x73, (byte) 0x69, (byte) 0xe3,
		(byte) 0x3b, (byte) 0xf9, (byte) 0x4b, (byte) 0x5c, (byte) 0x33,
		(byte) 0x40, (byte) 0x6a, (byte) 0x05, (byte) 0x82, };

        static byte[] OFF_SWITCH_BYTES = { (byte) 0x5a, (byte) 0xa5, (byte) 0xaa,
		(byte) 0x55, (byte) 0x5a, (byte) 0xa5, (byte) 0xaa, (byte) 0x55,
		(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
		(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x89,
		(byte) 0xce, (byte) 0x00, (byte) 0x00, (byte) 0x11, (byte) 0x27,
		(byte) 0x6a, (byte) 0x00, (byte) 0x8b, (byte) 0x82, (byte) 0x90,
		(byte) 0x1f, (byte) 0x11, (byte) 0x0d, (byte) 0x43, (byte) 0xb4,
		(byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0xb1,
		(byte) 0xbe, (byte) 0x00, (byte) 0x00, (byte) 0x05, (byte) 0x79,
		(byte) 0x61, (byte) 0x60, (byte) 0x4d, (byte) 0x85, (byte) 0xe7,
		(byte) 0x30, (byte) 0xe4, (byte) 0xbc, (byte) 0xd8, (byte) 0x59,
		(byte) 0x29, (byte) 0x3c, (byte) 0x4a, (byte) 0x53, };

        static public string SW_SERVER_CURIP = "10.0.0.2";
        static public String SW_SERVER_IP = "10.0.0.";
        static string QUIC_PORT = "80";
        static StreamSocket clientSocket = null;

        public static async void switchOn(String severip)
        {
            DatagramSocket datagramSocket = null;
            try
            {
                HostName hostName = new HostName(severip);
                datagramSocket = new DatagramSocket();

                IOutputStream outputStream = await datagramSocket.GetOutputStreamAsync(hostName, QUIC_PORT);
                DataWriter writer = new DataWriter(outputStream);
                writer.WriteBytes(ON_SWITCH_BYTES);
                await writer.StoreAsync();
                isSwitchOn = true;
            }
            catch (Exception exception)
            {

                datagramSocket.Dispose();
                datagramSocket = null;
            }
        }
        public static async void switchOff(String severip)
        {
            DatagramSocket datagramSocket = null;
            try
            {
                HostName hostName = new HostName(severip);
                datagramSocket = new DatagramSocket();
                IOutputStream outputStream = await datagramSocket.GetOutputStreamAsync(hostName, QUIC_PORT);
                DataWriter writer = new DataWriter(outputStream);
                writer.WriteBytes(OFF_SWITCH_BYTES);
                await writer.StoreAsync();
                isSwitchOn = false;
            }
            catch (Exception exception)
            {
                datagramSocket.Dispose();
                datagramSocket = null;
            }
        }
    }
}
