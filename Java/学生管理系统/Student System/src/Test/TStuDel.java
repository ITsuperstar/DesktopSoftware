package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class TStuDel extends JPanel  implements ActionListener{
	String save=null;
	JTextField ѧ��;
	JButton  ɾ��;
		
	public TStuDel(){
	ѧ��=new JTextField(12);
	ɾ��=new JButton("ɾ��");
	Box box1=Box.createHorizontalBox();//���box
	Box box2=Box.createHorizontalBox();
	box1.add(new JLabel("ѧ��:",JLabel.CENTER));
	box1.add(ѧ��);
	box2.add(ɾ��);
	Box boxH=Box.createVerticalBox();//����box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(Box.createVerticalGlue());
	ɾ��.addActionListener(this);
    JPanel picPanel=new JPanel();
	picPanel.add(boxH);
	this.setLayout(new BorderLayout());
	this.add(picPanel,BorderLayout.CENTER);
	validate();
	}
	public void actionPerformed(ActionEvent e){
	   Object obj=e.getSource();
	   Statement stmt=null;
	   ResultSet rs=null;
	   String sql=null,sql1=null,sqlSC=null;
	   sql1="SELECT * FROM STUDENT WHERE SNO='"+ѧ��.getText()+"'";
	   sql="DELETE FROM STUDENT WHERE SNO='"+ѧ��.getText()+"'";
	   sqlSC="DELETE FROM SC WHERE SNO='"+ѧ��.getText()+"'";
	   if(obj==ɾ��){
		   try{
			    Connection dbConn1=CONN();
			    stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		    	rs=stmt.executeQuery(sql1);
			    if(rs.next())
			    {
			    	stmt.executeUpdate(sqlSC);
			    	stmt.executeUpdate(sql);
			    	JOptionPane.showMessageDialog(this,"ɾ�����" );	       
			    }
			    else 
			    {JOptionPane.showMessageDialog(this,"û�����ѧ�ŵ�ѧ��" );}
		   }
		 catch(SQLException e1){
		   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
		   }
	 }
    }

	//�������ݿⷽ��
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