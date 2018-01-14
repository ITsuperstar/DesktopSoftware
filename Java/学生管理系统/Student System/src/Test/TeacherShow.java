package Test;

import java.awt.*;

import javax.swing.*;

public class TeacherShow extends JPanel {
	public TeacherShow()
	{    
		JLabel label,j1;
		label=new JLabel("欢迎登陆河北大学选课系统",JLabel.CENTER);
		label.setFont(new Font("宋体",Font.BOLD,25));
		label.setForeground(Color.red);
		this.add(label);
		ImageIcon ic=new ImageIcon("G:\\Myeclipse  Work\\Student System\\JPG\\11.jpg");
		//ic=new ImageIcon(ic.getImage().getScaledInstance(400,300, ic.getImage().SCALE_DEFAULT));
		j1=new JLabel(ic);
		this.add(j1);
        // 把标签的大小位置设置为图片刚好填充整个面板  
      
        // 把内容窗格转化为JPanel，否则不能用方法setOpaque()来使内容窗格透明  
       // this.getRootPane();  
        //this.setOpaque(false);  
        // 把背景图片添加到分层窗格的最底层作为背景  
        //this.getRootPane().add(j1, new Integer(Integer.MIN_VALUE));
	}
}
