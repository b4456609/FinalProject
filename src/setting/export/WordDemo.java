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
   
/**   
  * ��??�word��??�仿??    
  * 1,?��?��?��?    
  * 2,��????�葵?��?���?  
  * 3,���?��?    
  * 4,��獢��?��??��?��?    
  * 5,�喲�?��?   
  */   
public class WordDemo {   
	
	private static String inputFile;
	
	public WordDemo(ArrayList<PicContainer> pics, SettingParameter setting)
	{    
		try
		{   
			// ��??�word��??��??�蝵桃爾?��?�憭?��  
			Document document = new Document(PageSize.A4);
			
			// ?��?�word?�詨?��??��
			RtfWriter2.getInstance(document, new FileOutputStream(setting.getPath() + "/test2.doc"));
			document.open();
			
			for (PicContainer pic : pics) {
				Image image = Image.getInstance(pic.getPicture().toString());
				image.setAlignment(1);

				// filedata放到paragraph的那層
				Paragraph paragraph = new Paragraph(pic.getComment());
				paragraph.setAlignment(1);

				// 寫入document中
				document.add(image);
				document.add(paragraph);
			}
			/*
			// ?���?
			for (int counter = 0; counter < 3; counter++)
		    {
				// ?���?����?�獢?��?�可nputStream?��
			    inputFile = "E:/javatest/memo" + counter + ".txt";
			    FileReader inputStream = new FileReader(inputFile);
			       
			    // ?�活�?��?���byte?��??�filedata
			    String filedata = "";
			    while (inputStream.ready())
			    {
			    	int word = inputStream.read();
			    	if (word != 10)
			    		filedata += (char) word;
			    	else
			    		filedata += "...";	// "\n"���?����?
			    }
			    
				// ?���image?��?�?
			    inputFile = "E:/javatest/" + counter + ".png";
				Image image = Image.getInstance (inputFile);
				image.setAlignment(1);
						    
				//?��??��?��?��   
				//Paragraph paragraph = new Paragraph("��??��?��", new Font(Font.NORMAL, 18, Font.BOLDITALIC, new Color(0, 0, 0)));    
				//paragraph.setAlignment(1);
				//document.add(paragraph);
			
				document.add(image);
				Paragraph paragraph = new Paragraph(filedata);
				paragraph.setAlignment(1);
				document.add(paragraph);
				
			}*/
			document.close();
			// ?��??��??��?��?�?
			//BaseFont bfFont = BaseFont.createFont("STSongStd-Light",  "UniGB-UCS2-H",BaseFont.NOT_EMBEDDED);  
			
			/*
			// ��???��?����?�銵?�      
			Table table = new Table(4);    
	        document.add(new Paragraph("��?�銵?�"));    
	        table.setBorderWidth(1);    
	        table.setBorderColor(Color.BLACK);    
	        table.setPadding(0);    
	        table.setSpacing(0);    
	            
	        // ?��?�銵?��?��?��?�? 
	        Cell cell = new Cell("?�典�?);//��?���?  
	        cell.setHeader(true);    
	        cell.setColspan(3);//?��??�銵?�?��?���?  
	        cell.setRowspan(3);//?��??�銵?�?��?�銵�?  
	        table.addCell(cell);    
	        table.endHeaders();// ?�典仍�??��    
	       
	        // ?�冽�?��??��   
	        cell = new Cell("Example cell 2");    
	        cell.setRowspan(2);//?���?����?��??��,?��?��??�漲    
	        table.addCell(cell);    
	        table.addCell("1,1");    
	        table.addCell("1,2");    
	        table.addCell(new Paragraph("�甫ava��?��?��?��"));    
	        table.addCell(new Paragraph("�甫ava��?��?��?��"));
	        
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