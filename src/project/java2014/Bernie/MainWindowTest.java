package project.java2014.Bernie;

import setting.export.SettingParameter;

public class MainWindowTest {

	private static SettingParameter settingParameter;
	
	public static void main(String[] args) {
		settingParameter = new SettingParameter();
		
		// TODO Auto-generated method stub
		MainWindow frame = new MainWindow(settingParameter);
		frame.setVisible(true);
	}

}
