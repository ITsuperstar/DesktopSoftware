package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
public class  Sel  extends JPanel implements ActionListener
{
	JTextField �κ�;
	JButton ѡ��;
	String number;
	public Sel(String number)
	{
		this.add(new Label("��ѡ�γ�"));
		this.number=number;
		JPanel J1=new Que1(number);
		this.add(J1);
		�κ�=new JTextField(6);
		ѡ��=new JButton("ѡ��");
		ѡ��.addActionListener(this);
		Box box1=Box.createHorizontalBox();//���box
		Box box4=Box.createHorizontalBox();
		box1.add(new JLabel("����κ�:"));
		box1.add(�κ�);
		box4.add(ѡ��);
		Box boxH=Box.createVerticalBox();//����box
		boxH.add(box1);
		boxH.add(box4);
		boxH.add(Box.createVerticalGlue());
		JPanel J2=new JPanel();
		J2.add(boxH);
	    this.add(J2);
		validate();
	}
	public void actionPerformed(ActionEvent c){
		Object obj=c.getSource();
		if(obj==ѡ��){
			Statement stmt=null;
			ResultSet rs=null,rs1=null,rsC=null,rsS=null;
			String sql,sqlC,sql1;
			    sqlC="SELECT * FROM COURSE WHERE CNO='"+�κ�.getText()+"'";
			    sql1="SELECT * FROM SC WHERE CNO='"+�κ�.getText()+"' AND SNO="+number+" ";
			    sql="INSERT INTO SC  VALUES("+number+",'"+�κ�.getText()+"',0)";
		   try{
			   Connection dbConn1=CONN();
				stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
				rsC=stmt.executeQuery(sqlC);
				if(rsC.next()){
				rs1=stmt.executeQuery(sql1);
				if(rs1.next()){JOptionPane.showMessageDialog(this,"��ѧ����ѡ�ÿγ̺ţ��޷����");}
				else{
				stmt.executeUpdate(sql);	
				JOptionPane.showMessageDialog(this,"��ӳɹ�");
				�κ�.setText("");
				}
				rs1.close();
				}
				else{JOptionPane.showMessageDialog(this,"�ÿγ̲����ڣ��޷����");}
				rsC.close();
				stmt.close();
		   }
		   catch(SQLException e){
			   System.out.print("SQL Exception occur.Message is:"+e.getMessage());
			   }
		   }
		
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
