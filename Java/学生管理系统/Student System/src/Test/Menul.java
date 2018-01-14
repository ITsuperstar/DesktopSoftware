package Test;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class Menul extends Frame implements ActionListener{
	public Menul(){
	//创建框架对象 
	JFrame frmTest = new JFrame("测试窗口"); 
	//创建菜单栏 
	JMenuBar mnb = new JMenuBar(); 
	//创建菜单 
	JMenu mnuFile = new JMenu("文件"); 
	//创建菜单项 
	JMenuItem mniNew = new JMenuItem("新建"); 
	JMenuItem mniQuit = new JMenuItem("退出"); 
	//将菜单项添加到菜单中 
	mnuFile.add(mniNew); 
	mnuFile.add(mniQuit); 
	//将菜单添加到菜单栏中 
	mnb.add(mnuFile); 
	//将菜单栏挂靠在框架上 
	frmTest.setJMenuBar(mnb); 
	 mniNew.addActionListener(this);
	//设置窗口的大小 
	frmTest.setSize(400,300); 
	//设置窗口可见性 
	frmTest.setVisible(true); 
	}
	public void actionPerformed(ActionEvent e)
	{
		JMenuItem o = (JMenuItem)e.getSource(); 
		/*
		if( o == m11 )        //m11是你菜单项的JMenuItem 
		{ 
			On_m11();//这是你自己的处理函数 
		} */
	}
  public static void main(String[] args) {
    new Menul();
  }
} 