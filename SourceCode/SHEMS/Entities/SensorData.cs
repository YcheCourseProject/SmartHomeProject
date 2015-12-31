using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace SHEMS.Entities
{
    public class SensorData
    {
        public static int HUMAN_STATUS_INHOME=1;
        public static int HUMAN_STATUS_OUTHOME = 0;
        public SensorData()
        {

        }

       
        private string dateTime;

        public string DateTime
        {
            get { return dateTime; }
            set { dateTime = value; }
        }
        private string temperatureData;

        public string TemperatureData
        {
            get { return temperatureData; }
            set { temperatureData = value; }
        }
        private string humidityData;

        public string HumidityData
        {
            get { return humidityData; }
            set { humidityData = value; }
        }
        private string gasData;

        public string GasData
        {
            get { return gasData; }
            set { gasData = value; }
        }

        private string lightData;

        public string LightData
        {
            get { return lightData; }
            set { lightData = value; }
        }

        private string humanStatus;

        public string HumanStatus
        {
            get { return humanStatus; }
            set { humanStatus = value; }
        }

        private string fireData;

        public string FireData
        {
            get { return fireData; }
            set { fireData = value; }
        }
    }
}
