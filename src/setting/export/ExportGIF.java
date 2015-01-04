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

import java.io.File;
import java.io.IOException;

public class ExportGIF
{
	public static void main(String args[]) throws IOException
	{
		BufferedImage[] imageArray = new BufferedImage[3];
		File GIFImage = new File("E:/javatest/GIFImage.gif");
		
		
		for (int i = 0; i < 3; i++)
		{
			String imagePath = "E:/javatest/" + (i+3) + ".png";
			imageArray[i] = ImageIO.read(new File(imagePath));
		}
		
		//addTextWatermarkToGif(imageArray, GIFImage, "dskjfhlsdjkfhlkd");
		saveImageArrayAsAnimatedGif(imageArray, GIFImage);
		
		addTextWatermarkToGif(imageArray, GIFImage, "dskjfhlsdjkfhlkd");
	}
	
	public static void saveImageArrayAsAnimatedGif(BufferedImage[] images, File fileToSave) throws IOException
	{
        // ?µè?ä¸??GIFImage
        GifImage gifImage = new GifImage();
        
        // è¨­å?æ¯å¼µ?“é?ç§’æ•¸(å¾®ç? 100 = 1 sec)
        gifImage.setDefaultDelay(50);
        
        // åµŒå…¥è¨»è§£
        gifImage.addComment("Animated GIF image example");
       
        // add images wrapped by GifFrame
        for (int i = 0; i < images.length; i++)
            gifImage.addGifFrame(new GifFrame(images[i]));
        
        // save animated gif image
        GifEncoder.encode(gifImage, fileToSave);
	}

	public static void addTextWatermarkToGif(BufferedImage[] images, File fileToSave, String watermarkText)throws IOException
	{
		//æ°´å°?å??–ã?è®¾ç½®ï¼ˆå?ä½“ã??·å??å¤§å°ã?é¢œè‰²
		TextPainter textPainter = new TextPainter(new Font("é»‘é?", Font.BOLD, 12));
		textPainter.setOutlinePaint(Color.RED);
		BufferedImage renderedWatermarkText = textPainter.renderString(watermarkText, true);
		
		//?¾ç?å¯¹è±¡
		GifImage gf = GifDecoder.decode(fileToSave);
		
		//?·å??¾ç?å¤§å?
		int iw = gf.getScreenWidth();
		int ih = gf.getScreenHeight();
		
		//?·å?æ°´å°å¤§å?
		int tw = renderedWatermarkText.getWidth();
		int th = renderedWatermarkText.getHeight();
		
		//æ°´å°ä½ç½®
		Point p = new Point();
		p.x = iw - tw - 5;
		p.y = ih - th - 4;
		
		//? æ°´??
		Watermark watermark = new Watermark(renderedWatermarkText, p); 
	
		gf = watermark.apply(GifDecoder.decode(fileToSave), true);
		
		//è¾“å‡º
		GifEncoder.encode(gf, fileToSave);
	}
}