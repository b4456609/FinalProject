package capture;
import java.awt.*;

import javax.swing.*;

import java.awt.event.*;
import java.lang.*;

import javax.swing.ImageIcon;

import setting.export.SettingParameter;

public class SmallTool extends JFrame
{
	private JButton startButton;
	private JButton stopButton;
	private JButton screenCutButton;
	private JButton settingButton;
	private SettingParameter settingP;
	
	public SmallTool(SettingParameter settingP)
	{
		super("SmallTool");
		
		this.settingP = settingP;
		
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
	}
	
	private class startHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent click)
		{
			System.out.printf("Start.");
			AutoCapture autoCapture =  new AutoCapture(settingP);
			autoCapture.start();
		}
	}
	
	private class stopHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent click)
		{
			System.out.printf("Stop.");
		}
	}
	
	private class cutHandler implements ActionListener
	{
		public void actionPerformed( ActionEvent click)
		{
			System.out.printf("ScreenCut.");
			ManualCapture manualCapture =  new ManualCapture(settingP);
			manualCapture.start();
		}
	}
	
	private class settingButton implements ActionListener
	{
		public void actionPerformed( ActionEvent click)
		{
			System.out.printf("setting.");
		}
	}
	
}
