using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace SHEMS.Entities
{
    class ElectricPrice
    {
        private double price;

        public double Price
        {
            get { return price; }
            set { price = value; }
        }
        private int number;         //编号从0到23

        public int Number
        {
            get { return number; }
            set { number = value; }
        }

        public ElectricPrice()
        {

        }

    }
}
