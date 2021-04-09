package ����ϵͳ;
 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.io.BufferedReader;
 import java.io.BufferedWriter;
 import java.io.File;
 
 public class AdminMenu
 {
     private JFrame jFrame;
     private JButton createPoint, editPoint, deletePoint, createLength, editLength;
     private JButton cancelButton;
     private Toolkit toolkit = Toolkit.getDefaultToolkit();
     private Font font = new Font("΢���ź�", 1, 20);
     private File pointFile = new File("D://point.txt");
     private File lengthFile = new File("D://length.txt");
     private JFrame childFrame;
     private JPanel childPanel;
 
     private BufferedReader bufferedReader;
     private BufferedWriter bufferedWriter;
 
 
     public AdminMenu()
     {
         jFrame = new JFrame("����Ա�˵�");
         jFrame.setBounds((toolkit.getScreenSize().width - 250) / 2, (toolkit.getScreenSize().height - 310) / 2, 250, 310);
         jFrame.setLayout(new FlowLayout());
 
         childPanel = new JPanel();
         childPanel.setLayout(new FlowLayout());
         cancelButton = new JButton("�ر�");
         childPanel.add(cancelButton);
 
 
         cancelButton.addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent e)
             {
                 jFrame.setVisible(false);
             }
         });
 
         createPoint = new JButton("1.����������Ϣ");
         createPoint.setFont(font);
         createPoint.addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent e)
             {
                 new CreatePoint();
             }
         });
 
         editPoint = new JButton("2.�޸ľ�����Ϣ");
         editPoint.setFont(font);
         editPoint.addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent e)
             {
                 new EditPoint();
             }
         });
 
         deletePoint = new JButton("3.ɾ��������Ϣ");
         deletePoint.setFont(font);
         deletePoint.addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent e)
             {
                 new DeletePoint();
             }
         });
 
         createLength = new JButton("4.������·��Ϣ");
         createLength.setFont(font);
         createLength.addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent e)
             {
                 new CreateLength(jFrame);
             }         });
 
         editLength = new JButton("5.�޸ĵ�·��Ϣ");
         editLength.setFont(font);
         editLength.addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent e)
             {
            	 new CreateLength(jFrame);             }
         });
 
         jFrame.add(createPoint);
         jFrame.add(editPoint);
         jFrame.add(deletePoint);
         jFrame.add(createLength);
         jFrame.add(editLength);
         jFrame.add(childPanel);
         jFrame.setVisible(true);
     }
 
 
 }