using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
//线程相关
using System.Threading;
using System.Threading.Tasks;
//自己代码
using SHEMS.entities;
using SHEMS.Codes;

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
//
using Windows.UI.Core;
//Http
using Windows.Web.Http;
using Windows.Web.Http.Filters;
using Windows.Security.Cryptography;
using Windows.Storage.Streams;


// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkID=390556

namespace SHEMS
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class BlankPage1 : Page
    {
        private string server = "http://10.0.0.5:800/WebForm1.aspx";
        private HttpClient httpClient;
        private CancellationTokenSource cts;
        public static string TYPE_DAYSPERMONTH = "DaysPerMonth";
        public static string TYPE_HOURSPERDAY = "HoursPerDay";
        public static string TYPE_HOURSPERMONTH = "HoursPerMonth";

        bool meteracqflag = true;
        static int countMax = 50;
        SynchronizationContext context;
        public BlankPage1()
        {
            context = SynchronizationContext.Current;
            httpClient = new HttpClient();
            cts = new CancellationTokenSource();
            this.InitializeComponent();

        }

        /// <summary>
        /// Invoked when this page is about to be displayed in a Frame.
        /// </summary>
        /// <param name="e">Event data that describes how this page was reached.
        /// This parameter is typically used to configure the page.</param>
        ///   public async void ThreadProcAcqSmartMeterData()
        public async void ThreadProcAcqSmartMeterData()
        {
            byte[] bholdregs1 = null;

            TCPSGInterface meter1 = new TCPSGInterface("192.168.1.106", context);
            TCPAmmeterData meterData1 = new TCPAmmeterData(meter1);
            List<LoadIdentification.LoadData> loadDatalist = new List<LoadIdentification.LoadData>();
            int currentCount = 0;
            while (meteracqflag)
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
                DateTime dt = DateTime.Now;
                loadDatalist.Add(new LoadIdentification.LoadData(activePower1, reactivePower1, dt));
                currentCount++;
                List<String> strlist = null;
                if (currentCount == 10)
                {
                    strlist = LoadIdentification.handleHisLogDBMethodWindow(loadDatalist);
                    currentCount = 0;
                    loadDatalist = new List<LoadIdentification.LoadData>();
                }
                //bholdregs1 = meter1.ReadHoldingRegisters(65, 2, bholdregs1).Result;
                //Array.Reverse(bholdregs1);
                //float Energy1 = BitConverter.ToSingle(bholdregs1, 0);
                context.Post(async (s) =>
                {

                    //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的
                    if (strlist != null)
                    {
                        String tempstr = "";
                        foreach (String str in strlist)
                            tempstr += str;
                        TxtBoxLoadList.Text = tempstr;
                        TxtBoxLoadStatus.Text="Lamp Status:"+LoadIdentification.isLampOn.ToString();
                    }
                    TxtBoxTest.Text = csmartMeterData1.smartMeterData.toLoadIdentificationInfoJson();
                }, null);

                await Task.Delay(500);
            }
        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            //重写回退键事件
            meteracqflag = true;
            Windows.Phone.UI.Input.HardwareButtons.BackPressed += HardwareButtons_BackPressed;
            Task.Factory.StartNew(() =>
            {
                ThreadProcAcqSmartMeterData();
            });

        }
        protected override void OnNavigatedFrom(NavigationEventArgs e)
        {
            base.OnNavigatedFrom(e);
            meteracqflag = false;
        }
        void HardwareButtons_BackPressed(object sender, Windows.Phone.UI.Input.BackPressedEventArgs e)
        {

            e.Handled = true;

            Windows.Phone.UI.Input.HardwareButtons.BackPressed -= HardwareButtons_BackPressed;

            //Frame.GoBack();
            Frame frame = Window.Current.Content as Frame;
            frame.Navigate(typeof(MainPage));
            // Navigate to a page

        }

 

        private void Button_Click_Query24hPerDay(object sender, RoutedEventArgs e)
        {
            HttpRequestAsync(async () =>
            {
                string resourceAddress = server + "?cacheable=1";
                HttpResponseMessage response = await httpClient.GetAsync(new Uri(resourceAddress)).AsTask(cts.Token);
                string responseBody = await response.Content.ReadAsStringAsync().AsTask(cts.Token);
                return responseBody;
            });
        }

        private async void HttpRequestAsync(Func<Task<string>> httpRequestFuncAsync)
        {
            string responseBody;
            //waiting.Visibility = Visibility.Visible;
            try
            {
                responseBody = await httpRequestFuncAsync();
                cts.Token.ThrowIfCancellationRequested();
            }
            catch (TaskCanceledException)
            {
                responseBody = "请求被取消";
            }
            catch (Exception ex)
            {
                responseBody = "异常消息" + ex.Message;
            }
            finally
            {
                //waiting.Visibility = Visibility.Collapsed;
            }
            await Dispatcher.RunAsync(CoreDispatcherPriority.Normal, async () =>
            {
                await new MessageDialog(responseBody).ShowAsync();
            });
        }

        private void Button_Click(object sender, RoutedEventArgs e)
        {
            HttpRequestAsync(async () =>
            {
                string resourceAddress = server + "?y=2015&m=3&d=6&type=HoursPerMonth";
                HttpResponseMessage response = await httpClient.GetAsync(new Uri(resourceAddress)).AsTask(cts.Token);
                string responseBody = await response.Content.ReadAsStringAsync().AsTask(cts.Token);
                return responseBody;
            });
        }

    }
}
