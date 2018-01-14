using System;
using System.Collections.Generic;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using MODEL;
using TOOL;
using System.IO;

namespace DAL
{
    public class DALvideoinfo
    {
        //查询所有影碟信息
        public DataTable selectAllVideoinfo() 
        {
            string sql = "select * from videoinfo order by id desc";
            return SqlHelper.ExecuteDataTable(sql);
        }

        //添加影碟信息，返回受影响的行数
        public int insertVideoinfo(videoinfo v) 
        {
            //对图片的路径需要特殊处理
            string imagePath = v.imageurl;
            //由于数据库中图片的存储格式 为 "/images/003.jpg" （web的调用） ,书写方式和本地系统有点差距，但是实质一样。
            string defaultPath = @"F:\VS2012 工作目录\Web应用\自做项目\video\video/images";
            string defaultPath1 = @"F:\VS2012 工作目录\Web应用\自做项目\video\video\images";
            //需要录入的图片就在默认路径中，只需把路径存储在数据库中，给web调用
            if (Path.GetDirectoryName(imagePath) == defaultPath || Path.GetDirectoryName(imagePath) == defaultPath1)
            {
                v.imageurl = "/images/" + Path.GetFileName(imagePath);
            }
            //需要录入的图片不在默认路径中，需要自己复制
            else
            {
                //首先判断该文件是否存在，若存在，返回-1
                string newImageFullName = @"F:\VS2012 工作目录\Web应用\自做项目\video\video\images\" + Path.GetFileName(imagePath);
                if (IOHelper.Exists(newImageFullName))
                {
                    return -1;
                }
                else
                {
                    IOHelper.CopyFileToDirectory(imagePath, defaultPath1);
                    v.imageurl = "/images/" + Path.GetFileName(imagePath);
                }
            }

            string sql = "insert into videoinfo(videoid,videoname,number,price,buydate,describe,imageurl,clickrate) values(@videoid,@videoname,@number,@price,@buydate,@describe,@imageurl,@clickrate)";
            SqlParameter[] parms =
            {
                new SqlParameter("@videoid",v.videoid),
                new SqlParameter("@videoname",v.videoname),
                new SqlParameter("@number",v.number),
                new SqlParameter("@price",v.price),
                new SqlParameter("@buydate",v.buydate),
                new SqlParameter("@describe",v.describe),
                new SqlParameter("@imageurl",v.imageurl),
                new SqlParameter("@clickrate",v.clickrate)
            };
            return SqlHelper.ExecuteNonQuery(sql, parms);
        }

        //删除影碟信息，返回影响行数。删除之前，首先查看那些关系表中是否还有该影碟的相关信息。
        public int deleteVideoinfo(videoinfo v)
        {
            int recom = (int)SqlHelper.ExecuteScalar("select count(*) from recommend where videoid=@videoid", new SqlParameter("@videoid", v.videoid));
            int rev = (int)SqlHelper.ExecuteScalar("select count(*) from review where videoid=@videoid", new SqlParameter("@videoid", v.videoid));
            int hire = (int)SqlHelper.ExecuteScalar("select count(*) from hireinfo where videoid=@videoid", new SqlParameter("@videoid", v.videoid));
            int total = recom + rev + hire;   //若大于0，说明其他表还有引用，不能删除
            if (total > 0)
                return -1;
            else 
            {
                //先把文件夹中的图片删除，再删除数据库中的数据
                string delimage=(string)SqlHelper.ExecuteScalar("select imageurl  from videoinfo where videoid=@videoid", new SqlParameter("@videoid", v.videoid));
                string delimagepath = @"F:\VS2012 工作目录\Web应用\自做项目\video\video" + delimage;
                //如果图片存在，则删除；图片不存在，不用处理
                if (IOHelper.Exists(delimagepath))
                    IOHelper.DeleteFile(delimagepath);
                else { }

                string sql = "delete  from videoinfo where videoid=@videoid";
                SqlParameter parm = new SqlParameter("@videoid", v.videoid);
                return SqlHelper.ExecuteNonQuery(sql, parm);
            }
        }

        //修改影碟信息，返回影响行数
        public int updateVideoinfo(videoinfo v)
        {
            //对图片的路径需要特殊处理
            string imagePath = v.imageurl;
            //由于数据库中图片的存储格式 为 "/images/003.jpg" （web的调用） ,书写方式和本地系统有点差距，但是实质一样。
            string defaultPath = @"F:\VS2012 工作目录\Web应用\自做项目\video\video/images";
            string defaultPath1 = @"F:\VS2012 工作目录\Web应用\自做项目\video\video\images";
            //需要录入的图片就在默认路径中，只需把路径存储在数据库中，给web调用
            if (Path.GetDirectoryName(imagePath) == defaultPath || Path.GetDirectoryName(imagePath) == defaultPath1)
            {
                v.imageurl = "/images/" + Path.GetFileName(imagePath);
            }
            //需要录入的图片不在默认路径中，需要自己复制
            else 
            {
                //把当前时间转换成string型，加上传递来的文件的后缀，充当新的文件名
                //string newImageName=DateTime.Now.ToString("yyyyMMddHHmmss")+Path.GetExtension(imagePath);
                //string newImageFullName = @"F:\VS2012 工作目录\Web应用\自做项目\video\video\images\" + newImageName;
                //IOHelper.CreateFile(newImageFullName);
                //string imageContent = IOHelper.ReadFile(imagePath);
                //IOHelper.WriteFile(newImageFullName,imageContent);
                //首先判断该文件是否存在，若存在，返回-1
                string newImageFullName = @"F:\VS2012 工作目录\Web应用\自做项目\video\video\images\" + Path.GetFileName(imagePath);
                if (IOHelper.Exists(newImageFullName))
                {
                    return -1;
                }
                else
                {
                    IOHelper.CopyFileToDirectory(imagePath, defaultPath1);
                    v.imageurl = "/images/" + Path.GetFileName(imagePath);
                }
            }

            string sql = "update videoinfo set videoname=@videoname,number=@number,price=@price,buydate=@buydate,describe=@describe,imageurl=@imageurl,clickrate=@clickrate where videoid=@videoid";
            SqlParameter[] parms =
            {
                new SqlParameter("@videoid",v.videoid),
                new SqlParameter("@videoname",v.videoname),
                new SqlParameter("@number",v.number),
                new SqlParameter("@price",v.price),
                new SqlParameter("@buydate",v.buydate),
                new SqlParameter("@describe",v.describe),
                new SqlParameter("@imageurl",v.imageurl),
                new SqlParameter("@clickrate",v.clickrate)
            };
            return SqlHelper.ExecuteNonQuery(sql, parms);
        }

        //通过videoid,查询影碟信息
        public DataTable selectVideoinfoById(string videoid)
        {
            string sql = "select * from videoinfo where videoid=@videoid";
            SqlParameter parm = new SqlParameter("@videoid", videoid);
            return SqlHelper.ExecuteDataTable(sql, parm);
        }

        //根据条件，做相关查询                    
        public int countById(string videoid)
        {
            string sql = "select count(*) from videoinfo where videoid=@videoid";
            SqlParameter parm = new SqlParameter("@videoid", videoid);
            return (int)SqlHelper.ExecuteScalar(sql, parm);
        }

        public DataTable queryByContent(int queryType,int compareType,string content)
        {
            //queryType:    0("按影碟编号");   1("按影碟名");   2("按数量");   3("按单价");   4("按录入时间");
            int qt = queryType;
           //compareType:    0("=");   1(">");   2("<");  -1(没用)
            int ct = compareType;
            if (qt == 0)
            {
                string sql = "select * from videoinfo where videoid=@videoid";
                SqlParameter parm = new SqlParameter("@videoid", content);
                return SqlHelper.ExecuteDataTable(sql,parm);
            }
            else if (qt == 1)
            { 
                string sql = "select * from videoinfo where videoname like @videoname";
                SqlParameter parm = new SqlParameter("@videoname", "%" + content + "%");
                return SqlHelper.ExecuteDataTable(sql, parm);
            }
            else if (qt == 2)
            {
                //compareType:    0("=");   1(">");   2("<");  -1(没用)
                int num=TypeConversion.stringTOint(content );
                if (ct == 0)
                {
                    string sql = "select * from videoinfo where number=@number";
                    SqlParameter parm = new SqlParameter("@number", num);
                    return SqlHelper.ExecuteDataTable(sql, parm);
                }
                else if (ct == 1)
                {
                    string sql = "select * from videoinfo where number>@number";
                    SqlParameter parm = new SqlParameter("@number", num);
                    return SqlHelper.ExecuteDataTable(sql, parm);
                }
                else  //ct==2
                {
                    string sql = "select * from videoinfo where number<@number";
                    SqlParameter parm = new SqlParameter("@number", num);
                    return SqlHelper.ExecuteDataTable(sql, parm);
                }
            }
            else   //qt==3
            {
                //compareType:    0("=");   1(">");   2("<");  -1(没用)
                double price = TypeConversion.stringTOdouble(content);
                if (ct == 0)
                {
                    string sql = "select * from videoinfo where price=@price";
                    SqlParameter parm = new SqlParameter("@price", price);
                    return SqlHelper.ExecuteDataTable(sql, parm);
                }
                else if (ct == 1)
                {
                    string sql = "select * from videoinfo where price>@price";
                    SqlParameter parm = new SqlParameter("@price", price);
                    return SqlHelper.ExecuteDataTable(sql, parm);
                }
                else   //ct==2
                {
                    string sql = "select * from videoinfo where price<@price";
                    SqlParameter parm = new SqlParameter("@price", price);
                    return SqlHelper.ExecuteDataTable(sql, parm);
                }
            }
        }

        public DataTable queryByTime(int compareType, DateTime queryTime)
        {
            //compareType:    0("=");   1(">");   2("<"); 
            int ct = compareType;
            if (ct == 0)
            {       
                //SQL传参，传递只能是参数
                string sql = "select * from videoinfo where convert(varchar(10),buydate,120) = convert(varchar(10),@buydate,120)";
                SqlParameter parm = new SqlParameter("@buydate", queryTime);
                return SqlHelper.ExecuteDataTable(sql, parm);
            }
            else if (ct == 1)
            {
                string sql = "select * from videoinfo where convert(varchar(10),buydate,120) > convert(varchar(10),@buydate,120)";
                SqlParameter parm = new SqlParameter("@buydate", queryTime);
                return SqlHelper.ExecuteDataTable(sql, parm);
            }
            else   //ct==2
            {
                string sql = "select * from videoinfo where convert(varchar(10),buydate,120) < convert(varchar(10),@buydate,120)";
                SqlParameter parm = new SqlParameter("@buydate", queryTime);
                return SqlHelper.ExecuteDataTable(sql, parm);
            }
        }


    }
}
