package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
public class Student extends JFrame implements ActionListener{
	private JMenuBar mbar;  
	private JMenu A,B,C,D;
	private JMenuItem  query,query1,select,delete,update,refresh;  //��ѯ��ѡ�Ρ�ɾ�Ρ��Ŀ�
	private JPanel pCenter;
	private CardLayout card=null;
	private String number;
	public Student(String number)
	{
	   super("ѧ��ѡ��");    //������ܶ���
	   this.setBounds(200, 40,400,380);    //���ô��ڵĴ�С
	   this.number=number;
	   mbar = new JMenuBar();       //�����˵���	
	   A = new JMenu("��ѯ�γ�");   //�����˵�
	   B = new JMenu("ѡ��γ�"); 
	   C = new JMenu("ɾ���γ�");
	   D = new JMenu("�޸Ŀγ�"); 
	   query = new JMenuItem("��ѯ��ѡ�γ�");   //�����˵�
	   query1 = new JMenuItem("��ѯ��ѡ�γ�"); 
	   select = new JMenuItem("ѡ��γ�"); 
	   delete = new JMenuItem("ɾ���γ�");
	   update = new JMenuItem("�޸Ŀγ�"); 
	   refresh =new JMenuItem("ˢ��");
	   refresh.setForeground(Color.red);
	   mbar.add(A);    //���˵���ӵ��˵�����
	   mbar.add(new Label(" "));
	   mbar.add(B);
	   mbar.add(new Label(" "));
	   mbar.add(C);
	  // mbar.add(D);
	   mbar.add(new Label(" "));
	   mbar.add(refresh);
	   A.add(query);    //���˵���ӵ��˵�����
	   A.add(query1); 
	   B.add(select);
	   C.add(delete);
	   //D.add(update);
	   this.setJMenuBar(mbar);  //���˵����ҿ��ڿ����
	   query.addActionListener(this);
	   query1.addActionListener(this);
	   select.addActionListener(this); 
	   delete.addActionListener(this);
	   // update.addActionListener(this);
	   refresh.addActionListener(this);
	   card=new CardLayout();
	   pCenter=new JPanel();
	   pCenter.setLayout(card);
	   pCenter.add("��ӭ",new StudentShow(number));
	   pCenter.add("��ѯ",new Que(number));
	   pCenter.add("��ѯ1",new Que1(number));
	   pCenter.add("ѡ��",new Sel(number));
	   pCenter.add("ɾ��",new Del(number));
	   this.add(pCenter,BorderLayout.CENTER);
	   validate();
	   this.setVisible(true);
	   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	   this.addWindowListener(new WindowAdapter(){//�رճ���ʱ�Ĳ���
			public void windowClosing(WindowEvent e){System.exit(0);} });
	   validate();
	}
	public void actionPerformed(ActionEvent e)
	{
		Object m = e.getSource(); 
		if( m == query )        
		{ 
			card.show(pCenter,"��ѯ");
		} 
		else if(m == query1)
		{
			card.show(pCenter,"��ѯ1");
		}
		else if(m == select)
		{
			card.show(pCenter,"ѡ��");
		}
		else if (m == delete)
		{
			card.show(pCenter,"ɾ��");
		}
		else if(m == refresh)
		{
			this.dispose(); 
			new Student(this.number);
		}
	}
}
