/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;


/*********************************************************************************************/
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;

import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import Services.UserService;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Administrator
 */
public class Pdftable {
       //public static final String RESULT= "C:\\Users\\Ahmed\\Documents\\NetBeansProjects\\gestion espace parent\\FirstPdf.pdf";
   //
       
   
           /**
        * Main method.
        * @param    args    no arguments needed
        * @throws DocumentException 
        * @throws IOException
        */
       public static void main()
           throws IOException, DocumentException, SQLException{
           
           new Pdftable().createPdf("liste_users.pdf");
           Process pro=Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler liste_users.pdf");
       }

       /**
        * Creates a PDF with information about the movies
        * @param    filename the name of the PDF file that will be created.
        * @throws    DocumentException 
        * @throws    IOException
        */
       public void createPdf(String filename)
           throws IOException, DocumentException, SQLException {
           // step 1
           Document document = new Document();
           // step 2
           PdfWriter.getInstance(document, new FileOutputStream(filename));
           // step 3
           document.open();
           // step 4
           document.add(createFirstTable());
           // step 5
           document.close();
       }

       /**
        * Creates our first table
        * @return our first table
        */
       public static PdfPTable createFirstTable() throws SQLException, BadElementException, IOException {
           PdfPTable table = new PdfPTable(4);
UserService se = new UserService();
           
       PdfPCell c1 = new PdfPCell(new Phrase("Id"));
       c1.setHorizontalAlignment(Element.ALIGN_CENTER);
       table.addCell(c1);

       c1 = new PdfPCell(new Phrase("Username"));
       c1.setHorizontalAlignment(Element.ALIGN_CENTER);
       table.addCell(c1);

       c1 = new PdfPCell(new Phrase("email"));
       c1.setHorizontalAlignment(Element.ALIGN_CENTER);
       table.addCell(c1);
     
       
       c1 = new PdfPCell(new Phrase("Account type"));
       c1.setHorizontalAlignment(Element.ALIGN_CENTER);
       table.addCell(c1);
       
       table.setHeaderRows(1);
        
 for(int i = 0 ; i<se.getUsers().size(); i++)
 {
       table.addCell(""+se.getUsers().get(i).getId());
       table.addCell(se.getUsers().get(i).getUsername());
       table.addCell(se.getUsers().get(i).getEmail());
       table.addCell(se.getUsers().get(i).getAccount_type());
        
        
 }

           return table;
       }
}