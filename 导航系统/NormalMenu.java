package ����ϵͳ;

 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 
 class NormalMenu
 {
 
     private JFrame jFrame;
     private JButton visitButton, searchButton, okayButton;
     private Font font = new Font("΢���ź�", 1, 20);
     private Toolkit toolkit = Toolkit.getDefaultToolkit();
 
     public NormalMenu()
     {
         jFrame = new JFrame("���ܲ˵�");
         jFrame.setBounds((toolkit.getScreenSize().width - 250) / 2, (toolkit.getScreenSize().height - 200) / 2, 250, 200);
         jFrame.setLayout(new FlowLayout());
         visitButton = new JButton("1.����ص���Ϣ");
         visitButton.setFont(font);
         searchButton = new JButton("2.��ѯ���·��");
         searchButton.setFont(font);
         okayButton = new JButton("�ر�");
         okayButton.setFont(font);
 
 
         visitButton.addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent e)
             {
                 new VisitPoint();
             }
         });
 
         searchButton.addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent e)
             {
                 new SearchLength();
             }
         });
 
         okayButton.addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent e)
             {
                 jFrame.setVisible(false);
             }
         });
 
         jFrame.add(visitButton);
         jFrame.add(searchButton);
         jFrame.add(okayButton);
         jFrame.setResizable(false);
         jFrame.setVisible(true);
 
     }
 
 }