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
//
using Windows.UI.Core;
//Http
using Windows.Web.Http;
using Windows.Web.Http.Filters;
using Windows.Security.Cryptography;
using Windows.Storage.Streams;
//集合类
using System.Collections.ObjectModel;


// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkID=390556

namespace SHEMS
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class BlankPage1 : Page, INotifyPropertyChanged
    {
        private string server = "http://10.0.0.3:800/WebForm1.aspx";
        private HttpClient httpClient;
        private CancellationTokenSource cts;
        public static string TYPE_DAYSPERMONTH = "DaysPerMonth";
        public static string TYPE_HOURSPERDAY = "HoursPerDay";
        public static string TYPE_HOURSPERMONTH = "HoursPerMonth";
        ObservableCollection<LoadIDResult> currentLoadList = new ObservableCollection<LoadIDResult>();
        List<LoadIDResult> loadIDResultList = new List<LoadIDResult>();
        bool meteracqflag = true;
        static int countMax = 50;
        SynchronizationContext context;
        public BlankPage1()
        {
            context = SynchronizationContext.Current;
            httpClient = new HttpClient();
            cts = new CancellationTokenSource();
            this.InitializeComponent();
            cvs1.Source = currentLoadList;
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
            List<LoadData> loadDatalist = new List<LoadData>();

            List<LoadIDResult> loadIDResultListForDisBug = null;
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
                loadDatalist.Add(new LoadData(activePower1, reactivePower1, dt));
                currentCount++;
                LoadIDResult itemtest = new LoadIDResult();
                if (currentCount == 10)
                {
                    loadIDResultList = LoadIdentification.handleHisLogDBMethodWindow(loadDatalist);
                    //string ApplianceNameStr = LoadIdentification.NAME_LAMP;
                    //     item = new LoadIDResult {
                    //    Name = ApplianceNameStr,
                    //    Imgpath = LoadIdentification.mapName2ImgPath(ApplianceNameStr),
                    //    Datetime = DateTime.Now,
                    //    Event_type = EVENT_TYPE.ON
                    //};
                    //loadIDResultList.Add(item);

                    currentCount = 0;

                    loadDatalist = new List<LoadData>();
                }

                context.Post((s) =>
                {
                    //可以在此访问UI线程中的对象，因为代理本身是在UI线程的上下文中执行的
                    TxtAP.Text = String.Format("{0:F}", activePower1) + "W";
                    TxtRP.Text = String.Format("{0:F}", reactivePower1) + "Var";
                    if (currentCount == 0)
                    {
                        if (loadIDResultList.Count > 0)
                        {
                            foreach (LoadIDResult item in loadIDResultList)
                            {

                                if (currentLoadList.Count > 0)
                                {
                                    try
                                    { 
                                    foreach (LoadIDResult curitem in currentLoadList)
                                    {
                                        if (curitem.Name.Equals(item.Name))
                                        {
                                            if (item.Event_type != curitem.Event_type)
                                            {
                                                currentLoadList.Remove(curitem);
                                            }
                                        }
                                    }
                                     }
                                    catch
                                    {

                                    }
                                }

                                if (item.Event_type == EVENT_TYPE.ON)
                                {

                                    currentLoadList.Add(item);
                                }
                            }

                        }
                    }
                }
            , null);

                await Task.Delay(500);
            }
        }

        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            //重写回退键事件
            meteracqflag = true;
            //Windows.Phone.UI.Input.HardwareButtons.BackPressed += HardwareButtons_BackPressed;
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
        //void HardwareButtons_BackPressed(object sender, Windows.Phone.UI.Input.BackPressedEventArgs e)
        //{

        //    e.Handled = true;

        //    Windows.Phone.UI.Input.HardwareButtons.BackPressed -= HardwareButtons_BackPressed;

        //    //Frame.GoBack();
        //    Frame frame = Window.Current.Content as Frame;
        //    frame.Navigate(typeof(MainPage));
        //    // Navigate to a page

        //}

        private void Button_Click_BacktoCtrl(object sender, RoutedEventArgs e)
        {
            Frame frame = Window.Current.Content as Frame;
            frame.Navigate(typeof(MainPage));
        }

        private void Button_Click_HoursPerDay(object sender, RoutedEventArgs e)
        {
            HttpRequestAsync(async () =>
            {
                int year = DatePicker.Date.Year;
                int month = DatePicker.Date.Month;
                int day = DatePicker.Date.Day;

                string resourceAddress = server + "?y=" + year + "&m=" + month + "&d=" + day + "&type=HoursPerDay";
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
            await Dispatcher.RunAsync(CoreDispatcherPriority.Normal, () =>
            {
                //await new MessageDialog(responseBody).ShowAsync();
                List<ConsumptionStatistics> array = Utilities.DataContractJsonDeSerializer<List<ConsumptionStatistics>>(responseBody);
                ObservableCollection<ConsumptionStatistics> collecion = new ObservableCollection<ConsumptionStatistics>();

                if (array.Count > 0)
                {
                    bool isFirstIn = true;
                    foreach (var item in array)
                    {
                        if (isFirstIn)
                        {
                            if (item.D != null)
                                chart1.CategoryValueMemberPath = "D";
                            else
                                chart1.CategoryValueMemberPath = "H";
                            isFirstIn = false;
                        }
                        collecion.Add(item);
                    }

                    chart1.DataSource = collecion;

                }


            });
        }

        private void Button_Click_DaysPerMonth(object sender, RoutedEventArgs e)
        {
            HttpRequestAsync(async () =>
            {
                int year = DatePicker.Date.Year;
                int month = DatePicker.Date.Month;
                string resourceAddress = server + "?y=" + year + "&m=" + month + "&type=DaysPerMonth";
                HttpResponseMessage response = await httpClient.GetAsync(new Uri(resourceAddress)).AsTask(cts.Token);
                string responseBody = await response.Content.ReadAsStringAsync().AsTask(cts.Token);
                return responseBody;
            });
        }

        private void Button_Click_HoursPerMonth(object sender, RoutedEventArgs e)
        {
            HttpRequestAsync(async () =>
            {
                int year = DatePicker.Date.Year;
                int month = DatePicker.Date.Month;
                string resourceAddress = server + "?y=" + year + "&m=" + month + "&type=HoursPerMonth";

                HttpResponseMessage response = await httpClient.GetAsync(new Uri(resourceAddress)).AsTask(cts.Token);
                string responseBody = await response.Content.ReadAsStringAsync().AsTask(cts.Token);
                return responseBody;
            });
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
        //private void bindDataForChart()
        //{
        //    ObservableCollection<ChartData> collecion = new ObservableCollection<ChartData>();

        //    IEnumerable<string> enumerable2 = (from c in allRecords select c.Category).Distinct<string>();
        //    foreach (var item in enumerable2)
        //    {
        //        IEnumerable<double> enumerable3 = from c in allRecords.Where<Voucher>(c => c.Category == item) select c.Money;
        //        ChartData data = new ChartData
        //        {
        //            Sum = enumerable3.Sum(),
        //            TypeName = item
        //        };
        //        collecion.Add(data);
        //    }
        //    pie1.DataSource = collecion;
        //    chart1.DataSource = collecion;
        //}
    }
}
