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
    public partial class clientUpdate : Form
    {
        private string clientid = "";
        private BLLclientinfo bc = new BLLclientinfo(); //实例化 BLLclientinfo 对象，方便操作
        public clientUpdate(string clientid)
        {
            InitializeComponent();
            this.clientid = clientid;
        }

        //根据clientid初始窗体的方法
        private void window_Init()
        {
            DataTable dt = bc.getClientinfoById(clientid);

            for (int i = 0; i < dt.Rows.Count; i++)
            {
                this.label5.Text = dt.Rows[0].ItemArray[1].ToString();
                this.textBox1.Text = dt.Rows[0].ItemArray[2].ToString();
                this.textBox2.Text = dt.Rows[0].ItemArray[3].ToString();
                this.textBox3.Text = dt.Rows[0].ItemArray[4].ToString();
            }
        }

        private void clientUpdate_Load(object sender, EventArgs e)
        {
            this.window_Init();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            clientinfo c= new clientinfo();
            c.clientid = this.label5.Text;
            c.clientname = this.textBox1.Text;
            c.telphone = this.textBox2.Text;
            c.email = this.textBox3.Text;

            int flagReturn = bc.upClientinfo(c);   //返回值大于0，成功；等于0，保存失败
            if (flagReturn > 0)
            {
                MessageBox.Show("保存成功！");
                this.Close();
            }
            else if (flagReturn == 0)
            {
                MessageBox.Show("保存失败，请重试");
            }
           
        }

        private void textBox2_Leave(object sender, EventArgs e)
        {
            if (CheckData.checkTelphone(this.textBox2.Text))
                this.errorProvider1.SetError(this.textBox2, "");    //置为空时，错误提示消失          
            else
                this.errorProvider1.SetError(this.textBox2, "电话号码格式不正确，请重新输入！");
        }

        private void textBox3_Leave(object sender, EventArgs e)
        {
            if (CheckData.checkEmail(this.textBox3.Text))
                this.errorProvider2.SetError(this.textBox3, "");     //置为空时，错误提示消失          
            else
                this.errorProvider2.SetError(this.textBox3, "邮箱格式不正确！");
        }
    }
}
