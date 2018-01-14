package Test;
 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GetConnectionSqlServer {
	public void getConnectionSqlServer() {

		String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String dbURL = "jdbc:sqlserver://localhost:1433;DatabaseName=yang"; // 1433是端口，"USCSecondhandMarketDB"是数据库名称
		String userName = "sa"; // 用户名
		String userPwd = "123456"; // 密码

		Connection dbConn = null;
		try {

			Class.forName(driverName).newInstance();
		} catch (Exception ex) {
			System.out.println("驱动加载失败");
			ex.printStackTrace();
		}
		try {
			dbConn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("成功连接数据库！");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

			try {
				if (dbConn != null)
					dbConn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		GetConnectionSqlServer getConn = new GetConnectionSqlServer();
		getConn.getConnectionSqlServer();

	}
}

