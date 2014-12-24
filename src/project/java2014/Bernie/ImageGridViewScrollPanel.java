package project.java2014.Bernie;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JToolBar;

public class ImageGridViewScrollPanel extends JScrollPane {
	private final ImageGridViewArea imageGridViewArea;
	private final JToolBar toolBar = new JToolBar();
	private final JButton btnDelete = new JButton("Delete");
	private final JButton btnMoveForward = new JButton("Move Forward");
	private final JButton btnMoveBackward = new JButton("Move Backward");
	private final ButtonHandler buttonHandler = new ButtonHandler();
	private final JScrollPane commentscrollPane;

	/**
	 * Create the panel.
	 */
	public ImageGridViewScrollPanel(JScrollPane commentscrollPane) {
		// get the variable
		this.commentscrollPane = commentscrollPane;
		
		// new image grid view area
		this.imageGridViewArea = new ImageGridViewArea(commentscrollPane);
		
		// set tool bar
		toolbar();
		
		// add bottom display multiple pictures area
		this.setViewportView(imageGridViewArea);
	}

	private void toolbar() {
		toolBar.setFloatable(false);
		this.setColumnHeaderView(toolBar);

		btnDelete.addActionListener(buttonHandler);
		btnMoveForward.addActionListener(buttonHandler);
		btnMoveBackward.addActionListener(buttonHandler);

		toolBar.add(btnDelete);
		toolBar.add(btnMoveForward);
		toolBar.add(btnMoveBackward);

	}

	private class ButtonHandler implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			// get selected picture
			int index = imageGridViewArea.getSelectedPicIndex();

			if (e.getSource() == btnDelete) {
				// successful execute
				if (MainWindow.PIC_MODEL.deleteItem(index)) {

					// set index to -100
					// not exist
					imageGridViewArea.setSelectedPicIndex(ImageGridViewArea.NULL_INDEX);
					
					// refresh
					imageGridViewArea.refresh();

				}
			} else if (e.getSource() == btnMoveForward) {
				if (MainWindow.PIC_MODEL.swap(index, index - 1)) {

					imageGridViewArea.setSelectedPicIndex(index - 1);

					// refresh
					imageGridViewArea.refresh();
				}
			} else if (e.getSource() == btnMoveBackward) {
				if (MainWindow.PIC_MODEL.swap(index, index + 1)) {
					// set new index = index + 1
					imageGridViewArea.setSelectedPicIndex(index + 1);
					
					// refresh
					imageGridViewArea.refresh();
				}
			}
		}

	}

}
