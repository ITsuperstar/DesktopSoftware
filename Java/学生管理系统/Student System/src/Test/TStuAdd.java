package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class TStuAdd extends JPanel implements ActionListener{
	JTextField ѧ��,����,�Ա�,����,ϵ��;
	JButton ¼��;
	
public TStuAdd(){
	ѧ��=new JTextField(12);
	����=new JTextField(12);
	�Ա�=new JTextField(12);
	����=new JTextField(12);
	ϵ��=new JTextField(12);
	¼��=new JButton("¼��");
	¼��.addActionListener(this);
	����.setText("**-**-**");
	Box box1=Box.createHorizontalBox();//���box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	Box box6=Box.createHorizontalBox();
	box1.add(new JLabel("ѧ��:"/*,JLabel.CENTER*/));
	box1.add(ѧ��);
	box2.add(new JLabel("����:"/*,JLabel.CENTER*/));
	box2.add(����);
	box3.add(new JLabel("�Ա�:"/*,JLabel.CENTER*/));
	box3.add(�Ա�);
	box4.add(new JLabel("����:"/*,JLabel.CENTER*/));
	box4.add(����);
	box5.add(new JLabel("ϵ��:"/*,JLabel.CENTER*/));
	box5.add(ϵ��);
	box6.add(¼��);
	Box boxH=Box.createVerticalBox();//����box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(box5);
	boxH.add(box6);
	boxH.add(Box.createVerticalGlue());
	JPanel messPanel=new JPanel();
	messPanel.add(boxH);
	this.setLayout(new BorderLayout());
	this.add(messPanel,BorderLayout.CENTER);
	validate();
	}
public void actionPerformed(ActionEvent c){
	Object obj=c.getSource();
	if(obj==¼��){
		if(ѧ��.getText().equals("")||����.getText().equals("")||�Ա�.getText().equals("")||����.getText().equals("")||ϵ��.getText().equals("")){
			JOptionPane.showMessageDialog(this,"ѧ����Ϣ��������¼�룡" );
		}
		Statement stmt=null;
		ResultSet rs1=null;
		String sql,sql1;
		    sql1="SELECT * FROM STUDENT WHERE SNO='"+ѧ��.getText()+"'";
		    sql="INSERT INTO STUDENT VALUES('"+ѧ��.getText()+"','"+����.getText()+"','"+�Ա�.getText()+"','"+����.getText()+"','"+ϵ��.getText()+"')";
	   try{
		   Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1=stmt.executeQuery(sql1);
			if(rs1.next()){JOptionPane.showMessageDialog(this,"��ѧ���Դ��ڣ��޷����");}
			else{
			stmt.executeUpdate(sql);	
			JOptionPane.showMessageDialog(this,"��ӳɹ�");
			}		
			rs1.close();
			stmt.close();
	   }
	   catch(SQLException e){
		   System.out.print("SQL Exception occur.Message is:"+e.getMessage());
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
