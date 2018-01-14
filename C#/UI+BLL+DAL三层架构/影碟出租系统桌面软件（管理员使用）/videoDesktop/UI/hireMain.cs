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
    public partial class hireMain : Form
    {
        public hireMain()
        {
            InitializeComponent();
        }

        private BLLhireinfo bh = new BLLhireinfo();  //实例化一个BLLhireinfo对象，方便操作
        private void hireMain_Load(object sender, EventArgs e)
        {
            this.dataGridView_Init();
           
            //comboBox1  —选择查询类型
            //queryType:    0("按影碟编号");   1("按用户编号");   2("没归还的影碟");   —前3个选项
            //compareType:    0(借出时间);   1(归还时间);   2(应归还时间);                  —后3个选项
            comboBox1.Items.Add("按影碟编号");
            comboBox1.Items.Add("按用户编号");
            comboBox1.Items.Add("没归还的影碟");
            comboBox1.Items.Add("按借出时间");
            comboBox1.Items.Add("按归还时间");
            comboBox1.Items.Add("按应归还时间");
            comboBox1.SelectedIndex = 0;
        }

        //根据DataTable，改变dataGridView1
        private void dataGridView_Change(DataTable dt)
        {
            dataGridView1.Rows.Clear();
            DataTable allh = dt;
            for (int i = 0; i < allh.Rows.Count; i++)
            {
                int index = this.dataGridView1.Rows.Add();
                this.dataGridView1.Rows[index].Cells[1].Value = allh.Rows[i].ItemArray[1].ToString();
                this.dataGridView1.Rows[index].Cells[2].Value = allh.Rows[i].ItemArray[2].ToString();
                //是否归还需要进行数据含义转换
                if (allh.Rows[i].ItemArray[4].ToString() == "1")
                    this.dataGridView1.Rows[index].Cells[3].Value = "是";
                else
                    this.dataGridView1.Rows[index].Cells[3].Value = "否";
                this.dataGridView1.Rows[index].Cells[4].Value = allh.Rows[i].ItemArray[5].ToString();
                this.dataGridView1.Rows[index].Cells[5].Value = allh.Rows[i].ItemArray[6].ToString();
                this.dataGridView1.Rows[index].Cells[6].Value = allh.Rows[i].ItemArray[8].ToString();
                this.dataGridView1.Rows[index].Cells[7].Value = allh.Rows[i].ItemArray[7].ToString();
                this.dataGridView1.Rows[index].Cells[8].Value = "删除";
                this.dataGridView1.Rows[index].Cells[9].Value = "编辑";
                this.dataGridView1.Rows[index].Cells[10].Value = allh.Rows[i].ItemArray[0].ToString();  //id，是隐藏的
                this.dataGridView1.Rows[index].Cells[11].Value = allh.Rows[i].ItemArray[3].ToString();  //adminid，是隐藏的
            }
        }

        private void dataGridView_Init()
        {
            dataGridView1.Rows.Clear();
            DataTable allh = bh.getAllHireinfo();
            for (int i = 0; i < allh.Rows.Count; i++)
            {
                int index = this.dataGridView1.Rows.Add();
                this.dataGridView1.Rows[index].Cells[1].Value = allh.Rows[i].ItemArray[1].ToString();
                this.dataGridView1.Rows[index].Cells[2].Value = allh.Rows[i].ItemArray[2].ToString();
                //是否归还需要进行数据含义转换
                if (allh.Rows[i].ItemArray[4].ToString() == "1")
                    this.dataGridView1.Rows[index].Cells[3].Value = "是";
                else
                    this.dataGridView1.Rows[index].Cells[3].Value = "否";
                this.dataGridView1.Rows[index].Cells[4].Value = allh.Rows[i].ItemArray[5].ToString();
                this.dataGridView1.Rows[index].Cells[5].Value = allh.Rows[i].ItemArray[6].ToString();
                this.dataGridView1.Rows[index].Cells[6].Value = allh.Rows[i].ItemArray[8].ToString();
                this.dataGridView1.Rows[index].Cells[7].Value = allh.Rows[i].ItemArray[7].ToString();
                this.dataGridView1.Rows[index].Cells[8].Value = "删除";
                this.dataGridView1.Rows[index].Cells[9].Value = "编辑";
                this.dataGridView1.Rows[index].Cells[10].Value = allh.Rows[i].ItemArray[0].ToString();  //id，是隐藏的
                this.dataGridView1.Rows[index].Cells[11].Value = allh.Rows[i].ItemArray[3].ToString();  //adminid，是隐藏的
            }
        }

        private void button2_Click(object sender, EventArgs e)
        {
            this.dataGridView_Init();
        }

        private void button1_Click(object sender, EventArgs e)
        {
            this.Close();
        }

        private void comboBox1_SelectedIndexChanged(object sender, EventArgs e)
        {
           if (comboBox1.SelectedIndex == 0 || comboBox1.SelectedIndex == 1)
            {
                 this.textBox1.Visible = true;
                 this.dateTimePicker1.Visible = false;
                 this.dateTimePicker2.Visible = false;
                 this.label1.Visible = false;
            }
           else if(comboBox1.SelectedIndex == 2)
           {
                 this.textBox1.Visible = false;
                 this.dateTimePicker1.Visible = false;
                 this.dateTimePicker2.Visible = false;
                 this.label1.Visible = false;
           }
           else if (comboBox1.SelectedIndex == 3 || comboBox1.SelectedIndex == 4 || comboBox1.SelectedIndex == 5)
            {
                this.textBox1.Visible = false;
                this.dateTimePicker1.Visible = true;
                this.dateTimePicker2.Visible = true;
                this.label1.Visible = true;
            }
        }

        private void button3_Click(object sender, EventArgs e)
        {
            //queryType:    0("按影碟编号");   1("按用户编号");   2("没归还的影碟");   —前3个选项
            //compareType:    0(借出时间);   1(归还时间);   2(应归还时间);                  —后3个选项
            int queryType = this.comboBox1.SelectedIndex;
            int compareType = this.comboBox1.SelectedIndex - 3;       //后三项重新编号
            string content = this.textBox1.Text;
            DateTime startTime, endTime;
            if (DateTime.Compare(dateTimePicker1.Value, dateTimePicker2.Value) <=0)
            {
                 startTime = dateTimePicker1.Value;
                 endTime = dateTimePicker2.Value;
            }
            else 
            {
                 startTime = dateTimePicker2.Value;
                 endTime = dateTimePicker1.Value;
            }
            if (queryType >= 0 && queryType <= 2)
            {
                DataTable qbc= bh.queryByContent(queryType, content);
                this.dataGridView_Change(qbc);
            }
            else if (queryType >= 3 && queryType <= 5)
            {
                DataTable qbt = bh.queryByTime(compareType, startTime, endTime);
                this.dataGridView_Change(qbt);
            }

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
                    if (MessageBox.Show("确定要删除该记录吗？", "删除记录", MessageBoxButtons.OKCancel) == DialogResult.OK)
                    {
                        //删除之前先查询，否则存在关系的不允许删除
                        hireinfo h = new hireinfo();
                        string id = dataGridView1.Rows[e.RowIndex].Cells[10].Value.ToString();   //id，是隐藏的
                        h.id = TypeConversion.stringTOint(id);
                        int delflag= bh.delHireinfo(h);
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
                            MessageBox.Show("该记录还被引用，不能删除！");
                        }

                    }
                }

                else if (column.HeaderText == "编辑" && content == "编辑")
                {
                    string tempid = dataGridView1.Rows[e.RowIndex].Cells[10].Value.ToString();
                    int id = TypeConversion.stringTOint(tempid);
                    new hireUpdate(id).Show();
                }

            }
        }

        private void button4_Click(object sender, EventArgs e)
        {
            new hireAdd().Show();
        }

        private void button5_Click(object sender, EventArgs e)
        {
            new returnVideo().Show();
        }

    }
}
