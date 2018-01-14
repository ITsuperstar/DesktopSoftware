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
    public class DALhireinfo
    {
        //查询所有出租信息
        public DataTable selectAllHireinfo()
        {
            string sql = "select * from hireinfo order by id desc";
            return SqlHelper.ExecuteDataTable(sql);
        }

        //通过id,查询出租信息
        public DataTable selectHireinfoById(int id)
        {
            string sql = "select * from hireinfo where id=@id";
            SqlParameter parm = new SqlParameter("@id", id);
            return SqlHelper.ExecuteDataTable(sql, parm);
        }

        //根据条件，在hireinfo表中做相关查询                    
        public int countByVideoid(string videoid)
        {
            string sql = "select count(*) from hireinfo where videoid=@videoid";
            SqlParameter parm = new SqlParameter("@videoid", videoid);
            return (int)SqlHelper.ExecuteScalar(sql, parm);
        }

        public int countByClientid(string clientid)
        {
            string sql = "select count(*) from hireinfo where clientid=@clientid";
            SqlParameter parm = new SqlParameter("@clientid", clientid);
            return (int)SqlHelper.ExecuteScalar(sql, parm);
        }

        //查询该用户是否已经借了某影碟，若借了某影碟还没还(>0)，再次借同一种影碟，则不让借
        public int countIsReturn(string videoid, string clientid)
        {
            string sql = "select count(*) from hireinfo where videoid=@videoid and clientid=@clientid and isreturn=0";
            SqlParameter[] parms =
                {  
                    new SqlParameter("@videoid", videoid),
                    new SqlParameter("@clientid", clientid)   
                };
            return (int)SqlHelper.ExecuteScalar(sql, parms);
        }

        public DataTable queryByContent(int queryType, string content)
        {
            //queryType:    0("按影碟编号");   1("按用户编号");   2("没归还的影碟");
            int qt = queryType;
            if (qt == 0)
            {
                string sql = "select * from hireinfo where videoid=@videoid";
                SqlParameter parm = new SqlParameter("@videoid", content);
                return SqlHelper.ExecuteDataTable(sql, parm);
            }
            else if (qt == 1)
            {
                string sql = "select * from hireinfo where clientid = @clientid";
                SqlParameter parm = new SqlParameter("@clientid", content);
                return SqlHelper.ExecuteDataTable(sql, parm);
            }
            else    //qt==2
            {
                string sql = "select * from hireinfo where isreturn=0";
                return SqlHelper.ExecuteDataTable(sql);
            }
        }

        public DataTable queryByTime(int compareType, DateTime startTime, DateTime endTime)
        {
            //compareType:    0(借出时间);   1(归还时间);   2(应归还时间); 
            int ct = compareType;
            if (ct == 0)
            {
                //SQL传参，传递只能是参数
                string sql = "select * from hireinfo where convert(varchar(10),borrowtime,120) >= convert(varchar(10),@startTime,120)  and  convert(varchar(10),borrowtime,120) <= convert(varchar(10),@endTime,120)";
                SqlParameter[] parms =
                {   new SqlParameter("@startTime", startTime),
                     new SqlParameter("@endTime", endTime)
                };
                return SqlHelper.ExecuteDataTable(sql, parms);
            }

            else if (ct == 1)
            {
                string sql = "select * from hireinfo where convert(varchar(10),returntime,120) >= convert(varchar(10),@startTime,120)  and  convert(varchar(10),returntime,120) <= convert(varchar(10),@endTime,120)";
                SqlParameter[] parms =
                {   new SqlParameter("@startTime", startTime),
                     new SqlParameter("@endTime", endTime)
                };
                return SqlHelper.ExecuteDataTable(sql, parms);
            }
            else   //ct==2
            {
                string sql = "select * from hireinfo where convert(varchar(10),shouldtime,120) >= convert(varchar(10),@startTime,120)  and  convert(varchar(10),shouldtime,120) <= convert(varchar(10),@endTime,120)";
                SqlParameter[] parms =
                {   new SqlParameter("@startTime", startTime),
                     new SqlParameter("@endTime", endTime)
                };
                return SqlHelper.ExecuteDataTable(sql, parms);
            }
        }

        //根据影碟编号，在videoinfo表中，判断影碟是否存在,  >0则存在
        public int isVideoinfo(string videoid)
        {
            string sql = "select count(*) from videoinfo where videoid=@videoid";
            SqlParameter parm = new SqlParameter("@videoid", videoid);
            return (int)SqlHelper.ExecuteScalar(sql, parm);
        }
        //根据影碟编号，在videoinfo表中，查找影碟的数量,  >0才可借
        public int isVideoNumber(string videoid)
        {
            string sql = "select number from videoinfo where videoid=@videoid";
            SqlParameter parm = new SqlParameter("@videoid", videoid);
            return Convert.ToInt32( SqlHelper.ExecuteScalar(sql, parm) );
        }

        //根据影碟编号，在videoinfo表中，查找影碟的单价
        public double returnPrice(string videoid)
        {
            string sql = "select price from videoinfo where videoid=@videoid";
            SqlParameter parm = new SqlParameter("@videoid", videoid);
            return Convert.ToDouble(SqlHelper.ExecuteScalar(sql, parm));   //object类型转换double  ！！！
        }

        //根据用户编号，在clientinfo表中，判断用户是否存在,  >0则存在
        public int isClientinfo(string clientid)
        {
            string sql = "select count(*) from clientinfo where clientid=@clientid";
            SqlParameter parm = new SqlParameter("@clientid", clientid);
            return (int)SqlHelper.ExecuteScalar(sql, parm);
        }

        //根据管理员编号，在admininfo表中，判断管理员是否存在,  >0则存在
        public int isAdmininfo(string adminid)
        {
            string sql = "select count(*) from admininfo where adminid=@adminid";
            SqlParameter parm = new SqlParameter("@adminid", adminid);
            return (int)SqlHelper.ExecuteScalar(sql, parm);
        }

        //出租影碟，返回受影响的行数。出租之前先验证是否可以出租；可以出租，videoinfo表数量减1
        //-3(影碟不存在);  -2(影碟被借完);  -1(用户不存在);  0(出租失败);  1(成功);  -4(未归还)
        public int insertHireinfo(hireinfo h)
        {
            int isvideo=this.isVideoinfo(h.videoid);
            int videonum=this.isVideoNumber(h.videoid);
            int isclient = this.isClientinfo(h.clientid);
            int isreturn = this.countIsReturn(h.videoid, h.clientid);
            if (isvideo <= 0)
                return -3;
            if (videonum <= 0)
                return -2;
            if (isclient <= 0)
                return -1;
            //未归还
            if (isreturn > 0)
                return -4;
            //符合出租条件，可以出租，videoinfo表数量减1
            string sql = "insert into hireinfo(videoid,clientid,isreturn,borrowtime,rent,shouldtime) values(@videoid,@clientid,0,@borrowtime,@rent,@shouldtime)";
            SqlParameter[] parms =
            {
                new SqlParameter("@videoid",h.videoid),
                new SqlParameter("@clientid",h.clientid),
                new SqlParameter("@borrowtime",h.borrowtime),
                new SqlParameter("@rent",h.rent),
                new SqlParameter("@shouldtime",h.shouldtime)
            };
            //videoinfo表中的数量先减1
            SqlHelper.ExecuteNonQuery("update videoinfo set number = (number-1)  where videoid=@videoid", new SqlParameter("@videoid", h.videoid));

            return SqlHelper.ExecuteNonQuery(sql, parms);
        }

        //删除出租信息，返回影响行数。删除之前，首先查看那些关系表中是否还有该出租的相关信息。
        public int deleteHireinfo(hireinfo h)
        {
            string videoid=(string)SqlHelper.ExecuteScalar("select videoid from hireinfo where id=@id", new SqlParameter("@id", h.id));
            string clientid = (string)SqlHelper.ExecuteScalar("select clientid from hireinfo where id=@id", new SqlParameter("@id", h.id));
            string adminid = Convert.ToString( SqlHelper.ExecuteScalar("select adminid from hireinfo where id=@id", new SqlParameter("@id", h.id)) ); //object类型转换string
            int total = this.isVideoinfo(videoid) + this.isClientinfo(clientid) + this.isAdmininfo(adminid);
           //若大于0，说明其他表还有引用，不能删除
            if (total > 0)
                return -1;
            else
            {         
                //根据自增id删除即可
                string sql = "delete  from hireinfo where id=@id";
                SqlParameter parm = new SqlParameter("@id", h.id);
                return SqlHelper.ExecuteNonQuery(sql, parm);
            }
        }

        //修改出租信息，返回影响行数
        public int updateHireinfo(hireinfo h)
        {
            string sql = "update hireinfo set videoid=@videoid,clientid=@clientid,isreturn=@isreturn where id=@id";
            SqlParameter[] parms =
            {
                new SqlParameter("@id",h.id),
                new SqlParameter("@videoid",h.videoid),
                new SqlParameter("@clientid",h.clientid),
                new SqlParameter("@isreturn",h.isreturn)
            };
            return SqlHelper.ExecuteNonQuery(sql, parms);
        }

        //根据影碟编号和读者编号，归还影碟，返回受影响的行数。
        //若存在(isreturn=0)，hireinfo中的returntime和isreturn，videoinfo中的数量number（有多个结果时，按id排序，修改最早借的）
       //若不存在，提示错误，返回-1
        public int returnVideo(hireinfo h)
        {
            int hirenum = (int)SqlHelper.ExecuteScalar("select count(*) from hireinfo where videoid=@videoid and clientid=@clientid and isreturn=0",
                             new SqlParameter("@videoid", h.videoid), new SqlParameter("@clientid", h.clientid));
            if (hirenum <= 0)
                return -1;
            else if (hirenum == 1)   //只有一个结果符合
            {
                string sql = "update hireinfo set returntime=@returntime,isreturn=1 where videoid=@videoid and clientid=@clientid and isreturn=0";
                SqlParameter[] parms =
            {
                new SqlParameter("@videoid",h.videoid),
                new SqlParameter("@clientid",h.clientid),
                new SqlParameter("@returntime",h.returntime)
            };
                //videoinfo表中的数量加1
                SqlHelper.ExecuteNonQuery("update videoinfo set number = (number+1)  where videoid=@videoid", new SqlParameter("@videoid", h.videoid));
                return SqlHelper.ExecuteNonQuery(sql, parms);
            }
            else   //有多个结果符合，选择最早借出的置为"归还"，即id最小的
            {
                string sql = "update (select *,ROW_NUMBER() over( order by id asc) as num from  hireinfo where videoid=@videoid and clientid=@clientid and isreturn=0) s set returntime=@returntime,isreturn=1 where s.num=1";
                SqlParameter[] parms =
            {
                new SqlParameter("@videoid",h.videoid),
                new SqlParameter("@clientid",h.clientid),
                new SqlParameter("@returntime",h.returntime)
            };
                //videoinfo表中的数量加1
                SqlHelper.ExecuteNonQuery("update videoinfo set number = (number+1)  where videoid=@videoid", new SqlParameter("@videoid", h.videoid));
                return SqlHelper.ExecuteNonQuery(sql, parms);
            }
        }

    }
}