package Test;

import java.sql.*;
public class Practice{
	public static void main(String[] arg){
		PreparedStatement ps=null;
		Connection ct=null;
		ResultSet rs=null;
	try {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ct=DriverManager.getConnection
		("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=yang","sa","123456");
		ps=ct.prepareStatement("select * from sc");
		rs=ps.executeQuery();
		while(rs.next()){
			String s=rs.getString(2);//取得第几列的数
			System.out.println(s);
		}
		
	} catch (Exception e) {
		e.printStackTrace();
		  // TODO: handle exception
	}	
			
		
	}
}
