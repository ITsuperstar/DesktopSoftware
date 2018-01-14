package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Teacher extends JFrame implements ActionListener {
	private JMenuBar mbar;  
	private JMenu que,stu,gra;
	private JMenuItem  questu,quecou,quegra,stuadd,stuupd,studel,graadd,graupd,gradel,refresh;  //查询、选课、删课、改课
	private JPanel pCenter;
	private CardLayout card=null;
	public Teacher()
	{
		  super("老师管理");    //创建框架对象
		  this.setBounds(200, 40,400,400);    //设置窗口的大小
		   mbar = new JMenuBar();       //创建菜单栏	
		   que = new JMenu("查询管理");   //创建菜单
		   stu = new JMenu("学生管理"); 
		   gra = new JMenu("成绩管理");
		   questu = new JMenuItem("学生查询      ");   //创建菜单
		   quecou = new JMenuItem("课程查询"); 
		   quegra = new JMenuItem("选课查询");  //选课查询就是上述的成绩查询 
		   stuadd = new JMenuItem("录入学生"); 
		   stuupd = new JMenuItem("修改学生"); 
		   studel = new JMenuItem("删除学生"); 
		   graadd = new JMenuItem("录入成绩 "); 
		   graupd = new JMenuItem("修改成绩");
		   refresh =new JMenuItem("刷新");
		   refresh.setForeground(Color.red);
		   //JMenuItem mniQuit = new JMenuItem("退出"); 	//创建菜单项
		   mbar.add(que);    //将菜单添加到菜单栏中
		   mbar.add(new Label(" "));
		   mbar.add(stu);
		   mbar.add(new Label(" "));
		   mbar.add(gra);
		   mbar.add(new Label(" "));
		   mbar.add(refresh);
		   que.add(questu);    //将菜单添加到菜单栏中
		   que.add(quecou);
		   que.add(quegra);
		   stu.add(stuadd);
		   stu.add(stuupd);
		   stu.add(studel);
		   gra.add(graadd);
		   gra.add(graupd);
		   this.setJMenuBar(mbar);  //将菜单栏挂靠在框架上
		   questu.addActionListener(this);
		   quecou.addActionListener(this);
		   quegra.addActionListener(this);
		   stuadd.addActionListener(this);
		   stuupd.addActionListener(this);
		   studel.addActionListener(this);
		   graadd.addActionListener(this);
		   graupd.addActionListener(this);
		   refresh.addActionListener(this);
		   card=new CardLayout();
		   pCenter=new JPanel();
		   pCenter.setLayout(card);
		   pCenter.add("欢迎",new TeacherShow());
		   pCenter.add("学生查询",new TQueStu());
		   pCenter.add("课程查询",new TQueCou());
		   pCenter.add("成绩查询",new TQueGra());
		   pCenter.add("录入学生",new TStuAdd());
		   pCenter.add("修改学生",new TStuUpd());
		   pCenter.add("删除学生",new TStuDel());
		   pCenter.add("录入成绩",new TGraAdd());
		   pCenter.add("修改成绩",new TGraUpd());
		   this.add(pCenter,BorderLayout.CENTER);
		   validate();
		   this.setVisible(true);
		   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   this.addWindowListener(new WindowAdapter(){//关闭程序时的操作
		   public void windowClosing(WindowEvent e){System.exit(0);} });
		   validate();
	}
   public void actionPerformed(ActionEvent e)
   {
	   Object m = e.getSource(); 
		if( m == questu )        
		{ 
			card.show(pCenter,"学生查询");
		} 
		else if(m == quecou)
		{
			card.show(pCenter,"课程查询");
		}
		else if (m == quegra)
		{
			card.show(pCenter,"成绩查询");
		}
		else if (m == stuadd)
		{
			card.show(pCenter,"录入学生");
		}
		else if (m == stuupd)
		{
			card.show(pCenter,"修改学生");
		}else if (m == studel)
		{
			card.show(pCenter,"删除学生");
		}
		else if (m == graadd)
		{
			card.show(pCenter,"录入成绩");
		}
		else if (m == graupd)
		{
			card.show(pCenter,"修改成绩");
		}
		else if(m == refresh)
		{
			this.dispose(); 
			new Teacher();
		}
   }
}
