package capture;


public class ManualCapture extends Capture{

    
    void start(){
        catchTime();
        createFolder();  
        mySignal();
        
        
        if(mysignal){     //pause or not
            while(mysignal)
                try{
                    Thread.sleep(1000);//stop 1 sec
                    System.out.println("stop");
                }catch(Exception e){
                    e.printStackTrace();
                }
        }
        else{
            try{                
                HotkeyTest key = new HotkeyTest();   
                key.initHotkey();
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
     }
   
}
