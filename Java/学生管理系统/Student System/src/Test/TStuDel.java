package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class TStuDel extends JPanel  implements ActionListener{
	String save=null;
	JTextField 学号;
	JButton  删除;
		
	public TStuDel(){
	学号=new JTextField(12);
	删除=new JButton("删除");
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	box1.add(new JLabel("学号:",JLabel.CENTER));
	box1.add(学号);
	box2.add(删除);
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(Box.createVerticalGlue());
	删除.addActionListener(this);
    JPanel picPanel=new JPanel();
	picPanel.add(boxH);
	this.setLayout(new BorderLayout());
	this.add(picPanel,BorderLayout.CENTER);
	validate();
	}
	public void actionPerformed(ActionEvent e){
	   Object obj=e.getSource();
	   Statement stmt=null;
	   ResultSet rs=null;
	   String sql=null,sql1=null,sqlSC=null;
	   sql1="SELECT * FROM STUDENT WHERE SNO='"+学号.getText()+"'";
	   sql="DELETE FROM STUDENT WHERE SNO='"+学号.getText()+"'";
	   sqlSC="DELETE FROM SC WHERE SNO='"+学号.getText()+"'";
	   if(obj==删除){
		   try{
			    Connection dbConn1=CONN();
			    stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		    	rs=stmt.executeQuery(sql1);
			    if(rs.next())
			    {
			    	stmt.executeUpdate(sqlSC);
			    	stmt.executeUpdate(sql);
			    	JOptionPane.showMessageDialog(this,"删除完成" );	       
			    }
			    else 
			    {JOptionPane.showMessageDialog(this,"没有这个学号的学生" );}
		   }
		 catch(SQLException e1){
		   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
		   }
	 }
    }

	//连接数据库方法
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