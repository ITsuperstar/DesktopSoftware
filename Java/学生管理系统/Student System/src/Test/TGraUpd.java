package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class TGraUpd extends JPanel implements ActionListener {
	JTextField 课号,学号,成绩;
	JButton 修改,查找;
public TGraUpd(){
	学号=new JTextField(12);
	课号=new JTextField(6);
	成绩=new JTextField(12);
	修改=new JButton("修改");
	查找=new JButton("查找");
	Box box3=Box.createHorizontalBox();//横放box
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	box3.add(new JLabel("成绩:",JLabel.CENTER));
	box3.add(成绩);
	box4.add(修改);
	box5.add(new JLabel("学号:",JLabel.CENTER));
	box5.add(学号);
	box5.add(new JLabel("课号:",JLabel.CENTER));
	box5.add(课号);
	box5.add(查找);
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	修改.addActionListener(this);
    查找.addActionListener(this);
	JPanel picPanel=new JPanel();
	JPanel messPanel=new JPanel();
	messPanel.add(box5);
	picPanel.add(boxH);
	setLayout(new BorderLayout());
	JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//分割
	add(splitV,BorderLayout.CENTER);
	validate();
}
public void actionPerformed(ActionEvent e){
	Object obj=e.getSource();
	Statement stmt=null;
	ResultSet rs=null,rs1=null,rsC=null,rsS=null;
	String sql,sql1,sqlS,sqlC;
	sqlS="SELECT * FROM STUDENT WHERE SNO='"+学号.getText()+"'";
	sqlC="SELECT * FROM COURSE WHERE CNO='"+课号.getText()+"'";
    sql1="SELECT * FROM SC WHERE CNO='"+课号.getText()+"' AND SNO='"+学号.getText()+"'";
    sql="UPDATE SC SET GRADE='"+成绩.getText()+"' WHERE CNO='"+课号.getText()+"' AND SNO='"+学号.getText()+"'";
	if(obj==查找){
		if(课号.getText().equals("")||学号.getText().equals(""))
			JOptionPane.showMessageDialog(this,"请完整填写学号和课程号！" );
    	else{
	    try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rsS=stmt.executeQuery(sqlS);
		if(rsS.next()){
			rsC=stmt.executeQuery(sqlC);
			if(rsC.next()){
			   rs1=stmt.executeQuery(sql1);
			   if(rs1.next())
			        成绩.setText(rs1.getString("GRADE").trim());
			   else
			   JOptionPane.showMessageDialog(this,"该学生没有选修该课程，请您重新确认！");
			}
			else
			JOptionPane.showMessageDialog(this,"该课程不存在，无法修改！");
		   }
		  else
		   JOptionPane.showMessageDialog(this,"该学生不存在，无法修改！");
		}
		catch(SQLException e1){
			   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
		   }
	    }
    	}
		
    else if(obj==修改){		
    try{
	   Connection dbConn1=CONN();
	   stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	   stmt.executeUpdate(sql);	
	   JOptionPane.showMessageDialog(this,"修改成功！");
    }
   catch(SQLException e1){
	   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
	}
	}
}


//	连接数据库方法
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