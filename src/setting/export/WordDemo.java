package setting.export;


import java.awt.Color;    
import java.io.FileNotFoundException;    
import java.io.FileOutputStream;    
import java.io.FileReader;
import java.io.IOException;    
//import com.itextpdf.text.BadElementException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Image;



import java.util.ArrayList;

import project.java2014.Bernie.PicContainer;

import com.lowagie.text.Element;
import com.lowagie.text.Cell;    
import com.lowagie.text.Document;    
import com.lowagie.text.DocumentException;    
import com.lowagie.text.Font;    
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;    
import com.lowagie.text.Paragraph;    
import com.lowagie.text.Table;    
import com.lowagie.text.rtf.RtfWriter2;    

public class WordDemo {   
	
	private static String inputFile;
	private static int counter = 1;
	
	public WordDemo(ArrayList<PicContainer> pics, SettingParameter setting)
	{    
		try
		{   
			Document document = new Document(PageSize.A4);
			
			RtfWriter2.getInstance(document, new FileOutputStream(setting.getPath() + setting.getFolderName()  + "/export" + counter++ + ".doc"));
			document.open();
			
			for (PicContainer pic : pics) {
				Image image = Image.getInstance(pic.getPicture().toString());
				image.setAlignment(1);
				image.scaleToFit(500, 500);

				// filedata放到paragraph的那層
				Paragraph paragraph = new Paragraph(pic.getComment());
				paragraph.setAlignment(1);

				// 寫入document中
				
				document.add(paragraph);
				document.add(image);
			}
			document.close();
		}
		catch (FileNotFoundException e)
		{    
			e.printStackTrace();    
		}
		catch (DocumentException e)
		{    
			e.printStackTrace();    
		}
		catch (IOException e)
		{    
			e.printStackTrace();    
		}
	}       
}   