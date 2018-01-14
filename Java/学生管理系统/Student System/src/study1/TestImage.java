package study1;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class TestImage implements ActionListener{
	JPanel jp1=new JPanel();
	CardLayout c1=new CardLayout();
	Timer timer=new Timer(500,this);
	public TestImage()
	{
		JFrame jf=new JFrame("图片浏览器");
		jp1.setLayout(c1);
		String []name={"1.JPG","2.JPG","3.JPG","4.JPG","5.JPG","6.JPG","7.JPG"};
		for(int i=0;i<name.length;i++)
		{
			ImageIcon ic=new ImageIcon("G:\\Myeclipse  Work\\Student System\\JPG\\"+name[i]);
			JLabel j1=new JLabel(ic);
            jp1.add(j1,i+"");
		}
		jf.add(jp1);
		JPanel jp2=new JPanel();
		String []s={"第一张","上一张","下一张","最后一张","播放","暂停"};
		for(int i=0;i<s.length;i++)
		{
			JButton jb=new JButton(s[i]);
			jb.addActionListener(this);
			jp2.add(jb);
		}
		jf.add(jp2,BorderLayout.SOUTH);
		jf.setSize(200,100);
		jf.pack();
		jf.setLocation(0,0);
		jf.setVisible(true);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void actionPerformed(ActionEvent e)
	{
		String s=e.getActionCommand();
		if("上一张".equals(s))
		{
			c1.previous(jp1);
		}
		else if("下一张".equals(s))
		{
			c1.next(jp1);
		}
		else if("最后一张".equals(s))
		{
			c1.last(jp1);
		}
		else if("第一张".equals(s))
		{
			c1.first(jp1);
		}
		else if("播放".equals(s))
		{
			timer.start();
		}
		else if("暂停".equals(s))
		{
			timer.stop();
		}
		else
		{
			c1.next(jp1);
		}
			
	}
	public static void main(String[] args)
	{
		new TestImage();
	}

}
