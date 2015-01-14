package setting.export;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class SettingParameter extends JFrame {
	private String folderName = "";
	
	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	// auto panel ���
	private boolean autoMode = true; // auto mode �桀����(true for time)
	private int autoByTimeInterval = 2;

	// hot key
	private int hotKeyScreenShotNumber = -1;
	private int hotKeyStopNumber = -1;
	
	// path
	private String savePathInput = "D:/";
	
	// export
	private int exportGIFInterval = 2;
	
	private boolean lastAutoMode = true;
	private int lastAutoByTimeInterval = 2;
	private int lastHotKeyScreenShotNumber =-1;
	private int lastHotKeyStopNumber = -1;
	private String lastSavePathInput = "D:/";
	private int lastExportGIFInterval = 5;	
	
	// get the value of auto mode
	public void setAutoOption(boolean autoOption)
	{
		autoMode = autoOption;
	}
	
	// get the value of GIF time interval
	public void setGifInterval(int gifInterval)
	{
		exportGIFInterval = gifInterval;
	}
	
	// get the value of save path
	public void setPath(String path)
	{
		savePathInput = path;
	}
	
	// get the interval of auto by time
	public void setInterval(int autoInterval)
	{
		autoByTimeInterval = autoInterval;
	}
	
	// get the hot key character of stop 
	public void setPauseHotKey(int number)
	{
		hotKeyStopNumber = number;
	}
	
	// get the hot key character of screen shot
	public void setCaptureHotKey(int number)
	{
		hotKeyScreenShotNumber = number;
	}
	
	
	// get the value of auto mode
	public boolean getAutoOption()
	{
		return autoMode;
	}
	
	// get the value of GIF time interval
	public int getGifInterval()
	{
		return exportGIFInterval;
	}
	
	// get the value of save path
	public String getPath()
	{
		return savePathInput;
	}
	
	// get the interval of auto by time
	public int getInterval()
	{
		return autoByTimeInterval;
	}
	
	// get the hot key character of stop 
	public int getPauseHotKey()
	{
		return hotKeyStopNumber;
	}
	
	// get the hot key character of screen shot
	public int getCaptureHotKey()
	{
		return hotKeyScreenShotNumber;
	}
	
	public void setLastAutoOption(boolean autoOption)
	{
		lastAutoMode = autoOption;
	}
	
	// get the value of GIF time interval
	public void setLastGifInterval(int gifInterval)
	{
		lastExportGIFInterval = gifInterval;
	}
	
	// get the value of save path
	public void setLastPath(String path)
	{
		lastSavePathInput = path;
	}
	
	// get the interval of auto by time
	public void setLastInterval(int autoInterval)
	{
		lastAutoByTimeInterval = autoInterval;
	}
	
	// get the hot key character of stop 
	public void setLastPauseHotKey(int number)
	{
		lastHotKeyStopNumber = number;
	}
	
	// get the hot key character of screen shot
	public void setLastCaptureHotKey(int number)
	{
		lastHotKeyScreenShotNumber = number;
	}
	
	
	
	public boolean getLastAutoOption()
	{
		return lastAutoMode;
	}
	
	// get the value of GIF time interval
	public int getLastGifInterval()
	{
		return lastExportGIFInterval;
	}
	
	// get the value of save path
	public String getLastPath()
	{
		return lastSavePathInput;
	}
	
	// get the interval of auto by time
	public int getLastInterval()
	{
		return lastAutoByTimeInterval;
	}
	
	// get the hot key character of stop 
	public int getLastPauseHotKey()
	{
		return lastHotKeyStopNumber;
	}
	
	// get the hot key character of screen shot
	public int getLastCaptureHotKey()
	{
		return lastHotKeyScreenShotNumber;
	}
}