package study;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;


public class User extends JFrame{
private JLabel use,password;
private JTextField k1;//�û��������
private JPasswordField k2;//���������
private JButton b1,b2;

//��¼����
public User(JFrame f){
	super("ϵͳ��¼");
	Container c=getContentPane();
	c.setLayout(new FlowLayout());
	use=new JLabel("username:");
	use.setFont(new Font("Serif",Font.PLAIN,20));
	password=new JLabel("password:");
	password.setFont(new Font("Serif",Font.PLAIN,20));
	k1=new JTextField(12);
	k2=new JPasswordField(12);
	b1=new JButton("��¼");
	b2=new JButton("�˳�");

	//	���õ�¼����
	BHandler b=new BHandler();
	EXIT d=new EXIT();
	b1.addActionListener(b);
	b2.addActionListener(d);
	
		//��ӿؼ�
	c.add(use);
	c.add(k1);
	c.add(password);
	c.add(k2);
	c.add(b1);
	c.add(b2);
	
	setBounds(600,300,250,150);
	setVisible(true);
	setResizable(false);
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
//������
public static void main(String[] args) {
	User f1=new User(new JFrame());
	}
//��¼��ť����
private class BHandler implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(k1.getText().equals("")||k2.getText().equals("")){
				JOptionPane.showMessageDialog(User.this,"�û������벻��Ϊ�գ�" );
			}
			else{
				Statement stmt=null;
				ResultSet rs=null;
				String sql;
  			    sql="select * from admin where username='"+k1.getText()+"'";
			   try{
				   Connection dbConn1=CONN();
					stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
					rs=stmt.executeQuery(sql);					
					if(rs.next()){
						String xm=rs.getString("password");
						if(k2.getText().equals(xm.trim())){JOptionPane.showMessageDialog(User.this,"��¼�ɹ�");
						dispose();
							new Menu();//������																
							}
						else{JOptionPane.showMessageDialog(User.this,"�������");}
					}
					else{JOptionPane.showMessageDialog(User.this,"�û�������");}
					rs.close();
					stmt.close();
			   }
			   catch(SQLException e){
				   JOptionPane.showMessageDialog(User.this,"SQL Exception occur.Message is:"+e.getMessage());
				   }
			}			
	}
	}
//�˳���������
private class EXIT implements ActionListener{
	public void actionPerformed(ActionEvent even){
		System.exit(0);
	}
}

//�������ݿⷽ��
public static Connection CONN(){
	   String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";   //����JDBC����
	   String dbURL = "jdbc:sqlserver://localhost:1433; DatabaseName=student";   //���ӷ����������ݿ�test
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
}//�������
