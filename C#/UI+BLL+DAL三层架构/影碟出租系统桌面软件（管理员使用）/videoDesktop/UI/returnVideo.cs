using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MODEL;
using BLL;
using TOOL;

namespace UI
{
    public partial class returnVideo : Form
    {
        public returnVideo()
        {
            InitializeComponent();
        }

        private BLLhireinfo bh = new BLLhireinfo();   //实例化 BLLhireinfo 对象，方便操作
        private void button1_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void textBox1_Leave(object sender, EventArgs e)
        {
            string videoid = this.textBox1.Text;
            int isexist = bh.isVideoinfo(videoid);
            int num = bh.isVideoNumber(videoid);
            //影碟不存在
            if (isexist <= 0)
            {
                this.errorProvider1.SetError(this.textBox1, "该影碟不存在，请出租别的影碟！");
            }
            else
            {
                if (num <= 0)
                {
                    this.errorProvider1.SetError(this.textBox1, "该影碟已被借完，请出租别的影碟！");
                }
                else
                    this.errorProvider1.SetError(this.textBox1, "");    //置为空时，错误提示消失
            }
        }

        private void textBox2_Leave(object sender, EventArgs e)
        {
            string clientid = this.textBox2.Text;
            int isexist = bh.isClientinfo(clientid);
            //用户不存在
            if (isexist <= 0)
            {
                this.errorProvider2.SetError(this.textBox2, "该用户不存在，请重新输入！");
            }
            else
                this.errorProvider2.SetError(this.textBox2, "");    //置为空时，错误提示消失
        }

        private void button2_Click(object sender, EventArgs e)
        {
            hireinfo h = new hireinfo();
            h.videoid = this.textBox1.Text;
            h.clientid = this.textBox2.Text;
            h.returntime = DateTime.Now;
            //若不存在，提示错误，返回-1
            int flag=bh.returnVideo(h);
            if (flag == -1)
                MessageBox.Show("您没有租借过此影碟！");
            else if (flag == 0)
                MessageBox.Show("归还失败，请稍候再试！");
            else if (flag > 0)
            {
                MessageBox.Show("归还成功，祝您生活愉快！");
                this.Close();
            }
        }
    }
}
