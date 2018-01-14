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
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            comboBox1.Items.Add("管理员");
            //comboBox1.Items.Add("学生");
            comboBox1.SelectedIndex = 0;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            textBox1.Text = "";
            textBox2.Text = "";
            comboBox1.SelectedIndex = 0;
        }

        private void button1_Click(object sender, EventArgs e)
        {
            string username = textBox1.Text.Trim();
            string password = textBox2.Text.Trim();
            string usertype = comboBox1.Text.Trim();
            if (username == ""||password=="") 
            {
                MessageBox.Show("账号或密码不能为空！");
            }
            else
            {
                adminlogin ad= new adminlogin();
                ad.adminid = username;
                ad.password = password;
                BLLlogin bl = new BLLlogin();
                if (bl.bllCheckLogin(ad) == 1)   //登陆成功
                {               
                    //this.Close();     //直接关闭当前窗口.以后可以再调用.
                    //this.Dispose();  //关闭当前窗口,以后不可以调用.
                    //Application.Exit();    //强制所有消息中止，退出所有的窗体，但是若有托管线程（非主线程），无法干净地退出
                    //System.Environment.Exit(0);    //这是最彻底的退出方式，把程序结束的很干净
                    this.Hide();       //这是隐藏当前窗口,但会继续占用资源.
                    new index().Show();
                }
                else if (bl.bllCheckLogin(ad) == 0)   //账号或密码错误
                {
                    MessageBox.Show("账号或密码错误！");
                }
            }
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }
    }
}
