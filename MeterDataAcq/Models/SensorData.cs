using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MeterDataAcq.Models
{
    class SensorData
    {
        public static int HUMAN_STATUS_INHOME=1;
        public static int HUMAN_STATUS_OUTHOME = 0;
        public SensorData()
        {

        }

        public SensorData(string dateTime, double temperatureData, double humidityData, 
            double lightData,double gasData,double fireData,int humanStatus)
        {
            this.dateTime = dateTime;
            this.temperatureData = temperatureData;
            this.humidityData = humidityData;
            this.lightData = lightData;
            this.gasData = gasData;
            this.fireData = fireData;
            this.humanStatus = humanStatus;
        }


        private string dateTime;

        public string DateTime
        {
            get { return dateTime; }
            set { dateTime = value; }
        }
        private double temperatureData;

        public double TemperatureData
        {
            get { return temperatureData; }
            set { temperatureData = value; }
        }
        private double humidityData;

        public double HumidityData
        {
            get { return humidityData; }
            set { humidityData = value; }
        }
        private double gasData;

        public double GasData
        {
            get { return gasData; }
            set { gasData = value; }
        }
        
        private double lightData;

        public double LightData
        {
            get { return lightData; }
            set { lightData = value; }
        }

        private int humanStatus;

        public int HumanStatus
        {
            get { return humanStatus; }
            set { humanStatus = value; }
        }

        private double fireData;

        public double FireData
        {
            get { return fireData; }
            set { fireData = value; }
        }
    }
}
