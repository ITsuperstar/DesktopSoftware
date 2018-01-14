using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.IO;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using MODEL;
using BLL;
using TOOL;

namespace UI
{
    public partial class videoUpdate : Form
    {
        private string videoid = "";
        private string imagepath = "";   //图片存放路径
        private BLLvideoinfo bv = new BLLvideoinfo(); //实例化 BLLvideoinfo 对象，方便操作
        public videoUpdate(string videoid)
        {
            InitializeComponent();
            this.videoid=videoid;
        }

        //根据videoid初始窗体的方法
        private void window_Init()
        {
            DataTable dt = bv.getVideoinfoById(videoid);

            for (int i = 0; i < dt.Rows.Count; i++)
            {
                this.label2.Text = dt.Rows[0].ItemArray[1].ToString();
                this.textBox1.Text = dt.Rows[0].ItemArray[2].ToString();
                this.textBox2.Text = dt.Rows[0].ItemArray[3].ToString();
                this.textBox3.Text = dt.Rows[0].ItemArray[4].ToString();
                this.label8.Text = dt.Rows[0].ItemArray[5].ToString();
                this.textBox6.Text = dt.Rows[0].ItemArray[6].ToString();

                this.textBox4.Text = dt.Rows[0].ItemArray[8].ToString();
                //图片显示，特殊处理
                //图片的默认存储位置  F:\VS2012 工作目录\Web应用\自做项目\video\video\images
                string filePath = @"F:\VS2012 工作目录\Web应用\自做项目\video\video" + (string)dt.Rows[0].ItemArray[7];
                imagepath = filePath;
                this.pictureBox1.Image = Image.FromFile(filePath);
                this.textBox5.Text = textBox5.Text = Path.GetFileName(filePath);
            }
        }

        private void videoUpdate_Load(object sender, EventArgs e)
        {
            this.window_Init();
        }

        private void listBox1_SelectedIndexChanged(object sender, EventArgs e)
        {

        }

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

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            videoinfo v=new videoinfo();
            v.videoid = this.label2.Text;
            v.videoname = this.textBox1.Text;
            v.number = TypeConversion.stringTOint(this.textBox2.Text);
            v.price = TypeConversion.stringTOdouble(this.textBox3.Text);
            v.buydate = TypeConversion.stringTOdatetime(this.label8.Text);
            v.describe = this.textBox6.Text;
            v.clickrate = TypeConversion.stringTOint(this.textBox4.Text);
            v.imageurl = imagepath;
            int flagReturn= bv.upVideoinfo(v);   //返回值大于0，成功；等于0，保存失败；等于-1，上传路径中已经存在同名文件
            if (flagReturn > 0) 
            {
                MessageBox.Show("保存成功！");
                this.Close();
            }
            else if (flagReturn == 0)
            {
                MessageBox.Show("保存失败，请重试");
            }
            else if (flagReturn < 0)
            {
                MessageBox.Show("上传路径中已经存在同名文件,请先修改文件名,然后重新选择！");
            }
        }
    }
}
