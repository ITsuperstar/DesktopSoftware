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
    public partial class videoMain : Form
    {
        public videoMain()
        {
            InitializeComponent();
        }
        private BLLvideoinfo bv = new BLLvideoinfo();  //实例化一个BLLvideoinfo对象，方便操作
        private void videoMain_Load(object sender, EventArgs e)
        {
            this.dataGridView_Init();

            //comboBox1  —选择查询类型
            comboBox1.Items.Add("按影碟编号");
            comboBox1.Items.Add("按影碟名");
            comboBox1.Items.Add("按数量");
            comboBox1.Items.Add("按单价");
            comboBox1.Items.Add("按录入时间");
            comboBox1.SelectedIndex = 0;

            //comboBox2  —选择"=" "<" ">"
            comboBox2.Items.Add("=");
            comboBox2.Items.Add(">");
            comboBox2.Items.Add("<");
            comboBox2.SelectedIndex = 0;
        }
        //根据DataTable，改变dataGridView1
        private void dataGridView_Change(DataTable dt)
        {
            dataGridView1.Rows.Clear();
            DataTable  allv =  dt;
            for (int i = 0; i < allv.Rows.Count; i++)
            {
                int index = this.dataGridView1.Rows.Add();
                this.dataGridView1.Rows[index].Cells[1].Value = allv.Rows[i].ItemArray[1].ToString();
                this.dataGridView1.Rows[index].Cells[2].Value = allv.Rows[i].ItemArray[2].ToString();
                this.dataGridView1.Rows[index].Cells[3].Value = allv.Rows[i].ItemArray[3].ToString();
                this.dataGridView1.Rows[index].Cells[4].Value = allv.Rows[i].ItemArray[4].ToString();
                this.dataGridView1.Rows[index].Cells[5].Value = allv.Rows[i].ItemArray[5].ToString();
                this.dataGridView1.Rows[index].Cells[6].Value = allv.Rows[i].ItemArray[6].ToString();
                this.dataGridView1.Rows[index].Cells[7].Value = allv.Rows[i].ItemArray[8].ToString();
                this.dataGridView1.Rows[index].Cells[8].Value = "删除";
                this.dataGridView1.Rows[index].Cells[9].Value = "编辑";
            }
        }

        private void dataGridView_Init()
        {
            dataGridView1.Rows.Clear();
            DataTable allv = bv.getAllVideoinfo();
            for (int i = 0; i < allv.Rows.Count; i++)
            {
                int index = this.dataGridView1.Rows.Add();
                this.dataGridView1.Rows[index].Cells[1].Value = allv.Rows[i].ItemArray[1].ToString();
                this.dataGridView1.Rows[index].Cells[2].Value = allv.Rows[i].ItemArray[2].ToString();
                this.dataGridView1.Rows[index].Cells[3].Value = allv.Rows[i].ItemArray[3].ToString();
                this.dataGridView1.Rows[index].Cells[4].Value = allv.Rows[i].ItemArray[4].ToString();
                this.dataGridView1.Rows[index].Cells[5].Value = allv.Rows[i].ItemArray[5].ToString();
                this.dataGridView1.Rows[index].Cells[6].Value = allv.Rows[i].ItemArray[6].ToString();
                this.dataGridView1.Rows[index].Cells[7].Value = allv.Rows[i].ItemArray[8].ToString();
                this.dataGridView1.Rows[index].Cells[8].Value = "删除";
                this.dataGridView1.Rows[index].Cells[9].Value = "编辑";
            }
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void dataGridView1_RowPostPaint(object sender, DataGridViewRowPostPaintEventArgs e)
        {
            foreach (DataGridViewRow row in dataGridView1.Rows)
            {
                row.Cells[0].Value = row.Index + 1;
            }
        }

        private void dataGridView1_CellClick(object sender, DataGridViewCellEventArgs e)
        {
            int clickingColumn = e.ColumnIndex;
            //点击-1—7列时，不响应
            if (clickingColumn >= -1 && clickingColumn <= 7) { }

            else
            {
                DataGridViewColumn column = dataGridView1.Columns[e.ColumnIndex];
                string content = (string)dataGridView1.Rows[e.RowIndex].Cells[e.ColumnIndex].Value;
                if (column.HeaderText == "删除" && content == "删除")
                {
                    if (MessageBox.Show("确定要删除该影碟吗？", "删除影碟", MessageBoxButtons.OKCancel) == DialogResult.OK)
                    {

                        //删除之前先查询，否则存在关系的不允许删除
                        videoinfo v = new videoinfo();
                        v.videoid = dataGridView1.Rows[e.RowIndex].Cells[1].Value.ToString();
                        int delflag=bv.delVideoinfo(v);
                        if (delflag > 0)      //删除成功
                        {
                            MessageBox.Show("删除成功！");
                            dataGridView1.Rows.RemoveAt(e.RowIndex);
                        }
                        else if (delflag == 0)    //删除失败
                        {
                            MessageBox.Show("删除失败，请稍后重试！");
                        }
                        else if (delflag == -1)   //不让删除
                        {
                            MessageBox.Show("该影碟还被引用，不能删除！");
                        }

                    }
                }

                else if (column.HeaderText == "编辑" && content == "编辑")
                {
                    string videoid = (string)dataGridView1.Rows[e.RowIndex].Cells[1].Value;
                    new videoUpdate(videoid).Show();
                }

            }

        }

        private void dataGridView1_CellContentClick(object sender, DataGridViewCellEventArgs e)
        {

        }

        private void dataGridView1_MouseHover(object sender, EventArgs e)
        {

        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.dataGridView_Init();
        }

        private void button4_Click(object sender, EventArgs e)
        {
            new videoAdd().Show();
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
            if (comboBox1.SelectedIndex == 4)
            {
                this.comboBox2.Visible = true;
                this.comboBox2.SelectedIndex = 0;
                this.textBox1.Visible = false;
                this.dateTimePicker1.Visible = true;
            }
            else if (comboBox1.SelectedIndex == 0 || comboBox1.SelectedIndex == 1)
            {
                this.comboBox2.Visible = false;
                this.textBox1.Visible = true;
                this.dateTimePicker1.Visible = false;
            }
            else if (comboBox1.SelectedIndex == 2 || comboBox1.SelectedIndex == 3)
            {
                this.comboBox2.Visible = true;
                this.comboBox2.SelectedIndex = 0;
                this.textBox1.Visible = true;
                this.dateTimePicker1.Visible = false;
            }

        }

        private void button3_Click(object sender, EventArgs e)
        {
            //queryType:    0("按影碟编号");   1("按影碟名");   2("按数量");   3("按单价");   4("按录入时间");
            int queryType = this.comboBox1.SelectedIndex;
            //compareType:    0("=");   1(">");   2("<");  -1(没用)
            int compareType = this.comboBox2.SelectedIndex;
            string content = this.textBox1.Text;
            DateTime queryTime = dateTimePicker1.Value;
            if (queryType == 4)
            {
                DataTable  qbt=bv.queryByTime(compareType, queryTime);
                this.dataGridView_Change(qbt);
            }
            else 
            {
                DataTable qbc=bv.queryByContent(queryType, compareType, content);
                this.dataGridView_Change(qbc);
            }
        }
    }
}
