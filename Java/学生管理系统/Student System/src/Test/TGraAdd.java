package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class TGraAdd extends JPanel implements ActionListener{
	JTextField 课号,学号,成绩;
	JButton 录入;
	
public TGraAdd(){
	课号=new JTextField(12);
	学号=new JTextField(12);
	成绩=new JTextField(12);
	录入=new JButton("录入");
	录入.addActionListener(this);
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	box1.add(new JLabel("课号:"));
	box1.add(课号);
	box2.add(new JLabel("学号:"));
	box2.add(学号);
	box3.add(new JLabel("成绩:"));
	box3.add(成绩);
	box4.add(录入);
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box2);
	boxH.add(box1);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	JPanel messPanel=new JPanel();
	messPanel.add(boxH);
	this.setLayout(new BorderLayout());
	this.add(messPanel,BorderLayout.CENTER);
	validate();
}
public void actionPerformed(ActionEvent c){
	Object obj=c.getSource();
	if(obj==录入){
		if(课号.getText().equals("")||学号.getText().equals("")){
			JOptionPane.showMessageDialog(this,"填写课号和学号才能录入！" );
		}
		else
		{
		Statement stmt=null;
		ResultSet rs=null,rs1=null,rsC=null,rsS=null;
		String sql,sql1,sqlS,sqlC;
		    sqlC="SELECT * FROM COURSE WHERE CNO='"+课号.getText()+"'";
		    sqlS="SELECT * FROM STUDENT WHERE SNO='"+学号.getText()+"'";
		    sql1="SELECT * FROM SC WHERE CNO='"+课号.getText()+"' AND SNO='"+学号.getText()+"'";
		    sql="UPDATE SC SET GRADE='"+成绩.getText()+"' WHERE SNO='"+学号.getText()+"' AND CNO='"+课号.getText()+"'";
	   try{
		   Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rsC=stmt.executeQuery(sqlC);
			if(rsC.next()){
				rsS=stmt.executeQuery(sqlS);
				if(rsS.next()){
			rs1=stmt.executeQuery(sql1);
			if(rs1.next()){
				stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(this,"添加成功");}
			else{
				JOptionPane.showMessageDialog(this,"该学生没有选该课程号，无法录入成绩");
			}
			rs1.close();
			}
				else{JOptionPane.showMessageDialog(this,"该学生不存在，无法添加");}
				rsS.close();
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