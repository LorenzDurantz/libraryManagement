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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import sysaddLibrary.main.ex;

public class returnBook {

	public static void returnBooks() {
		// TODO Auto-generated method stub
		JLabel l1,l2,l3,l4;  
    	JFrame g = new JFrame("Enter Details");
    	g.setResizable(false);
		g.setTitle("Return Books");
        
        l1=new JLabel("Book Accession No.");
        l1.setBounds(10,122, 150,30); 
        
        l4=new JLabel("Return Date(YYYY-MM-DD)");  
        l4.setBounds(10,290, 150,30); 
         
        JTextField R_bid = new JTextField();
        R_bid.setEditable(false);
        R_bid.setBounds(132, 122, 189, 30);
         
        JTextField R_return=new JTextField();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        R_return.setText(dateFormat.format(date));
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
            
            Connection connection = databaseConnect.connect();
             
            try {
            Statement stmt = connection.createStatement();
             stmt.executeUpdate("USE LIBRARY");
             //Intialize date1 with NULL value
             String date1=null;

             
             //select issue date
             ResultSet rs = stmt.executeQuery("SELECT ISSUED_DATE FROM ISSUED WHERE BACCNUM="+bid);
             

             //update return date, clear borrowing period.
             stmt.executeUpdate("UPDATE ISSUED SET RETURN_DATE='"+return_date+"' WHERE BACCNUM="+bid);
             stmt.executeUpdate("UPDATE BOOKS SET RETURN_DATE='"+return_date+"' WHERE BACCNUM="+bid);
             stmt.executeUpdate("UPDATE BOOKS SET STATUS='Available' WHERE BACCNUM="+bid);
             stmt.executeUpdate("UPDATE BOOKS SET PERIOD=NULL  WHERE BACCNUM="+bid);
             
             home.initialize();
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
            R_brfid.setBounds(132, 82, 189, 30);
            g.getContentPane().add(R_brfid);
            
            JLabel lblNewLabel = new JLabel("New label");
            lblNewLabel.setIcon(new ImageIcon(design.class.getResource("/img/header.png")));
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
            
            JButton btnSearch = new JButton("");

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
            g.getRootPane().setDefaultButton(btnSearch);
            
            btnSearch.setBackground(Color.WHITE);
            btnSearch.setOpaque(false);
            btnSearch.setBorderPainted(false);
            g.setIconImage(Toolkit.getDefaultToolkit().getImage(home.class.getResource("/img/icon.png")));
            g.getContentPane().add(R_bStatus);
            g.setVisible(true);
            g.setLocationRelativeTo(null);                       
	}	
};

