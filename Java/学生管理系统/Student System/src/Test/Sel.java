package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
public class  Sel  extends JPanel implements ActionListener
{
	JTextField 课号;
	JButton 选课;
	String number;
	public Sel(String number)
	{
		this.add(new Label("可选课程"));
		this.number=number;
		JPanel J1=new Que1(number);
		this.add(J1);
		课号=new JTextField(6);
		选课=new JButton("选课");
		选课.addActionListener(this);
		Box box1=Box.createHorizontalBox();//横放box
		Box box4=Box.createHorizontalBox();
		box1.add(new JLabel("输入课号:"));
		box1.add(课号);
		box4.add(选课);
		Box boxH=Box.createVerticalBox();//竖放box
		boxH.add(box1);
		boxH.add(box4);
		boxH.add(Box.createVerticalGlue());
		JPanel J2=new JPanel();
		J2.add(boxH);
	    this.add(J2);
		validate();
	}
	public void actionPerformed(ActionEvent c){
		Object obj=c.getSource();
		if(obj==选课){
			Statement stmt=null;
			ResultSet rs=null,rs1=null,rsC=null,rsS=null;
			String sql,sqlC,sql1;
			    sqlC="SELECT * FROM COURSE WHERE CNO='"+课号.getText()+"'";
			    sql1="SELECT * FROM SC WHERE CNO='"+课号.getText()+"' AND SNO="+number+" ";
			    sql="INSERT INTO SC  VALUES("+number+",'"+课号.getText()+"',0)";
		   try{
			   Connection dbConn1=CONN();
				stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rsC=stmt.executeQuery(sqlC);
				if(rsC.next()){
				rs1=stmt.executeQuery(sql1);
				if(rs1.next()){JOptionPane.showMessageDialog(this,"该学生以选该课程号，无法添加");}
				else{
				stmt.executeUpdate(sql);	
				JOptionPane.showMessageDialog(this,"添加成功");
				课号.setText("");
				}
				rs1.close();
				}
				else{JOptionPane.showMessageDialog(this,"该课程不存在，无法添加");}
				rsC.close();
				stmt.close();
		   }
		   catch(SQLException e){
			   System.out.print("SQL Exception occur.Message is:"+e.getMessage());
			   }
		   }
		
	}
	public static Connection CONN(){
		   String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   //加载JDBC驱动
		   String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=选课系统";   //连接服务器和数据库test
		   String userName = "sa";   //默认用户名
		   String userPwd = "123456";   //密码
		   Connection dbConn=null;

		   try {
		   Class.forName(driverName);
		   dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
		   System.out.println("Connection Successful!");   //如果连接成功 控制台输出Connection Successful!
		   } catch (Exception e) {
		   e.printStackTrace();
		   }
		   return dbConn;
	}
}
