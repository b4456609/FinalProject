package capture;

import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.lang.*;

import javax.swing.ImageIcon;

import project.java2014.Bernie.MainWindow;
import setting.export.SettingParameter;
import setting.export.actionTest;

public class SmallTool extends JFrame
{
	private JButton startButton;
	private JButton stopButton;
	private JButton screenCutButton;
	private JButton settingButton;
	private static SettingParameter settingParameter;
	//private boolean signal;
	
	
	
	public SmallTool(SettingParameter settingParameter)
	{
		super("SmallTool");
		this.settingParameter = settingParameter;
		
		setLayout(new FlowLayout());
		
		Icon start = new ImageIcon( "src/start.jpg");
		Icon stop =new ImageIcon( "src/stop.jpg");
		Icon cut = new ImageIcon( "src/handcut.jpg");
		Icon setting = new ImageIcon( "src/setting.jpg");

		startButton = new JButton(start);
		stopButton = new JButton(stop);
		screenCutButton = new JButton(cut);
		settingButton = new JButton(setting);
		add(startButton );
		add(stopButton );
		add(screenCutButton );
		add(settingButton );
		startButton.addActionListener( new startHandler());
		stopButton.addActionListener( new stopHandler());
		screenCutButton.addActionListener( new cutHandler());
		settingButton.addActionListener( new settingButton());
		startButton.setBounds(60, 30 , 40, 40);
		stopButton.setBounds(120, 30 , 40, 40);
		screenCutButton.setBounds(180, 30 , 40, 40);
		settingButton.setBounds(240 , 30 , 40 , 40);
		Capture.mysignal = false;
	}
	
	public void nextWindow(){
		// new edit preview window
		MainWindow frame = new MainWindow(settingParameter);
		frame.setVisible(true);
		
		this.setVisible(false);
	}
	
	private class startHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent click)
		{
			
			startButton.setEnabled(false);
			settingButton.setEnabled(false);
			screenCutButton.setEnabled(false);
			System.out.printf("Start.");
			AutoCapture autoCapture = new AutoCapture(settingParameter);
			autoCapture.start();
		}
	}
	
	private class stopHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent click)
		{
			startButton.setEnabled(true);
			settingButton.setEnabled(true);
			screenCutButton.setEnabled(true);
			System.out.printf("Stop.");
			Capture.mysignal = true;
			nextWindow();
		}
	}
	
	private class cutHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent click)
		{
			
			startButton.setEnabled(false);
			settingButton.setEnabled(false);
			screenCutButton.setEnabled(false);
			System.out.printf("ScreenCut.");
			ManualCapture manualCapture = new ManualCapture(settingParameter);
			manualCapture.start();
		}
	}
	
	private class settingButton implements ActionListener
	{
		public void actionPerformed( ActionEvent click)
		{
			System.out.printf("setting.");
			try
			{
			    org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
			    UIManager.put("RootPane.setupButtonVisible", false);
			    // 改变InsetsUIResource参数的值即可实现 设置BeantuEye外观下JTabbedPane的左缩进
			    UIManager.put("TabbedPane.tabAreaInsets", new javax.swing.plaf.InsetsUIResource(3,0,2,20));

			    //设置属性即可：true表示使用ToolBar.background颜色实现纯
			    //色填充背景，BeautyEye中此属性默认是false
			    UIManager.put("ToolBar.isPaintPlainBackground", Boolean.TRUE);
			}
			catch(Exception ex)
			{
			    // TODO exception
			}
			new actionTest(settingParameter);
		}
	}
	
}
