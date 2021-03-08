package sysaddLibrary;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class addBook {

	public static void add() {
		// TODO Auto-generated method stub
		JFrame g = new JFrame("Enter Book Details");
		g.setResizable(false);
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
        lblNewLabel.setIcon(new ImageIcon(design.class.getResource("/img/header.png")));
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
        addBook.setIcon(new ImageIcon(design.class.getResource("/img/add.png")));
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
             g.dispose();
             homeScreen.home();
              
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
            g.setIconImage(Toolkit.getDefaultToolkit().getImage(home.class.getResource("/img/icon.png")));
            g.setVisible(true);//making the frame visible 
            g.setLocationRelativeTo(null);
            
            
    	}	
}