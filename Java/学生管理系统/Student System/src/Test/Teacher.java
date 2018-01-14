package Test;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;

public class Teacher extends JFrame implements ActionListener {
	private JMenuBar mbar;  
	private JMenu que,stu,gra;
	private JMenuItem  questu,quecou,quegra,stuadd,stuupd,studel,graadd,graupd,gradel,refresh;  //��ѯ��ѡ�Ρ�ɾ�Ρ��Ŀ�
	private JPanel pCenter;
	private CardLayout card=null;
	public Teacher()
	{
		  super("��ʦ����");    //������ܶ���
		  this.setBounds(200, 40,400,400);    //���ô��ڵĴ�С
		   mbar = new JMenuBar();       //�����˵���	
		   que = new JMenu("��ѯ����");   //�����˵�
		   stu = new JMenu("ѧ������"); 
		   gra = new JMenu("�ɼ�����");
		   questu = new JMenuItem("ѧ����ѯ      ");   //�����˵�
		   quecou = new JMenuItem("�γ̲�ѯ"); 
		   quegra = new JMenuItem("ѡ�β�ѯ");  //ѡ�β�ѯ���������ĳɼ���ѯ 
		   stuadd = new JMenuItem("¼��ѧ��"); 
		   stuupd = new JMenuItem("�޸�ѧ��"); 
		   studel = new JMenuItem("ɾ��ѧ��"); 
		   graadd = new JMenuItem("¼��ɼ� "); 
		   graupd = new JMenuItem("�޸ĳɼ�");
		   refresh =new JMenuItem("ˢ��");
		   refresh.setForeground(Color.red);
		   //JMenuItem mniQuit = new JMenuItem("�˳�"); 	//�����˵���
		   mbar.add(que);    //���˵���ӵ��˵�����
		   mbar.add(new Label(" "));
		   mbar.add(stu);
		   mbar.add(new Label(" "));
		   mbar.add(gra);
		   mbar.add(new Label(" "));
		   mbar.add(refresh);
		   que.add(questu);    //���˵���ӵ��˵�����
		   que.add(quecou);
		   que.add(quegra);
		   stu.add(stuadd);
		   stu.add(stuupd);
		   stu.add(studel);
		   gra.add(graadd);
		   gra.add(graupd);
		   this.setJMenuBar(mbar);  //���˵����ҿ��ڿ����
		   questu.addActionListener(this);
		   quecou.addActionListener(this);
		   quegra.addActionListener(this);
		   stuadd.addActionListener(this);
		   stuupd.addActionListener(this);
		   studel.addActionListener(this);
		   graadd.addActionListener(this);
		   graupd.addActionListener(this);
		   refresh.addActionListener(this);
		   card=new CardLayout();
		   pCenter=new JPanel();
		   pCenter.setLayout(card);
		   pCenter.add("��ӭ",new TeacherShow());
		   pCenter.add("ѧ����ѯ",new TQueStu());
		   pCenter.add("�γ̲�ѯ",new TQueCou());
		   pCenter.add("�ɼ���ѯ",new TQueGra());
		   pCenter.add("¼��ѧ��",new TStuAdd());
		   pCenter.add("�޸�ѧ��",new TStuUpd());
		   pCenter.add("ɾ��ѧ��",new TStuDel());
		   pCenter.add("¼��ɼ�",new TGraAdd());
		   pCenter.add("�޸ĳɼ�",new TGraUpd());
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
		if( m == questu )        
		{ 
			card.show(pCenter,"ѧ����ѯ");
		} 
		else if(m == quecou)
		{
			card.show(pCenter,"�γ̲�ѯ");
		}
		else if (m == quegra)
		{
			card.show(pCenter,"�ɼ���ѯ");
		}
		else if (m == stuadd)
		{
			card.show(pCenter,"¼��ѧ��");
		}
		else if (m == stuupd)
		{
			card.show(pCenter,"�޸�ѧ��");
		}else if (m == studel)
		{
			card.show(pCenter,"ɾ��ѧ��");
		}
		else if (m == graadd)
		{
			card.show(pCenter,"¼��ɼ�");
		}
		else if (m == graupd)
		{
			card.show(pCenter,"�޸ĳɼ�");
		}
		else if(m == refresh)
		{
			this.dispose(); 
			new Teacher();
		}
   }
}
