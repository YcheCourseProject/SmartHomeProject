using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using System.Net.NetworkInformation;
using System.Diagnostics;

//线程相关
using System.Threading;
using System.Threading.Tasks;
//自己代码
using SHEMS.entities;
 
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Popups;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;
using Windows.Networking.Sockets;
using Windows.Networking;
using Windows.Storage.Streams;




// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkId=391641

namespace SHEMS
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    ///  

    public sealed partial class MainPage : Page
    {
        //上下文
        SynchronizationContext context;
        bool acqflag = true;
        bool isReadyOn = true;
        public MainPage()
        {
            this.InitializeComponent();
            this.NavigationCacheMode = NavigationCacheMode.Required;
            context = SynchronizationContext.Current;
            List<string> acmodes = new List<string>
            {
                "Cool","Warm","Ventilate","Dehydrate"
            };
            comboBoxACMode.ItemsSource = acmodes;
        }

        /// <summary>
        /// Invoked when this page is about to be displayed in a Frame.
        /// </summary>
        /// <param name="e">Event data that describes how this page was reached.
        /// This parameter is typically used to configure the page.</param>
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            // TODO: Prepare page for display here.
            Task.Factory.StartNew(() =>
            {
                ThreadProcAcqTmpHumid();
            });
             Task.Factory.StartNew(() =>
            {
                ThreadProcAcqSmartMeterData();
            });
           
            // TODO: If your application contains multiple pages, ensure that you are
            // handling the hardware Back button by registering for the
            // Windows.Phone.UI.Input.HardwareButtons.BackPressed event.
            // If you are using the NavigationHelper provided by some templates,
            // this event is handled for you.
        }



        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Frame frame = Window.Current.Content as Frame;

            frame.Navigate(typeof(BlankPage1));
        }

        public async void ThreadProcAcqSmartMeterData()
        {
            byte[] bholdregs1 = null;
 
            TCPSGInterface meter1 = new TCPSGInterface("192.168.1.106",context);
            while(true)
            { 
            bholdregs1 = meter1.ReadHoldingRegisters(65, 2, bholdregs1).Result;
            Array.Reverse(bholdregs1);
            float Energy1 = BitConverter.ToSingle(bholdregs1, 0);
            context.Post(async (s) =>
            {

                //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  
                TxtTest.Text = Energy1.ToString();
            }, null);

            await Task.Delay(2000);
            }
        }
        public void ThreadProcOnOffAC(bool isReadyOn)
        {
            context.Post(async (s) =>
            {

                //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  
                if (isReadyOn == true)
                    AirConditioner.onAC();
                else
                    AirConditioner.OffAC();
                // MessageDialog messageDialog = new MessageDialog("ThreadProc1");
                //await messageDialog.ShowAsync();
            }, null);
        }
        public void ThreadProcUpDownAC(bool isReadyDown)
        {
            context.Post(async (s) =>
            {

                //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  
                if (isReadyDown == true)
                    AirConditioner.downTemperature();
                else
                    AirConditioner.upTemperature();
            }, null);
        }
        public void ThreadProcAcqTmpHumid()
        {

            context.Post(async (s) =>
            {

                //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  
                StreamSocket clientSocket = new StreamSocket();
                try
                {
                    HostName serverHost = new HostName(TmpHumidCtrl.TMP_HUM_SERVER_IP);
                    await clientSocket.ConnectAsync(serverHost, TmpHumidCtrl.TMP_HUM_PORT);
                    String sb = "";
                    String temprature;
                    String humidity;
                    DataReader reader = new DataReader(clientSocket.InputStream);
                    reader.InputStreamOptions = InputStreamOptions.Partial;  //采用异步方式

                    int tempint;

                    while (acqflag)
                    {
                        await reader.LoadAsync(1);  //获取一定大小的数据流
                        tempint = reader.ReadByte();

                        if (tempint == '%')
                        {
                            char[] ch = new char[] { ':' };
                            String[] tempstrs = sb.Split(ch);
                            string tempstr = tempstrs[1];
                            temprature = tempstr.Substring(2, 4);
                            humidity = tempstrs[1].Substring(9, 4);
                            TextBox_Tmp.Text = temprature;
                            TextBox_Humid.Text = humidity;
                            sb = "";
                        }
                        else
                        {
                            sb = sb + (char)tempint;

                        }
                    }
                }
                catch (Exception e)
                {
                    Debug.WriteLine(e.StackTrace);
                    clientSocket.Dispose();
                    clientSocket = null;
                }
                // MessageDialog messageDialog = new MessageDialog("ThreadProc1");
                //await messageDialog.ShowAsync();
            }, null);
        }
        public void ThreadProcOnOffSW(bool isReadyOn)
        {
            context.Post(async (s) =>
            {

                //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  
                if (isReadyOn == true)
                    for (int i = 0; i < 255; i++)
                    {
                        SwitchCtrl.switchOn(SwitchCtrl.SW_SERVER_IP + i);
                    }

                else
                    for (int i = 0; i < 255; i++)
                    {
                        SwitchCtrl.switchOff(SwitchCtrl.SW_SERVER_IP + i);
                    }
            }, null);
        }
        
        private void Button_Click_OnAC(object sender, RoutedEventArgs e)
        {
            Task.Factory.StartNew(() =>
            {
                ThreadProcOnOffAC(true);
            });
        }

        private void Button_Click_OffAC(object sender, RoutedEventArgs e)
        {
            Task.Factory.StartNew(() =>
            {
                ThreadProcOnOffAC(false);
            });
        }

        private void Button_Click_TmpDown(object sender, RoutedEventArgs e)
        {
            Task.Factory.StartNew(() =>
            {
                ThreadProcUpDownAC(true);
            });
        }

        private void Button_Click_TmpUp(object sender, RoutedEventArgs e)
        {
            Task.Factory.StartNew(() =>
            {
                ThreadProcUpDownAC(false);
            });
        }

        private void Button_Click_SW(object sender, RoutedEventArgs e)
        {
            Task.Factory.StartNew(() =>
            {
                ThreadProcOnOffSW(isReadyOn);
                isReadyOn = !isReadyOn;
            });
        }

        private void comboBoxACMODE_DropDownClosed(object sender,object e)
        {
            if(comboBoxACMode.SelectedItem!=null)
            {
                 
                String acmodestr = comboBoxACMode.SelectedItem as String;
                textBlock1.Text = acmodestr;
            }
        }

 


    }
}
