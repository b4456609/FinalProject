package setting.export;

// Setting Hotkey 傳出一個char
import java.io.*;
import java.util.ArrayList;

import project.java2014.Bernie.PicContainer;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;

public class PDF {
	// 產生中文字型
	private static Font fontBlackSmallCN;

	// 產生字型,字體大小
	private static final Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN,
			18, Font.BOLD);

	// 讀取文字檔
	private static String inputFile = "C:/Users/user/Desktop/JAVA/JAVA Project/javatest/memo0.txt";
	private static int counter = 0;
	// 產生一個A4大小的PDF檔案
	private static Document document = new Document(PageSize.A4);

	public PDF(ArrayList<PicContainer> pics, SettingParameter setting)
			throws DocumentException {

		try {
			// 產生中文字型,字體大小
			BaseFont bfChinese = BaseFont.createFont("MHei-Medium",
					"UniCNS-UCS2-H", BaseFont.NOT_EMBEDDED);
			fontBlackSmallCN = new Font(bfChinese, 12, Font.BOLD,
					new BaseColor(0, 0, 0));

			// 將document放入指定路徑
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream(setting.getPath() + "/test2.pdf"));
			document.open();

			for (PicContainer pic : pics) {
				Image image = Image.getInstance(pic.getPicture().toString());
				image.setAlignment(Element.ALIGN_CENTER);

				// filedata放到paragraph的那層
				Paragraph paragraph = new Paragraph(pic.getComment(),
						fontBlackSmallCN);
				paragraph.setAlignment(Element.ALIGN_CENTER);

				// 寫入document中
				document.add(image);
				document.add(addEmptyLine(paragraph, 3));
			}

			// PDF 文件內容部分
			addMetaDataTitle(document);
			document.close();

			/*
			 * for ( ; counter < 3; counter++) { // 將讀進來的檔案放入inputStream中
			 * inputFile =
			 * "C:\\Users\\user\\Desktop\\JAVA\\JAVA Project\\javatest\\memo" +
			 * counter + ".txt"; FileReader inputStream = new
			 * FileReader(inputFile);
			 * 
			 * // 每次讀取一個byte丟入filedata String filedata = ""; while
			 * (inputStream.ready()) { int word = inputStream.read(); filedata
			 * += (char) word; }
			 * 
			 * 
			 * 
			 * 
			 * // 讀取image檔案 inputFile =
			 * "C:/Users/user/Desktop/JAVA/JAVA Project/javatest/" + counter +
			 * ".png"; Image image = Image.getInstance (inputFile);
			 * image.setAlignment(Element.ALIGN_CENTER);
			 * 
			 * // filedata放到paragraph的那層 Paragraph paragraph = new
			 * Paragraph(filedata, fontBlackSmallCN);
			 * paragraph.setAlignment(Element.ALIGN_CENTER);
			 * 
			 * // 寫入document中 document.add(image);
			 * document.add(addEmptyLine(paragraph, 3)); }
			 * 
			 * // PDF 文件內容部分 addMetaDataTitle(document); document.close();
			 */

			/*
			 * Anchor anchor = new Anchor("標題一", fontRedCN);
			 * anchor.setName("First Chapter");
			 * 
			 * Chapter catPart = new Chapter(new Paragraph(anchor), 1);
			 * Paragraph subPara = new Paragraph("Subcategory 1", subFont);
			 * 
			 * Section subCatPart = catPart.addSection(subPara);
			 * subCatPart.add(new Paragraph(filedata));
			 * 
			 * Paragraph paragraph = new Paragraph(filedata);
			 * subCatPart.add(paragraph); document.add(paragraph);
			 */
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 文件內容部分
	 * 
	 * @param document
	 */
	private static void addMetaDataTitle(Document document) {
		// 增加標題
		document.addTitle("截圖君");
		// 增加作者
		document.addAuthor("第九組製作");
		// 增加建立PDF時間以及修改PDF日期
		document.addCreationDate();
		// 增加PDF中的關鍵字
		document.addKeywords("希望高分~~");
		// 增加PDF的主題
		document.addSubject("哼哼");
	}

	/**
	 * 換行
	 */
	private static Paragraph addEmptyLine(Paragraph paragraph, int number) {
		if (number != 0)
			for (int i = 0; i < number; i++)
				paragraph.add(new Paragraph(" "));
		return paragraph;
	}

	/**
	 * 增加空白
	 */
	private static String addBlank(int blank) {
		StringBuilder bu = new StringBuilder();
		if (blank > 0)
			for (int i = 0; i <= blank; i++)
				bu.append(" ");
		return bu.toString();
	}
}