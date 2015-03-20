using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SHEMS
{
    class Constants
    {
        public static  string LOAD_ANALYSIS_SERVER_ADDR = "http://10.0.0.10:800/WebFormLoadService.aspx";
        public static  string AC_CTRL_SERVER_IP = "10.0.0.151";
        public static  string AC_CTRL_SERVER_PORT = "60000";
        public static  string TMP_HUM_PUSH_SERVER_IP = "10.0.0.152";
        public static  string TMP_HUM_PUSH_SERVER_PORT = "8000";
        public static  string METER_SERVER_IP = "192.168.1.106";
        public static  string BLUE_DEFINED = "#DBEEF3";
        public static  string YELLOW_DEFINED = "#FFFF80";
        public static  double[] E_PRICES = 
        {
            0.391,0.391,0.391,0.391,0.391,0.391,0.391,1.194,1.194,1.194,1.194,0.781,
            0.781,0.781,0.781,0.781,0.781,0.781,0.781,1.194,1.194,1.194,1.194,0.391
	                       
        };
    }
}
