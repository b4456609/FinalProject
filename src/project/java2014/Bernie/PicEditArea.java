package project.java2014.Bernie;

import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.LayoutStyle.ComponentPlacement;

public class PicEditArea extends JPanel {

	/**
	 * Create the panel.
	 */
	public PicEditArea() {
		
		JToolBar toolBar = new JToolBar();
		toolBar.setOrientation(SwingConstants.VERTICAL);
		
		JLabel lblNewLabel = new JLabel("New label");
		GroupLayout groupLayout = new GroupLayout(this);
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(toolBar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 383, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addComponent(toolBar, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
				.addComponent(lblNewLabel, GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
		);
		
		JButton btnNewButton = new JButton("New button");
		toolBar.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("New button");
		toolBar.add(btnNewButton_1);
		setLayout(groupLayout);

	}
}
