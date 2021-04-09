package 导航系统;

 import javax.imageio.event.IIOReadProgressListener;
 import javax.swing.*;
 import java.awt.*;
 import java.awt.event.ItemEvent;
 import java.awt.event.ItemListener;
 import java.awt.event.MouseAdapter;
 import java.awt.event.MouseEvent;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.ObjectInputStream;
 import java.util.ArrayList;
 import java.util.Iterator;
 import java.util.Set;
 import java.util.TreeMap;
 
 public class SearchLength
 {
     private JFrame jFrame;
     private JComboBox jComboBox1, jComboBox2;
     private JLabel jLabel,jLabel1,jLabel2;
     private JButton jButton;
 
     private ObjectInputStream objectInputStream1, objectInputStream2;
     private File lengthFile, pointFile;
 
     private TreeMap treeMap;
     private ArrayList arrayList;
     private Set set;
 
     private LengthInfo lengthInfo;
     private Createmessage createmessage;
     private Toolkit toolkit = Toolkit.getDefaultToolkit();
 
     public SearchLength()
     { 																							
         jFrame = new JFrame("最短路径查询");
         try
         {
             lengthFile = new File("D://length.obj");
             pointFile = new File("D://point.obj");
             objectInputStream1 = new ObjectInputStream(new FileInputStream(lengthFile));
             objectInputStream2 = new ObjectInputStream(new FileInputStream(pointFile));
             arrayList = (ArrayList) objectInputStream1.readObject();
             lengthInfo = (LengthInfo) arrayList.get(0);
             treeMap = (TreeMap) objectInputStream2.readObject();
 
         } catch (IOException e)
         {
             new mDialog("错误", "无景点信息！", jFrame);
         } catch (ClassNotFoundException e)
         {
             new mDialog("错误！", "文件信息错误！", jFrame);
         }
 
         try
         {
             set = treeMap.keySet();
         } catch (NullPointerException e)
         {
             new mDialog("错误", "无道路长度信息！", jFrame);
         }
         frameInit();
     }
 
     public void frameInit()
     {
         jFrame.setLayout(new FlowLayout());
         jFrame.setBounds((toolkit.getScreenSize().width - 200) / 2, (toolkit.getScreenSize().height - 200) / 2, 400, 370);
 
         jComboBox1 = new JComboBox();
         jComboBox1.setPreferredSize(new Dimension(180, 30));
         jComboBox1.setFont(new Font("微软雅黑", 1, 20));
         jComboBox2 = new JComboBox();
         jComboBox2.setPreferredSize(new Dimension(180, 30));
         jComboBox2.setFont(new Font("微软雅黑", 1, 20));
         
         Iterator iterator = set.iterator();
         while (iterator.hasNext())
         {
             String string = (String) iterator.next();
             jComboBox1.addItem(string);
             jComboBox2.addItem(string);
         }
         jLabel = new JLabel();
         jLabel.setPreferredSize(new Dimension(350, 100));
         jLabel.setFont(new Font("微软雅黑", 1, 20));
         double str1 = lengthInfo.getMin(0, 1, treeMap);
         jLabel1 = new JLabel();
         jLabel1.setPreferredSize(new Dimension(350,50));
         jLabel1.setFont(new Font("微软雅黑",Font.BOLD, 20));
         jLabel2 = new JLabel();
         jLabel2.setPreferredSize(new Dimension(350,50));
         jLabel2.setFont(new Font("微软雅黑",Font.BOLD, 20));
         
         jComboBox1.addItemListener(new ItemListener()
         {
             @Override
             public void itemStateChanged(ItemEvent e)
             {
                double str1 = lengthInfo.getMin(jComboBox1.getSelectedIndex(), jComboBox2.getSelectedIndex(), treeMap);
                 String str2 = lengthInfo.getStringBuilder();
                 String str3 = null;
                 String str4 = null;
     			if (str1<=1500)
     			{ str3="步行";
     			  if (str1<500)
     			  str4 = "步行时间约10分钟";
     			  else if(str1<1000&&str1>500)
     			  str4 = "步行时间约15分钟";
     			  else
     				  str4="步行时间约20分钟";
     			}
                  else if(str1>1500&&str1<=5000)
                  {	 str3="自行车";
                  	 if(str1<2500)
                  		str4="骑行时间约20分钟";
                  	 else if(str1<3500)
                  	 	str4="骑行时间约30分钟";
                  	 else
                  	 	str4="骑行时间约40分钟";
                  }
                  else if(str1>5000)
                  {	str3="驾车";
     				str4="驾车出行，祝您一路顺风！";
                  }
                 jLabel.setText("<html><body>" + "最优路径: " + str2 + "<br>" + "里程：  " + str1 + "m" + "<body></html>");
                 jLabel1.setText("<html><body>" + "建议交通工具:  "+str3+"<body></html>");
                 jLabel2.setText("<html><body>" + "出行时间:  "+str4+"<body></html>");

             }
         });
         jComboBox2.addItemListener(new ItemListener()
         { 
             @Override
             public void itemStateChanged(ItemEvent e)
             {
                 double str1 = lengthInfo.getMin(jComboBox1.getSelectedIndex(), jComboBox2.getSelectedIndex(), treeMap);
                 String str2 = lengthInfo.getStringBuilder();
                 String str3 = null;
                 String str4 = null;

                 if (str1<=1500)
      			{ str3="步行";
      			  if (str1<500)
      			  str4 = "步行时间约10分钟";
      			  else if(str1<1000&&str1>500)
      			  str4 = "步行时间约15分钟";
      			  else
      				  str4="步行时间约20分钟";
      			}
                   else if(str1>1500&&str1<=5000)
                   {	 str3="自行车";
                   	 if(str1<2500)
                   		str4="骑行时间约20分钟";
                   	 else if(str1<3500)
                   	 	str4="骑行时间约30分钟";
                   	 else
                   	 	str4="骑行时间约40分钟";
                   }
                   else if(str1>5000)
                   {	str3="驾车";
      				str4="驾车出行，祝您一路顺风！";
                   }
      			
                  jLabel.setText("<html><body>" + "最优路径: " + str2 + "<br>" + "里程：  " + str1 + "m" + "<body></html>");
                  jLabel1.setText("<html><body>" + "建议交通工具:  "+str3+"<body></html>");
                  jLabel2.setText("<html><body>" + "出行时间:  "+str4+"<body></html>");

                  }
         });

         jButton = new JButton("确定");
         jButton.addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent e)
             {
                 jFrame.setVisible(false);
             }
         });
 
         jFrame.add(jComboBox1);
         jFrame.add(jComboBox2);
         jFrame.add(jLabel);
         jFrame.add(jLabel1);
         jFrame.add(jLabel2);
         jFrame.add(jButton);
         jFrame.setResizable(false);
         jFrame.setVisible(true);
     }
 
 
 }
