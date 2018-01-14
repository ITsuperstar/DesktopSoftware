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
    public partial class clientAdd : Form
    {
        public clientAdd()
        {
            InitializeComponent();
        }

        private BLLclientinfo bc = new BLLclientinfo();   //实例化 BLLclientinfo 对象，方便操作
        private void button1_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            clientinfo c = new clientinfo();
            c.clientid = this.textBox1.Text;
            c.clientname = this.textBox2.Text;
            c.telphone = this.textBox3.Text;
            c.email = this.textBox4.Text;
            //数据验证
            //点击录入按钮时，对用户编号进行验证
            if (this.textBox1.Text == "")
            {
                MessageBox.Show("用户编号不能为空，请输入！");
            }
            else
            {
                if (bc.countById(this.textBox1.Text) > 0)  //用户编号已经存在
                {
                    MessageBox.Show("用户编号已经存在，不能重复录入！");
                }
                //符合条件，可以录入
                else
                {
                    int flagReturn = bc.addClientinfo(c);   //返回值大于0，成功；等于0，保存失败
                    if (flagReturn > 0)
                    {
                        MessageBox.Show("录入成功！");
                        this.Close();
                    }
                    else if (flagReturn == 0)
                    {
                        MessageBox.Show("录入失败，请重试");
                    }
                }
            }
        }

        private void textBox1_Leave(object sender, EventArgs e)
        {
            string clientid = this.textBox1.Text;
            int num = bc.countById(clientid);
            if (num > 0)
            {
                this.errorProvider1.SetError(this.textBox1, "用户编号已经存在，不能重复录入！");
            }
            else
                this.errorProvider1.SetError(this.textBox1, "");   //置为空时，错误提示消失
        }

        private void textBox3_Leave(object sender, EventArgs e)
        {
            if (CheckData.checkTelphone(this.textBox3.Text))
                this.errorProvider2.SetError(this.textBox3, "");    //置为空时，错误提示消失          
            else
                this.errorProvider2.SetError(this.textBox3, "电话号码格式不正确，请重新输入！");
        }

        private void textBox4_Leave(object sender, EventArgs e)
        {
            if (CheckData.checkEmail(this.textBox4.Text))
                this.errorProvider3.SetError(this.textBox4, "");    //置为空时，错误提示消失          
            else
                this.errorProvider3.SetError(this.textBox4, "邮箱格式不正确！");
        }

        private void clientAdd_Load(object sender, EventArgs e)
        {

        }

    }
}
