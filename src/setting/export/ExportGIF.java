package setting.export;
// http://docs.oracle.com/javase/tutorial/2d/images/index.html
// http://www.gif4j.com/java-api-light/com/gif4j/light/GifEncoder.html

import com.gif4j.GifDecoder;
import com.gif4j.GifEncoder;
import com.gif4j.GifFrame;
import com.gif4j.GifImage;
import com.gif4j.TextPainter;
import com.gif4j.Watermark;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Point;
import java.awt.image.BufferedImage;

import javax.imageio.*;

import project.java2014.Bernie.PicContainer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class ExportGIF
{
	private static int counter = 1;
	public ExportGIF(ArrayList<PicContainer> pics, SettingParameter setting) throws IOException
	{
		BufferedImage[] imageArray = new BufferedImage[pics.size()];
		File GIFImage = new File(setting.getPath() + setting.getFolderName() +"/GIFImage"+ counter++ +".gif");
		
		int i = 0;
		for (PicContainer pic : pics) 
		{
			try {
				imageArray[i] = ImageIO.read(pic.getPicture());
				imageArray[i] = addTextWatermarkToImage(imageArray[i++], pic.getComment());
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		saveImageArrayAsAnimatedGif(imageArray, GIFImage);
	}
	
	public static void saveImageArrayAsAnimatedGif(BufferedImage[] images, File fileToSave) throws IOException
	{
        GifImage gifImage = new GifImage();
        
        // 設�?每張?��?秒數(微�? 100 = 1 sec)
        gifImage.setDefaultDelay(50);
        
        // 播放次數
        gifImage.setLoopNumber(0);
               
        // add images wrapped by GifFrame
        for (int i = 0; i < images.length; i++)
        {        	
        	GifFrame nextFrame = new GifFrame(images[i]);
        	nextFrame.setDisposalMethod(GifFrame.DISPOSAL_METHOD_RESTORE_TO_BACKGROUND_COLOR);
        	gifImage.addGifFrame(nextFrame);
        }

        // save animated gif image
        GifEncoder.encode(gifImage, fileToSave);
	}

	 public BufferedImage addTextWatermarkToImage(BufferedImage image, String text)
	 {
	       //create new TextPainter
	       TextPainter textPainter = new TextPainter(new Font("Verdana", Font.BOLD, 40));
	       textPainter.setOutlinePaint(Color.WHITE);
	       
	       //render the specified text outlined
	       BufferedImage renderedWatermarkText = textPainter.renderString(text,true);
	       
	       //create new Watermark
	       Watermark watermark =
	               new Watermark(renderedWatermarkText, Watermark.LAYOUT_BOTTOM_RIGHT);
	       //apply watermark to the specified image and return the result
	       return watermark.apply(image);
	 }
}