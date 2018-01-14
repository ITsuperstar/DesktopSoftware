using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MODEL;
using TOOL;

namespace DAL
{
    public class DALlogin
    {
        public int dalCheckLogin(adminlogin ad)
        {
            int num = 0;
            try
            {
                num = (int)SqlHelper.ExecuteScalar("select count(*) from adminlogin where adminid=@adminid and password=@password",
                    new SqlParameter("@adminid", ad.adminid),
                    new SqlParameter("@password", ad.password)
                    );
            }
            catch (Exception e) { }
            return num;

        }
    }
}
