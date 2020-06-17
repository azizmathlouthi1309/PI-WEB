/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package manage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
 
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import Utils.DataBase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Header;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.border.TitledBorder;

/**
 *
 * @author maiss
 */
public class Pdf {
        private Connection con;
        private Statement ste;
        Font redFont = FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD, new CMYKColor(0, 255, 0, 0));
        Font f1=new Font(Font.FontFamily.TIMES_ROMAN,17,TitledBorder.CENTER, BaseColor.RED);
        Font f2=new Font(Font.FontFamily.TIMES_ROMAN,8,TitledBorder.CENTER, BaseColor.BLACK);
        Font f3=new Font(Font.FontFamily.COURIER,8,TitledBorder.CENTER, BaseColor.BLUE);
    public Pdf()  {
        con = DataBase.getInstance().getConnection();
          
    }
    public void add(String file, String date, String cl, String Lundi1 ,String Lundi2 ,String Lundi3,String Lundi4,String Mardi1
            ,String Mardi2,String Mardi3
            ,String Mardi4, String Mercredi1,String Mercredi2, String Mercredi3,String Mercredi4,
            String Jeudi1,String Jeudi2,String Jeudi3,String Jeudi4,
            String Vendredi1,String Vendredi2, String Vendredi3,String Vendredi4) throws FileNotFoundException, SQLException, DocumentException{
        
        /* Create Connection objects */
//                con = DataBase.getInstance().getConnection();
                Document my_pdf_report = new Document(PageSize.A5.rotate());
                PdfWriter.getInstance(my_pdf_report, new FileOutputStream(file));
                my_pdf_report.open();  
                
                //we have four columns in our table
                Paragraph chapterTitle = new Paragraph("Your TimeTable  ",f1);
                Paragraph d = new Paragraph(" For the week: "+date);
                chapterTitle.setExtraParagraphSpace(10);
                chapterTitle.setSpacingAfter(3);
                 d.setSpacingAfter(15);
                 d.setAlignment(Element.ALIGN_CENTER);
                chapterTitle.setAlignment(Element.ALIGN_CENTER);
                Chapter chapter1 = new Chapter(chapterTitle, 1);
                chapter1.setNumberDepth(0);
                
                my_pdf_report.add(chapterTitle);
               //my_pdf_report.add(new Header(file, "------"));
                my_pdf_report.add(d);
                PdfPTable my_report_table = new PdfPTable(5);
                Paragraph p = new Paragraph(my_report_table.toString());
                p.setAlignment(Element.ALIGN_MIDDLE);
                 Paragraph a = new Paragraph("School year : 2020-2021");
                 a.setAlignment(Element.ALIGN_RIGHT);
                  Paragraph c = new Paragraph("Class: "+cl);
                       c.setAlignment(Element.ALIGN_RIGHT);
                         a.setSpacingAfter(4);            
                 c.setSpacingAfter(2);
                   my_pdf_report.add(c);
               
                  my_pdf_report.add(a);
                    a.setSpacingAfter(4);            
            
               
               // my_report_table.;
                my_report_table.size();
                my_report_table.setWidthPercentage(100);
                //create a cell object
                PdfPCell table_cell ;
                //   table_cell;
                                table_cell=new PdfPCell(new Phrase(""));
                                 table_cell.setPadding(5);
                                 
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("8-10"));
                                table_cell.setPadding(5);
                                  
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("11-12"));
                                 
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("13-14"));
                                
                                table_cell.setPadding(5);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("15-16"));
                                  
                                table_cell.setPadding(5);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("Lundi"));
                                  
                                table_cell.setPadding(5);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Lundi1));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Lundi2));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Lundi3));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Lundi4));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("Mardi"));
                                  
                                table_cell.setPadding(5);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Mardi1));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Mardi2));  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                 table_cell=new PdfPCell(new Phrase(Mardi3));
                                   
                                 table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Mardi4));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("Mercredi"));
                                  
                                table_cell.setPadding(5);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Mercredi1));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Mercredi2));
                                  
                                table_cell.setPadding(14);
                                
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Mercredi3));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Mercredi4));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("Jeudi"));
                                  
                                table_cell.setPadding(5);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Jeudi1));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Jeudi2));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Jeudi3));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Jeudi4));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase("Vendredi"));
                                  
                                table_cell.setPadding(5);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Vendredi1));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Vendredi2));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Vendredi3));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                                table_cell=new PdfPCell(new Phrase(Vendredi4));
                                  
                                table_cell.setPadding(14);
                                my_report_table.addCell(table_cell);
                               
                                
                /* Attach report table to PDF */
                my_pdf_report.add(my_report_table);                       
                my_pdf_report.close();
                
               /* Close all DB related objects */

        
    }
   
     
}
