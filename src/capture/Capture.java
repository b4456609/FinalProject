package capture;


import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Scanner;
import java.util.TimeZone;

import javax.imageio.ImageIO;

import setting.export.SettingParameter;
import setting.export.actionTest;


public class Capture {
    
     private String storagePath;     
     //private String filePath;
     private String imageFormat;  
     private int fileName;
     int num = 1; 
     String sdate = catchTime();
     static public boolean mysignal = false;
     
     int autoSecond; 
     
     protected SettingParameter setting;
     Scanner scanner = new Scanner(System.in);
     Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
     
     
     public Capture(SettingParameter setting){
    	 if(setting == null)
    		 System.out.println("nULL");
    	 this.setting = setting;
    	 this.storagePath = setting.getPath();
     }
          
     void data(int fileName, String imageFormat){
    	//storagePath = setting.getPath();
        this.fileName = fileName;
        this.imageFormat = imageFormat;
        
     }        
     
     public void catcher(){
        data(num,"jpg");
        screenCatch();
        num++;     
     }
     
 
     String catchTime(){
        SimpleDateFormat nowdate = new java.text.SimpleDateFormat("yyyyMMdd_HHmmss"); 
        nowdate.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        this.sdate = nowdate.format(new java.util.Date());
        return sdate;
     }
    
     void createFolder(){
    	//System.out.println( "\n" + storagePath + "\n" + sdate );
    	 
        File f = new File( storagePath + sdate );
        if (f.mkdir()) {
            System.out.println("success");
        } else {
            System.out.println("fail");
        }
     }     
     void screenCatch(){
         try {
             BufferedImage screen = (new Robot()).createScreenCapture(new Rectangle(0,0,(int)screenSize.getWidth(),(int)screenSize.getHeight()));
             String name = "";
             if(fileName < 10)
            	 name += "0";
             name += this.fileName + "." + this.imageFormat;             
             ImageIO.write(screen, this.imageFormat, new File(storagePath + sdate + "/" + name));            
         } catch (AWTException | IOException e) {
            System.out.println("Wrong \n" + e.getMessage());
        }
     String name = this.fileName + "." + this.imageFormat;     
     }
       
     void mySignal(){  
          mysignal = true;
    }
     
     void getautoSecond(){
         autoSecond = setting.getInterval();
     }
     
}
