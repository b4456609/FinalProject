package project.java2014.Bernie;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JToolBar;

public class ImageGridViewArea extends JPanel {

	//private String path = "C:\\pic";
	private ArrayList<PicContainer> pics;
	private ArrayList<ThumbnailLabel> diplayedPic = new ArrayList<ThumbnailLabel>();
	private ThumbnailLabel selectedPic;
	private MouseListener itemClick = new itemClick();
	
	
	/**
	 * Create the panel.
	 */	
	public ImageGridViewArea() {
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.addMouseListener(itemClick);
		add(lblNewLabel);
		
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
				/*super.run();
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
				
				

				////////////////////////test

				ThumbnailLabel aPic = new ThumbnailLabel(new ImageIcon("‪D:\\我的圖片\\Screenshots\\HDR001.png"));
				//set display size
				aPic.setPreferredSize(new Dimension(200, 200));
				
				//display
				add(aPic);
				
				ThumbnailLabel aPic1 = new ThumbnailLabel(new ImageIcon("‪D:\\我的圖片\\Screenshots\\HDR002.png"));
				//set display size
				aPic.setPreferredSize(new Dimension(200, 200));
				
				//display
				add(aPic1);*/
				

				getPicDisplay();
				refresh();
			}
			
		}.start();
		

	}
	
	private void refresh(){

		this.removeAll();
		this.revalidate();
		this.repaint();
		
		diplayedPic.clear();
	
		for(PicContainer pic:pics){
			ImageIcon img = new ImageIcon(pic.getPicture().toString());
			ThumbnailLabel aImg = new ThumbnailLabel(img);
			
			//add click listener 
			aImg.addMouseListener(itemClick);
			
			//add to array list
			diplayedPic.add(aImg);
			
			//transfer to edit area
			tranferPreviewPic(aImg);
			
			//set display size
			aImg.setPreferredSize(new Dimension(200, 200));
			
			//display
			add(aImg);
		}
	}
	
	private void getPicDisplay(){
		pics = MainWindow.PIC_MODEL.getPic();
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

	public void moveForward(){
		int index = diplayedPic.indexOf(selectedPic);
		
		//no need to move forward
		if(index == 0)
			return;
		
		//save temp file
		PicContainer temp1 = pics.get(index);
		PicContainer temp2 = pics.get(index-1);
		
		pics.set(index - 1, temp1);
		pics.set(index, temp2);

		System.out.println("clicked");

		refresh();
	}
	
	public void moveBackWard(){
		int index = diplayedPic.indexOf(selectedPic);
		
		//no need to move forward
		if(index == diplayedPic.size())
			return;
		
		//save temp file
		PicContainer temp1 = pics.get(index);
		PicContainer temp2 = pics.get(index+1);
		
		pics.set(index + 1, temp1);
		pics.set(index, temp2);

		System.out.println("clicked");

		refresh();
	}
	
	///mouse listener
	private class itemClick extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			selectedPic = (ThumbnailLabel)e.getSource();
			moveBackWard();
		}
	}
	
}


