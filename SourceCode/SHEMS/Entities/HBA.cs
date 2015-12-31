using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
//bind
using System.ComponentModel;
namespace SHEMS.Entities
{
    class HBA : INotifyPropertyChanged
    {
        public static string IN_HOME="IN";
        public static string OUT_HOME="OUT";
         
        private string isInHome;

        public string IsInHome
        {
            get { return isInHome; }
            set { isInHome = value;
            OnPropertyChanged("IsInHome");
            OnPropertyChanged("InOutInt");
            OnPropertyChanged("getBGColor");
            }
        }
        private string number;

        public string Number
        {
            get { return number; }
            set { number = value; }
        }

        public int InOutInt
        {
            get
            {
                if (isInHome.Equals(IN_HOME))
                    return 1;
                else
                    return 0;
            }
        }
       
        public string getBGColor
        {
            get
            {
                if (isInHome.Equals(OUT_HOME))
                    return Constants.BLUE_DEFINED;
                else
                    return Constants.YELLOW_DEFINED;
            }
        }
        public void toggleInOut()
        {
            if (isInHome.Equals(IN_HOME))
                isInHome = OUT_HOME;
            else if (isInHome.Equals(OUT_HOME))
                isInHome = IN_HOME;
            OnPropertyChanged("IsInHome");
            OnPropertyChanged("InOutInt");
            OnPropertyChanged("getBGColor");
        }

        public HBA()
        {

        }
        public event PropertyChangedEventHandler PropertyChanged;
        protected void OnPropertyChanged(string name)
        {
            PropertyChangedEventHandler handler = PropertyChanged;
            if (handler != null)
            {
                handler(this, new PropertyChangedEventArgs(name));
            }
        }
    }
}
