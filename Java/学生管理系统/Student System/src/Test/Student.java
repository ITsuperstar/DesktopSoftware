package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
public class Student extends JFrame implements ActionListener{
	private JMenuBar mbar;  
	private JMenu A,B,C,D;
	private JMenuItem  query,query1,select,delete,update,refresh;  //查询、选课、删课、改课
	private JPanel pCenter;
	private CardLayout card=null;
	private String number;
	public Student(String number)
	{
	   super("学生选课");    //创建框架对象
	   this.setBounds(200, 40,400,380);    //设置窗口的大小
	   this.number=number;
	   mbar = new JMenuBar();       //创建菜单栏	
	   A = new JMenu("查询课程");   //创建菜单
	   B = new JMenu("选择课程"); 
	   C = new JMenu("删除课程");
	   D = new JMenu("修改课程"); 
	   query = new JMenuItem("查询已选课程");   //创建菜单
	   query1 = new JMenuItem("查询可选课程"); 
	   select = new JMenuItem("选择课程"); 
	   delete = new JMenuItem("删除课程");
	   update = new JMenuItem("修改课程"); 
	   refresh =new JMenuItem("刷新");
	   refresh.setForeground(Color.red);
	   mbar.add(A);    //将菜单添加到菜单栏中
	   mbar.add(new Label(" "));
	   mbar.add(B);
	   mbar.add(new Label(" "));
	   mbar.add(C);
	  // mbar.add(D);
	   mbar.add(new Label(" "));
	   mbar.add(refresh);
	   A.add(query);    //将菜单添加到菜单栏中
	   A.add(query1); 
	   B.add(select);
	   C.add(delete);
	   //D.add(update);
	   this.setJMenuBar(mbar);  //将菜单栏挂靠在框架上
	   query.addActionListener(this);
	   query1.addActionListener(this);
	   select.addActionListener(this); 
	   delete.addActionListener(this);
	   // update.addActionListener(this);
	   refresh.addActionListener(this);
	   card=new CardLayout();
	   pCenter=new JPanel();
	   pCenter.setLayout(card);
	   pCenter.add("欢迎",new StudentShow(number));
	   pCenter.add("查询",new Que(number));
	   pCenter.add("查询1",new Que1(number));
	   pCenter.add("选课",new Sel(number));
	   pCenter.add("删课",new Del(number));
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
		if( m == query )        
		{ 
			card.show(pCenter,"查询");
		} 
		else if(m == query1)
		{
			card.show(pCenter,"查询1");
		}
		else if(m == select)
		{
			card.show(pCenter,"选课");
		}
		else if (m == delete)
		{
			card.show(pCenter,"删课");
		}
		else if(m == refresh)
		{
			this.dispose(); 
			new Student(this.number);
		}
	}
}
