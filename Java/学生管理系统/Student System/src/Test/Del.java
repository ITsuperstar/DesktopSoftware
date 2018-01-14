package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
public class Del extends JPanel implements ActionListener
{
	String saveC=null;
	String saveS=null;
	JTextField 课号;
	JButton  删除;
	String number;
	public Del(String number)
	{
		this.number=number;
		this.add(new Label("已选课程"));
		JPanel J1=new Que(number);
		this.add(J1);
		课号=new JTextField(6);
		删除=new JButton("删除");
		Box box1=Box.createHorizontalBox();//横放box
		Box box4=Box.createHorizontalBox();
		box1.add(new JLabel("课号:",JLabel.CENTER));
		box1.add(课号);
		box4.add(删除);
		Box boxH=Box.createVerticalBox();//竖放box
		boxH.add(box1);
		boxH.add(box4);
		boxH.add(Box.createVerticalGlue());
		删除.addActionListener(this);
		JPanel J2=new JPanel();
		J2.add(boxH);
		this.add(J2);
		validate();	
	}
	public void actionPerformed(ActionEvent e){
		Object obj=e.getSource();
		Statement stmt=null;
		ResultSet rs=null,rs1=null;
	    String sql=null,sql1=null;
	    sql1="SELECT * FROM SC WHERE CNO='"+(课号.getText())+"'  AND SNO='"+number+"' ";
	    sql="DELETE FROM SC WHERE CNO='"+(课号.getText())+"'  AND SNO='"+number+"'";
	    if(obj==删除)
	    {
	      if(课号.getText().equals("")) 
	    	   JOptionPane.showMessageDialog(this,"请填写需要删除的课程号！" );	
          else{
		    try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1=stmt.executeQuery(sql1);
			if(rs1.next()){
			    stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(this,"删除完成" );
				课号.setText("");
			}
			else
			    JOptionPane.showMessageDialog(this,"您没有选修该课程，请您重新填写！" );
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
