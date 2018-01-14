using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace TOOL
{
    public class TypeConversion
    {
        //string类型转换为int?类型，当string类型的数据为空或不能转换成int类型时，返回0
        public static int stringTOint(string s)
        {
            int temp;   
             //int.TryParse (String s,out int num.)与 int.Parse(string s)又较为类似，但它不会产生异常，
            //最后一个参数为输出值,如果转换失败，输出值为 0，如果转换成功，输出值为转换后的int值。
            int.TryParse(s, out  temp);
            return temp;
        }

        //string类型转换为double?类型，当string类型的数据为空或不能转换成double类型时，返回0
        public static double stringTOdouble(string s)
        {
            double temp;
            double.TryParse(s, out  temp);
            return temp;
        }

        //string类型转换为DateTime?类型，当string类型的数据为空或不能转换成DateTime类型时，返回null空的DateTime类型
        public static DateTime stringTOdatetime(string s)
        {
            DateTime temp;
            DateTime.TryParse(s, out  temp);
            return temp;
        }

    }
}
