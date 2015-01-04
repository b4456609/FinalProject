package setting.export;


import java.awt.Color;    
import java.io.FileNotFoundException;    
import java.io.FileOutputStream;    
import java.io.FileReader;
import java.io.IOException;    
//import com.itextpdf.text.BadElementException;
//import com.itextpdf.text.Element;
//import com.itextpdf.text.Image;


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
   
/**   
  * ï¿½î??£wordï¿½ï‹ªï¹??‡ä»¿??    
  * 1,?±ç?ï¿½ï¿½?ªï?    
  * 2,ï¿½î????ï¿½è‘µ?‹è?ï¿½ï¿½ï¿?  
  * 3,ï¿½îš¥ï¿½ï¿½?ªï?    
  * 4,ï¿½î•¬ï¿½ç¢ï¿½è?ï¿½î??¯ï¿½?³ï?    
  * 5,ï¿½å–²î£¡ï¿½?ªï?   
  */   
public class WordDemo {   
	
	private static String inputFile;
	
	public static void main(String[] args)
	{    
		try
		{   
			// ï¿½î??£wordï¿½ï‹ªï¹??Ÿå??½èµæ¡ƒçˆ¾?˜î?ï¿½æ†­?¹ï¿½  
			Document document = new Document(PageSize.A4);
			
			// ?®å?ï¿½word?›è©¨?¾é??¬ï¿½
			RtfWriter2.getInstance(document, new FileOutputStream("E:/javatest/word.doc"));
			document.open();
			
			// ?ˆï¿½ï¿?
			for (int counter = 0; counter < 3; counter++)
		    {
				// ? ï‹¬ï¿½ï¿½?–ï¿½ï¿½ï?ï¿½ç¢?‡î?ï¿½å¯nputStream?ï¿½
			    inputFile = "E:/javatest/memo" + counter + ".txt";
			    FileReader inputStream = new FileReader(inputFile);
			       
			    // ?¥î²æ´»é?ï¿½ï¿½?ï¿½ï¿½byte?î??¯filedata
			    String filedata = "";
			    while (inputStream.ready())
			    {
			    	int word = inputStream.read();
			    	if (word != 10)
			    		filedata += (char) word;
			    	else
			    		filedata += "...";	// "\n"ï¿½ïµï¿½é?ï¿½ï¿½ï¿½î´ï¿?
			    }
			    
				// ?ˆï¿½ï¿½image?¼î?ï¿?
			    inputFile = "E:/javatest/" + counter + ".png";
				Image image = Image.getInstance (inputFile);
				image.setAlignment(1);
						    
				//?ˆæ??­ï¿½?†ï¿½?­ï¿½   
				//Paragraph paragraph = new Paragraph("ï¿½ç??¯ï¿½?†ï¿½", new Font(Font.NORMAL, 18, Font.BOLDITALIC, new Color(0, 0, 0)));    
				//paragraph.setAlignment(1);
				//document.add(paragraph);
			
				document.add(image);
				Paragraph paragraph = new Paragraph(filedata);
				paragraph.setAlignment(1);
				document.add(paragraph);
				
			}
			document.close();
			// ?ˆæ??­é??œï¿½?®î?ï¿?
			//BaseFont bfFont = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);  
			
			/*
			// ï¿½î???¿½?¢ï¿½ï¿½î?ï¿½éŠµ?½î¹µ      
			Table table = new Table(4);    
	        document.add(new Paragraph("ï¿½î?ï¿½éŠµ?½î¹µ"));    
	        table.setBorderWidth(1);    
	        table.setBorderColor(Color.BLACK);    
	        table.setPadding(0);    
	        table.setSpacing(0);    
	            
	        // ?›é?ï¿½éŠµ?¸ä?ï¿½ï?ï¿½è?ï¿? 
	        Cell cell = new Cell("?µå…¸ä»?);//ï¿½î?ï¿½ï¿½ï¿?  
	        cell.setHeader(true);    
	        cell.setColspan(3);//?ˆæ??­éŠµ?½î¹µ?ç?ï¿½ï¿½ï¿?  
	        cell.setRowspan(3);//?ˆæ??­éŠµ?½î¹µ?ç?ï¿½éŠµï¿?  
	        table.addCell(cell);    
	        table.endHeaders();// ?µå…¸ä»è??¦ï¿½    
	       
	        // ?µå†½î¹µï¿½?‘è??¿ï¿½   
	        cell = new Cell("Example cell 2");    
	        cell.setRowspan(2);//?¶îš¥ï¿½ï¿½?Ÿï¿½ï¿½æ?ï¿½é??¹ï¿½,?¥è?ï¿½é??¸æ¼²    
	        table.addCell(cell);    
	        table.addCell("1,1");    
	        table.addCell("1,2");    
	        table.addCell(new Paragraph("ï¿½ç”«avaï¿½î?ï¿½ï¿½?•â?ï¿½ï¿½"));    
	        table.addCell(new Paragraph("ï¿½ç”«avaï¿½î?ï¿½ï¿½?•â?ï¿½ï¿½"));
	        
	        document.add(table);
	        */    
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