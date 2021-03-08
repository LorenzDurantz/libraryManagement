package sysaddLibrary;

import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class removeBook {

	public static void remove() {
		// TODO Auto-generated method stub
		JFrame g = new JFrame("Enter Book Details");
		g.setResizable(false);
		g.setTitle("Remove Book from Library");
        //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // set labels
        JLabel l1,l2,l3;  
        l1=new JLabel("Book Title");  //lebel 1 for book name
        l1.setBounds(30,160, 100,30); 
         
         
        l2=new JLabel("Author");  //label 2 for genre
        l2.setBounds(30,197, 100,30); 
         
        l3=new JLabel("Genre");  //label 2 for price
        l3.setBounds(30,230, 100,30); 
        
        JLabel lblPublisher = new JLabel("Publisher");
        lblPublisher.setBounds(30, 263, 73, 30);
        g.getContentPane().add(lblPublisher);

        JLabel lblbAccNum = new JLabel("Book Accession No.");
        lblbAccNum.setBounds(30, 122, 121, 30);
        g.getContentPane().add(lblbAccNum);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setIcon(new ImageIcon(design.class.getResource("/img/header.png")));
        lblNewLabel.setBounds(-6, 1, 388, 69);
        
        JLabel lblRfidTag = new JLabel("RFID Tag");
        lblRfidTag.setBounds(30, 88, 73, 30);
        
        //set text field for book name
        JTextField F_btitle = new JTextField();
        F_btitle.setEditable(false);
        F_btitle.setBounds(110, 160, 200, 30);
        
        //set text field for genre 
        JTextField F_bauth=new JTextField();
        F_bauth.setEditable(false);
        F_bauth.setBounds(110, 197, 200, 30);
        //set text field for price
        JTextField F_genre=new JTextField();
        F_genre.setEditable(false);
        F_genre.setBounds(110, 230, 200, 30);
                 
        JTextField F_Publisher = new JTextField();
        F_Publisher.setEditable(false);
        F_Publisher.setBounds(110, 263, 200, 30);
        
        JTextField F_bAccNum = new JTextField();
        F_bAccNum.setEditable(false);
        F_bAccNum.setBounds(161, 122, 149, 30);
        
        JTextField rfidTag = new JTextField();
        rfidTag.setBounds(110, 88, 169, 30);
        
        JButton addBook=new JButton("");//creating instance of JButton to submit details  
        addBook.setOpaque(false);
        addBook.setBorderPainted(false);
        addBook.setBackground(Color.WHITE);
        addBook.setIcon(new ImageIcon(design.class.getResource("/img/remove.png")));
        addBook.setBounds(60,327,228,53);//x axis, y axis, width, height 
        addBook.addActionListener(new ActionListener() {
             
            public void actionPerformed(ActionEvent e){
            // assign the book name, genre, price
            String bname = F_btitle.getText();
            String auth = F_bauth.getText();
            String genre = F_genre.getText();
            String pub = F_Publisher.getText();
            String yrPub = F_bAccNum.getText();
            String rfid = rfidTag.getText();
            Connection connection = main.connect();
             
            try {
            Statement stmt = connection.createStatement();
             stmt.executeUpdate("USE LIBRARY");
             stmt.executeUpdate("DELETE FROM BOOKS WHERE BRFID = '"+rfid+"'");
             JOptionPane.showMessageDialog(null,"Book removed!");
             
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
            g.getContentPane().add(F_Publisher);
            g.getContentPane().add(F_bAccNum);
            g.getContentPane().add(lblNewLabel);
            g.getContentPane().add(lblRfidTag);
            g.getContentPane().add(rfidTag);
            
            JButton searchBtn = new JButton("");
            searchBtn.addActionListener(new ActionListener() {
            	public void actionPerformed(ActionEvent e) {
            		try {  
                        String str = rfidTag.getText();  
                        Class.forName("com.mysql.cj.jdbc.Driver"); 
                        Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library");  
                        PreparedStatement st = con.prepareStatement("SELECT * FROM BOOKS where BRFID = ?");  
                        st.setString(1, str);  
                        //Excuting Query  
                        ResultSet rs = st.executeQuery();  
                        if (rs.next()) {  
                            String s = rs.getString("GENRE");  
                            String s1 = rs.getString("BTITLE");  
                            String s2 = rs.getString("BAUTH");  
                            String s3 = rs.getString("PUBLISHER");
                            String s4 = rs.getString("BACCNUM");
                            //Sets Records in TextFields.  
                            F_genre.setText(s);  
                            F_btitle.setText(s1);  
                            F_bauth.setText(s2);  
                            F_Publisher.setText(s3);  
                            F_bAccNum.setText(s4);
                        } else {  
                            JOptionPane.showMessageDialog(null, "Name not Found");  
                        }  
                        //Create Exception Handler  
                    } catch (Exception ex) {  
                        System.out.println(ex);  
                    }  
                }  
            });
            searchBtn.setBounds(221, 88, 89, 30);
            g.getContentPane().add(searchBtn);
            searchBtn.setBackground(Color.WHITE);
            searchBtn.setOpaque(false);
            searchBtn.setBorderPainted(false);
            g.getRootPane().setDefaultButton(searchBtn);
            g.setIconImage(Toolkit.getDefaultToolkit().getImage(home.class.getResource("/img/icon.png")));
            g.setVisible(true);//making the frame visible 
            g.setLocationRelativeTo(null);
            
            
    	}	
}