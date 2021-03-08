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
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class issueBook {

	public static void issueBooks() {
		// TODO Auto-generated method stub
		JFrame g = new JFrame("Enter Details");
		g.setResizable(false);
		
		 g.setTitle("Borrow Book");
        //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //create labels
		 JTextField F_bid ,F_uid,F_period,F_brfid,F_issue,F_bstatus,F_bauth,F_btitle;
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
        F_brfid.setBounds(96, 108, 260, 30);
        

        F_bid = new JTextField();
        F_bid.setBounds(167, 158, 189, 30);
         
        F_uid=new JTextField();
        F_uid.setBounds(167, 288, 189, 30);
         
        F_period=new JTextField();
        F_period.setBounds(167, 321, 189, 30);
        
        F_issue=new JTextField();
        F_issue.setBounds(167, 353, 189, 30);  
        
        
        F_bstatus = new JTextField();
        F_bstatus.setBounds(222, 232, 134,30);
        
        F_btitle = new JTextField();
        F_btitle.setBounds(167, 191, 189, 30);
        
        F_bauth = new JTextField();
        F_bauth.setBounds(65, 232, 93, 30);
        
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        F_issue.setText(dateFormat.format(date));

  	  
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
             
            Connection connection = main.connect(); 
            try {
            Statement stmt = connection.createStatement();
             stmt.executeUpdate("USE LIBRARY");
             stmt.executeUpdate("INSERT INTO ISSUED(BRFID,UID,BACCNUM,ISSUED_DATE,PERIOD) VALUES ('"+brfid+"','"+uid+"','"+bid+"','"+issued_date+"','"+period_int+"')");
             stmt.executeUpdate("UPDATE BOOKS SET ISSUED_DATE='"+issued_date+"' WHERE BACCNUM="+bid);
             stmt.executeUpdate("UPDATE BOOKS SET PERIOD='"+period_int+"' WHERE BACCNUM="+bid);
             stmt.executeUpdate("UPDATE BOOKS SET STATUS='On Loan' WHERE BACCNUM="+bid);
             stmt.executeUpdate("UPDATE BOOKS SET UID='"+uid+"' WHERE BACCNUM="+bid);
             stmt.executeUpdate("UPDATE BOOKS SET RETURN_DATE='' WHERE BACCNUM="+bid);
             JOptionPane.showMessageDialog(null,"Book Issued!");
             //f.dispose();
             
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
            g.getContentPane().add(l4);
            g.getContentPane().add(issueButton);
            g.getContentPane().add(l1);
            g.getContentPane().add(l2);
            g.getContentPane().add(F_uid);
            g.getContentPane().add(F_bid);
            g.getContentPane().add(F_period);
            g.getContentPane().add(F_issue);
            g.setSize(372,509);//400 width and 500 height  
            g.getContentPane().setLayout(null);//using no layout managers  
            g.getContentPane().add(F_brfid);
            g.getContentPane().add(l5);
            
            JButton searchDB = new JButton("");
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
            g.getRootPane().setDefaultButton(searchDB);
            searchDB.setBackground(Color.WHITE);
            searchDB.setOpaque(false);
            searchDB.setBorderPainted(false);
            JLabel lblNewLabel = new JLabel("");
            lblNewLabel.setBackground(Color.WHITE);
            lblNewLabel.setIcon(new ImageIcon("C:\\Users\\Miko\\eclipse-workspace\\sysaddLibrary\\src\\img\\header.png"));
            lblNewLabel.setBounds(0, 0, 416, 69);
            g.getContentPane().add(lblNewLabel);
            g.setIconImage(Toolkit.getDefaultToolkit().getImage(home.class.getResource("/img/icon.png")));
           
           
  	}
};

	

