using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Text.RegularExpressions;
using System.Threading.Tasks;

namespace TOOL
{
    public class CheckData
    {
        //检查姓名是否为汉字
        public static bool checkName(string s)
        {
            Regex reg = new Regex(@"^[\u4e00-\u9fa5]{1,30}$");
            return reg.IsMatch(s);
        }

        //检查性别是否符合规范
        public static bool checkSex(string s)
        {
            Regex reg = new Regex(@"^['男'|'女']?$");
            return reg.IsMatch(s);
        }

        //检查身高是否为符合规范
        public static bool checkHeight(string s)
        {
            Regex reg = new Regex(@"^([1|2]?)([0-9])([0-9])$");
            return reg.IsMatch(s);
        }

        //检查生日是否符合规范
        public static bool checkBirthday(string s)
        {
            Regex reg = new Regex(@"^([\d]{4}(((0[13578]|1[02])((0[1-9])|([12][0-9])|(3[01])))|(((0[469])|11)((0[1-9])|([12][1-9])|30))|(02((0[1-9])|(1[0-9])|(2[1-8])))))|((((([02468][048])|([13579][26]))00)|([0-9]{2}(([02468][048])|([13579][26]))))(((0[13578]|1[02])((0[1-9])|([12][0-9])|(3[01])))|(((0[469])|11)((0[1-9])|([12][1-9])|30))|(02((0[1-9])|(1[0-9])|(2[1-9])))))$");
            return reg.IsMatch(s);
        }

        //检查邮箱是否符合规范
        public static bool checkEmail(string s)
        {
            Regex reg = new Regex(@"^\w[-\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\.)+[A-Za-z]{2,14}$");
            return reg.IsMatch(s);
        }

        //检查手机号码是否符合规范
        public static bool checkTelphone(string s)
        {
            Regex reg = new Regex(@"^((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)");
            return reg.IsMatch(s);
        }

        //正则表达式匹配bug，整数之后有字母也会匹配成功，不能正确匹配后面的内容！！！
        //检查数字是否为正整数
        public static bool checkPositiveInt(string s)
        {
            Regex reg = new Regex(@"^[1-9]\d*$");
            return reg.IsMatch(s);
        }

        //检查数字是否为非负整数
        public static bool checkNoNegativeInt(string s)
        {
            Regex reg = new Regex(@"^([1-9]\d*)|0$");
            return reg.IsMatch(s);
        }

        //检查数字是否为非负浮点数
        public static bool checkNoNegativeFloat(string s)
        {
            Regex reg = new Regex(@"^[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$");
            return reg.IsMatch(s);
        }

        //检查数字是否为非负数
        public static bool checkNoNegative(string s)
        {
            Regex reg = new Regex(@"^[1-9]\d*|[1-9]\d*\.\d*|0\.\d*[1-9]\d*|0?\.0+|0$");
            return reg.IsMatch(s);
        }
    }
}
