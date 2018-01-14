using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DAL;
using MODEL;

namespace BLL
{
    public class BLLlogin
    {
        private DALlogin dl = new DALlogin();
        public  int bllCheckLogin(adminlogin ad)
        {
            return (dl.dalCheckLogin(ad));
        } 
    }
}
