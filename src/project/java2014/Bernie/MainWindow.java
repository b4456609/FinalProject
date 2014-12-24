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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

	private JPanel contentPane;
	public static PicModel PIC_MODEL = new PicModel();
	private JScrollPane commentArea;
	private JScrollPane imageGridViewScrollPanel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow frame = new MainWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 788, 470);
		
		//set menu bar
		mainMenuBar();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		
		//call construct split panel
		JSplitPane splitPane = mainSplitPane();
		contentPane.add(splitPane, BorderLayout.CENTER);
	}

	//main Vertical split
	private JSplitPane mainSplitPane() {
		JSplitPane mainVerticalSplit = new JSplitPane();
		mainVerticalSplit.setOrientation(JSplitPane.VERTICAL_SPLIT);
		
		//create simple view bottom area
		simpleViewArea(mainVerticalSplit);
		
		//center picture and comment area
		JSplitPane splitPicuterComment = new JSplitPane();
		mainVerticalSplit.setLeftComponent(splitPicuterComment);
		
		//add picture edit and preview area
		editPicArea(splitPicuterComment);
		
		//add comment area
		commentArea(splitPicuterComment);
		return mainVerticalSplit;
	}

	/**
	 * @param mainVerticalSplit
	 */
	private void simpleViewArea(JSplitPane mainVerticalSplit) {
		// bottom simple view area
		imageGridViewScrollPanel = new ImageGridViewScrollPanel(commentArea);
		mainVerticalSplit.setRightComponent(imageGridViewScrollPanel);
	}

	//picture edit and preview area
	private void editPicArea(JSplitPane splitPicuterComment) {
		JPanel PicEditPanel = new JPanel();
		splitPicuterComment.setLeftComponent(PicEditPanel);
		
		//Picture Edit Tool bar
		JToolBar PicEditToolBar = new JToolBar();
		PicEditToolBar.setBorder(UIManager.getBorder("ToolBar.border"));
		PicEditToolBar.setAlignmentX(Component.LEFT_ALIGNMENT);
		PicEditToolBar.setOrientation(SwingConstants.VERTICAL);
		PicEditToolBar.setFloatable(false);
		
		
		JButton panBtn = new JButton("Pen");
		PicEditToolBar.add(panBtn);
		
		JButton eraserBtn = new JButton("Eraser");
		PicEditToolBar.add(eraserBtn);
		
		
		//Display preview image
		JLabel lblPicture = new JLabel("Picture");
		
		lblPicture.setAlignmentX(Component.CENTER_ALIGNMENT);
		lblPicture.setForeground(Color.BLACK);
		
		
		//Group layout
		GroupLayout gl_PicEditPanel = new GroupLayout(PicEditPanel);
		gl_PicEditPanel.setHorizontalGroup(
			gl_PicEditPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_PicEditPanel.createSequentialGroup()
					.addComponent(PicEditToolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblPicture, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE))
		);
		gl_PicEditPanel.setVerticalGroup(
			gl_PicEditPanel.createParallelGroup(Alignment.LEADING)
				.addComponent(lblPicture, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
				.addComponent(PicEditToolBar, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 118, Short.MAX_VALUE)
		);
		PicEditPanel.setLayout(gl_PicEditPanel);
	}

	//comment area
	private void commentArea(JSplitPane splitPicuterComment) {
		commentArea = new CommentArea();
		splitPicuterComment.setRightComponent(commentArea);
	}

	//main menu bar
	private void mainMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnExport = new JMenu("Export");
		menuBar.add(mnExport);
		
		JMenuItem mntmPdf = new JMenuItem("PDF");
		mnExport.add(mntmPdf);
		
		JMenuItem mntmWord = new JMenuItem("Word");
		mnExport.add(mntmWord);
		
		JMenu mnSetting = new JMenu("Setting");
		menuBar.add(mnSetting);
		
		JMenuItem mntmSetting = new JMenuItem("Setting");
		mnSetting.add(mntmSetting);
	}
}
