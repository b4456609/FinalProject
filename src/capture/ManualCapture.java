package capture;

import setting.export.SettingParameter;


public class ManualCapture extends Capture{

	private SettingParameter setting;
	
    public ManualCapture(SettingParameter setting) {
    	
		super(setting);
		this.setting = setting;
		// TODO Auto-generated constructor stub
	}

	void start(){
        catchTime();
        setting.setFolderName(sdate);
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
                HotkeyTest key = new HotkeyTest(setting);   
                key.initHotkey();
            }catch(Exception e){
                e.printStackTrace();
            }
            
        }
     }
   
}
