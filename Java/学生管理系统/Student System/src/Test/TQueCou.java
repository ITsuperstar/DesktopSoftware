package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

import javax.swing.*;

public class TQueCou extends JPanel implements ActionListener {
	JTextField �κ�1,�κ�,����,ѧ��,��ʦ,����;
	JButton ����;
public TQueCou(){
	�κ�1=new JTextField(12);
	�κ�=new JTextField(12);
	����=new JTextField(12);
	ѧ��=new JTextField(12);
	��ʦ=new JTextField(12);
	����=new JTextField(12);
	����=new JButton("����");
	Box box1=Box.createHorizontalBox();//���box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	Box box6=Box.createHorizontalBox();
	box1.add(new JLabel("�κ�:",JLabel.CENTER));
	box1.add(�κ�);
	box2.add(new JLabel("����",JLabel.CENTER));
	box2.add(����);
	box3.add(new JLabel("ѧ��:",JLabel.CENTER));
	box3.add(ѧ��);
	box4.add(new JLabel("��ʦ:",JLabel.CENTER));
	box4.add(��ʦ);
	box5.add(new JLabel("����:",JLabel.CENTER));
	box5.add(����);
	box6.add(new JLabel("�κ�:",JLabel.CENTER));
	box6.add(�κ�1);
	box6.add(����);
	Box boxH=Box.createVerticalBox();//����box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(box5);
	boxH.add(Box.createVerticalGlue());
    ����.addActionListener(this);
	JPanel picPanel=new JPanel();
	JPanel messPanel=new JPanel();
	messPanel.add(box6);
	picPanel.add(boxH);
	this.setLayout(new BorderLayout());
	JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//�ָ�
	this.add(splitV,BorderLayout.CENTER);
	validate();
}
public void actionPerformed(ActionEvent e){
	Object obj=e.getSource();
	Statement stmt=null;
	ResultSet rsC=null,rs=null;
	String sqlC=null,sql=null;
	sqlC="SELECT COURSE.CNO,COURSE.CNAME,COURSE.CCREDIT,TEACHER.TNAME FROM COURSE,TEACHER WHERE COURSE.CNO='"+�κ�1.getText()+"' AND COURSE.TNO=TEACHER.TNO ";
	sql="SELECT COUNT(*) FROM SC WHERE CNO='"+�κ�1.getText()+"'";
	if(obj==����){
		if(�κ�1.getText().equals(""))
			JOptionPane.showMessageDialog(this,"��������д�γ̺ţ�" );
    	else{
	    try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rsC=stmt.executeQuery(sqlC);
		if(rsC.next()){
			�κ�.setText(rsC.getString(1).trim());
			����.setText(rsC.getString(2).trim());
			ѧ��.setText(rsC.getString(3).trim());
			��ʦ.setText(rsC.getString(4).trim());
			rs=stmt.executeQuery(sql);
			if(rs.next())
			����.setText(rs.getString(1).trim());
			}
			else
			JOptionPane.showMessageDialog(this,"�ÿγ̲�����");
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
