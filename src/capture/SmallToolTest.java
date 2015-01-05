package capture;
import javax.swing.JFrame;

import setting.export.SettingParameter;


public class SmallToolTest 
{
	private static SettingParameter settingParameter;
	
	public static void main(String[] args)
	{
		settingParameter = new SettingParameter();
		SmallTool smallTool = new SmallTool(settingParameter);
		smallTool.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		smallTool.setLayout(null);
		smallTool.setBounds(100, 100, 350 , 125);
		smallTool.setResizable(false);
		smallTool.setVisible( true);
	}
}
