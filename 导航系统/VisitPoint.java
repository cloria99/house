package 导航系统;

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
 import java.util.Iterator;
 import java.util.Set;
 import java.util.TreeMap;
 import java.util.TreeSet;
 
 public class VisitPoint
 {
     private JFrame jFrame;

     private JComboBox jComboBox;
     private JLabel jLabel,jLabel1;
     private JButton okayButton;
     private Toolkit toolkit = Toolkit.getDefaultToolkit();
     private File file = new File("D://point.obj");
     private ObjectInputStream objectInputStream;
 
     private TreeMap treeMap;
     private Set set;
 
     public VisitPoint()
     {
         try
         {
             objectInputStream = new ObjectInputStream(new FileInputStream(file));
             jFrame = new JFrame("附近地点查询");
          } catch (IOException e)
         {
             new mDialog("错误", "无景点信息文件！", jFrame);
         }
         frameInit();
     }
 
     public void frameInit()
     {
         try
         {
             jFrame.setLayout(new FlowLayout());
             jFrame.setBounds((toolkit.getScreenSize().width - 350) / 2, (toolkit.getScreenSize().height - 250) / 2, 500, 310);
         } catch (Exception e)
         {
             e.printStackTrace();
         }
 
         jComboBox = new JComboBox();
         jComboBox.setPreferredSize(new Dimension(350,30));
         try
         {
             treeMap = (TreeMap) objectInputStream.readObject();
             set = treeMap.keySet();
         } catch (IOException e)
         {
 
         } catch (ClassNotFoundException e)
         {
 
         }
         Iterator iterator = set.iterator();
         while (iterator.hasNext())
         {
             jComboBox.addItem((String) iterator.next());
         }
         jLabel = new JLabel("附近：");
         jLabel.setPreferredSize(new Dimension(480,20));
         jLabel.setFont(new Font("微软雅黑", 4, 20));
         jLabel1= new JLabel();
         jLabel1.setPreferredSize(new Dimension(480,150));
         jLabel1.setFont(new Font("微软雅黑", 4, 20));
         jLabel1.setText((String) treeMap.get(jComboBox.getSelectedItem()));
         jComboBox.addItemListener(new ItemListener()
         {
             @Override
             public void itemStateChanged(ItemEvent e)
             {
                 jLabel1.setText((String) treeMap.get(jComboBox.getSelectedItem()));
                 
             }
         });
 
         okayButton = new JButton("确定");
         okayButton.setSize(100,200);//设置大小
         okayButton.setFont(new Font("微软雅黑", 1, 15));
         okayButton.addMouseListener(new MouseAdapter()
         {

             @Override
             public void mouseClicked(MouseEvent e)
             {
                 jFrame.setVisible(false);
             }
         });
         jFrame.add(jComboBox);
         jFrame.add(jLabel);
         jFrame.add(jLabel1);
         jFrame.add(okayButton);
         jFrame.setResizable(false);
         jFrame.setVisible(true);
     }
 
     public static void main(String[] args)
     {
         new VisitPoint();
     }
 }
