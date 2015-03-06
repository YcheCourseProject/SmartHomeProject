using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MeterDataAcq.Models
{
    class UserfulMeterData
    {
        public UserfulMeterData(float activePower,float reactivePower,double activeEnergy,DateTime dateTime)
        {
            this.activePower = activePower;
            this.reactivePower = reactivePower;
            this.activeEnergy = activeEnergy;
            this.dateTime = dateTime.ToString("yyyy/MM/dd HH:mm:ss:fff");
        }
        private float activePower;

        public float ActivePower
        {
            get { return activePower; }
            set { activePower = value; }
        }
        private float reactivePower;

        public float ReactivePower
        {
            get { return reactivePower; }
            set { reactivePower = value; }
        }

  
        private double activeEnergy;

        public double ActiveEnergy
        {
            get { return activeEnergy; }
            set { activeEnergy = value; }
        }
        private string dateTime;

        public string DateTime
        {
            get { return dateTime; }
            set { dateTime = value; }
        }

    }
}
