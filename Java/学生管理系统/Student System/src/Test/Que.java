package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
public class Que extends JPanel
	{
		Vector rowData, columnNames;
		Statement stmt=null;
	    String sql=null;
		JTable jt = null;
		JScrollPane jsp = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		public Que(String number){
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

