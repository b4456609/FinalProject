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
	public ExportGIF(ArrayList<PicContainer> pics, SettingParameter setting) throws IOException
	{
		BufferedImage[] imageArray = new BufferedImage[pics.size()];
		File GIFImage = new File(setting.getPath() +"/GIFImage.gif");
		
		int i = 0;
		for (PicContainer pic : pics) 
		{
			try {
				imageArray[i++] = ImageIO.read(pic.getPicture());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		//addTextWatermarkToGif(imageArray, GIFImage, "dskjfhlsdjkfhlkd");
		saveImageArrayAsAnimatedGif(imageArray, GIFImage);
		
		addTextWatermarkToGif(imageArray, GIFImage, "dskjfhlsdjkfhlkd");
	}
	
	public static void saveImageArrayAsAnimatedGif(BufferedImage[] images, File fileToSave) throws IOException
	{
        // ?��?�??GIFImage
        GifImage gifImage = new GifImage();
        
        // 設�?每張?��?秒數(微�? 100 = 1 sec)
        gifImage.setDefaultDelay(50);
        
        // 嵌入註解
        gifImage.addComment("Animated GIF image example");
       
        // add images wrapped by GifFrame
        for (int i = 0; i < images.length; i++)
            gifImage.addGifFrame(new GifFrame(images[i]));
        
        // save animated gif image
        GifEncoder.encode(gifImage, fileToSave);
	}

	public static void addTextWatermarkToGif(BufferedImage[] images, File fileToSave, String watermarkText)throws IOException
	{
		//水印?��??��?设置（�?体�??��??�大小�?颜色
		TextPainter textPainter = new TextPainter(new Font("黑�?", Font.BOLD, 12));
		textPainter.setOutlinePaint(Color.RED);
		BufferedImage renderedWatermarkText = textPainter.renderString(watermarkText, true);
		
		//?��?对象
		GifImage gf = GifDecoder.decode(fileToSave);
		
		//?��??��?大�?
		int iw = gf.getScreenWidth();
		int ih = gf.getScreenHeight();
		
		//?��?水印大�?
		int tw = renderedWatermarkText.getWidth();
		int th = renderedWatermarkText.getHeight();
		
		//水印位置
		Point p = new Point();
		p.x = iw - tw - 5;
		p.y = ih - th - 4;
		
		//?�水??
		Watermark watermark = new Watermark(renderedWatermarkText, p); 
	
		gf = watermark.apply(GifDecoder.decode(fileToSave), true);
		
		//输出
		GifEncoder.encode(gf, fileToSave);
	}
}