package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;

public class TQueCou extends JPanel implements ActionListener {
	JTextField 课号1,课号,课名,学分,老师,人数;
	JButton 查找;
public TQueCou(){
	课号1=new JTextField(12);
	课号=new JTextField(12);
	课名=new JTextField(12);
	学分=new JTextField(12);
	老师=new JTextField(12);
	人数=new JTextField(12);
	查找=new JButton("查找");
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	Box box6=Box.createHorizontalBox();
	box1.add(new JLabel("课号:",JLabel.CENTER));
	box1.add(课号);
	box2.add(new JLabel("课名",JLabel.CENTER));
	box2.add(课名);
	box3.add(new JLabel("学分:",JLabel.CENTER));
	box3.add(学分);
	box4.add(new JLabel("老师:",JLabel.CENTER));
	box4.add(老师);
	box5.add(new JLabel("人数:",JLabel.CENTER));
	box5.add(人数);
	box6.add(new JLabel("课号:",JLabel.CENTER));
	box6.add(课号1);
	box6.add(查找);
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(box5);
	boxH.add(Box.createVerticalGlue());
    查找.addActionListener(this);
	JPanel picPanel=new JPanel();
	JPanel messPanel=new JPanel();
	messPanel.add(box6);
	picPanel.add(boxH);
	this.setLayout(new BorderLayout());
	JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//分割
	this.add(splitV,BorderLayout.CENTER);
	validate();
}
public void actionPerformed(ActionEvent e){
	Object obj=e.getSource();
	Statement stmt=null;
	ResultSet rsC=null,rs=null;
	String sqlC=null,sql=null;
	sqlC="SELECT COURSE.CNO,COURSE.CNAME,COURSE.CCREDIT,TEACHER.TNAME FROM COURSE,TEACHER WHERE COURSE.CNO='"+课号1.getText()+"' AND COURSE.TNO=TEACHER.TNO ";
	sql="SELECT COUNT(*) FROM SC WHERE CNO='"+课号1.getText()+"'";
	if(obj==查找){
		if(课号1.getText().equals(""))
			JOptionPane.showMessageDialog(this,"请完整填写课程号！" );
    	else{
	    try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rsC=stmt.executeQuery(sqlC);
		if(rsC.next()){
			课号.setText(rsC.getString(1).trim());
			课名.setText(rsC.getString(2).trim());
			学分.setText(rsC.getString(3).trim());
			老师.setText(rsC.getString(4).trim());
			rs=stmt.executeQuery(sql);
			if(rs.next())
			人数.setText(rs.getString(1).trim());
			}
			else
			JOptionPane.showMessageDialog(this,"该课程不存在");
		   }
		catch(SQLException e1){
			   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
		   }
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
