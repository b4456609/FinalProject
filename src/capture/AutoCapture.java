package capture;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;



public class AutoCapture  extends Capture{ 
    

   void start(){
        catchTime();
        createFolder();  
        mySignal();
        getautoSecond();
        
        if(mysignal){     //pause or not
            while(mysignal)
                try{
                    Thread.sleep(1000);//?»è…¦?«å?ä¸????
                    System.out.println("stop");
                }catch(Exception e){}
        }
        else{
            if(true/*ä»¥ç???/){
                try{
                    catcher();
                    Thread.sleep(autoSecond * 1000);
                }catch(Exception e){
                    e.printStackTrace();
                }
           
            }
            else{    //ä»¥é???
                //ç¼ºé?è£?
            }
        }
    } 
        
}

