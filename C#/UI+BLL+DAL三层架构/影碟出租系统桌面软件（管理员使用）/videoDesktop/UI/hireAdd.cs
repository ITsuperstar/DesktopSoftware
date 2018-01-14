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
    public partial class hireAdd : Form
    {
        public hireAdd()
        {
            InitializeComponent();
        }

        private BLLhireinfo bh = new BLLhireinfo();   //实例化 BLLhireinfo 对象，方便操作
        private void button1_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            hireinfo h = new hireinfo();
            h.videoid = this.textBox1.Text;
            h.clientid = this.textBox2.Text;
            h.borrowtime = DateTime.Now;
            int rentmonth = comboBox1.SelectedIndex + 1;
            h.shouldtime = DateTime.Now.AddMonths(rentmonth);
            //总租金必须自己计算，不能从textBox3获取
            double oneprice = bh.returnPrice(this.textBox1.Text);
            double totalprice = (comboBox1.SelectedIndex + 1) * oneprice;
            h.rent = totalprice;
            //数据验证
            if (this.textBox1.Text == "")
                MessageBox.Show("影碟编号不能为空，请输入！");
            else
            {
                if (this.textBox2.Text == "")
                    MessageBox.Show("用户编号不能为空，请输入！");
                    else
                    {
                        //先尝试录入，根据返回值提示错误
                        //-3(影碟不存在);  -2(影碟被借完);  -1(用户不存在);  0(出租失败);  1(成功);  -4(未归还)
                        int flagReturn = bh.addHireinfo(h);   
                        if (flagReturn > 0)
                        {
                            MessageBox.Show("出租成功，祝您生活愉快！");
                            this.Close();
                        }
                        else if (flagReturn == 0)
                        {
                            MessageBox.Show("出租失败，请稍后重试");
                        }
                        else if (flagReturn == -1)
                        {
                            MessageBox.Show("该用户不存在！");
                        }
                        else if (flagReturn == -2)
                        {
                            MessageBox.Show("该影碟被借完，请出租别的影碟");
                        }
                        else if (flagReturn == -3)
                        {
                            MessageBox.Show("该影碟不存在，请出租别的影碟");
                        }
                        else if (flagReturn == -4)
                        {
                            MessageBox.Show("您已经借过该影碟，还没有归还，请先归还再借该影碟！");
                        }
                    }
              
            }
        }

        private void hireAdd_Load(object sender, EventArgs e)
        {
            comboBox1.Items.Add("1");
            comboBox1.Items.Add("2");
            comboBox1.Items.Add("3");
            comboBox1.SelectedIndex = 0;
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            string videoid = this.textBox1.Text;
            double price=bh.returnPrice(videoid);
            double totalprice = (comboBox1.SelectedIndex + 1) * price;
            this.textBox3.Text = totalprice.ToString();
        }

        private void textBox1_Leave(object sender, EventArgs e)
        {
            string videoid = this.textBox1.Text;
            int isexist=bh.isVideoinfo(videoid);
            int num = bh.isVideoNumber(videoid);
            //影碟不存在
            if (isexist <= 0)
            {
                this.errorProvider1.SetError(this.textBox1, "该影碟不存在，请出租别的影碟！");
                //重新计算租金，放入textBox3中
                this.textBox3.Text = ((comboBox1.SelectedIndex + 1) * (bh.returnPrice(this.textBox1.Text))).ToString();
            }
            else
            {
                if (num <= 0)
                {
                    this.errorProvider1.SetError(this.textBox1, "该影碟已被借完，请出租别的影碟！");
                    //重新计算租金，放入textBox3中
                    this.textBox3.Text = ((comboBox1.SelectedIndex + 1) * (bh.returnPrice(this.textBox1.Text))).ToString();
                }
                else
                {
                    this.errorProvider1.SetError(this.textBox1, "");    //置为空时，错误提示消失
                    //重新计算租金，放入textBox3中
                    this.textBox3.Text = ( (comboBox1.SelectedIndex + 1) * (bh.returnPrice(this.textBox1.Text)) ).ToString();
                }
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
    }
}
