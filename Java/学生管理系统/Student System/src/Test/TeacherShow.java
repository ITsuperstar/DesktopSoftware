package Test;

import java.awt.*;

import javax.swing.*;

public class TeacherShow extends JPanel {
	public TeacherShow()
	{    
		JLabel label,j1;
		label=new JLabel("��ӭ��½�ӱ���ѧѡ��ϵͳ",JLabel.CENTER);
		label.setFont(new Font("����",Font.BOLD,25));
		label.setForeground(Color.red);
		this.add(label);
		ImageIcon ic=new ImageIcon("G:\\Myeclipse  Work\\Student System\\JPG\\11.jpg");
		//ic=new ImageIcon(ic.getImage().getScaledInstance(400,300, ic.getImage().SCALE_DEFAULT));
		j1=new JLabel(ic);
		this.add(j1);
        // �ѱ�ǩ�Ĵ�Сλ������ΪͼƬ�պ�����������  
      
        // �����ݴ���ת��ΪJPanel���������÷���setOpaque()��ʹ���ݴ���͸��  
       // this.getRootPane();  
        //this.setOpaque(false);  
        // �ѱ���ͼƬ��ӵ��ֲ㴰�����ײ���Ϊ����  
        //this.getRootPane().add(j1, new Integer(Integer.MIN_VALUE));
	}
}
