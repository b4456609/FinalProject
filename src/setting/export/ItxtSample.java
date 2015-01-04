package setting.export;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ItxtSample {

 //產生中文字型
 private static Font fontRedCN, fontBlueCN, fontBlueSmallCN, fontBlackSmallCN;
 // 產生PDF路徑
 private static final String fileName = "E:/ItextSample.pdf";
 
 private static String empTitle[] = {"員工編號", "姓名", "生日", "電話", "年齡"};

 // 產生字型,字體大小
 private static final Font smallFont = new Font(Font.FontFamily.TIMES_ROMAN, 18, Font.BOLD);

 private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);

 private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 16, Font.BOLD);

 public static void main(String[] args) throws DocumentException {
  //建立假資料
  java.util.List<Emp> list = FalseInformation();

  try {
   // 產生中文字型,字體大小
   BaseFont bfChinese = BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
   fontRedCN = new Font(bfChinese, 18, Font.BOLD, new BaseColor(255, 0, 0));
   fontBlueCN = new Font(bfChinese, 18, Font.BOLD, new BaseColor(0, 0, 255));
   fontBlueSmallCN = new Font(bfChinese, 12, Font.BOLD, new BaseColor(0, 255, 0));
   fontBlackSmallCN = new Font(bfChinese, 12, Font.BOLD, new BaseColor(0, 0, 0));

   // 產生一個A4大小的PDF檔案
   Document document = new Document(PageSize.A4);

   
   PdfWriter.getInstance(document, new FileOutputStream(fileName));

   document.open();

   // PDF 文件內容部分
   addMetaDataTitle(document);
   // PDF 表頭部分
   addTitlePage(document);
   // PDF 內容部分
   addContent(document, list);

   document.close();

  } catch (DocumentException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  } catch (IOException e) {
   // TODO Auto-generated catch block
   e.printStackTrace();
  }
 }

 /**
  * 假資料
  * @return
  */
 private static java.util.List<Emp> FalseInformation() {
  java.util.List<Emp> list = new ArrayList<Emp>();
  Emp emp = new Emp();
  emp.setEmpNo("000001");
  emp.setEmpName("王曉明");
  emp.setTel("031234567");
  emp.setYear("25");
  emp.setBirthday("1988/06/06");
  list.add(emp);
  Emp emp2 = new Emp();
  emp2.setEmpNo("000002");
  emp2.setEmpName("黃小強");
  emp2.setTel("037654321");
  emp2.setYear("52");
  emp2.setBirthday("1980/12/12");
  list.add(emp2);
  return list;
 }

 /**
  * PDF 內容部分
  * @param document
  * @param list
  * @throws DocumentException
  */
 private static void addContent(Document document, java.util.List<Emp> list) throws DocumentException {

  //建立標題一
  Anchor anchor = new Anchor("標題一", fontRedCN);
  anchor.setName("First Chapter");

  Chapter catPart = new Chapter(new Paragraph(anchor), 1);
  Paragraph subPara = new Paragraph("Subcategory 1", subFont);

  Section subCatPart = catPart.addSection(subPara);
  subCatPart.add(new Paragraph("Hello World"));

  Paragraph paragraph = new Paragraph();
  subCatPart.add(paragraph);

  addEmptyLine(paragraph, 10);

  subPara = new Paragraph("Subcategory 2", subFont);
  subCatPart = catPart.addSection(subPara);
  subCatPart.add(new Paragraph("Paragraph 1"));
  subCatPart.add(new Paragraph("Paragraph 2"));
  subCatPart.add(new Paragraph("Paragraph 3"));
  document.add(catPart);

  //建立標題二
  anchor = new Anchor("標題二", fontRedCN);
  anchor.setName("Second Chapter");

  catPart = new Chapter(new Paragraph(anchor), 2);
  subPara = new Paragraph("Subcategory 2", subFont);

  Section subCatPart2 = catPart.addSection(subPara);
  
  // Add a list
  createList(subCatPart2);
  // Add a table
  createTable(subCatPart2);
  // Add a table2
  createTable2(subCatPart2, list);

  document.add(catPart);

 }

 /**
  * 建立表格2
  * @param subCatPart2
  * @param list
  */
 private static void createTable2(Section subCatPart2, java.util.List<Emp> list) {
  
  PdfPTable table = new PdfPTable(5);
  PdfPCell cell = new PdfPCell(new Paragraph("員工基本資料", fontBlueCN));
  //字體內容至中
  cell.setHorizontalAlignment(Element.ALIGN_CENTER);
  // 背景顏色
  cell.setBackgroundColor(new BaseColor(0, 255, 0));
  // 外框顏色
  cell.setBorderColor(new BaseColor(255, 0, 0));
  //合併儲存格
  cell.setColspan(5);
  
  table.addCell(cell);
  
  for(String title : empTitle ){
   PdfPCell cell1 = new PdfPCell(new Paragraph( title, fontBlueSmallCN));
   cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
   table.addCell(cell1);
  }
  
  if( list != null && !list.isEmpty() ){
   for(Emp emp : list ){
    PdfPCell cell1 = new PdfPCell(new Paragraph( emp.getEmpNo(), catFont));
    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell1); 
    cell1 = new PdfPCell(new Paragraph( emp.getEmpName(), fontBlackSmallCN));
    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell1);
    cell1 = new PdfPCell(new Paragraph( emp.getBirthday(), catFont));
    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell1);
    cell1 = new PdfPCell(new Paragraph( emp.getTel(), catFont));
    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell1);
    cell1 = new PdfPCell(new Paragraph( emp.getYear(), catFont));
    cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
    table.addCell(cell1);
   }
  }
  subCatPart2.add(table);
 }

 /**
  * 建立表格
  * @param subCatPart
  */
 private static void createTable(Section subCatPart2) {
  PdfPTable table = new PdfPTable(3);
  //表格與上排字體間隙
  table.setSpacingBefore(5);
  //表格與下排字體間隙
  table.setSpacingAfter(5);
       
  PdfPCell c1 = new PdfPCell(new Phrase("Table Header 1"));
  //字體至中
  c1.setHorizontalAlignment(Element.ALIGN_CENTER);
  table.addCell(c1);

  c1 = new PdfPCell(new Phrase("Table Header 2"));
  c1.setHorizontalAlignment(Element.ALIGN_LEFT);
  table.addCell(c1);

  c1 = new PdfPCell(new Phrase("Table Header 3"));
  c1.setHorizontalAlignment(Element.ALIGN_RIGHT);
  table.addCell(c1);
  table.setHeaderRows(1);

  table.addCell("1.0");
  table.addCell("1.1");
  table.addCell("1.2");
  table.addCell("2.1");
  table.addCell("2.2");
  table.addCell("2.3");

  subCatPart2.add(table);
  
 }

 /**
  * 增加list
  * @param subCatPart
  */
 private static void createList(Section subCatPart) {
  List list = new List(true, false, 10);
  //list.add(new ListItem("標題二", fontRedCN));
  list.add(new ListItem("First point"));
  list.add(new ListItem("Second point"));
  list.add(new ListItem("Third point"));
  subCatPart.add(list);
 }

 /**
  * 表頭部分
  * @param document
  * @throws DocumentException
  */
 private static void addTitlePage(Document document)throws DocumentException {

  Paragraph preface = new Paragraph("This is a paragraph", 
                  FontFactory.getFont(FontFactory.HELVETICA, 16, Font.BOLDITALIC,new BaseColor(0, 0, 255)));

  //換行
  addEmptyLine(preface, 2);

  preface.add(new Paragraph("顯示中文", fontRedCN));

  addEmptyLine(preface, 2);

  preface.add(new Paragraph(addBlank(3) + "顯示中文", fontRedCN));

  addEmptyLine(preface, 8);

  preface.add(new Paragraph("This document is a preliminary version and not subject to your li cense agreement or any other agreement with vogella.com ;-).", smallFont));

  addEmptyLine(preface, 4);
  
  document.add(preface);
  
  Phrase phrase0 = new Phrase("PDF");
  Phrase phrase1 = new Phrase(new Chunk("字體顏色", fontRedCN));
  Phrase phrase2 = new Phrase(new Chunk("測試", fontBlueCN));
  document.add(phrase0);
  document.add(phrase1);
  document.add(phrase2);
  // 產生新的一頁
  document.newPage();
 }

 /**
  * 文件內容部分
  * @param document
  */
 private static void addMetaDataTitle(Document document) {

  // 增加標題
  document.addTitle("PDF表頭");
  // 增加作者
  document.addAuthor("Puma製作");
  // 增加建立PDF時間以及修改PDF日期
  document.addCreationDate();
  // 增加PDF中的關鍵字
  document.addKeywords("關鍵字");
  // 增加PDF的主題
  document.addSubject("PDF TEST");
  // 增加自訂內容
  document.addHeader("PDF1", "測試1");
  document.addHeader("PDF2", "測試2");

 }

 /**
  * 換行
  * @param paragraph
  * @param number
  */
 private static void addEmptyLine(Paragraph paragraph, int number) {
  if (number != 0) {
   for (int i = 0; i < number; i++) {
    paragraph.add(new Paragraph(" "));
   }
  }
 }

 /**
  * 增加空白
  * @param blank
  * @return
  */
 private static String addBlank(int blank) {
  StringBuilder bu = new StringBuilder();
  if( blank > 0 ){
   for (int i = 0; i <= blank; i++) {
    bu.append(" ");
   }
  }
  return bu.toString();
 }
}