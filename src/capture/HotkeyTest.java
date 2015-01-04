package capture;

import com.melloware.jintellitype.HotkeyListener;  
import com.melloware.jintellitype.JIntellitype;  

  
public class HotkeyTest extends Capture implements HotkeyListener {  
    

    static final int KEY_1 = 65;   //A
    static final int KEY_2 = 90;   //Z
    
    @Override
    public void onHotKey(int key) {   

        switch (key) {   
            case KEY_1:   
                System.out.println("catch!");  
                mySignal();
                catcher();
                break;   
            case KEY_2:   
                System.out.println("stop");   
                destroy();
        }   
    }   
    
    void destroy() {   
        JIntellitype.getInstance().unregisterHotKey(KEY_1);   
        JIntellitype.getInstance().unregisterHotKey(KEY_2);          
        System.exit(0);   
    }   
  
    void initHotkey() {   
       
        JIntellitype.getInstance().registerHotKey(KEY_1, JIntellitype.MOD_ALT, (int) 'A');   
        JIntellitype.getInstance().registerHotKey(KEY_2, JIntellitype.MOD_ALT, (int) 'Z');          
  
        JIntellitype.getInstance().addHotKeyListener(this);   

    }   
}  