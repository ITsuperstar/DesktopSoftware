package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class TGraUpd extends JPanel implements ActionListener {
	JTextField �κ�,ѧ��,�ɼ�;
	JButton �޸�,����;
public TGraUpd(){
	ѧ��=new JTextField(12);
	�κ�=new JTextField(6);
	�ɼ�=new JTextField(12);
	�޸�=new JButton("�޸�");
	����=new JButton("����");
	Box box3=Box.createHorizontalBox();//���box
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	box3.add(new JLabel("�ɼ�:",JLabel.CENTER));
	box3.add(�ɼ�);
	box4.add(�޸�);
	box5.add(new JLabel("ѧ��:",JLabel.CENTER));
	box5.add(ѧ��);
	box5.add(new JLabel("�κ�:",JLabel.CENTER));
	box5.add(�κ�);
	box5.add(����);
	Box boxH=Box.createVerticalBox();//����box
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(Box.createVerticalGlue());
	�޸�.addActionListener(this);
    ����.addActionListener(this);
	JPanel picPanel=new JPanel();
	JPanel messPanel=new JPanel();
	messPanel.add(box5);
	picPanel.add(boxH);
	setLayout(new BorderLayout());
	JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//�ָ�
	add(splitV,BorderLayout.CENTER);
	validate();
}
public void actionPerformed(ActionEvent e){
	Object obj=e.getSource();
	Statement stmt=null;
	ResultSet rs=null,rs1=null,rsC=null,rsS=null;
	String sql,sql1,sqlS,sqlC;
	sqlS="SELECT * FROM STUDENT WHERE SNO='"+ѧ��.getText()+"'";
	sqlC="SELECT * FROM COURSE WHERE CNO='"+�κ�.getText()+"'";
    sql1="SELECT * FROM SC WHERE CNO='"+�κ�.getText()+"' AND SNO='"+ѧ��.getText()+"'";
    sql="UPDATE SC SET GRADE='"+�ɼ�.getText()+"' WHERE CNO='"+�κ�.getText()+"' AND SNO='"+ѧ��.getText()+"'";
	if(obj==����){
		if(�κ�.getText().equals("")||ѧ��.getText().equals(""))
			JOptionPane.showMessageDialog(this,"��������дѧ�źͿγ̺ţ�" );
    	else{
	    try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rsS=stmt.executeQuery(sqlS);
		if(rsS.next()){
			rsC=stmt.executeQuery(sqlC);
			if(rsC.next()){
			   rs1=stmt.executeQuery(sql1);
			   if(rs1.next())
			        �ɼ�.setText(rs1.getString("GRADE").trim());
			   else
			   JOptionPane.showMessageDialog(this,"��ѧ��û��ѡ�޸ÿγ̣���������ȷ�ϣ�");
			}
			else
			JOptionPane.showMessageDialog(this,"�ÿγ̲����ڣ��޷��޸ģ�");
		   }
		  else
		   JOptionPane.showMessageDialog(this,"��ѧ�������ڣ��޷��޸ģ�");
		}
		catch(SQLException e1){
			   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
		   }
	    }
    	}
		
    else if(obj==�޸�){		
    try{
	   Connection dbConn1=CONN();
	   stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	   stmt.executeUpdate(sql);	
	   JOptionPane.showMessageDialog(this,"�޸ĳɹ���");
    }
   catch(SQLException e1){
	   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
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