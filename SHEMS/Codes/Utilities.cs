using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
//json解析
using System.Runtime.Serialization.Json;
using System.IO;
using Windows.Data.Json;

namespace SHEMS.Codes
{
    class Utilities
    {
        public static string toJsonData(object item)
        {
            DataContractJsonSerializer serializer = new DataContractJsonSerializer(item.GetType());
            string result = String.Empty;
            using (MemoryStream ms = new MemoryStream())
            {
                serializer.WriteObject(ms, item);
                ms.Position = 0;
                using (StreamReader reader = new StreamReader(ms))
                {
                    result = reader.ReadToEnd();
                }
            }
            return result;
        }
        public static T DataContractJsonDeSerializer<T>(string jsonString)
        {
           
            var ds=new DataContractJsonSerializer(typeof(T));
             
            
            var ms = new MemoryStream(Encoding.UTF8.GetBytes(jsonString));
            T obj = (T)ds.ReadObject(ms);
            ms.Dispose();
            return obj;
        }
     
    }
}
