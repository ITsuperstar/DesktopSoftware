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
    public class BLLhireinfo
    {
        private DALhireinfo dh = new DALhireinfo();

        //获取所有出租信息
        public DataTable getAllHireinfo()
        {
            return dh.selectAllHireinfo();
        }

        //通过id,查询出租信息
        public DataTable getHireinfoById(int id)
        {
            return dh.selectHireinfoById(id);
        }

        //根据条件，在hireinfo表中做相关查询  
        public int countByVideoid(string videoid) 
        {
            return dh.countByVideoid(videoid);
        }

        public int countByClientid(string clientid)
        {
            return dh.countByClientid(clientid);
        }

        //查询该用户是否已经借了某影碟，若借了某影碟还没还(>0)，再次借同一种影碟，则不让借
        public int countIsReturn(string videoid, string clientid)
        {
            return dh.countIsReturn(videoid, clientid);
        }

        public DataTable queryByContent(int queryType, string content) 
        {
            return dh.queryByContent(queryType, content);
        }

        public DataTable queryByTime(int compareType, DateTime startTime, DateTime endTime)
        {
            return dh.queryByTime(compareType, startTime, endTime);
        }

        //根据影碟编号判断影碟是否存在,  >0则存在
        public int isVideoinfo(string videoid)
        {
            return dh.isVideoinfo(videoid);
        }
        //根据影碟编号查找影碟的数量,  >0才可借
        public int isVideoNumber(string videoid)
        {
            return dh.isVideoNumber(videoid);
        }
        //根据影碟编号，在videoinfo表中，查找影碟的单价
        public double returnPrice(string videoid)
        {
            return dh.returnPrice(videoid);
        }

        //根据用户编号判断用户是否存在,  >0则存在
        public int isClientinfo(string clientid)
        {
            return dh.isClientinfo(clientid);
        }

        //根据管理员编号判断管理员是否存在,  >0则存在
        public int isAdmininfo(string adminid)
        {
            return dh.isAdmininfo(adminid);
        }


        //返回值   -3(影碟不存在);  -2(影碟被借完);  -1(用户不存在);  0(出租失败);  1(成功);  -4(未归还)
        public int addHireinfo(hireinfo h)
        {
            return dh.insertHireinfo(h); 
        }

        //删除出租信息，是否成功
        public int delHireinfo(hireinfo h)
        {
            return dh.deleteHireinfo(h);  //返回值大于0，成功；等于0，保存失败；等于-1，不能删除
        }

        //修改出租信息，是否成功
        public int upHireinfo(hireinfo h)
        {
            return dh.updateHireinfo(h);   //返回值大于0，成功；等于0，保存失败
        }

        //根据影碟编号和读者编号，归还影碟，返回受影响的行数。
        //若存在(isreturn=0)，hireinfo中的returntime和isreturn，videoinfo中的数量number（有多个结果时，按id排序，修改最早借的）
        //若不存在，提示错误，返回-1
        public int returnVideo(hireinfo h)
        {
            return dh.returnVideo(h);
        }

    }
}