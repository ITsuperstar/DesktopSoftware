package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class TQueStu extends JPanel implements ActionListener {
	JTextField 学号1,学号,姓名,性别,生日,系别;
	JButton 查找;
public TQueStu(){
	学号1=new JTextField(12);
	学号=new JTextField(12);
	姓名=new JTextField(12);
	性别=new JTextField(12);
	生日=new JTextField(12);
	系别=new JTextField(12);
	查找=new JButton("查找");
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	Box box6=Box.createHorizontalBox();
	box1.add(new JLabel("学号:",JLabel.CENTER));
	box1.add(学号);
	box2.add(new JLabel("姓名",JLabel.CENTER));
	box2.add(姓名);
	box3.add(new JLabel("性别:",JLabel.CENTER));
	box3.add(性别);
	box4.add(new JLabel("生日:",JLabel.CENTER));
	box4.add(生日);
	box5.add(new JLabel("系别:",JLabel.CENTER));
	box5.add(系别);
	box6.add(new JLabel("学号:",JLabel.CENTER));
	box6.add(学号1);
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
	ResultSet rs=null;
	String  sql=null;
	sql="SELECT * FROM STUDENT WHERE SNO='"+学号1.getText()+"'";
	if(obj==查找){
		if(学号1.getText().equals(""))
			JOptionPane.showMessageDialog(this,"请完整填写学号！" );
    	else{
	    try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs=stmt.executeQuery(sql);
		if(rs.next()){
			学号.setText(rs.getString(1).trim());
			姓名.setText(rs.getString(2).trim());
			性别.setText(rs.getString(3).trim());
			生日.setText(rs.getString(4).trim());
			系别.setText(rs.getString(5).trim());
			}
			else
			JOptionPane.showMessageDialog(this,"该学生不存在");
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
