using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MODEL;
using TOOL;
using System.Data;
using System.Data.SqlClient;

namespace DAL
{
    public class DALclientinfo
    {
        //查询所有用户信息
        public DataTable selectAllClientinfo()
        {
            string sql = "select * from clientinfo order by id desc";
            return SqlHelper.ExecuteDataTable(sql);
        }

        //添加用户信息，返回受影响的行数
        public int insertClientinfo(clientinfo c)
        {     
            string sql = "insert into clientinfo(clientid,clientname,telphone,email) values(@clientid,@clientname,@telphone,@email)";
            SqlParameter[] parms =
            {
                new SqlParameter("@clientid",c.clientid),
                new SqlParameter("@clientname",c.clientname),
                new SqlParameter("@telphone",c.telphone),
                new SqlParameter("@email",c.email)
            };
            return SqlHelper.ExecuteNonQuery(sql, parms);
        }

        //删除用户信息，返回影响行数。删除之前，首先查看那些关系表中是否还有该用户的相关信息。
        public int deleteClientinfo(clientinfo c)
        {
            int cl = (int)SqlHelper.ExecuteScalar("select count(*) from clientlogin where clientid=@clientid", new SqlParameter("@clientid", c.clientid));
            int hire = (int)SqlHelper.ExecuteScalar("select count(*) from hireinfo where clientid=@clientid", new SqlParameter("@clientid", c.clientid));
            int total = cl + hire;   //若大于0，说明其他表还有引用，不能删除
            if (total > 0)
                return -1;
            else
            {         
                string sql = "delete  from clientinfo where clientid=@clientid";
                SqlParameter parm = new SqlParameter("@clientid", c.clientid);
                return SqlHelper.ExecuteNonQuery(sql, parm);
            }
        }

        //修改用户信息，返回影响行数
        public int updateClientinfo(clientinfo c)
        {              
            string sql = "update clientinfo set clientname=@clientname,telphone=@telphone,email=@email where clientid=@clientid";
            SqlParameter[] parms =
            {
                new SqlParameter("@clientid",c.clientid),
                new SqlParameter("@clientname",c.clientname),
                new SqlParameter("@telphone",c.telphone),
                new SqlParameter("@email",c.email)
            };
            return SqlHelper.ExecuteNonQuery(sql, parms);
        }

        //通过clientid,查询用户信息
        public DataTable selectClientinfoById(string clientid)
        {
            string sql = "select * from clientinfo where clientid=@clientid";
            SqlParameter parm = new SqlParameter("@clientid", clientid);
            return SqlHelper.ExecuteDataTable(sql, parm);
        }

        //根据条件，做相关查询                    
        public int countById(string clientid)
        {
            string sql = "select count(*) from clientinfo where clientid=@clientid";
            SqlParameter parm = new SqlParameter("@clientid", clientid);
            return (int)SqlHelper.ExecuteScalar(sql, parm);
        }

        public DataTable queryByContent(int queryType , string content)
        {
            //queryType:    0("按用户编号");   1("按用户名");   2("按电话");   3("按邮箱")
            int qt = queryType;
            if (qt == 0)
            {
                string sql = "select * from clientinfo where clientid=@clientid";
                SqlParameter parm = new SqlParameter("@clientid", content);
                return SqlHelper.ExecuteDataTable(sql, parm);
            }
            else if (qt == 1)
            {
                string sql = "select * from clientinfo where clientname like @clientname";
                SqlParameter parm = new SqlParameter("@clientname", "%" + content + "%");
                return SqlHelper.ExecuteDataTable(sql, parm);
            }
            else if (qt == 2)
            {
                    string sql = "select * from clientinfo where telphone like @telphone";
                    SqlParameter parm = new SqlParameter("@telphone", "%" + content + "%");
                    return SqlHelper.ExecuteDataTable(sql, parm);      
            }
            else   //qt==3
            {
                string sql = "select * from clientinfo where email  like @email";
                SqlParameter parm = new SqlParameter("@email", "%" + content + "%");
                    return SqlHelper.ExecuteDataTable(sql, parm);         
            }
        }

    }
}
