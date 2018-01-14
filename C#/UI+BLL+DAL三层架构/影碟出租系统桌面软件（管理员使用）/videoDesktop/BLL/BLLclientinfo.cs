using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using DAL;
using MODEL;
using System.Data;

namespace BLL
{
    public class BLLclientinfo
    {
        private DALclientinfo dc = new DALclientinfo();

        //获取所有用户信息
        public DataTable getAllClientinfo()
        {
            return dc.selectAllClientinfo();
        }

        //添加用户信息，是否成功
        public int addClientinfo(clientinfo c)
        {
            return dc.insertClientinfo(c);  //返回值大于0，成功；等于0，保存失败
        }

        //删除用户信息，是否成功
        public int delClientinfo(clientinfo c)
        {
            return dc.deleteClientinfo(c);  //返回值大于0，成功；等于0，保存失败；等于-1，不能删除
        }

        //修改用户信息，是否成功
        public int upClientinfo(clientinfo c)
        {
            return dc.updateClientinfo(c);   //返回值大于0，成功；等于0，保存失败
        }

        //通过clientid,查询用户信息
        public DataTable getClientinfoById(string clientid)
        {
            return dc.selectClientinfoById(clientid);
        }

        //根据条件，做相关查询                    
        public int countById(string clientid)
        {
            return dc.countById(clientid);
        }
        public DataTable queryByContent(int queryType, string content)
        {
            return dc.queryByContent(queryType, content);
        }

    }
}