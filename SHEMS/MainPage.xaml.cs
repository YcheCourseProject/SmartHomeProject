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
using Windows.UI.Xaml.Media.Imaging;



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
        bool acqflag = true;    //控制实时温湿度获取
        bool isAutoControlFlag = false;   //自动控制
        bool isReadyOn = true;
        static String COOL = "Cool";
        static String WARM = "Warm";
        static String VENTILATE = "Ventilate";
        static String DEHYDRATE = "Dehydrate";
        public MainPage()
        {
            this.InitializeComponent();
            this.NavigationCacheMode = NavigationCacheMode.Required;
            context = SynchronizationContext.Current;
            List<string> acmodes = new List<string>
            {
               COOL,WARM,VENTILATE,DEHYDRATE
            };
            comboBoxACMode.ItemsSource = acmodes;
            comboBoxACMode.SelectedIndex = 1;

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

            TCPSGInterface meter1 = new TCPSGInterface("192.168.1.106", context);
            TCPAmmeterData meterData1 = new TCPAmmeterData(meter1);
            while (true)
            {
                CSmartMeterData csmartMeterData1 = new CSmartMeterData();
                csmartMeterData1.getActive_Power(meterData1.read_active_Power());
                csmartMeterData1.getReactive_Power(meterData1.read_Reactive_Power());
                csmartMeterData1.getCurrent(meterData1.read_current());
                csmartMeterData1.getVoltage_Vph_n(meterData1.read_voltage_Vph_n());
                csmartMeterData1.getActive_Energy(meterData1.read_active_Energy());

                float activePower1 = csmartMeterData1.smartMeterData.Total_Active_Power_65;
                float reactivePower1 = csmartMeterData1.smartMeterData.Reactive_Power_Total_67;
                float current1 = csmartMeterData1.smartMeterData.Current_a_13;
                float voltage1 = csmartMeterData1.smartMeterData.Voltage_Va_n_1;
                double activeEnergy1 = csmartMeterData1.smartMeterData.Active_Energy_Import_Tariff_1_801;
                //bholdregs1 = meter1.ReadHoldingRegisters(65, 2, bholdregs1).Result;
                //Array.Reverse(bholdregs1);
                //float Energy1 = BitConverter.ToSingle(bholdregs1, 0);
                context.Post(async (s) =>
                {

                    //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  
                    TxtTest.Text = activePower1.ToString() + "\n\r" + reactivePower1.ToString()
                        + "\n\r" + current1.ToString() + "\n\r" + voltage1.ToString() + "\n\r" + activeEnergy1.ToString();
                }, null);

                await Task.Delay(2000);
            }
        }
        public void ThreadProcOnOffAC(bool isReadyOn)
        {
            if (isReadyOn == true)
                AirConditioner.onAC();
            else
                AirConditioner.OffAC();

            context.Post(async (s) =>
            {
                // MessageDialog messageDialog = new MessageDialog("ThreadProc1");
                //await messageDialog.ShowAsync();
                //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  

            }, null);
        }
        public void ThreadProcUpDownAC(bool isReadyDown)
        {
            if (isReadyDown == true)
                AirConditioner.downTemperature();
            else
                AirConditioner.upTemperature();
            context.Post(async (s) =>
            {
                //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  
                textBlock1.Text = AirConditioner.temperature.ToString();
            }, null);
        }
        public  async void ThreadProcAcqTmpHumid()
        {
            StreamSocket clientSocket = new StreamSocket();
            String temprature="";
            String humidity="";
            try
            {
                HostName serverHost = new HostName(TmpHumidCtrl.TMP_HUM_SERVER_IP);
                await clientSocket.ConnectAsync(serverHost, TmpHumidCtrl.TMP_HUM_PORT);
                String sb = "";
 
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
   
                        sb = "";
                        if (isAutoControlFlag)
                        {
                            if (Single.Parse(temprature) - AirConditioner.COMFORT_TEMPERATURE > AirConditioner.COMFORT_RESTRAIN_BOUND)
                            {
                                if (AirConditioner.isACOn == true)
                                {
                                    AirConditioner.OffAC();
                                    AirConditioner.isACOn = false;
                                }
                            }
                            else if (Single.Parse(temprature) - AirConditioner.COMFORT_TEMPERATURE < -AirConditioner.COMFORT_RESTRAIN_BOUND)
                            {
                                if (AirConditioner.isACOn == false)
                                {
                                    //AirConditioner.onAC();
                                    AirConditioner.setTemperatureWithComfortT();
                                    AirConditioner.isACOn = true;
                                }
                            }

                        }
                        context.Post(async (s) =>
                        {

                            //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  
                            TextBox_Tmp.Text = temprature;
                            TextBox_Humid.Text = humidity;
                            textBlock1.Text = AirConditioner.isACOn.ToString();
                            // MessageDialog messageDialog = new MessageDialog("ThreadProc1");
                            //await messageDialog.ShowAsync();
                        }, null);
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
           
        }
        public void ThreadProcOnOffSW(bool isReadyOn)
        {
            //要考虑
            int i = 8;
            if (isReadyOn == true)
            //for (int i = 0; i < 255; i++)
            //{
            {
               
                SwitchCtrl.switchOn(SwitchCtrl.SW_SERVER_IP + i);

            }//}

            else
                //for (int i = 0; i < 255; i++)
                {
                
                    SwitchCtrl.switchOff(SwitchCtrl.SW_SERVER_IP + i);
                }
            context.Post(async (s) =>
            {

                //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的            
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

        private void comboBoxACMODE_DropDownClosed(object sender, object e)
        {


            if (comboBoxACMode.SelectedItem != null)
            {

                String acmodestr = comboBoxACMode.SelectedItem as String;

                textBlock1.Text = acmodestr;
                BitmapImage bmp = new BitmapImage();
                String ACmodeStr = comboBoxACMode.SelectedItem as String;
                String picstr;

                if (ACmodeStr == COOL)
                {
                    picstr = "ac_mode_cool.png";
                    Task.Factory.StartNew(() =>
                {
                    AirConditioner.setACMode(AirConditioner.AC_MODE.COLD);   
                });


                }
                else if (ACmodeStr == WARM)
                {
                    picstr = "ac_mode_hot.png";
                    Task.Factory.StartNew(() =>
                {
                    AirConditioner.setACMode(AirConditioner.AC_MODE.WARM);
           
              
                });

                }
                else if (ACmodeStr == VENTILATE)
                {
                    picstr = "ac_mode_ventilate.png";
                    Task.Factory.StartNew(() =>
            {
                AirConditioner.setACMode(AirConditioner.AC_MODE.VENTILATION);
            });

                }
                else
                {
                    picstr = "ac_mode_dehydrate.png";
                    Task.Factory.StartNew(() =>
                    {
                        AirConditioner.setACMode(AirConditioner.AC_MODE.DEHYDRATION);
                    });

                }

                bmp.UriSource = new Uri("ms-appx:///Assets/" + picstr, UriKind.RelativeOrAbsolute);
                Img_ACMode.Source = bmp;
                Img_ACMode.Stretch = Stretch.Fill;

            }
        }

        private void RadioButton_Checked(object sender, RoutedEventArgs e)
        {
            isAutoControlFlag = true;
        }

        private void RadioButton_Checked_1(object sender, RoutedEventArgs e)
        {
            isAutoControlFlag = false;
        }

        private void Button_Click_ChangeComfort(object sender, RoutedEventArgs e)
        {
           
            if(TxtBox_ComfortTemperature.Text!="")
            {
                AirConditioner.COMFORT_TEMPERATURE = Single.Parse(TxtBox_ComfortTemperature.Text);
            }
        }




    }
}
