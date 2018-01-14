package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class TStuUpd extends JPanel implements ActionListener {
	String save=null;
	JTextField 学号1,学号,姓名,性别,生日,系别;
	JButton 修改,查找;
	
public TStuUpd(){
	学号1=new JTextField(12);
	学号=new JTextField(12);
	姓名=new JTextField(12);
	性别=new JTextField(12);
	生日=new JTextField(12);
	系别=new JTextField(12);
	修改=new JButton("修改");
	查找=new JButton("查找");
	生日.setText("**-**-**");
	Box box1=Box.createHorizontalBox();//横放box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	Box box6=Box.createHorizontalBox();
	Box box7=Box.createHorizontalBox();
	box1.add(new JLabel("学号:",JLabel.CENTER));
	box1.add(学号);
	box2.add(new JLabel("姓名:",JLabel.CENTER));
	box2.add(姓名);
	box3.add(new JLabel("性别:"/*,JLabel.CENTER*/));
	box3.add(性别);
	box4.add(new JLabel("生日:"/*,JLabel.CENTER*/));
	box4.add(生日);
	box5.add(new JLabel("系别:",JLabel.CENTER));
	box5.add(系别);
	box6.add(修改);
	box7.add(new JLabel("学号:",JLabel.CENTER));
	box7.add(学号1);
	box7.add(查找);
	
	修改.addActionListener(this);
    查找.addActionListener(this);
	
	Box boxH=Box.createVerticalBox();//竖放box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(box5);
	boxH.add(box6);
	boxH.add(Box.createVerticalGlue());
	JPanel picPanel=new JPanel();
	JPanel messPanel=new JPanel();
	messPanel.add(box7);
	picPanel.add(boxH);
	setLayout(new BorderLayout());
	JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//分割
	add(splitV,BorderLayout.CENTER);
	validate();
}

public void actionPerformed(ActionEvent e){
	Object obj=e.getSource();
	Statement stmt=null;
	ResultSet rs=null,rs1=null;
    String sql=null,sql1=null,sqlSC;
	
	if(obj==查找)
	{if(学号1.getText().equals(""))
		JOptionPane.showMessageDialog(this,"修改之前，请填写查询的学号！" );
	 else{
	     
	    sql1="SELECT * FROM STUDENT WHERE SNO='"+学号1.getText()+"'";
	    try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs1=stmt.executeQuery(sql1);
	    if(rs1.next()){学号.setText(rs1.getString("SNO").trim());
	                   姓名.setText(rs1.getString("SNAME").trim());
	                   性别.setText(rs1.getString("SSEX").trim());
	                   生日.setText(rs1.getString("SDATE").trim());
	                   系别.setText(rs1.getString("SDEPT").trim());
	                   save=学号1.getText();	    	
	    }
	    else
	    {JOptionPane.showMessageDialog(this,"没有这个学号的学生" );}
	    stmt.close();
	    rs1.close();
	    }
	    catch(SQLException e1){
			   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
		}
	    }
	}
	else{
	if(obj==修改)
	{if(save==null)  {JOptionPane.showMessageDialog(this,"还没查找需要修改的学生" );}
	 else{
		if(学号.getText().equals("")||姓名.getText().equals("")){
			JOptionPane.showMessageDialog(this,"学号和姓名填满才能修改！" );
		}
		else{sql="UPDATE STUDENT SET SNO='"+学号.getText()+"',SNAME='"+姓名.getText()+"',SSEX='"+性别.getText()+"',SDATE='"+生日.getText()+"',SDEPT='"+系别.getText()+"'"+"WHERE SNO='"+save+"'";
		if(save.trim().equals(学号.getText().trim())){
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			save=null;
			JOptionPane.showMessageDialog(this,"修改完成" );
			学号.setText("");
            姓名.setText("");
            性别.setText("");
            生日.setText("**-**-**");
            系别.setText("");
			stmt.close();
		    }catch(SQLException e1){
				   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
	}
		else{sql1="SELECT * FROM STUDENT WHERE SNO='"+学号.getText()+"'";
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1=stmt.executeQuery(sql1);
		    if(rs1.next()){  	JOptionPane.showMessageDialog(this,"已存在此学号学生" );
		    }
		    else{
		    	sqlSC="UPDATE SC SET SNO='"+学号.getText()+"' WHERE SNO='"+save+"'";
		    	stmt.executeUpdate(sql);
		    	stmt.executeUpdate(sqlSC);
		    	save=null;
			JOptionPane.showMessageDialog(null,"修改完成" );
			学号.setText("");
            姓名.setText("");
            性别.setText("");
            生日.setText("**-**-**");
            系别.setText("");}
		    stmt.close();
		    rs1.close();
		    }catch(SQLException e1){
				   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
		
		}
		
	}}}}
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