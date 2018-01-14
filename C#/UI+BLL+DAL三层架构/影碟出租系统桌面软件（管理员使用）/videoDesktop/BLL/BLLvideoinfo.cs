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
    public class BLLvideoinfo
    {
        private DALvideoinfo dv=new DALvideoinfo();

        //获取所有影碟信息
        public DataTable getAllVideoinfo() 
        {
            return dv.selectAllVideoinfo();
        }

        //添加影碟信息，是否成功
        public int addVideoinfo(videoinfo v) 
        {
            return dv.insertVideoinfo(v);  //返回值大于0，成功；等于0，保存失败；等于-1，上传路径中已经存在同名文件

        }

        //删除影碟信息，是否成功
        public int delVideoinfo(videoinfo v)
        {
            return dv.deleteVideoinfo(v);  //返回值大于0，成功；等于0，保存失败；等于-1，不能删除
        }

        //修改影碟信息，是否成功
        public int upVideoinfo(videoinfo v)
        {
            return dv.updateVideoinfo(v);  //返回值大于0，成功；等于0，保存失败；等于-1，上传路径中已经存在同名文件
        }

        //通过videoid,查询影碟信息
        public DataTable getVideoinfoById(string videoid)
        {
            return dv.selectVideoinfoById(videoid);
        }

        //根据条件，做相关查询                    
        public int countById(string videoid)
        {
            return dv.countById(videoid);
        }
        public DataTable queryByContent(int queryType, int compareType, string content)
        {
           return  dv.queryByContent(queryType, compareType, content);
        }
        public DataTable queryByTime(int compareType, DateTime queryTime) 
        {
            return dv.queryByTime(compareType, queryTime);
        }
    }
}
