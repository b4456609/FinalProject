package project.java2014.Bernie;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

public class CommentArea extends JScrollPane {
	private final JTextArea textArea ;
	private final PicModel picModel;
	
	//constructor
	CommentArea(PicModel picModel){
		
		//set pic model
		this.picModel = picModel;
		
		//title border
		this.setBorder(new TitledBorder(null, "Comment", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		//text area
		textArea = new JTextArea();
		textArea.setLineWrap(true);
		setViewportView(textArea);
	}
	
	public void getComment(int index){
		if(index == ImageGridViewArea.NULL_INDEX)
			return;
		String comment = picModel.getText(index);
		textArea.setText(comment);
	}
	
	public void setComment(int index){
		if(index == ImageGridViewArea.NULL_INDEX)
			return;
		String comment = textArea.getText();
		picModel.setText(index, comment);
	}
}
