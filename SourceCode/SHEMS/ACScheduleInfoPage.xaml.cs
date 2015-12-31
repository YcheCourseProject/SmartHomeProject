using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices.WindowsRuntime;
using Windows.Foundation;
using Windows.Foundation.Collections;
using Windows.UI.Xaml;
using Windows.UI.Xaml.Controls;
using Windows.UI.Xaml.Controls.Primitives;
using Windows.UI.Xaml.Data;
using Windows.UI.Xaml.Input;
using Windows.UI.Xaml.Media;
using Windows.UI.Xaml.Navigation;
using Windows.UI.Popups;
//thread
using System.Threading;
using System.Threading.Tasks;
//绑定UI和数据的
using System.ComponentModel;
using System.Collections.ObjectModel;
//models
using SHEMS.Entities;
// The Blank Page item template is documented at http://go.microsoft.com/fwlink/?LinkID=390556
using SHEMS.Codes;

namespace SHEMS
{
    /// <summary>
    /// An empty page that can be used on its own or navigated to within a Frame.
    /// </summary>
    public sealed partial class ACScheduleInfoPage : Page
    {
        public ACScheduleInfoPage()
        {
            this.InitializeComponent();
        }

        /// <summary>
        /// Invoked when this page is about to be displayed in a Frame.
        /// </summary>
        /// <param name="e">Event data that describes how this page was reached.
        /// This parameter is typically used to configure the page.</param>
        protected override void OnNavigatedTo(NavigationEventArgs e)
        {
            Text_SetTmp.Text = "21℃";
            //chart 绑定实时电价
            ObservableCollection<ElectricPrice> collecion = new ObservableCollection<ElectricPrice>();
            double[] prices = Constants.E_PRICES;
            int index = 0;
            foreach(double price in prices)
            {
                ElectricPrice tempEPriceObj = new ElectricPrice();
                tempEPriceObj.Number = index;
                index++;
                tempEPriceObj.Price = price;
                collecion.Add(tempEPriceObj);
            }
            chartEprice.DataSource = collecion;

            ObservableCollection<HBA> collecion2 = new ObservableCollection<HBA>();
            for(int i=0;i<24;i++)
            {
                HBA tempHBA = new HBA();
                int temphba = ACScheduleAlgorithm.HumanBehavior[i];
                if (temphba == 1)
                    tempHBA.IsInHome = HBA.IN_HOME;
                else
                    tempHBA.IsInHome = HBA.OUT_HOME;
                collecion2.Add(tempHBA);
            }
            listHBAs.ItemsSource = collecion2;
            //foreach (var item in array)
            //{
            //    if (isFirstIn)
            //    {
            //        if (item.D != null)
            //            chart1.CategoryValueMemberPath = "D";
            //        else
            //            chart1.CategoryValueMemberPath = "H";
            //        isFirstIn = false;
            //    }
            //    collecion.Add(item);
            //}

            //chart1.DataSource = collecion;
        }
    
        private void Button_Click_ChangeComfort(object sender, RoutedEventArgs e)
        {
            int comfortT = Int32.Parse(TxtBox_ComfortTemperature.Text);
            Text_SetTmp.Text = comfortT + "℃";
        }

        private void ItemClickChangeStatus(object sender, ItemClickEventArgs e)
        {
            HBA hba = e.ClickedItem as HBA;
           
            hba.toggleInOut();
            //TxtBox_TEST.Text = "当前状态为：" + hba.InOutInt + ";" ;
            
        }

        //private void gridView_SelectionChanged(object sender, SelectionChangedEventArgs e)
        //{
        //    //TxtBox_TEST.Text = "被单击的 employee 的 name 为：" + (e.AddedItems[0] as HBA).IsInHome;

        //}
        
        private void Button_Click_Back2LoadInfo(object sender, RoutedEventArgs e)
        {
            Frame frame = Window.Current.Content as Frame;
            frame.Navigate(typeof(LoadAnalysisPage));
        }

        private void Button_Click_StartACOpt(object sender, RoutedEventArgs e)
        {
            int setT=Int32.Parse(Text_SetTmp.Text.TrimEnd(new char[]{'℃'}));
            int [] hba = new int[24];           //在家与否序列，在家为1，不在为0
            ObservableCollection<HBA> collecion = (ObservableCollection<HBA>)listHBAs.ItemsSource;
            int index=0;
            string tempstr = setT+"";
            foreach(var hbaitem in collecion)
            {
                hba[index] = hbaitem.InOutInt;
                index++;
                //tempstr += hba[index-1] + ";";
            }
            //TxtBox_TEST.Text = tempstr;
           
            //base policy算法
            ACScheduleAlgorithm.HumanBehavior = hba;
            for (int i = 0; i < 24;i++ )
            {
                ACScheduleAlgorithm.T_need[i] = setT;
            }
                ACScheduleAlgorithm.run();
            double[] optimalPolicy = ACScheduleAlgorithm.BasePolicy;
            ACScheduleAlgorithm.runwithoutscheduling();
            double[] formerPolicy = ACScheduleAlgorithm.formerPolicy;
            for (int i = 0; i < optimalPolicy.Length; i++)
            {
                optimalPolicy[i] =Double.Parse( Convert.ToDouble(optimalPolicy[i]).ToString("0.00")); 
                formerPolicy[i] =Double.Parse( Convert.ToDouble(formerPolicy[i]).ToString("0.00")); 
            }		
            double costwithoutscheuling=ACScheduleAlgorithm.costwithoutscheuling;
            double costwithscheuling=ACScheduleAlgorithm.costwithscheuling;
            costwithoutscheuling = Double.Parse(Convert.ToDouble(costwithoutscheuling).ToString("0.00"));
            costwithscheuling = Double.Parse(Convert.ToDouble(costwithscheuling).ToString("0.00")); 
            
            //推送策略
            string tempstr2="W/O:"+costwithoutscheuling+";"+"\tWith:"+costwithscheuling+"\n";
            string policy = "Policy Comparison:\n";
            for(int i=0;i<24;i++)
            {
                policy += i + "" +"~"+ (i + 1) + " ";
                policy += "optE:" + optimalPolicy[i] + "KWH;" + "formerE:" + formerPolicy[i] + "KWH\n";

            }
            MessageDialog messageDialog = new MessageDialog(tempstr2+policy);
            messageDialog.ShowAsync();
     
        }
    }
}
