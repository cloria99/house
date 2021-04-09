package 导航系统;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.*;

public class Home
{
    private JFrame jFrame;
    private JPanel mapLabel;
    private JLabel title, map;
    private JButton admin, menu, close;
    private Font titleFont = new Font("微软雅黑", 1, 28);
    private Font charFont = new Font("微软雅黑", 1, 20);
    private Toolkit toolkit = Toolkit.getDefaultToolkit(); 
    private File pointFile = new File("D://point.obj");
    private File lengthFile = new File("D://length.obj");
    private File mapFile = new File("C://Users//Lenovo//Desktop//111.jpg");
    private boolean isAdmin = true;
 
    public Home(boolean isAdmin)
    {
        this.isAdmin = isAdmin;//确定用户身份
        init();
    }

     public void init()
     {
    	 jFrame = new JFrame("主界面");
         jFrame.setLayout(new BorderLayout());
         titleInit();//初始化标题栏
         mapInit();//初始化地图
         jFrame.setBounds(300,50, 1400, 900);
         JPanel buttonPanel = new JPanel();
         buttonPanel.setLayout(new FlowLayout());
         admin = new JButton("管理员菜单");
         admin.setFont(charFont);
         admin.addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent e)
             {
                 new AdminMenu();
             }
         });

         menu = new JButton("功能菜单");
         menu.setFont(charFont);
         menu.addMouseListener(new MouseAdapter()
         {
             @Override
            public void mouseClicked(MouseEvent e)
            {
                new NormalMenu();
             }
         });
         buttonPanel.add(menu);
         if (isAdmin)
         {
             buttonPanel.add(admin);
             adminTips();
         }
         close = new JButton("关闭");
         close.setFont(charFont);
         close.addMouseListener(new MouseAdapter()
         {
             @Override
             public void mouseClicked(MouseEvent e)
             {
                 System.exit(0);
             }
          });
         buttonPanel.add(close);
         jFrame.add(buttonPanel, BorderLayout.SOUTH);
         jFrame.setResizable(false);
         jFrame.setVisible(true);
     }
     public void titleInit()
     {
         title = new JLabel("导航系统", SwingConstants.CENTER);
         title.setFont(titleFont);
         jFrame.add(title, BorderLayout.NORTH);//标题文字
     }

     public void mapInit()
     {
         ImageIcon imageIcon = new ImageIcon(mapFile.getPath());
         imageIcon.setImage(imageIcon.getImage().getScaledInstance(imageIcon.getIconWidth(),
                 imageIcon.getIconHeight(), Image.SCALE_DEFAULT));
         map = new JLabel();
         map.setBounds(0, 0, 800, 500);
         map.setHorizontalAlignment(0);
         map.setIcon(imageIcon);
         mapLabel = new JPanel();
         mapLabel.setSize(690, 400);
         mapLabel.add(map);
         jFrame.add(mapLabel, BorderLayout.CENTER);//地图显示
     }

 
     public void adminTips()
     {
         String errorTitle = "数据错误！";
         try
         {
             checkFile(mapFile, "地图");
         } catch (IOException e)
         {
             e.printStackTrace();
             new mDialog(errorTitle, "请管理员先录入地图数据！", jFrame);
             //writeMap
         }
         try
         {
             checkFile(pointFile, "景点");
 
         } catch (IOException e)
         {
             e.printStackTrace();
             new mDialog(errorTitle, "请管理员先录入景点数据！", jFrame);
             //writePoint
         }
         try
         {
             checkFile(lengthFile, "距离");
 
         } catch (IOException e)
         {
             e.printStackTrace();
             new mDialog(errorTitle, "请管理员先录入距离数据！", jFrame);
             //writeLength
         }
     }
 
     public void checkFile(File file, String string) throws IOException
     {
         if (!file.exists() || file.length() == 0)
         {
             throw new IOException(string + "文件打开失败！");
         }
     }
}
 
