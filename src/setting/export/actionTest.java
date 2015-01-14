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
	private JRadioButton autoByTimeRadioButton = new JRadioButton("By Time (default)");
	private JRadioButton autoByStepRadioButton = new JRadioButton("By Step");
	private ButtonGroup autoButtonGroup = new ButtonGroup();
	private JTextField autoByTimeDefaultSecText = new JTextField(2);
	
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

	
	// path
	private JTextField defaultPathText = new JTextField();
	//private String savePathInput;

	
	// export
	private JTextField exportDefaultSecText = new JTextField(2);
	//private int exportGIFInterval;

	private JFileChooser pathChoose = new JFileChooser();

	private SettingParameter parameters;

	
	public actionTest(final SettingParameter parameters) {
		super("Setting");
		
		this.parameters = parameters;
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
		auto.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(settingPanel, "p1");
			}
		});
		
		hotKey.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				card.show(settingPanel, "p2");
			}
		});
		
		path.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				card.show(settingPanel, "p3");
			}
		});
		
		export.addActionListener(new ActionListener() {
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
	autoByTimeDefaultSecText.setText(Integer.toString(parameters.getInterval()));
		
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
		
		defaultPathText.setText(parameters.getPath());
		defaultPathText.addActionListener(new TextFieldHandler());
		
		// export
		exportPanel.add(exportDefaultSecText);
		
		exportDefaultSecText.setText(Integer.toString(parameters.getGifInterval()));
		// export listener
		exportDefaultSecText.addActionListener(new TextFieldHandler());
		
		JLabel exportLabel = new JLabel("sec/page");
		exportPanel.add(exportLabel);
		
		this.getContentPane().add(settingPanel);
		this.setBounds(100, 100, 430, 240);
		this.setResizable(false);
		this.getContentPane().add(toolBar, BorderLayout.NORTH);
		
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		this.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosing(WindowEvent e) {
            	int state = JOptionPane.showConfirmDialog(getOuterClass(), "Are you sure ?");
                if (state == JOptionPane.OK_OPTION)
                {
                	if (autoByTimeDefaultSecText.getText().length() > 0)
                		parameters.setInterval(Integer.parseInt(autoByTimeDefaultSecText.getText()));
                		//autoByTimeInterval = Integer.parseInt(autoByTimeDefaultSecText.getText());
                	if (exportDefaultSecText.getText().length() > 0)
                		parameters.setGifInterval(Integer.parseInt(exportDefaultSecText.getText()));
                		//exportGIFInterval = Integer.parseInt(exportDefaultSecText.getText());
    				
                	getOuterClass().setVisible(false);
                	getOuterClass().dispose();
                }
                else if(state == JOptionPane.NO_OPTION)
                {
                	parameters.setAutoOption(parameters.getLastAutoOption());
                	parameters.setInterval(parameters.getLastInterval());
                	parameters.setCaptureHotKey(parameters.getLastCaptureHotKey());
                	parameters.setPauseHotKey(parameters.getLastPauseHotKey());
                	parameters.setPath(parameters.getLastPath());
                	parameters.setGifInterval(parameters.getLastGifInterval());
                	/*autoMode = lastAutoMode;
                	autoByTimeInterval = lastAutoByTimeInterval;
                	hotKeyScreenShotNumber = lastHotKeyScreenShotNumber;
                	hotKeyStopNumber = lastHotKeyStopNumber;
                	savePathInput = lastSavePathInput;
                	exportGIFInterval = lastExportGIFInterval;*/
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
				parameters.setLastAutoOption(parameters.getAutoOption());
				parameters.setAutoOption(mode);
				// lastAutoMode = autoMode;
				// autoMode = mode;
			}
			if (parameters.getAutoOption() == false)
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
				parameters.setLastGifInterval(parameters.getGifInterval());
				parameters.setGifInterval(Integer.parseInt(event.getActionCommand()));
			}
			
			// for path
			else if (event.getSource() == defaultPathText)
			{
				parameters.setLastPath(parameters.getPath());
				parameters.setPath(event.getActionCommand());
			}
			
			// for auto by time
			else
			{
				parameters.setLastInterval(parameters.getInterval());
				parameters.setInterval(Integer.parseInt(event.getActionCommand()));
			}
		}
	}

// Hot Key
	public class ComboBoxHandler implements ActionListener 
	{    
	    public void actionPerformed (ActionEvent event)
	    {
	    	if (event.getSource() == hotKeyScreenShotComboBox)
	    	{
	    		String get = (String) hotKeyScreenShotComboBox.getSelectedItem();
	    		if (get == "NONE")
	    		{
	    			parameters.setLastCaptureHotKey(parameters.getCaptureHotKey());
	    			parameters.setCaptureHotKey(-1);
	    		}
	    		else  // 全部轉成ASCII，包括數字
	    		{
	    			parameters.setLastCaptureHotKey(parameters.getCaptureHotKey());
	    			parameters.setCaptureHotKey(get.charAt(0));
	    		}
	    	}
	    	else if (event.getSource() == hotKeyStopComboBox)
	    	{
	    		String get = (String) hotKeyStopComboBox.getSelectedItem();
	    		if (get == "NONE")
	    		{
	    			parameters.setLastPauseHotKey(parameters.getPauseHotKey());
	    			parameters.setPauseHotKey(-1);
	    		}
	    		else  // 全部轉成ASCII，包括數字
	    		{
	    			parameters.setLastPauseHotKey(parameters.getPauseHotKey());
	    			parameters.setPauseHotKey(get.charAt(0));
	    		}
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
			parameters.setLastPath(parameters.getPath());
			parameters.setPath(path);
			defaultPathText.setText(path);
		}
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
		SettingParameter set= new SettingParameter();
		actionTest a = new actionTest(set);
	}

}

