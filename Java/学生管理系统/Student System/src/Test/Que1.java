package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
public class Que1 extends JPanel
	{
		Vector rowData, columnNames;
		Statement stmt=null;
	    String sql=null;
		JTable jt = null;
		JScrollPane jsp = null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		public Que1(String number){
			columnNames = new Vector();
			// ��������
			columnNames.add("�κ�");
			columnNames.add("�γ���");
			columnNames.add("ѧ��");
			columnNames.add("��ʦ");
			rowData=new Vector();
			sql="SELECT CNO,CNAME,CCREDIT,TNAME  FROM TEACHER,COURSE  WHERE  COURSE.TNO=TEACHER.TNO AND CNO NOT IN(SELECT CNO FROM SC WHERE SNO='"+ number+"')";
			try{
			    Connection dbConn1=CONN();
				stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rs=stmt.executeQuery(sql);
				
				while(rs.next()){
					Vector hang = new Vector();
					hang.add(rs.getString("CNO"));
					hang.add(rs.getString("CNAME"));
					hang.add(rs.getString("CCREDIT"));
					hang.add(rs.getString("TNAME"));
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
			   String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   //����JDBC����
			   String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=ѡ��ϵͳ";   //���ӷ����������ݿ�test
			   String userName = "sa";   //Ĭ���û���
			   String userPwd = "123456";   //����
			   Connection dbConn=null;

			   try {
			   Class.forName(driverName);
			   dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			   System.out.println("Connection Successful!");   //������ӳɹ� ����̨���Connection Successful!
			   } catch (Exception e) {
			   e.printStackTrace();
			   }
			   return dbConn;
		}
	}

