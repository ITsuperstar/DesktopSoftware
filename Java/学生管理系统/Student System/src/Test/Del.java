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
	JTextField �κ�;
	JButton  ɾ��;
	String number;
	public Del(String number)
	{
		this.number=number;
		this.add(new Label("��ѡ�γ�"));
		JPanel J1=new Que(number);
		this.add(J1);
		�κ�=new JTextField(6);
		ɾ��=new JButton("ɾ��");
		Box box1=Box.createHorizontalBox();//���box
		Box box4=Box.createHorizontalBox();
		box1.add(new JLabel("�κ�:",JLabel.CENTER));
		box1.add(�κ�);
		box4.add(ɾ��);
		Box boxH=Box.createVerticalBox();//����box
		boxH.add(box1);
		boxH.add(box4);
		boxH.add(Box.createVerticalGlue());
		ɾ��.addActionListener(this);
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
	    sql1="SELECT * FROM SC WHERE CNO='"+(�κ�.getText())+"'  AND SNO='"+number+"' ";
	    sql="DELETE FROM SC WHERE CNO='"+(�κ�.getText())+"'  AND SNO='"+number+"'";
	    if(obj==ɾ��)
	    {
	      if(�κ�.getText().equals("")) 
	    	   JOptionPane.showMessageDialog(this,"����д��Ҫɾ���Ŀγ̺ţ�" );	
          else{
		    try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1=stmt.executeQuery(sql1);
			if(rs1.next()){
			    stmt.executeUpdate(sql);
				JOptionPane.showMessageDialog(this,"ɾ�����" );
				�κ�.setText("");
			}
			else
			    JOptionPane.showMessageDialog(this,"��û��ѡ�޸ÿγ̣�����������д��" );
		    }
			catch(SQLException e1){
				   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
		  }
	    }
	 }
//	�������ݿⷽ��
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
