package Test;

import java.sql.*;
import java.util.*;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
public class Login extends JFrame implements ActionListener {
	private TextField number,password;
    private Button bustudent,buteacher;
    PreparedStatement ps=null;
	Connection ct=null;
	ResultSet rs=null;
    public Login()
   {
       super("选课系统登录");
       this.setBounds(300, 240,250, 150);
       this.setBackground(Color.lightGray);
       this.setLayout(new BorderLayout());
       
      /* ImageIcon ic=new ImageIcon("G:\\Myeclipse  Work\\Student System\\JPG\\13.jpg");
	   ic=new ImageIcon(ic.getImage().getScaledInstance(250,200, ic.getImage().SCALE_DEFAULT));
	   JLabel j1=new JLabel(ic);
	   this.getLayeredPane().add(j1, new Integer(Integer.MIN_VALUE));
	   j1.setBounds(0, 0, ic.getIconWidth(),ic.getIconHeight());
	   Container cp = getContentPane();
	   cp.setLayout(null);
	   ((JPanel) cp).setOpaque(false);*/
	   
       Box box1=Box.createHorizontalBox();//横放box
       Box box2=Box.createHorizontalBox();
       Box box3=Box.createHorizontalBox();
       Box box4=Box.createHorizontalBox();
       JPanel panel=new JPanel();
       // panel.setOpaque(false);
       this.getContentPane().add(panel,"Center");
       box1.add(new Label("账号"));
       number=new  TextField((20));
       box1.add(number);
       box2.add(new Label("密码"));
       password=new  TextField((20));
       password.setEchoChar('*');     //实现密文的输入
       box2.add(password);
       panel.add(box1);
       panel.add(box2);
       JPanel panel2=new JPanel();
      // panel2.setOpaque(false);
       this.getContentPane().add(panel2,"South");
       bustudent=new Button("学生登录 ");
       buteacher=new Button("老师登录 ");
       panel2.add(bustudent);
       panel2.add(new Label(" "));
       panel2.add(buteacher);
       bustudent.addActionListener(this);
       buteacher.addActionListener(this);
       this.addWindowListener(new WinClose());
       this.setVisible(true);
   }
   public void actionPerformed(ActionEvent ev){
    if(ev.getSource()==bustudent){
	  String str=number.getText();
	  String str1=password.getText();
	  if(str.equals("")||str1.equals(""))
			JOptionPane.showMessageDialog(this,"请完整填写账号和密码！" );
	  else{
	  try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ct=DriverManager.getConnection
			("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=选课系统","sa","123456");
			ps=ct.prepareStatement("SELECT *  FROM STUADMIN  WHERE ID='学生' AND NUMBER='"+str+"'");
			rs=ps.executeQuery();
			if(rs.next())
			{
				if(str1.equals(rs.getString("PASSWORD")))
				{
				  JOptionPane.showMessageDialog(this, "登录成功!");
				  this.dispose();
				  new Student(str);
				}
				if(!str1.equals(rs.getString("PASSWORD")))
				{
				  JOptionPane.showMessageDialog(this, "您的密码有误，请重新输入！");
				}				
			}
			else
			{
		      JOptionPane.showMessageDialog(this, "您的帐号不存在，请重新输入!", "警告",JOptionPane.WARNING_MESSAGE);
		    }
		}
     catch (Exception e) {
			e.printStackTrace();
		}
	  }
	
   }
    
    if(ev.getSource()==buteacher){
  	  String str=number.getText();
  	  String str1=password.getText();
  	if(str.equals("")||str1.equals(""))
		JOptionPane.showMessageDialog(this,"请完整填写账号和密码！" );
  	else{
  	 try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			ct=DriverManager.getConnection
			("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=选课系统","sa","123456");
			ps=ct.prepareStatement("SELECT * FROM STUADMIN WHERE ID='老师' AND NUMBER='"+str+"'");
			rs=ps.executeQuery();
			if(rs.next())
			{
				if(str1.equals(rs.getString("PASSWORD")))
				{
				  JOptionPane.showMessageDialog(this, "登录成功!");
				  this.dispose();
				  new Teacher();
				}
				if(!str1.equals(rs.getString(2)))
				{
				  JOptionPane.showMessageDialog(this, "您的密码有误，请重新输入！");
				}
				
			}
			else
			{
		      JOptionPane.showMessageDialog(this, "您的帐号不存在，请重新输入!", "警告",JOptionPane.WARNING_MESSAGE);
		    }
		}
  catch (Exception e) {
			e.printStackTrace();
		}
  	 }
     }
  }
  public static void main(String arg[]){
      new   Login();
  }
}
class WinClose implements WindowListener{
   public void windowClosing (WindowEvent ev){
   System.exit(0);
   }
   public void windowOpened(WindowEvent ev){}
   public void windowActivated(WindowEvent ev){}
   public void windowDeactivated(WindowEvent ev){}
   public void windowClosed(WindowEvent ev){}
   public void windowIconified(WindowEvent ev){}
   public void windowDeiconified(WindowEvent ev){}
}
