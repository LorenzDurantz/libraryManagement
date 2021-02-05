package sysaddLibrary;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
 
import javax.swing.*;
import net.proteanit.sql.DbUtils;
import sysaddLibrary.main.ex;

import java.awt.Color;
import java.awt.SystemColor;
 
public class main {
     
    public static class ex{
        public static int days=0;
            }
 
    public static void main(String[] args) {
         
        admin_menu();
    }
    //login still wip
	public static void login() {
     
    JFrame f=new JFrame("Login");//creating instance of JFrame  
    JLabel l1,l2;  
    l1=new JLabel("Email");  //Create label Username
    l1.setBounds(30,15, 100,30); //x axis, y axis, width, height 
     
    l2=new JLabel("Password");  //Create label Password
    l2.setBounds(30,50, 100,30);    
     
    JTextField F_user = new JTextField(); //Create text field for username
    F_user.setBounds(110, 15, 200, 30);
         
    JPasswordField F_pass=new JPasswordField(); //Create text field for password
    F_pass.setBounds(110, 50, 200, 30);
       
    JButton login_but=new JButton("Login");//creating instance of JButton for Login Button
    login_but.setBounds(130,90,80,25);//Dimensions for button
    login_but.addActionListener(new ActionListener() {  //Perform action
         
        public void actionPerformed(ActionEvent e){ 
 
        String username = F_user.getText(); //Store username entered by the user in the variable "username"
        String password = F_pass.getText(); //Store password entered by the user in the variable "password"
         
        if(username.equals("")) //If username is null
        {
            JOptionPane.showMessageDialog(null,"Please enter Email"); //Display dialog box with the message
        } 
        else if(password.equals("")) //If password is null
        {
            JOptionPane.showMessageDialog(null,"Please enter password"); //Display dialog box with the message
        }
        else { //If both the fields are present then to login the user, check wether the user exists already
            //System.out.println("Login connect");
            Connection connection=connect();  //Connect to the database
            try
            {
            Statement stmt = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
            	    ResultSet.CONCUR_READ_ONLY);
              stmt.executeUpdate("USE LIBRARY"); //Use the database with the name "Library"
              String st = ("SELECT * FROM USERS WHERE EMAIL='"+username+"' AND PASSWORD='"+password+"'"); //Retreive username and passwords from users
              ResultSet rs = stmt.executeQuery(st); //Execute query
              if(rs.next()==false) { //Move pointer below
                  System.out.print("No user");  
                  JOptionPane.showMessageDialog(null,"Wrong Username/Password!"); //Display Message
 
              }
              else {
                  f.dispose();
                rs.beforeFirst();  //Move the pointer above
                while(rs.next())
                {
                  String admin = rs.getString("ADMIN"); //user is admin
                  //System.out.println(admin);
                  String UID = rs.getString("IDNUM"); //Get user ID of the user
                  if(admin.equals("1")) { //If boolean value 1
                      admin_menu(); //redirect to admin menu
                  }
                  else{
                	  JOptionPane.showMessageDialog(null,"Error: Contact Administrator!"); //Display Message //redirect to user menu for that user ID
                  }
              }
              }
            }
            catch (Exception ex) {
                 ex.printStackTrace();
        }
        }
    }               
    });
 
     
    f.getContentPane().add(F_pass); //add password
    f.getContentPane().add(login_but);//adding button in JFrame  
    f.getContentPane().add(F_user);  //add user
    f.getContentPane().add(l1);  // add label1 i.e. for username
    f.getContentPane().add(l2); // add label2 i.e. for password
    f.setSize(400,180);//400 width and 500 height  
    f.getContentPane().setLayout(null);//using no layout managers  
    f.setVisible(true);//making the frame visible 
    f.setLocationRelativeTo(null);
     
}
	
	public static Connection connect()
	{
	try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        //System.out.println("Loaded driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library");
	        //System.out.println("Connected to MySQL");
	        return con;
	 } 
	 catch (Exception ex) {
	        ex.printStackTrace();
	 }
	return null;
	}
	
	public static void database() {
	    try {
	    Connection connection=connect();
	    ResultSet resultSet = connection.getMetaData().getCatalogs();
	    //iterate each catalog in the ResultSet
	        while (resultSet.next()) {
	          // Get the database name, which is at position 1
	          String databaseName = resultSet.getString(1);
	          if(databaseName.equals("library")) {
	              //System.out.print("yes");
	              Statement stmt = connection.createStatement();
	              //Drop database if it pre-exists to reset the complete database
	              String sql = "DROP DATABASE library";
	              stmt.executeUpdate(sql);
	          }
	        }
	          Statement stmt = connection.createStatement();
	           
	          String sql = "CREATE DATABASE LIBRARY"; //Create Database
	          stmt.executeUpdate(sql); 
	          stmt.executeUpdate("USE LIBRARY"); //Use Database
	          //Create Users Table
	          String sql1 = "CREATE TABLE USERS(IDNUM INT NOT NULL AUTO_INCREMENT PRIMARY KEY, EMAIL VARCHAR(30), PASSWORD VARCHAR(30), ADMIN BOOLEAN)";
	          stmt.executeUpdate(sql1);
	          //Insert into users table
	          stmt.executeUpdate("INSERT INTO USERS(EMAIL, PASSWORD, ADMIN) VALUES('admin','admin',TRUE)");
	          //Create Books table
	          stmt.executeUpdate("CREATE TABLE BOOKS(BACCNUM INT NOT NULL AUTO_INCREMENT PRIMARY KEY, BRFID VARCHAR(10),BTITLE VARCHAR(50), BAUTH VARCHAR(50), GENRE VARCHAR(50), PUBLISHER VARCHAR(50), PUBYEAR INT(4), ISSUED_DATE VARCHAR(20), RETURN_DATE VARCHAR(20), PERIOD INT, STATUS VARCHAR(20), UID INT)");
	          //Create Issued Table
	          stmt.executeUpdate("CREATE TABLE ISSUED(ISSUEID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, BRFID INT(10), UID INT, BACCNUM INT, ISSUED_DATE VARCHAR(20), RETURN_DATE VARCHAR(20), PERIOD INT)");
	          //Insert into books table
	          stmt.executeUpdate("INSERT INTO BOOKS(BRFID, BTITLE, BAUTH, GENRE, PUBLISHER, PUBYEAR, STATUS) VALUES ('0007315860', 'War and Peace', 'Mike Stint', 'Fiction', 'Publeash Inc.', 2002, 'Available'),  ('0007315861', 'The Sost Lymbol', 'Ban Drown', 'Nonfiction', 'Penguin Inc.', 2010, 'Available'), ('0007315862', 'Jomeo and Ruliet', 'Shilliam Wakespeare', 'Mystery', 'Microsoft Publishing.', 2022, 'Available'),('0007315863', '1489', 'Borge Borwell', 'Comedy', 'Mystery Inc.', 1489, 'Available'), ('0007315864', 'Tarey Fails', 'Princess Aurora', 'Horror', 'The Printer Inc', 2012, 'Available'),('0007315865', 'Bambee', 'Salt Widney', 'Mythology', 'Jack Sparrow Publishers', 1985, 'Available'), ('0007315866', 'Mankey', 'Shmibid Attenmibid', 'Educational', 'SF Publishing', 1521, 'Available')");
	          //login
	          stmt.executeUpdate("CREATE TABLE LOGIN(studentID INT NOT NULL, URFID VARCHAR(10), FNAME VARCHAR(50), LNAME VARCHAR(50), EMAIL VARCHAR(50), USTYPE VARCHAR(50), DATE datetime default now())");
	    resultSet.close();
	    
	    }
	     catch (Exception ex) {
	         ex.printStackTrace();
             admin_menu();
	     }
	}
	
	public static void admin_menu() {
	     
		JFrame f=new JFrame("APC RFID LIBRARY SYSTEM");
	    f.getContentPane().setBackground(new Color(192, 192, 192));
	    f.getContentPane().setForeground(Color.WHITE);
	    f.setTitle("APC RFID LIBRARY SYSTEM - Librarian");
	    JLabel backgroundImage = new JLabel("");
	    backgroundImage.setIcon(new ImageIcon("src/img/GUI.PNG"));
	    backgroundImage.setBounds(0, 0, 648, 69);
	    
	    
	    //FOR DEBUGGING PURPOSES ONLY
	    JButton database_but=new JButton("Database Reset");
	    database_but.setEnabled(false);
	    database_but.setBackground(Color.PINK);
	    database_but.setBounds(422,349,208,29);
	    database_but.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	             
	            main.database();
	            JOptionPane.showMessageDialog(null,"Database Reset!");
	             
	        }
	    });

	   //Table to display book inventory in UI
	    Connection connection = main.connect();
	    String sql="SELECT BACCNUM as 'Accession No.', BTITLE as 'Title', BAUTH as 'Author', GENRE as 'Genre', PUBLISHER as 'Publisher', PUBYEAR as 'Year Published', ISSUED_DATE as 'Issued Date', RETURN_DATE as 'Return Date', PERIOD as 'Period', STATUS as 'Status' FROM BOOKS"; //select all books 

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("USE LIBRARY"); 
            stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
            JTable book_list= new JTable(); 
            book_list.setModel(DbUtils.resultSetToTableModel(rs)); 
            //mention scroll bar
            JScrollPane scrollPane = new JScrollPane(book_list);
            scrollPane.setEnabled(false);
            scrollPane.setBounds(10, 115, 620, 228);
            f.getContentPane().add(scrollPane);
            f.getContentPane().setLayout(null);
            
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
             JOptionPane.showMessageDialog(null, e1);
        }
	     
        //Reportt Generation button and its ActionListener
        JButton genRep=new JButton("",new ImageIcon("src/img/genrep.png"));
        genRep.setToolTipText("Generate attendance or book logs");
        genRep.setBackground(Color.WHITE);
        genRep.setOpaque(false);
        genRep.setBorderPainted(false);
        genRep.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e){
                generate.generateWindow();
        	}
        });
        genRep.setBounds(10, 72, 227, 39);
        f.getContentPane().add(genRep);
        f.setSize(656, 428);
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        
	     
        
        //Button to add books
	    JButton add_book=new JButton("",new ImageIcon("src/img/add.png")); 
	    add_book.setToolTipText("Add a book to the database");
	    add_book.setBackground(Color.WHITE);
	    add_book.setOpaque(false);
	    add_book.setBorderPainted(false);
	    add_book.setBounds(240,72,157,39); 
	    //Add books ActionListener
	    add_book.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	                //set frame wot enter book details
	        	JFrame g = new JFrame("Enter Book Details");
	    		g.setTitle("Add Book to Library");
	            //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	            // set labels
	            JLabel l1,l2,l3;  
	            l1=new JLabel("Book Title");  //lebel 1 for book name
	            l1.setBounds(30,89, 100,30); 
	             
	             
	            l2=new JLabel("Author");  //label 2 for genre
	            l2.setBounds(30,127, 100,30); 
	             
	            l3=new JLabel("Genre");  //label 2 for price
	            l3.setBounds(30,164, 100,30); 
	            
	            JLabel lblPublisher = new JLabel("Publisher");
	            lblPublisher.setBounds(30, 205, 100, 30);
	            g.getContentPane().add(lblPublisher);

	            JLabel lblYearPublished = new JLabel("Year Published");
	            lblYearPublished.setBounds(30, 246, 121, 30);
	            g.getContentPane().add(lblYearPublished);
	            
	            JLabel lblNewLabel = new JLabel("");
	            lblNewLabel.setIcon(new ImageIcon("src/img/header.png"));
	            lblNewLabel.setBounds(-6, 1, 388, 69);
	            
	            JLabel lblRfidTag = new JLabel("RFID Tag");
	            lblRfidTag.setBounds(30, 287, 73, 30);
	            
	            //set text field for book name
	            JTextField F_btitle = new JTextField();
	            F_btitle.setBounds(110, 89, 200, 30);
	            
	            //set text field for genre 
	            JTextField F_bauth=new JTextField();
	            F_bauth.setBounds(110, 127, 200, 30);
	            //set text field for price
	            JTextField F_genre=new JTextField();
	            F_genre.setBounds(110, 164, 200, 30);
	                     
	            JTextField publ = new JTextField();
	            publ.setBounds(110, 205, 200, 30);
	            
	            JTextField yearPub = new JTextField();
	            yearPub.setBounds(161, 246, 149, 30);
	            
	            JTextField rfidTag = new JTextField();
	            rfidTag.setBounds(110, 287, 200, 30);
	            
	            JButton addBook=new JButton("");//creating instance of JButton to submit details  
	            addBook.setOpaque(false);
	            addBook.setBorderPainted(false);
	            addBook.setBackground(Color.WHITE);
	            addBook.setIcon(new ImageIcon("C:\\Users\\Miko\\eclipse-workspace\\sysaddLibrary\\src\\img\\add.png"));
	            addBook.setBounds(81,327,179,43);//x axis, y axis, width, height 
	            addBook.addActionListener(new ActionListener() {
	                 
	                public void actionPerformed(ActionEvent e){
	                // assign the book name, genre, price
	                String bname = F_btitle.getText();
	                String auth = F_bauth.getText();
	                String genre = F_genre.getText();
	                String pub = publ.getText();
	                String yrPub = yearPub.getText();
	                String rfid = rfidTag.getText();
	                Connection connection = main.connect();
	                 
	                try {
	                Statement stmt = connection.createStatement();
	                 stmt.executeUpdate("USE LIBRARY");
	                 stmt.executeUpdate("INSERT INTO BOOKS(BRFID, BTITLE, BAUTH, GENRE, PUBLISHER, PUBYEAR, STATUS) VALUES ('"+rfid+"','"+bname+"','"+auth+"','"+genre+"','"+pub+"', '"+yrPub+"','Available')");
	                 JOptionPane.showMessageDialog(null,"Book added!");
	                 f.dispose();
	                 admin_menu();
	                 g.dispose();
	                 
	                  
	                }
	                 
	                catch (SQLException e1) {
	                    // TODO Auto-generated catch block
	                     JOptionPane.showMessageDialog(null, e1);
	                }   
	                 
	             }
	                 
	            });
	                             
	                g.getContentPane().add(l3);
	                g.getContentPane().add(addBook);
	                g.getContentPane().add(l1);
	                g.getContentPane().add(l2);
	                g.getContentPane().add(F_btitle);
	                g.getContentPane().add(F_bauth);
	                g.getContentPane().add(F_genre);
	                g.setSize(350,420);//400 width and 500 height  
	                g.getContentPane().setLayout(null);//using no layout managers  
	                g.getContentPane().add(publ);
	                g.getContentPane().add(yearPub);
	                g.getContentPane().add(lblNewLabel);
	                g.getContentPane().add(lblRfidTag);
	                g.getContentPane().add(rfidTag);
	                g.setVisible(true);//making the frame visible 
	                g.setLocationRelativeTo(null);
	                
	                
	        	}	
	    });
	     
	    
	    
	    //Issue books button
	    JButton issue_book=new JButton("",new ImageIcon("src/img/borrow.png")); 
	    issue_book.setToolTipText("Issue book ");
	    issue_book.setOpaque(false);
	    issue_book.setBorderPainted(false);
	    issue_book.setBackground(Color.WHITE);
	    issue_book.setBounds(523,72,107,39); 
	    //Issue books ActionListener
	    issue_book.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	                //enter details

	        	JFrame g = new JFrame("Enter Details");
	     		 g.setTitle("Borrow Book");
	              //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	              //create labels
	     		  JTextField F_bid,F_uid,F_period,F_issue,F_brfid,F_bstatus,F_bauth,F_btitle;
	              JLabel l1,l2,l3,l4,l5,lblBookTitle,F_status,F_bauthor;  
	              l1=new JLabel("Book Accession No.");  // Label 1 for Book ID
	              l1.setBounds(10,157, 138,30); 
	               
	              l2=new JLabel("Personal ID No.");  //Label 2 for user ID
	              l2.setBounds(10,287, 100,30); 
	               
	              l3=new JLabel("Period(days)");  //Label 3 for period
	              l3.setBounds(10,320, 100,30); 
	               
	              l4=new JLabel("Issued Date(DD-MM-YYYY)");  //Label 4 for issue date
	              l4.setBounds(10,353, 150,30); 
	               
	              l5 = new JLabel("Scanned RFID");
	              l5.setBounds(10, 108, 138, 30);
	              
	              lblBookTitle = new JLabel("Book Title");
	              lblBookTitle.setBounds(10, 198, 100, 14);
	              
	              F_status = new JLabel("Status");
	              F_status.setBounds(176, 240, 46, 14);
	              
	              F_bauthor = new JLabel("Author");
	              F_bauthor.setBounds(10, 239, 46, 14);
	              
	              F_brfid = new JTextField();
	              F_brfid.setBounds(96, 108, 150, 30);
	              
	              F_bid = new JTextField();
	              F_bid.setBounds(167, 158, 189, 30);
	               
	              F_uid=new JTextField();
	              F_uid.setBounds(167, 288, 189, 30);
	               
	              F_period=new JTextField();
	              F_period.setBounds(167, 321, 189, 30);
	               
	              F_issue=new JTextField();
	              F_issue.setBounds(167, 354, 189, 30);  
	              
	              F_bstatus = new JTextField();
	              F_bstatus.setBounds(222, 232, 134,30);
	              
	              F_btitle = new JTextField();
	              F_btitle.setBounds(167, 191, 189, 30);
	              
	              F_bauth = new JTextField();
	              F_bauth.setBounds(65, 232, 93, 30);
	              
	              JButton issueButton=new JButton("");//creating instance of JButton  
	              issueButton.setOpaque(false);
	              issueButton.setBorderPainted(false);
	              issueButton.setBackground(Color.WHITE);
	              issueButton.setIcon(new ImageIcon("src/img/borrow.png"));
	              issueButton.setBounds(113,394,138,57);//x axis, y axis, width, height 
	              issueButton.addActionListener(new ActionListener() {
	                   
	                  public void actionPerformed(ActionEvent e){
	                   
	                  String uid = F_uid.getText();
	                  String bid = F_bid.getText();
	                  String period = F_period.getText();
	                  String issued_date = F_issue.getText();
	                  String brfid = F_brfid.getText();
	                  int period_int = Integer.parseInt(period);
	                   
	                  Connection connection = main.connect(); try {
	                  Statement stmt = connection.createStatement();
	                   stmt.executeUpdate("USE LIBRARY");
	                   stmt.executeUpdate("INSERT INTO ISSUED(BRFID,UID,BACCNUM,ISSUED_DATE,PERIOD) VALUES ('"+brfid+"','"+uid+"','"+bid+"','"+issued_date+"','"+period_int+"')");
	                   stmt.executeUpdate("UPDATE BOOKS SET ISSUED_DATE='"+issued_date+"' WHERE BACCNUM="+bid);
	                   stmt.executeUpdate("UPDATE BOOKS SET PERIOD='"+period_int+"' WHERE BACCNUM="+bid);
	                   stmt.executeUpdate("UPDATE BOOKS SET STATUS='On Loan' WHERE BACCNUM="+bid);
	                   stmt.executeUpdate("UPDATE BOOKS SET UID='"+uid+"' WHERE BACCNUM="+bid);
	                   stmt.executeUpdate("UPDATE BOOKS SET RETURN_DATE='' WHERE BACCNUM="+bid);
	                   JOptionPane.showMessageDialog(null,"Book Issued!");
	                   f.dispose();
	                   admin_menu();
	                   g.dispose();
	                    
	                  }
	                   
	                  catch (SQLException e1) {
	                      // TODO Auto-generated catch block
	                       JOptionPane.showMessageDialog(null, e1);
	                  }   
	                   
	                  }
	                   
	              });
	                   
	               
	                  g.getContentPane().add(l3);
	                  g.getContentPane().add(l4);
	                  g.getContentPane().add(issueButton);
	                  g.getContentPane().add(l1);
	                  g.getContentPane().add(l2);
	                  g.getContentPane().add(F_uid);
	                  g.getContentPane().add(F_bid);
	                  g.getContentPane().add(F_period);
	                  g.getContentPane().add(F_issue);
	                  g.setSize(375,509);//400 width and 500 height  
	                  g.getContentPane().setLayout(null);//using no layout managers  
	                  g.getContentPane().add(F_brfid);
	                  g.getContentPane().add(l5);
	                  
	                  JButton searchDB = new JButton("Search");
	                  searchDB.addActionListener(new ActionListener() {
	                      public void actionPerformed(ActionEvent e) {  
	                          //Create DataBase Coonection and Fetching Records  
	                          try {  
	                              String str = F_brfid.getText();  
	                              Class.forName("com.mysql.cj.jdbc.Driver"); 
	                              Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library");  
	                              PreparedStatement st = con.prepareStatement("SELECT * FROM BOOKS where BRFID = ?");  
	                              st.setString(1, str);  
	                              //Excuting Query  
	                              ResultSet rs = st.executeQuery();  
	                              if (rs.next()) {  
	                                  String s = rs.getString("BACCNUM");  
	                                  String s1 = rs.getString("BTITLE");  
	                                  String s2 = rs.getString("BAUTH");  
	                                  String s3 = rs.getString("STATUS");  
	                                  //Sets Records in TextFields.  
	                                  F_bid.setText(s);  
	                                  F_btitle.setText(s1);  
	                                  F_bauth.setText(s2);  
	                                  F_bstatus.setText(s3);  
	                              } else {  
	                                  JOptionPane.showMessageDialog(null, "Name not Found");  
	                              }  
	                              //Create Exception Handler  
	                          } catch (Exception ex) {  
	                              System.out.println(ex);  
	                          }  
	                      }  
	                  });
	                  searchDB.setBounds(256, 108, 100, 30);
	                  g.getContentPane().add(searchDB);
	                  g.getContentPane().add(lblBookTitle);
	                  g.getContentPane().add(F_btitle);
	                  g.getContentPane().add(F_bauth);
	                  g.getContentPane().add(F_bauthor);
	                  g.getContentPane().add(F_status);
	                  g.getContentPane().add(F_bstatus);
	                  g.setVisible(true);//making the frame visible 
	                  g.setLocationRelativeTo(null);
	                  F_bid.setEditable(false);
	                  F_bstatus.setEditable(false);
	                  F_bauth.setEditable(false);
	                  F_btitle.setEditable(false);
	                  
	                  JLabel lblNewLabel = new JLabel("");
	                  lblNewLabel.setBackground(Color.WHITE);
	                  lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Miko\\eclipse-workspace\\sysaddLibrary\\src\\img\\header.png"));
	                  lblNewLabel.setBounds(0, 0, 416, 69);
	                  g.getContentPane().add(lblNewLabel);
		             
	        	}
	        
	    });
	     
	    
	    //Return books button
	    JButton return_book=new JButton("",new ImageIcon("src/img/return.png")); 
	    return_book.setToolTipText("Return borrowed books");
	    return_book.setOpaque(false);
	    return_book.setBorderPainted(false); //creating instance of JButton to return books
	    return_book.setBackground(Color.GREEN);
	    return_book.setBounds(401,72,124,39); 
	    //Return books ActionListener
	    return_book.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e){
	        	JLabel l1,l2,l3,l4;  
	        	JFrame g = new JFrame("Enter Details");
	    		g.setTitle("Return Books");
	            
	            l1=new JLabel("Book Accession No.");
	            l1.setBounds(10,122, 150,30); 
	            
	            l4=new JLabel("Return Date(DD-MM-YYYY)");  
	            l4.setBounds(10,290, 150,30); 
	             
	            JTextField R_bid = new JTextField();
	            R_bid.setEditable(false);
	            R_bid.setBounds(132, 122, 189, 30);
	             
	            JTextField R_return=new JTextField();
	            R_return.setBounds(159, 290, 162, 30);
	         
	            JButton returnButton=new JButton("");

	            returnButton.setOpaque(false);
	            returnButton.setBorderPainted(false);
	            returnButton.setBackground(Color.WHITE);
	            returnButton.setIcon(new ImageIcon("src/img/return.png"));
	            returnButton.setBounds(100,354,143,51); 
	            returnButton.addActionListener(new ActionListener() {
	                 
	                public void actionPerformed(ActionEvent e){                 
	                 
	                String bid = R_bid.getText();
	                String return_date = R_return.getText();
	                
	                Connection connection = connect();
	                 
	                try {
	                Statement stmt = connection.createStatement();
	                 stmt.executeUpdate("USE LIBRARY");
	                 //Intialize date1 with NULL value
	                 String date1=null;
	                 String date2=return_date; //Intialize date2 with return date
	                 
	                 //select issue date
	                 ResultSet rs = stmt.executeQuery("SELECT ISSUED_DATE FROM ISSUED WHERE BACCNUM="+bid);
	                 //BETA TEST OF ADDITIONAL FINE SYSTEM
	                 while (rs.next()) {
	                     date1 = rs.getString(1);
	                      
	                   }
	                  
	                 try {
	                        Date date_1=new SimpleDateFormat("dd-MM-yyyy").parse(date1);
	                        Date date_2=new SimpleDateFormat("dd-MM-yyyy").parse(date2);
	                        //subtract the dates and store in diff
	                        long diff = date_2.getTime() - date_1.getTime();
	                        //Convert diff from milliseconds to days
	                        ex.days=(int)(TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS));
	                         
	                         
	                    } catch (ParseException e1) {
	                        // TODO Auto-generated catch block
	                        e1.printStackTrace();
	                    }
	                  
	                 
	                 //update return date, clear borrowing period.
	                 stmt.executeUpdate("UPDATE ISSUED SET RETURN_DATE='"+return_date+"' WHERE BACCNUM="+bid);
	                 stmt.executeUpdate("UPDATE BOOKS SET RETURN_DATE='"+return_date+"' WHERE BACCNUM="+bid);
	                 stmt.executeUpdate("UPDATE BOOKS SET STATUS='Available' WHERE BACCNUM="+bid);
	                 stmt.executeUpdate("UPDATE BOOKS SET PERIOD=NULL  WHERE BACCNUM="+bid);
	                 f.dispose();
	                 admin_menu();
	                 g.dispose();
	                 JOptionPane.showMessageDialog(null,"Book Returned!");
	                  
	                }
	                         
	                 
	                catch (SQLException e1) {
	                    // TODO Auto-generated catch block
	                     JOptionPane.showMessageDialog(null, e1);
	                }   
	                 
	                }
	                 
	            }); 
	                g.getContentPane().add(l4);
	                g.getContentPane().add(returnButton);
	                g.getContentPane().add(l1);
	                g.getContentPane().add(R_bid);
	                g.getContentPane().add(R_return);
	                g.setSize(347,455);  
	                g.getContentPane().setLayout(null);
	                
	                JLabel lblScannedRfid = new JLabel("Scanned RFID");
	                lblScannedRfid.setBounds(12, 81, 97, 30);
	                g.getContentPane().add(lblScannedRfid);
	                
	                JTextField R_brfid = new JTextField();
	                R_brfid.setBounds(132, 82, 95, 30);
	                g.getContentPane().add(R_brfid);
	                
	                JLabel lblNewLabel = new JLabel("New label");
	                lblNewLabel.setIcon(new ImageIcon("src/img/header.png"));
	                lblNewLabel.setBounds(-12, 0, 354, 69);
	                g.getContentPane().add(lblNewLabel);
	                
	                JLabel lblBookTitle = new JLabel("Book Title");
	                lblBookTitle.setBounds(10, 163, 150, 30);
	                g.getContentPane().add(lblBookTitle);
	                
	                JTextField R_bTitle = new JTextField();
	                R_bTitle.setEditable(false);
	                R_bTitle.setBounds(132, 163, 189, 30);
	                g.getContentPane().add(R_bTitle);
	                
	                JLabel lblBookAuthor = new JLabel("Book Author");
	                lblBookAuthor.setBounds(10, 204, 75, 30);
	                g.getContentPane().add(lblBookAuthor);
	                
	                JTextField R_bAuth = new JTextField();
	                R_bAuth.setEditable(false);
	                R_bAuth.setBounds(74, 204, 108, 30);
	                g.getContentPane().add(R_bAuth);
	                
	                JLabel lblPersonalIdNumber = new JLabel("Personal ID Number");
	                lblPersonalIdNumber.setBounds(10, 245, 150, 30);
	                g.getContentPane().add(lblPersonalIdNumber);
	                
	                JTextField R_idNum = new JTextField();
	                R_idNum.setEditable(false);
	                R_idNum.setBounds(132, 245, 189, 30);
	                g.getContentPane().add(R_idNum);
	                
	                JButton btnSearch = new JButton("Search");

	                JTextField R_bStatus = new JTextField();
	                R_bStatus.setEditable(false);
	                R_bStatus.setBounds(238, 204, 83, 30);
	    			btnSearch.addActionListener(new ActionListener() {
	                	public void actionPerformed(ActionEvent arg0) {
	                		//Create DataBase Coonection and Fetching Records  
	                        try {  
	                            String str = R_brfid.getText();  
	                            Class.forName("com.mysql.cj.jdbc.Driver"); 
	                            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library");  
	                            PreparedStatement st = con.prepareStatement("SELECT * FROM BOOKS where BRFID = ?");  
	                            st.setString(1, str);  
	                            //Excuting Query  
	                            ResultSet rs = st.executeQuery();  
	                            if (rs.next()) {  
	                                String s = rs.getString("BACCNUM");  
	                                String s1 = rs.getString("BTITLE");  
	                                String s2 = rs.getString("BAUTH");  
	                                String s3 = rs.getString("STATUS");
	                                String s4 = rs.getString("UID");
	                                //Sets Records in TextFields.  
	                                R_bid.setText(s);  
	                                R_bTitle.setText(s1);  
	                                R_bAuth.setText(s2);  
	                                R_bStatus.setText(s3); 
	                                R_idNum.setText(s4);
	                            } else {  
	                                JOptionPane.showMessageDialog(null, "Name not Found");  
	                            }  
	                            //Create Exception Handler  
	                        } catch (Exception ex) {  
	                            System.out.println(ex);  
	                        }  
	                    } 
	                });
	                btnSearch.setBackground(Color.WHITE);
	                btnSearch.setBounds(246, 81, 75, 30);
	                g.getContentPane().add(btnSearch);
	                
	                JLabel lblStatus = new JLabel("Status");
	                lblStatus.setBounds(192, 204, 75, 30);
	                g.getContentPane().add(lblStatus);
	                
	                
	                g.getContentPane().add(R_bStatus);
	                g.setVisible(true);
	                g.setLocationRelativeTo(null);                       
	    }	
	    });
  	     
	    f.getContentPane().add(database_but);
	    f.getContentPane().add(return_book);
	    f.getContentPane().add(issue_book);
	    f.getContentPane().add(add_book); 
	    f.getContentPane().setLayout(null);
	    f.getContentPane().add(backgroundImage);
	    f.setVisible(true);
	    f.setLocationRelativeTo(null);
	     
	    }
	}