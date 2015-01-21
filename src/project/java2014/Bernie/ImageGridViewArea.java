package project.java2014.Bernie;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

public class ImageGridViewArea extends JPanel {

	private ArrayList<PicContainer> pics;
	private ArrayList<ThumbnailLabel> diplayedPic = new ArrayList<ThumbnailLabel>();
	private int selectedPicIndex = 0;
	private MouseListener itemClick = new itemClick();
	private final PicModel picModel;
	private PicEditArea picEditPanel;

	public static int NULL_INDEX = -100;
	private final JScrollPane commentArea;
	
	/**
	 * Create the panel.
	 * @param picEditPanel 
	 */	
	public ImageGridViewArea(JScrollPane commentArea, PicModel picModel, JPanel picEditPanel) {
		//set pic edit panel 
		this.picEditPanel = (PicEditArea)picEditPanel;
		//set pic model
		this.picModel = picModel;

		this.commentArea = commentArea;

		refresh();

	}
	
	public void refresh(){

		// remove all component
		this.removeAll();
		this.revalidate();
		this.repaint();
		
		
		
		//remove array list
		diplayedPic.clear();
		
		//get display pic from model
		getPicDisplay();
				
		//set Edit picture
		// delete picture index will be null 
		if(selectedPicIndex != NULL_INDEX)
			picEditPanel.setPicture(pics.get(selectedPicIndex).getPicture().toString());
		else
			picEditPanel.setPicture(pics.get(0).getPicture().toString());

		int i = 1;
		for(PicContainer pic:pics){

			//flush imgae cache
			ImageIcon img = new ImageIcon(pic.getPicture().toString());			
			img.getImage().flush();
			
			//reload new image
			img = new ImageIcon(pic.getPicture().toString());
			ThumbnailLabel aImg = new ThumbnailLabel(img);
			
			//add click listener 
			aImg.addMouseListener(itemClick);
			
			//add to array list
			diplayedPic.add(aImg);
						
			//set display size
			aImg.setPreferredSize(new Dimension(200, 200));
			
			
			setImgBorder(aImg, i++);
			
			//display
			add(aImg);
		}
	}
	
	private void getPicDisplay(){
		pics = picModel.getPics();
	}

	public int getSelectedPicIndex() {
		return selectedPicIndex;
	}
	
	public void setSelectedPicIndex(int selectedPicIndex) {
		if(selectedPicIndex == NULL_INDEX)
			this.selectedPicIndex = NULL_INDEX;
		else if(selectedPicIndex >= diplayedPic.size())
			this.selectedPicIndex = diplayedPic.size() - 1;
		else if(selectedPicIndex < 0)
			this.selectedPicIndex = 0;
		else
			this.selectedPicIndex = selectedPicIndex;
	}
	
	private void setImgBorder(ThumbnailLabel targetPic, int number){
		if( number - 1== selectedPicIndex){
			Border border = BorderFactory.createRaisedSoftBevelBorder();
			TitledBorder title =  BorderFactory.createTitledBorder(border, "Capture" + number);
			targetPic.setBorder(title);
		}
		else{
			TitledBorder title;
			title = BorderFactory.createTitledBorder("Capture" + number);
			targetPic.setBorder(title);
		}
	}
		
	///mouse listener
	private class itemClick extends MouseAdapter {
		@Override
		public void mouseClicked(MouseEvent e) {
			// get clicked pic
			ThumbnailLabel targetPic = (ThumbnailLabel)e.getSource();			
			
			//save comment to image
			((CommentArea) commentArea).setComment(selectedPicIndex);
			
			//get pic's index
			selectedPicIndex = diplayedPic.indexOf(targetPic);
			
			//savePicture
			((PicEditArea)picEditPanel).savePicture();		
			
			//update comment area to correspond image
			((CommentArea)commentArea).getComment(selectedPicIndex);
			
			refresh();
		}
	}
	
}


