using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SHEMS.Entities
{
    public class ConsumptionStatistics
    {
        private string consumption;

        public string Consumption
        {
            get { return consumption; }
            set { consumption = value; }
        }
        private string h;

        public string H
        {
            get { return h; }
            set { h = value; }
        }

        private string d;

        public string D
        {
            get { return d; }
            set { d = value; }
        }

        public ConsumptionStatistics()
        {

        }
        //public ConsumptionStatistics(string consumption,string h)
        //{
        //    this.Consumption = consumption;
        //    this.H = h;
        //}
    }
}
