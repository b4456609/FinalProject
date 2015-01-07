package capture;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;

import setting.export.SettingParameter;

import com.melloware.jintellitype.HotkeyListener;   
import com.melloware.jintellitype.JIntellitype;   
  
  
public class HandScreenShot extends Capture 
{
    Frame frame;
    Panel panel;
    Label text;
    private JButton clickButton;
    private SettingParameter setting;
     
    public HandScreenShot(SettingParameter setting)
    {
    	super(setting);
    	this.setting = setting;
    	new mousePressed();

    }
   // public static void main(String[] args) {
       // new HandScreenShot();
   // }
      
   
     
    private class mousePressed implements ActionListener
    {
    	 public mousePressed() {
    	    	Icon click = new ImageIcon( "src/Click.jpg");
    	    	clickButton = new JButton(click);
    	       frame = new Frame("�芸�");
    	        frame.addWindowListener(new AdapterDemo());
    	        frame.setSize(150, 150);
    	        frame.setAlwaysOnTop(true);
    	        /*panel = new Panel();*/
    	        clickButton.addActionListener(this);
    	        
    	       text = new Label();
    	         
    	        frame.setVisible(true);
    	        frame.setResizable(false);
    	        frame.add(clickButton);
    	    }
    	@Override
		public void actionPerformed(ActionEvent e) {
			
	             
	        	catcher();

	           // System.out.println("mouse clicks: " + e.getClickCount());
		}
    }
    
    
    /*static void save(String path) throws Exception
	{
		Robot robot = new Robot();
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		Rectangle rect = new Rectangle(0, 0, d.width, d.height);
		BufferedImage image = robot.createScreenCapture(rect);
		ImageIO.write(image, "jpg", new File(path));
	}*/
    
  }
  
class AdapterDemo extends WindowAdapter 
{
    public void windowClosing(WindowEvent e) 
    {
        System.exit(0);
    }
}

