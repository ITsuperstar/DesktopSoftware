package Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Menul extends Frame implements ActionListener{
	public Menul(){
	//������ܶ��� 
	JFrame frmTest = new JFrame("���Դ���"); 
	//�����˵��� 
	JMenuBar mnb = new JMenuBar(); 
	//�����˵� 
	JMenu mnuFile = new JMenu("�ļ�"); 
	//�����˵��� 
	JMenuItem mniNew = new JMenuItem("�½�"); 
	JMenuItem mniQuit = new JMenuItem("�˳�"); 
	//���˵�����ӵ��˵��� 
	mnuFile.add(mniNew); 
	mnuFile.add(mniQuit); 
	//���˵���ӵ��˵����� 
	mnb.add(mnuFile); 
	//���˵����ҿ��ڿ���� 
	frmTest.setJMenuBar(mnb); 
	 mniNew.addActionListener(this);
	//���ô��ڵĴ�С 
	frmTest.setSize(400,300); 
	//���ô��ڿɼ��� 
	frmTest.setVisible(true); 
	}
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem o = (JMenuItem)e.getSource(); 
		/*
		if( o == m11 )        //m11����˵����JMenuItem 
		{ 
			On_m11();//�������Լ��Ĵ����� 
		} */
	}
  public static void main(String[] args) {
    new Menul();
  }
} 