package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;

public class TQueGra extends JPanel implements ActionListener{
	JTextField 学号1;
	JButton 查找;
	JPanel messPanel,picPanel;
public TQueGra(){
	学号1=new JTextField(12);
	查找=new JButton("查找");
	Box box6=Box.createHorizontalBox();
	box6.add(new JLabel("学号:",JLabel.CENTER));
	box6.add(学号1);
	box6.add(查找);
    查找.addActionListener(this);
	picPanel=new JPanel();
	messPanel=new JPanel();
	messPanel.add(box6);
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
			picPanel.add(new result(学号1.getText()));
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
   class result extends JPanel
   {
	Vector rowData, columnNames;
	Statement stmt=null;
    String sql=null;
	JTable jt = null;
	JScrollPane jsp = null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	public result(String number){
		columnNames = new Vector();
		// 设置列名
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("课号");
		columnNames.add("课名");
		columnNames.add("成绩");
		rowData=new Vector();
		sql="SELECT SC.CNO,SC.SNO,SC.GRADE,SNAME,CNAME  FROM STUDENT,COURSE,SC  WHERE SC.SNO=STUDENT.SNO AND SC.CNO=COURSE.CNO AND STUDENT.SNO ="+number+"";
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs=stmt.executeQuery(sql);
			
			while(rs.next()){
				Vector hang = new Vector();
				hang.add(rs.getString("SNO"));System.out.print(rs.getString("SNO"));
				hang.add(rs.getString("SNAME"));System.out.print(rs.getString("SNAME"));
				hang.add(rs.getString("CNO"));System.out.print(rs.getString("CNO"));
				hang.add(rs.getString("CNAME"));System.out.print(rs.getString("CNAME"));
				hang.add(rs.getString("GRADE"));System.out.println(rs.getString("GRADE"));
				rowData.add(hang);
				}
				jt=new JTable(rowData,columnNames);
				jsp=new JScrollPane(jt);
				jsp.setPreferredSize(new Dimension(380,200));
				this.add(jsp);
		}
		catch(SQLException e1){
				   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			  
		}
		validate();
		}}
   
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
