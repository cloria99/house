package ����ϵͳ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.TreeMap;
import java.util.Iterator;
import java.util.Set;
 
public class EditPoint
{
     private String key;
     private ObjectInputStream objectInputStream;
     private ObjectOutputStream objectOutputStream;
     private TreeMap treeMap;
     private Set<String> set;
     private File file;
     private Toolkit toolkit = Toolkit.getDefaultToolkit();
     private JFrame jFrame;
	 private JTextArea jTextArea;
     
     public EditPoint()
     {
         try
         {
             file = new File("D://point.obj");
             jFrame = new JFrame("���ӵص㹦��");
             objectInputStream = new ObjectInputStream(new FileInputStream(file));
           
             treeMap = (TreeMap) objectInputStream.readObject();

             set = treeMap.keySet();
             frameInit();
         } catch (IOException e)
         {
             new mDialog("����", "û���ļ���", jFrame);
             e.printStackTrace();
         } catch (ClassNotFoundException e)
         {
             e.printStackTrace();
         }
     }
 
     public void frameInit()
     {
         jFrame.setBounds((toolkit.getScreenSize().width - 350) / 2, (toolkit.getScreenSize().height - 450) / 2, 350, 370);
         jFrame.setLayout(new FlowLayout());
         JComboBox jComboBox = new JComboBox();
         jComboBox.setPreferredSize(new Dimension(280, 30));
         Iterator iterator = set.iterator();
         while (iterator.hasNext())
         {
             jComboBox.addItem((String) iterator.next());
         }
         JTextArea jTextArea = new JTextArea(10, 25);
         JLabel label=new JLabel("����:                                           ");
        // jTextArea.addKeyListener((KeyListener) this);
         jTextArea.setText((String) treeMap.get(jComboBox.getSelectedItem()));
         jComboBox.addItemListener(new ItemListener()
         {
             @Override
             public void itemStateChanged(ItemEvent e)
             {
             }
         });
         JButton okayButton = new JButton("ȷ��");
         JButton cancelButton = new JButton("ȡ��");
         cancelButton.addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent e)
             {
                 jFrame.setVisible(false);
             }
         });
         okayButton.addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent e)
             {
                 String string = jTextArea.getText();
                 treeMap.put((String) jComboBox.getSelectedItem(), string);
                 try
                 {
                     objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                     objectOutputStream.writeObject(treeMap);
                     new mDialog("�ɹ�", "���ݳɹ��޸�", jFrame);
                     jFrame.setVisible(false);
                 } catch (IOException e1)
                 {
                     new mDialog("ʧ��", "�����쳣��", jFrame);
                 }
                 
                 
             }
         });
         jFrame.add(jComboBox);
         jFrame.add(label,BorderLayout.CENTER);
         jFrame.add(jTextArea,BorderLayout.CENTER);
        
         jFrame.add(cancelButton);
         jFrame.add(okayButton);
         jFrame.setResizable(false);
         jFrame.setVisible(true);
     }

     public static void main(String[] args)
     {
         new EditPoint();
     }
}
