using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MODEL
{
    public class MODELuser
    {
        //根据数据库表，手动编写model层
        private string _username = string.Empty;  //设置用户名称为空值
        private string _password = string.Empty;  //设置用户密码为空值
        public string username  //设置相对应的属性
        {
            set { _username = value; }
            get { return _username; }
        }
        public string password//设置相对应的属性
        {
            set { _password = value; }
            get { return _password; }
        }

    }

}
