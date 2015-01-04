package project.java2014.Bernie;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import java.awt.Dimension;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

import java.awt.Component;

import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

import setting.export.actionTest;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	private final PicModel picModel;
	private JScrollPane commentArea;
	private JScrollPane imageGridViewScrollPanel;
	private JSplitPane mainVerticalSplit;
	private JSplitPane splitPicuterComment;
	private JPanel PicEditPanel;
	private JMenuBar menuBar;
	private JMenu mnExport;
	private JMenuItem mntmPdf;	
	private JMenuItem mntmWord;	
	private JMenu mnSetting;	
	private JMenuItem mntmSetting;
	private actionTest a;


	/**
	 * Create the frame.
	 */
	public MainWindow() {
		actionTest a = new actionTest();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 470);
		
		//set data model
		 picModel = new PicModel();
		 
		//set menu bar
		mainMenuBar();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		//initialize first for other view use
		commentArea = new CommentArea(picModel);
		
		//call construct split panel
		mainSplitPane();
		topSplitePanel();
		
	}

	//main Vertical split
	private void mainSplitPane() {
		mainVerticalSplit = new JSplitPane();
		mainVerticalSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		//create simple view bottom area
		imageGridViewScrollPanel = new ImageGridViewScrollPanel(commentArea, picModel);
		mainVerticalSplit.setRightComponent(imageGridViewScrollPanel);
		
		contentPane.add(mainVerticalSplit, BorderLayout.CENTER);
	}
	
	private void topSplitePanel(){
		//center picture and comment area
		splitPicuterComment = new JSplitPane();
		mainVerticalSplit.setLeftComponent(splitPicuterComment);
		
		//add comment area
		
		splitPicuterComment.setRightComponent(commentArea);
		
		//add picture edit and preview area
		editPicArea();
	}

	//picture edit and preview area
	private void editPicArea() {
		PicEditPanel = new PicEditArea();
		splitPicuterComment.setLeftComponent(PicEditPanel);
	}

	//main menu bar
	private void mainMenuBar() {
		menuBar = new JMenuBar();
		mnExport = new JMenu("Export");
		mntmPdf = new JMenuItem("PDF");
		mntmWord = new JMenuItem("Word");
		mnSetting = new JMenu("Setting");
		mntmSetting = new JMenuItem("Setting");
		mntmSetting.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
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
				catch(Exception exception)
				{
				    // TODO exception
				}
				actionTest a = new actionTest();
				
		    }
		});
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnExport);		
		mnExport.add(mntmPdf);	
		mnExport.add(mntmWord);		
		menuBar.add(mnSetting);		
		mnSetting.add(mntmSetting);
	}
}
