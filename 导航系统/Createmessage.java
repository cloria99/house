package ����ϵͳ;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;


public class Createmessage{

	JFrame frame;

	JPanel panel;

	JLabel lblroom,lblchar;
    private ObjectInputStream objectInputStream;
    private TreeMap treeMap;
    private Set set;
	JComboBox jComboBox1,cmbchar;

    //private ObjectInputStream objectInputStream;
    private ObjectOutputStream objectOutputStream;
    private File file = new File("D://point.obj");

	//String[] room = new String[]{"����","����"};
	String[] character = new String[]{"��ʳ","�̳�","��ӰԺ","ͣ����","������ʩ","����","ѧУ"};

	public Createmessage() {
	         try
	         {
	              objectInputStream = new ObjectInputStream(new FileInputStream(file));
	             frame = new JFrame("������������");
	          } catch (IOException e)
	         {
	             new mDialog("����", "�޾�����Ϣ�ļ���", frame);
	         }
	         frameInit();
	     }
	public void frameInit() {
		panel=new JPanel();
		frame.setSize(300,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		//lblroom=new JLabel("����/����  ");
		lblchar=new JLabel("�������ܣ�  ");
		
		jComboBox1 = new JComboBox();
        jComboBox1.setPreferredSize(new Dimension(270,30));
        Iterator iterator = set.iterator();
        while (iterator.hasNext())
        {
            jComboBox1.addItem(iterator.next());
        }
        //JTextArea jTextArea = new JTextArea(10, 25);
    	//cmbroom=new JComboBox(room);
        //cmbchar.setModel(new DefaultComboBoxModel(getCity(province)));
		cmbchar=new JComboBox(character);
        jComboBox1.addItemListener(new ItemListener()
        {
            @Override
            public void itemStateChanged(ItemEvent e)
            {
            	
            }
        });
        
        JButton cancelButton = new JButton("ȡ��");
        JButton okayButton = new JButton("ȷ��");

        cancelButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                frame.setVisible(false);
            }
        });
        okayButton = new JButton("ȷ��");
        //okayButton.setFont(new Font("΢���ź�", 1, 20));
        okayButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                //treeMap.put(jComboBox1.getSelectedItem(), cmbroom.getSelectedItem());
                /*treeMap.put(jComboBox1.getSelectedItem(), cmbchar.getSelectedItem());
                try
                {
                    objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
                    objectOutputStream.writeObject(treeMap);
                    new mDialog("�ɹ�", "���ݳɹ����", frame);
                     frame.setVisible(false);
                } catch (IOException e1)
                {
                    new mDialog("ʧ��", "�����쳣��", frame);
                }*/

            }
        });
        
		panel.add(jComboBox1);
		//panel.add(lblroom);
		//panel.add(cmbroom);
		panel.add(lblchar);
		panel.add(cmbchar);
		panel.add(cancelButton);
		panel.add(okayButton);

}
	
	public static void main(String args[]) {

	Createmessage obj=new Createmessage();

	obj.frame.show();

}

}
