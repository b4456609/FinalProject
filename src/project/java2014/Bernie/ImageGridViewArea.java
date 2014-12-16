package project.java2014.Bernie;

import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;

public class ImageGridViewArea extends JPanel {

	private String path = "C:\\pic";
	private ArrayList<ThumbnailLabel> pics = new ArrayList();
	
	/**
	 * Create the panel.
	 */	
	public ImageGridViewArea() {
		
		
		/**
		 * Test
		 
		String output = "";
		for(File name:files)
			output += name.toString();		
		
		JTextArea txtrGdfgearea = new JTextArea();
		txtrGdfgearea.setText(output);
		add(txtrGdfgearea);
		*/	
		new Thread(){

			/* (non-Javadoc)
			 * @see java.lang.Thread#run()
			 */
			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				File picFinder = new File(path);
				File[] files =picFinder.listFiles();
				for(File path:files){
					ImageIcon pic = new ImageIcon(path.toString());
					ThumbnailLabel aPic = new ThumbnailLabel(pic);
					
					tranferPreviewPic(aPic);
					
					//add to array list
					pics.add(aPic);
					
					//set display size
					aPic.setPreferredSize(new Dimension(200, 200));
					
					//display
					add(aPic);
				}
			}
			
		}.start();
		

	}

	private void tranferPreviewPic(ThumbnailLabel aPic) {
		//add mouse click listener
		aPic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				//lblNewLabel.setIcon(image2);
			}
		});
	}

}
