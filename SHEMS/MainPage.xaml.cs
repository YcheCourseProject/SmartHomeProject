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
//绑定UI和数据的
using System.ComponentModel;

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

    public sealed partial class MainPage : Page, INotifyPropertyChanged
    {
        //上下文
        public static string AC_STATUS_PRESS2On="Press On";
        public static string AC_STATUS_PRESS2OFF = "Press Off";
        SynchronizationContext context;
        bool meteracqflag = true;
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
            //ACItem.DataContext = this;
            
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
            meteracqflag = true;
            // TODO: Prepare page for display here.
            Task.Factory.StartNew(() =>
            {
                ThreadProcAcqTmpHumid();
            });
           // Task.Factory.StartNew(() =>
           //{
           //    ThreadProcAcqSmartMeterData();

           //});

            // TODO: If your application contains multiple pages, ensure that you are
            // handling the hardware Back button by registering for the
            // Windows.Phone.UI.Input.HardwareButtons.BackPressed event.
            // If you are using the NavigationHelper provided by some templates,
            // this event is handled for you.
        }
        protected override void OnNavigatedFrom(NavigationEventArgs e)
        {
            base.OnNavigatedFrom(e);
            meteracqflag = false;
        }


        private void Button_Click(object sender, RoutedEventArgs e)
        {
            Frame frame = Window.Current.Content as Frame;

            frame.Navigate(typeof(BlankPage1));
        }

       
        public void ThreadProcOnOffAC(bool isReadyOn)
        {
            if (isReadyOn == true)
                AirConditioner.onAC();
            else
                AirConditioner.OffAC();

 
        }
        public void ThreadProcUpDownAC(bool isReadyDown)
        {
            if (isReadyDown == true)
                AirConditioner.downTemperature();
            else
                AirConditioner.upTemperature();
            context.Post(  (s) =>
            {
                //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  
                this.Txt_SetT.Text = AirConditioner.temperature.ToString();
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
                        context.Post(  (s) =>
                        {

                            //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  
                            this.TextBox_Tmp.Text = temprature+"℃";
                            this.TextBox_Humid.Text= humidity+"%";
                            //textBlock1.Text = AirConditioner.isACOn.ToString();
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
            string picstr = "";
            context.Post((s) =>
            {

                //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  
                if (isReadyOn == true)
                {
                    TxtHumidifierStatus.Text = AC_STATUS_PRESS2OFF;
                    picstr = "switch_on_normal.png";
                }
                else
                {
                    TxtHumidifierStatus.Text = AC_STATUS_PRESS2On;
                    picstr = "switch_off_normal.png";
                }
                BitmapImage bmp = new BitmapImage();
                Img_SW.Source = bmp;

                bmp.UriSource = new Uri("ms-appx:///Assets/" + picstr, UriKind.RelativeOrAbsolute);
            }, null);
            //要考虑
            int i = 2;
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

                
        }
 
        private void Button_Click_OnOffAC(object sender,RoutedEventArgs e)
        {
            Task.Factory.StartNew(() =>
            {
                 
              
                string picstr = "";
                 string tempstr="";
                context.Post(  (s) =>
                {

                    //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  
                   tempstr = TxtOnOffStatus.Text.ToString();
                }, null);
                bool isTextPress2On = true;
                if (tempstr.Equals(AC_STATUS_PRESS2OFF))
                {
                    ThreadProcOnOffAC(false);
                    isTextPress2On = true;
                   
                }
                else if (tempstr.Equals(AC_STATUS_PRESS2On))
                {
                    ThreadProcOnOffAC(true);
                    isTextPress2On = false;
                }
                else
                    return;
                context.Post(  (s) =>
                {

                    //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的  
                    if(isTextPress2On==true)
                    {
                        TxtOnOffStatus.Text = AC_STATUS_PRESS2On;
                        picstr = "switch_off_normal.png";
                    }
                    else
                    {
                        TxtOnOffStatus.Text = AC_STATUS_PRESS2OFF;
                        picstr = "switch_on_normal.png";
                    }
                    BitmapImage bmp = new BitmapImage();
                    Img_ACOnOFF.Source = bmp;
             
                    bmp.UriSource = new Uri("ms-appx:///Assets/" + picstr, UriKind.RelativeOrAbsolute);
                }, null);
               
            });
        }
        //private void Button_Click_OnAC(object sender, RoutedEventArgs e)
        //{
        //    Task.Factory.StartNew(() =>
        //    {
        //        ThreadProcOnOffAC(true);
        //    });
        //}

        //private void Button_Click_OffAC(object sender, RoutedEventArgs e)
        //{
        //    Task.Factory.StartNew(() =>
        //    {
        //        ThreadProcOnOffAC(false);
        //    });
        //}

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

        private void Button_Click_toLoad(object sender, RoutedEventArgs e)
        {
            Frame frame = Window.Current.Content as Frame;
            frame.Navigate(typeof(BlankPage1));
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

                //textBlock1.Text = acmodestr;
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

        //private void Button_Click_ChangeComfort(object sender, RoutedEventArgs e)
        //{
           
        //    if(TxtBox_ComfortTemperature.Text!="")
        //    {
        //        AirConditioner.COMFORT_TEMPERATURE = Single.Parse(TxtBox_ComfortTemperature.Text);
        //    }
        //}

        private void ToggleSwitch_Toggled(object sender, RoutedEventArgs e)
        {
            //改是否自动控制的状态

            var toggle = sender as ToggleSwitch;
            if (toggle.IsOn)
            {
                isAutoControlFlag = true;
            }
            else
            {
                isAutoControlFlag = false;
            }
        }

        public event PropertyChangedEventHandler PropertyChanged;
        public void OnPropertyChanged(string name)
        {
            PropertyChangedEventHandler handler = PropertyChanged;
            if (handler != null)
            {
                handler(this, new PropertyChangedEventArgs(name));
            }
        }

        private void Button_Click_ChangeHumidityComfort(object sender, RoutedEventArgs e)
        {
            SwitchCtrl.COMFORT_HUMID = Single.Parse(TxtBox_ComfortHumidity.Text);
            Text_SetHumid.Text = SwitchCtrl.COMFORT_HUMID.ToString() + "%";
        }

        private void Button_Click_ChangeComfort(object sender, RoutedEventArgs e)
        {
            AirConditioner.COMFORT_TEMPERATURE = Single.Parse(TxtBox_ComfortTemperature.Text);
            Text_SetTmp.Text = AirConditioner.COMFORT_TEMPERATURE.ToString() + "℃";
        }


    }
}
