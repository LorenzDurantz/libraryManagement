package sysaddLibrary;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class generate {

	private static final BaseFont Courier = null;
	/**
	 * Create the panel.
	 * @return 
	 */
	public static void main(String[] args) throws Exception{
		generateBookReport();
		generateAttendance();
		 /* Create Connection objects */
		
	}
	   public static void generateBookReport() {
		   try {
	   	        Class.forName("com.mysql.cj.jdbc.Driver");
	   	        //System.out.println("Loaded driver");
	   	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library");
	   	        //System.out.println("Connected to MySQL");
	   	        Statement stmt = con.createStatement();
	               /* Define the SQL query */
	               ResultSet query_set = stmt.executeQuery("SELECT BACCNUM,BTITLE,ISSUED_DATE,RETURN_DATE,STATUS,UID FROM BOOKS");
	               /* Step-2: Initialize PDF documents - logical objects */
	               Document my_pdf_report = new Document();
	               PdfWriter.getInstance(my_pdf_report, new FileOutputStream("Book Report.pdf"));
	               my_pdf_report.open();            
	               //we have four columns in our table
	               PdfPTable my_report_table = new PdfPTable(6);
	               Font size = new Font(Courier, 10, Font.NORMAL);
	               //create a cell object
	               PdfPCell table_cell;
	               PdfPCell cell = new PdfPCell(new Paragraph("Book Report"));
	               cell.setColspan(6); 
	               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
	               cell.setBackgroundColor(BaseColor.CYAN);
	               my_report_table.addCell(cell);
	               PdfPCell cell1 = new PdfPCell(new Paragraph("Book Accession No."));
	               cell1.setVerticalAlignment(Element.ALIGN_MIDDLE);
	               cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
	               cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
	               my_report_table.addCell(cell1);
	               PdfPCell cell2 = new PdfPCell(new Paragraph("Book Title"));
	               cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
	               cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
	               my_report_table.addCell(cell2);
	               PdfPCell cell3 = new PdfPCell(new Paragraph("Issued Date"));
	               cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
	               cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
	               my_report_table.addCell(cell3);
	               PdfPCell cell4 = new PdfPCell(new Paragraph("Return Date"));
	               cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
	               cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
	               my_report_table.addCell(cell4);
	               PdfPCell cell5 = new PdfPCell(new Paragraph("Status"));
	               cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
	               cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
	               my_report_table.addCell(cell5);
	               PdfPCell cell6 = new PdfPCell(new Paragraph("Last Borrower"));
	               cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
	               cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);
	               my_report_table.addCell(cell6);
	               
	               while (query_set.next()) {                
	                               String BACCNUM = query_set.getString("BACCNUM");
	                               table_cell=new PdfPCell(new Phrase(BACCNUM,size));
	                               my_report_table.addCell(table_cell);
	                               String BTITLE=query_set.getString("BTITLE");
	                               table_cell=new PdfPCell(new Phrase(BTITLE,size));
	                               my_report_table.addCell(table_cell);
	                               String ISSUED_DATE=query_set.getString("ISSUED_DATE");
	                               table_cell=new PdfPCell(new Phrase(ISSUED_DATE,size));
	                               my_report_table.addCell(table_cell);
	                               String RETURN_DATE=query_set.getString("RETURN_DATE");
	                               table_cell=new PdfPCell(new Phrase(RETURN_DATE,size));
	                               my_report_table.addCell(table_cell);
	                               String STATUS=query_set.getString("STATUS");
	                               table_cell=new PdfPCell(new Phrase(STATUS,size));
	                               my_report_table.addCell(table_cell);
	                               String UID=query_set.getString("UID");
	                               table_cell=new PdfPCell(new Phrase(UID,size));
	                               my_report_table.addCell(table_cell);
	                               }
	               /* Attach report table to PDF */
	               my_pdf_report.add(my_report_table);                       
	               my_pdf_report.close();
	               
	               /* Close all DB related objects */
	               query_set.close();
	               stmt.close(); 
	               con.close();   	
	              
	   	 } 
	   	 catch (Exception ex) {
	   	        ex.printStackTrace();
	   	 }
	   	return;
	    }
       public static void generateAttendance() {
    	   try {
   	        Class.forName("com.mysql.cj.jdbc.Driver");
   	        //System.out.println("Loaded driver");
   	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library");
   	        //System.out.println("Connected to MySQL");
   	        Statement stmt = con.createStatement();
               /* Define the SQL query */
               ResultSet query_set = stmt.executeQuery("SELECT studentID,FNAME,LNAME,EMAIL,USTYPE,DATE FROM LOGIN");
               /* Step-2: Initialize PDF documents - logical objects */
               Document my_pdf_report = new Document();
               PdfWriter.getInstance(my_pdf_report, new FileOutputStream("Log System Report.pdf"));
               my_pdf_report.open();            
               //we have four columns in our table
               PdfPTable my_report_table = new PdfPTable(6);
               Font size = new Font(Courier, 10, Font.NORMAL);
               //create a cell object
               PdfPCell table_cell;
               PdfPCell cell = new PdfPCell(new Paragraph("Attendance"));
               cell.setColspan(6); 
               cell.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell.setBackgroundColor(BaseColor.CYAN);
               my_report_table.addCell(cell);
               PdfPCell cell1 = new PdfPCell(new Paragraph("Student ID"));
               cell1.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell1.setBackgroundColor(BaseColor.LIGHT_GRAY);
               my_report_table.addCell(cell1);
               PdfPCell cell2 = new PdfPCell(new Paragraph("First Name"));
               cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell2.setBackgroundColor(BaseColor.LIGHT_GRAY);
               my_report_table.addCell(cell2);
               PdfPCell cell3 = new PdfPCell(new Paragraph("Last Name"));
               cell3.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell3.setBackgroundColor(BaseColor.LIGHT_GRAY);
               my_report_table.addCell(cell3);
               PdfPCell cell4 = new PdfPCell(new Paragraph("E-Mail"));
               cell4.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell4.setBackgroundColor(BaseColor.LIGHT_GRAY);
               my_report_table.addCell(cell4);
               PdfPCell cell5 = new PdfPCell(new Paragraph("Type"));
               cell5.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell5.setBackgroundColor(BaseColor.LIGHT_GRAY);
               my_report_table.addCell(cell5);
               PdfPCell cell6 = new PdfPCell(new Paragraph("Date & Time"));
               cell6.setHorizontalAlignment(Element.ALIGN_CENTER);
               cell6.setBackgroundColor(BaseColor.LIGHT_GRAY);
               my_report_table.addCell(cell6);
               
               while (query_set.next()) {                
                               String studentID = query_set.getString("studentID");
                               table_cell=new PdfPCell(new Phrase(studentID,size));
                               my_report_table.addCell(table_cell);
                               String FNAME=query_set.getString("FNAME");
                               table_cell=new PdfPCell(new Phrase(FNAME,size));
                               my_report_table.addCell(table_cell);
                               String LNAME=query_set.getString("LNAME");
                               table_cell=new PdfPCell(new Phrase(LNAME,size));
                               my_report_table.addCell(table_cell);
                               String EMAIL=query_set.getString("EMAIL");
                               table_cell=new PdfPCell(new Phrase(EMAIL,size));
                               my_report_table.addCell(table_cell);
                               String USTYPE=query_set.getString("USTYPE");
                               table_cell=new PdfPCell(new Phrase(USTYPE,size));
                               my_report_table.addCell(table_cell);
                               String DATE=query_set.getString("DATE");
                               table_cell=new PdfPCell(new Phrase(DATE,size));
                               my_report_table.addCell(table_cell);
                               }
               /* Attach report table to PDF */
               my_pdf_report.add(my_report_table);                       
               my_pdf_report.close();
               
               /* Close all DB related objects */
               query_set.close();
               stmt.close(); 
               con.close();   	
              
   	 } 
   	 catch (Exception ex) {
   	        ex.printStackTrace();
   	 }
   	return;
    }
       
       
       public static void generateWindow() {
    	   JFrame frmGenerateReport = new JFrame();
      		frmGenerateReport.setTitle("Generate Report");
      		frmGenerateReport.setBounds(100, 100, 379, 273);
      		frmGenerateReport.getContentPane().setLayout(null);
      		frmGenerateReport.setVisible(true);
      		JLabel successLbl = new JLabel("");
      		successLbl.setHorizontalAlignment(SwingConstants.CENTER);
      		successLbl.setBounds(147, 173, 74, 35);
      		frmGenerateReport.getContentPane().add(successLbl);
      		
      		JButton attBtn = new JButton("");
      		attBtn.setIcon(new ImageIcon("src/img/attendance.png"));
      		attBtn.setBackground(Color.WHITE);
      		attBtn.setOpaque(false);
      		attBtn.setBorderPainted(false);
      		attBtn.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent arg0) {
      				generate.generateAttendance();
      				successLbl.setText("Success!");
      				
      				int delay = 2500; //milliseconds, Clears the labels for the next user
      			      ActionListener taskPerformer = new ActionListener() {
      			          public void actionPerformed(ActionEvent evt) {
      			        	  successLbl.setText("");
      			        	  
      			            }
      			        };
      			        javax.swing.Timer tick=new javax.swing.Timer(delay,taskPerformer);
      			        tick.setRepeats(false);
      			        tick.start();
      			}
      		});
      		attBtn.setBounds(25, 111, 163, 51);
      		frmGenerateReport.getContentPane().add(attBtn);
      		
      		JButton bookBtn = new JButton("");
      		bookBtn.setIcon(new ImageIcon("src/img/book.png"));
      		bookBtn.setBackground(Color.WHITE);
      		bookBtn.setOpaque(false);
      		bookBtn.setBorderPainted(false);
      		bookBtn.addActionListener(new ActionListener() {
      			public void actionPerformed(ActionEvent e) {
      				generate.generateBookReport();
      				successLbl.setText("Success!");
      				
      				int delay = 2500; //milliseconds, Clears the labels for the next user
      			      ActionListener taskPerformer = new ActionListener() {
      			          public void actionPerformed(ActionEvent evt) {
      			        	  successLbl.setText("");
      			        	  
      			            }
      			        };
      			        javax.swing.Timer tick=new javax.swing.Timer(delay,taskPerformer);
      			        tick.setRepeats(false);
      			        tick.start();
      			}
      		});
      		
      		
      		bookBtn.setBounds(198, 111, 139, 51);
      		frmGenerateReport.getContentPane().add(bookBtn);
      		
      		JLabel header = new JLabel("");
      		header.setIcon(new ImageIcon("src/img/header.png"));
      		header.setBounds(0, 0, 376, 70);
      		frmGenerateReport.getContentPane().add(header);
      		
      		
      	}
}