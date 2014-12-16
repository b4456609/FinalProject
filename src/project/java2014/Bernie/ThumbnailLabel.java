/**
 * Copyright 2009 Daniel Dee.
 */
package project.java2014.Bernie;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

/**
 * A JLabel extension that automatically resizes the image contained in it when the JLabel resizes
 * while maintaining the aspect ratio.
 * 
 * @author Daniel Dee, 7/2/09, 7/17/09
 *
 */
public class ThumbnailLabel extends JLabel
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -159519665103703373L;
	private Border iconBorder = null;
	
	/**
	 * Default constructor.
	 */
	public ThumbnailLabel()
	{		
	}
	
	/**
	 * Constructor. Takes an ImageIcon that is to be displayed inside the JLabel.
	 * 
	 * @param icon an ImageIcon to place inside the JLabel
	 */
	public ThumbnailLabel(ImageIcon icon)
	{
		super(icon);
	}
	
	/**
	 * Sets the icon for this label.
	 * 
	 * @param icon
	 */
	public void setIcon(ImageIcon icon)
	{
		super.setIcon(icon);
	}
	
	/**
	 * Sets the border around the ImageIcon (not the label component).
	 * 
	 * @param iconBorder a Border object
	 */
	public void setIconBorder(Border iconBorder)
	{
		this.iconBorder = iconBorder;
	}
	
	/**
	 * Overrides JLabel's paintComponent to recalculate the embedded image's size
	 * when drawing.
	 */
	protected void paintComponent(Graphics g)
	{
		int top = 0, bottom = 0, left = 0, right = 0;
		
		Border border = getBorder();
		Insets insets = null;
		
		if(border != null)
			insets = border.getBorderInsets(this);
		else
			insets = getInsets();
		
		if(insets != null)
		{
			top = insets.top;
			bottom = insets.bottom;
			left = insets.left;
			right = insets.right;
		}
		
		int halign = getHorizontalAlignment();
		int valign = getVerticalAlignment();
		
		ImageIcon icon = (ImageIcon) getIcon();
		int iconWidth = icon.getIconWidth();
		int iconHeight = icon.getIconHeight();
		double iconAspect = (double) iconHeight / iconWidth;

		int w = getWidth() - left - right;
		int h = getHeight() - top - bottom;
		double canvasAspect = (double) h / w;

		int x = left, y = top;
		
		// Maintain aspect ratio.
		if(iconAspect < canvasAspect)
		{
			// Drawing space is taller than image.
			h = (int) (w * iconAspect);

			switch(valign)
			{
			case SwingConstants.TOP:
				y = top;
				break;
			case SwingConstants.BOTTOM:
				y = getHeight() - bottom - h; 
				break;
			default: // CENTER
				y = (getHeight() - top - bottom - h) / 2 + top; // center it along vertical
				break;
			}
		}
		else
		{
			// Drawing space is wider than image.
			w = (int) (h / iconAspect);

			switch(halign)
			{
			case SwingConstants.LEFT:
				x = left;
				break;
			case SwingConstants.RIGHT:
 				x = getWidth() - right - w;
				break;
			default: // CENTER
				x = (getWidth() - left - right - w) / 2 + left; // center it along horizontal
				break;
			}
		}

		Image img = icon.getImage();
		g.drawImage(img, x, y, w + x, h + y, 0, 0, iconWidth, iconHeight, null);
		
		// Draws the border around the ImageIcon.
		if(iconBorder != null)
			iconBorder.paintBorder(this, g, x, y, w, h);
	}
}