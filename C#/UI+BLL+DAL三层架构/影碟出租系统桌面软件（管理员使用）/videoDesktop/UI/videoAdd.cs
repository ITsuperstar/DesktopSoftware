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
using System.IO;

namespace UI
{
    public partial class videoAdd : Form
    {
        public videoAdd()
        {
            InitializeComponent();
        }
        private string imagepath = "";   //图片存放路径
        private BLLvideoinfo bv = new BLLvideoinfo(); //实例化 BLLvideoinfo 对象，方便操作

        private void button3_Click(object sender, EventArgs e)
        {
            OpenFileDialog file1 = new OpenFileDialog();
            //Filter选择图片时，属性为" 标签|*.jpg;*.png;*.gif "，注意：只是在筛选器中多添加了几个后缀，不同后缀之间使用分号隔开
            file1.Filter = "图片|*.jpg;*.png;*.gif";  //设置文件后缀过滤
            if (file1.ShowDialog() == DialogResult.OK)
            {
                imagepath = file1.FileName;  //获取文件全名，并存放在imagepath全局变量中
                string path = file1.FileName;
                textBox5.Text = Path.GetFileName(path);
                pictureBox1.Image = Image.FromFile(file1.FileName);
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void button2_Click(object sender, EventArgs e)
        {
            videoinfo v = new videoinfo();
            v.videoid = this.textBox1.Text;        
            v.videoname = this.textBox2.Text;   
            v.number = TypeConversion.stringTOint(this.textBox3.Text);
            v.price = TypeConversion.stringTOdouble(this.textBox4.Text);
            v.buydate = dateTimePicker1.Value;
            v.describe = this.textBox7.Text;
            v.clickrate = TypeConversion.stringTOint(this.textBox6.Text);
            v.imageurl = imagepath;
            //数据验证
            //点击录入按钮时，对影碟编号和图片进行验证
            if (this.textBox1.Text == "")
            {
                MessageBox.Show("影碟编号不能为空，请输入！");
            }
            else 
            {
                if (bv.countById(this.textBox1.Text) > 0)  //影碟编号已经存在
                {
                    MessageBox.Show("影碟编号已经存在，不能重复录入！");
                }
                else 
                {
                    if (this.textBox5.Text == "")
                    {
                        MessageBox.Show("影碟图片不能为空，请选择！");
                    }
                     //符合条件，可以录入
                    else 
                    {
                        int flagReturn = bv.addVideoinfo(v);   //返回值大于0，成功；等于0，保存失败；等于-1，上传路径中已经存在同名文件
                        if (flagReturn > 0)
                        {
                            MessageBox.Show("录入成功！");
                            this.Close();
                        }
                        else if (flagReturn == 0)
                        {
                            MessageBox.Show("录入失败，请重试");
                        }
                        else if (flagReturn < 0)
                        {
                            MessageBox.Show("上传路径中已经存在同名文件,请先修改文件名,然后重新选择！");
                        }
                    }
                }
            }
            
        }
       
        //光标离开时，验证影碟编号是否已经存在，若存在，提示错误
        //关于errorProvider1的使用，最好是一个控件用一个errorProvider1
        private void textBox1_Leave(object sender, EventArgs e)
        {
            string videoid = this.textBox1.Text;
            int num = bv.countById(videoid);
            if (num > 0)
            {
                this.errorProvider1.SetError(this.textBox1, "影碟编号已经存在，不能重复录入！");
            }
            else
                this.errorProvider1.SetError(this.textBox1, "");   //置为空时，错误提示消失
        }

        //鼠标离开时，验证影碟编号是否已经存在，若存在，提示错误
        private void textBox1_MouseLeave_1(object sender, EventArgs e)
        {
            string videoid = this.textBox1.Text;
            int num = bv.countById(videoid);
            if (num > 0)
            {
                this.errorProvider1.SetError(this.textBox1, "影碟编号已经存在，不能重复录入！");
            }
            else
                this.errorProvider1.SetError(this.textBox1, "");   //置为空时，错误提示消失
        }

        private void textBox3_Leave(object sender, EventArgs e)
        {
            if (CheckData.checkNoNegativeInt(this.textBox3.Text))
                this.errorProvider2.SetError(this.textBox3, "");   //置为空时，错误提示消失          
            else
                this.errorProvider2.SetError(this.textBox3, "数量应为非负整数，请重新输入！");
        }

        private void textBox4_Leave(object sender, EventArgs e)
        {
            if (CheckData.checkNoNegative(this.textBox4.Text))
                this.errorProvider3.SetError(this.textBox4, "");   //置为空时，错误提示消失          
            else
                this.errorProvider3.SetError(this.textBox4, "单价输入错误！");
        }

        private void textBox6_Leave(object sender, EventArgs e)
        {
            if (CheckData.checkNoNegativeInt(this.textBox6.Text))
                this.errorProvider4.SetError(this.textBox6, "");   //置为空时，错误提示消失          
            else
                this.errorProvider4.SetError(this.textBox6, "应为非负整数，请重新输入！");
        }


    }
}
