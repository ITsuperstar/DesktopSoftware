package Test;

import java.awt.*;
import java.sql.*;
import javax.swing.*;

public class StudentShow extends JPanel {
	private String name=null;
	private String sql=null;
	private PreparedStatement ps=null;
	Statement stmt=null;
	private	ResultSet rs=null;
	public StudentShow(String number)
	{   
		sql="SELECT SNAME FROM STUDENT WHERE SNO='"+number+"'";
		try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs=stmt.executeQuery(sql);
		if(rs.next()){
			name=rs.getString("SNAME");}
		}
		catch(SQLException e1){
			   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
		}
		JLabel label;
		label=new JLabel("��ӭ"+name+"ͬѧ��½�ӱ���ѧѡ��ϵͳ",JLabel.CENTER);
		label.setFont(new Font("����",Font.BOLD,22));
		label.setForeground(Color.red);
		this.add(label);
		ImageIcon ic=new ImageIcon("G:\\Myeclipse  Work\\Student System\\JPG\\12.jpg");
		ic=new ImageIcon(ic.getImage().getScaledInstance(400,280, ic.getImage().SCALE_DEFAULT));
		JLabel j1=new JLabel(ic);
		this.add(j1);
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
