package 导航系统;

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

	//String[] room = new String[]{"室内","室外"};
	String[] character = new String[]{"美食","商场","电影院","停车场","游乐设施","景点","学校"};

	public Createmessage() {
	         try
	         {
	              objectInputStream = new ObjectInputStream(new FileInputStream(file));
	             frame = new JFrame("场所功能属性");
	          } catch (IOException e)
	         {
	             new mDialog("错误", "无景点信息文件！", frame);
	         }
	         frameInit();
	     }
	public void frameInit() {
		panel=new JPanel();
		frame.setSize(300,300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(panel);
		//lblroom=new JLabel("室内/室外  ");
		lblchar=new JLabel("场所功能：  ");
		
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
        
        JButton cancelButton = new JButton("取消");
        JButton okayButton = new JButton("确认");

        cancelButton.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent e)
            {
                frame.setVisible(false);
            }
        });
        okayButton = new JButton("确定");
        //okayButton.setFont(new Font("微软雅黑", 1, 20));
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
                    new mDialog("成功", "数据成功添加", frame);
                     frame.setVisible(false);
                } catch (IOException e1)
                {
                    new mDialog("失败", "数据异常！", frame);
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
