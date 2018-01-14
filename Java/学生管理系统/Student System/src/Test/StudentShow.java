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
		label=new JLabel("欢迎"+name+"同学登陆河北大学选课系统",JLabel.CENTER);
		label.setFont(new Font("宋体",Font.BOLD,22));
		label.setForeground(Color.red);
		this.add(label);
		ImageIcon ic=new ImageIcon("G:\\Myeclipse  Work\\Student System\\JPG\\12.jpg");
		ic=new ImageIcon(ic.getImage().getScaledInstance(400,280, ic.getImage().SCALE_DEFAULT));
		JLabel j1=new JLabel(ic);
		this.add(j1);
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
