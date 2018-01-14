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
    public partial class hireUpdate : Form
    {
        private int id =0;
        private BLLhireinfo bh = new BLLhireinfo(); //实例化 BLLhireinfo 对象，方便操作
        public hireUpdate(int id)
        {
            InitializeComponent();
            this.id = id;
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            hireinfo h = new hireinfo();
            h.id = this.id;
            h.videoid = this.textBox1.Text;
            h.clientid = this.textBox2.Text;
            h.isreturn = this.comboBox1.SelectedIndex;
            int flagReturn = bh.upHireinfo(h);   //返回值大于0，成功；等于0，保存失败。 对数据库有修改的方法，必须设置中间变量。
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
        //根据clientid初始窗体的方法
        private void window_Init()
        {
            DataTable dt = bh.getHireinfoById(this.id);
            for (int i = 0; i < dt.Rows.Count; i++)
            {
                this.textBox1.Text = dt.Rows[0].ItemArray[1].ToString();
                this.textBox2.Text = dt.Rows[0].ItemArray[2].ToString();
                this.comboBox1.SelectedIndex=(int)dt.Rows[0].ItemArray[4];
            }
        }
        private void hireUpdate_Load(object sender, EventArgs e)
        {
            this.comboBox1.Items.Add("否");
            this.comboBox1.Items.Add("是");

            this.window_Init();

        }
    }
}
