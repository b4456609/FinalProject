package setting.export;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;


public class actionTest extends JFrame {
	private JPanel settingPanel = null; // 主要的JPanel，该JPanel的布局管理将被设置成CardLayout
	
	private CardLayout card = null; // CardLayout布局管理器
	
	// tool bar
	private JToolBar toolBar = new JToolBar();
	private ButtonGroup toggleButton = new ButtonGroup();
	private JToggleButton auto = new JToggleButton("Auto Mode");
	private JToggleButton hotKey = new JToggleButton("Hot Key");
	private JToggleButton path = new JToggleButton("Path");
	private JToggleButton export = new JToggleButton("Export");
	
	// auto panel 的選項
	private JRadioButton autoByTimeRadioButton = new JRadioButton("By Time", true);
	private JRadioButton autoByStepRadioButton = new JRadioButton("By Step");
	private ButtonGroup autoButtonGroup = new ButtonGroup();
	private boolean autoMode = true; // auto mode 目前狀態(true for time)
	private JTextField autoByTimeDefaultSecText = new JTextField(2);
	private int autoByTimeInterval;
	
	// tool bar 選項的panel
	private JPanel autoPanel = new JPanel(new BorderLayout());
	private JTabbedPane hotKeyPanel = new JTabbedPane();
	private JPanel pathPanel = new JPanel();
	private JPanel exportPanel = new JPanel();
	
	// hot key
	private JPanel hotKeyScreenShotPanel = new JPanel();
	private JComboBox hotKeyScreenShotComboBox = new JComboBox();
	private JPanel hotKeyStopPanel = new JPanel();
	private JComboBox hotKeyStopComboBox = new JComboBox();
	private int hotKeyScreenShotNumber;
	private int hotKeyStopNumber;
	
	// path
	private JTextField defaultPathText = new JTextField("C:/");
	private String savePathInput;

	
	// export
	private JTextField exportDefaultSecText = new JTextField(2);
	private int exportGIFInterval;

	private JFileChooser pathChoose = new JFileChooser();
	
	private boolean lastAutoMode = true;
	private int lastAutoByTimeInterval;
	private int lastHotKeyScreenShotNumber =-1;
	private int lastHotKeyStopNumber = -1;
	private String lastSavePathInput = "C:/";
	private int lastExportGIFInterval;

	
	public actionTest() {
		super("Setting");
		
		/**创建一个具有指定的水平和垂直间隙的新卡片布局*/
		card = new CardLayout(5, 5);
		settingPanel = new JPanel(card); // JPanel的布局管理将被设置成CardLayout
		
		toggleButton.add(auto);
		toggleButton.add(hotKey);
		toggleButton.add(path);
		toggleButton.add(export);
		
		toolBar.add(auto);
		toolBar.add(hotKey);
		toolBar.add(path);
		toolBar.add(export);
		
		settingPanel.add(autoPanel, "p1");
		settingPanel.add(hotKeyPanel, "p2");
		settingPanel.add(pathPanel, "p3");
		settingPanel.add(exportPanel, "p4");
		
		// tool bar listener
		/**下面是翻转到卡片布局的某个组件，可参考API中的文档*/
		auto.addActionListener(new ActionListener(){ // 上一步的按钮动作
			public void actionPerformed(ActionEvent e) {
				card.show(settingPanel, "p1");
			}
		});
		
		hotKey.addActionListener(new ActionListener(){ // 下一步的按钮动作
			public void actionPerformed(ActionEvent e) {
				card.show(settingPanel, "p2");
			}
		});
		
		path.addActionListener(new ActionListener() { // 直接翻转到p_1
			public void actionPerformed(ActionEvent e) {
				card.show(settingPanel, "p3");
			}
		});
		
		export.addActionListener(new ActionListener() { // 直接翻转到p_2
			public void actionPerformed(ActionEvent e) {
				card.show(settingPanel, "p4");
			}
		});		
		
		// auto mode
		// 拿掉虛線
		autoByTimeRadioButton.setFocusPainted(false);
		autoByStepRadioButton.setFocusPainted(false);
		
		JPanel autoByTimePanel = new JPanel();
		autoByTimePanel.add(autoByTimeRadioButton);
		autoByTimePanel.add(autoByTimeDefaultSecText);
		JLabel autoByTimeLabel = new JLabel("sec/page");
		autoByTimePanel.add(autoByTimeLabel);
		
		autoPanel.add(autoByTimePanel, BorderLayout.NORTH);
		JPanel autoByStepPanel = new JPanel();
		autoByStepPanel.add(autoByStepRadioButton);
		autoPanel.add(autoByStepPanel);
		
		autoButtonGroup.add(autoByTimeRadioButton);
		autoButtonGroup.add(autoByStepRadioButton);
		
		// auto mode listener
		autoByTimeRadioButton.addItemListener(new RadioButtonHandler(true));
		autoByStepRadioButton.addItemListener(new RadioButtonHandler(false));
		autoByTimeDefaultSecText.addActionListener(new TextFieldHandler());
		//autoByTimeDefaultSecText.addKeyListener(new secTextFieldHandler());
		
		// hot key
		// hot key - screen shot
		hotKeyPanel.addTab("Screen Shot", null, hotKeyScreenShotPanel, null);
		
		JLabel hotKeyScreenShotALTLabel = new JLabel("alt + ");
		hotKeyScreenShotPanel.add(hotKeyScreenShotALTLabel);
		
		hotKeyScreenShotComboBox.setModel(new DefaultComboBoxModel(new String[] {"NONE", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}));
		hotKeyScreenShotPanel.add(hotKeyScreenShotComboBox);
		
		
		// hot key - stop
		hotKeyPanel.addTab("Stop", null, hotKeyStopPanel, null);
		
		JLabel hotKeyStopALTLabel = new JLabel("alt + ");
		hotKeyStopPanel.add(hotKeyStopALTLabel);
		
		hotKeyStopComboBox.setModel(new DefaultComboBoxModel(new String[] {"NONE", "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"}));
		hotKeyStopPanel.add(hotKeyStopComboBox);
		
		// register hot key
		hotKeyScreenShotComboBox.addActionListener(new ComboBoxHandler());
		hotKeyStopComboBox.addActionListener(new ComboBoxHandler());
		
		
		// path
		pathPanel.add(defaultPathText);
		defaultPathText.setColumns(30);
		defaultPathText.setEditable(false);
		
		JLabel spaceLabel = new JLabel("  ");
		pathPanel.add(spaceLabel);
		
		JButton pathBrowse = new JButton("browse..");
		pathPanel.add(pathBrowse);
		
		pathBrowse.addActionListener(new ButtonHandler());
		pathBrowse.setActionCommand("browse..");
		
		defaultPathText.addActionListener(new TextFieldHandler());
		
		// export
		exportPanel.add(exportDefaultSecText);
		// export listener
		exportDefaultSecText.addActionListener(new TextFieldHandler());
		
		JLabel exportLabel = new JLabel("sec/page");
		exportPanel.add(exportLabel);
		
		this.getContentPane().add(settingPanel);
		this.setBounds(100, 100, 430, 240);
		this.setResizable(false);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {
            //I skipped unused callbacks for readability

            @Override
            public void windowClosing(WindowEvent e) {
            	int state = JOptionPane.showConfirmDialog(getOuterClass(), "Are you sure ?");
                if (state == JOptionPane.OK_OPTION)
                {
                	if (autoByTimeDefaultSecText.getText().length() > 0)
                		autoByTimeInterval = Integer.parseInt(autoByTimeDefaultSecText.getText());
                	if (exportDefaultSecText.getText().length() > 0)
                		exportGIFInterval = Integer.parseInt(exportDefaultSecText.getText());
    				
                	getOuterClass().setVisible(false);
                	getOuterClass().dispose();
                }
                else if(state == JOptionPane.NO_OPTION)
                {
                	autoMode = lastAutoMode;
                	autoByTimeInterval = lastAutoByTimeInterval;
                	hotKeyScreenShotNumber = lastHotKeyScreenShotNumber;
                	hotKeyStopNumber = lastHotKeyStopNumber;
                	savePathInput = lastSavePathInput;
                	exportGIFInterval = lastExportGIFInterval;
                	getOuterClass().setVisible(false);
                	getOuterClass().dispose();
                }
               	
            }
        });
		
		this.setVisible(true);
	}
	
// Auto	
	// inner class to handle radio button events
	private class RadioButtonHandler implements ItemListener
	{
		private boolean mode;
		
		public RadioButtonHandler (boolean mode)
		{
			this.mode = mode;
		}
		
		public void itemStateChanged (ItemEvent event)
		{
			autoByTimeDefaultSecText.setEditable(true);
			if (event.getStateChange() == ItemEvent.SELECTED)
			{	
				lastAutoMode = autoMode;
				autoMode = mode;
			}
			if (autoMode == false)
				autoByTimeDefaultSecText.setEditable(false);
				
		}
			
	}
	
// TextField
	// inner class to handle TextField
	private class TextFieldHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{		
			// for export
			if (event.getSource() == exportDefaultSecText)
			{
				lastExportGIFInterval = exportGIFInterval;
				exportGIFInterval = Integer.parseInt(event.getActionCommand());
			}
			
			// for path
			else if (event.getSource() == defaultPathText)
			{
				lastSavePathInput = savePathInput;
				savePathInput = event.getActionCommand();
			}
			
			// for auto by time
			else
			{

				lastAutoByTimeInterval = autoByTimeInterval;
				autoByTimeInterval = Integer.parseInt(event.getActionCommand());
			}
		}
	}
		
	/*public class secTextFieldHandler extends KeyAdapter
	{
		public void keyTyped(KeyEvent event)
		{
			if (event.getSource() == autoByTimeDefaultSecText)
			{
				int keyChar = event.getKeyCode();				
				if(keyChar >= KeyEvent.VK_0 && keyChar <= KeyEvent.VK_9)
				{
					lastAutoByTimeInterval = autoByTimeInterval;
					autoByTimeInterval = keyChar;//Character.getNumericValue(keyChar);
				}
				else
					event.consume(); //关键，屏蔽掉非法输入
			}
		}
	}*/

// Hot Key
	public class ComboBoxHandler implements ActionListener 
	{    
	    public void actionPerformed (ActionEvent event)
	    {
	    	if (event.getSource() == hotKeyScreenShotComboBox)
	    	{
	    		String get = (String) hotKeyScreenShotComboBox.getSelectedItem();
	    		if (get == "NONE")
	    			hotKeyScreenShotNumber = -1;
	    		else  // 全部轉成ASCII，包括數字
	    			hotKeyScreenShotNumber = get.charAt(0);
	    	}
	    	else if (event.getSource() == hotKeyStopComboBox)
	    	{
	    		String get = (String) hotKeyStopComboBox.getSelectedItem();
	    		if (get == "NONE")
	    			hotKeyStopNumber = -1;
	    		else  // 全部轉成ASCII，包括數字
	    			hotKeyStopNumber = get.charAt(0);
	    	}
	    }
	    
	}
	
// Path - browse
	public class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
	    {
	        if (event.getActionCommand() == "browse..")
	        {
	        	pathChoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	        	int result = pathChoose.showOpenDialog(getOuterClass());
	        	if( result == JFileChooser.APPROVE_OPTION)
	        		setPath(pathChoose.getSelectedFile().getPath());
	        }
	    }
		
		public void setPath(String path)
		{
			lastSavePathInput = savePathInput;
			savePathInput = path; 
			defaultPathText.setText(path);
		}
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
	
	// get outer class Object
	public actionTest getOuterClass()
	{
		return this;
	}
	
	public static void main(String[] args) 
	{
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
		catch(Exception e)
		{
		    // TODO exception
		}
		actionTest a = new actionTest();
	}

}

