package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public class TStuUpd extends JPanel implements ActionListener {
	String save=null;
	JTextField ѧ��1,ѧ��,����,�Ա�,����,ϵ��;
	JButton �޸�,����;
	
public TStuUpd(){
	ѧ��1=new JTextField(12);
	ѧ��=new JTextField(12);
	����=new JTextField(12);
	�Ա�=new JTextField(12);
	����=new JTextField(12);
	ϵ��=new JTextField(12);
	�޸�=new JButton("�޸�");
	����=new JButton("����");
	����.setText("**-**-**");
	Box box1=Box.createHorizontalBox();//���box
	Box box2=Box.createHorizontalBox();
	Box box3=Box.createHorizontalBox();
	Box box4=Box.createHorizontalBox();
	Box box5=Box.createHorizontalBox();
	Box box6=Box.createHorizontalBox();
	Box box7=Box.createHorizontalBox();
	box1.add(new JLabel("ѧ��:",JLabel.CENTER));
	box1.add(ѧ��);
	box2.add(new JLabel("����:",JLabel.CENTER));
	box2.add(����);
	box3.add(new JLabel("�Ա�:"/*,JLabel.CENTER*/));
	box3.add(�Ա�);
	box4.add(new JLabel("����:"/*,JLabel.CENTER*/));
	box4.add(����);
	box5.add(new JLabel("ϵ��:",JLabel.CENTER));
	box5.add(ϵ��);
	box6.add(�޸�);
	box7.add(new JLabel("ѧ��:",JLabel.CENTER));
	box7.add(ѧ��1);
	box7.add(����);
	
	�޸�.addActionListener(this);
    ����.addActionListener(this);
	
	Box boxH=Box.createVerticalBox();//����box
	boxH.add(box1);
	boxH.add(box2);
	boxH.add(box3);
	boxH.add(box4);
	boxH.add(box5);
	boxH.add(box6);
	boxH.add(Box.createVerticalGlue());
	JPanel picPanel=new JPanel();
	JPanel messPanel=new JPanel();
	messPanel.add(box7);
	picPanel.add(boxH);
	setLayout(new BorderLayout());
	JSplitPane splitV=new JSplitPane(JSplitPane.VERTICAL_SPLIT,messPanel,picPanel);//�ָ�
	add(splitV,BorderLayout.CENTER);
	validate();
}

public void actionPerformed(ActionEvent e){
	Object obj=e.getSource();
	Statement stmt=null;
	ResultSet rs=null,rs1=null;
    String sql=null,sql1=null,sqlSC;
	
	if(obj==����)
	{if(ѧ��1.getText().equals(""))
		JOptionPane.showMessageDialog(this,"�޸�֮ǰ������д��ѯ��ѧ�ţ�" );
	 else{
	     
	    sql1="SELECT * FROM STUDENT WHERE SNO='"+ѧ��1.getText()+"'";
	    try{
	    Connection dbConn1=CONN();
		stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		rs1=stmt.executeQuery(sql1);
	    if(rs1.next()){ѧ��.setText(rs1.getString("SNO").trim());
	                   ����.setText(rs1.getString("SNAME").trim());
	                   �Ա�.setText(rs1.getString("SSEX").trim());
	                   ����.setText(rs1.getString("SDATE").trim());
	                   ϵ��.setText(rs1.getString("SDEPT").trim());
	                   save=ѧ��1.getText();	    	
	    }
	    else
	    {JOptionPane.showMessageDialog(this,"û�����ѧ�ŵ�ѧ��" );}
	    stmt.close();
	    rs1.close();
	    }
	    catch(SQLException e1){
			   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
		}
	    }
	}
	else{
	if(obj==�޸�)
	{if(save==null)  {JOptionPane.showMessageDialog(this,"��û������Ҫ�޸ĵ�ѧ��" );}
	 else{
		if(ѧ��.getText().equals("")||����.getText().equals("")){
			JOptionPane.showMessageDialog(this,"ѧ�ź��������������޸ģ�" );
		}
		else{sql="UPDATE STUDENT SET SNO='"+ѧ��.getText()+"',SNAME='"+����.getText()+"',SSEX='"+�Ա�.getText()+"',SDATE='"+����.getText()+"',SDEPT='"+ϵ��.getText()+"'"+"WHERE SNO='"+save+"'";
		if(save.trim().equals(ѧ��.getText().trim())){
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			stmt.executeUpdate(sql);
			save=null;
			JOptionPane.showMessageDialog(this,"�޸����" );
			ѧ��.setText("");
            ����.setText("");
            �Ա�.setText("");
            ����.setText("**-**-**");
            ϵ��.setText("");
			stmt.close();
		    }catch(SQLException e1){
				   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
	}
		else{sql1="SELECT * FROM STUDENT WHERE SNO='"+ѧ��.getText()+"'";
		try{
		    Connection dbConn1=CONN();
			stmt=(Statement)dbConn1.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs1=stmt.executeQuery(sql1);
		    if(rs1.next()){  	JOptionPane.showMessageDialog(this,"�Ѵ��ڴ�ѧ��ѧ��" );
		    }
		    else{
		    	sqlSC="UPDATE SC SET SNO='"+ѧ��.getText()+"' WHERE SNO='"+save+"'";
		    	stmt.executeUpdate(sql);
		    	stmt.executeUpdate(sqlSC);
		    	save=null;
			JOptionPane.showMessageDialog(null,"�޸����" );
			ѧ��.setText("");
            ����.setText("");
            �Ա�.setText("");
            ����.setText("**-**-**");
            ϵ��.setText("");}
		    stmt.close();
		    rs1.close();
		    }catch(SQLException e1){
				   System.out.print("SQL Exception occur.Message is:"+e1.getMessage());
			   }
		
		}
		
	}}}}
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