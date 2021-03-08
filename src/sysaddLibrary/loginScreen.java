package sysaddLibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class loginScreen {

	public static void login() {
		// TODO Auto-generated method stub
		JFrame loginSystem = new JFrame();
		loginSystem.setIconImage(Toolkit.getDefaultToolkit().getImage(loginScreen.class.getResource("/img/icon.png")));
		loginSystem.setResizable(false);
		loginSystem.setTitle("Library Login System");
		loginSystem.setBounds(100, 100, 501, 331);
		//loginSystem.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		loginSystem.getContentPane().setLayout(null);
		
		JTextField rfidInput = new JTextField();
		
		JLabel header = new JLabel("");
		header.setBounds(6, 5, 483, 69);
		header.setIcon(new ImageIcon("C:\\Users\\Miko\\eclipse-workspace\\sysaddLibrary\\src\\img\\loginheader.png"));
		loginSystem.getContentPane().add(header);
		
		JLabel motd = new JLabel("Please tap your ID card");
		motd.setBounds(148, 80, 200, 21);
		motd.setFont(new Font("Tahoma", Font.BOLD, 17));
		motd.setHorizontalAlignment(SwingConstants.CENTER);
		loginSystem.getContentPane().add(motd);
		
		JLabel greeting = new JLabel("");
		greeting.setHorizontalAlignment(SwingConstants.CENTER);
		greeting.setBounds(148, 112, 200, 20);
		greeting.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		loginSystem.getContentPane().add(greeting);
		
		JLabel userName = new JLabel("");
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		userName.setBounds(148, 150, 200, 14);
		loginSystem.getContentPane().add(userName);
		
		JLabel idNum = new JLabel("");
		idNum.setHorizontalAlignment(SwingConstants.CENTER);
		idNum.setBounds(148, 200, 200, 14);
		loginSystem.getContentPane().add(idNum);
		
		JLabel email = new JLabel("");
		email.setHorizontalAlignment(SwingConstants.CENTER);
		email.setBounds(148, 175, 200, 14);
		loginSystem.getContentPane().add(email);
		
		JButton confirm = new JButton("");
		confirm.setBounds(286, 312, 33, 9);
		loginSystem.getContentPane().add(confirm);
		confirm.setForeground(new Color(0, 51, 153));
		confirm.setBackground(Color.WHITE);
		confirm.setOpaque(false);
		confirm.setBorderPainted(false);
		loginSystem.getRootPane().setDefaultButton(confirm);
		confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {  
                    String str = rfidInput.getText();  
                    Class.forName("com.mysql.cj.jdbc.Driver"); 
                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost/library");  
                    PreparedStatement st = con.prepareStatement("SELECT * FROM USERS where URFID = ?"); 
                    Statement scon=con.createStatement();
                    
                    
                    st.setString(1, str);  
                    //Excuting Query  
                    ResultSet rs = st.executeQuery();  
                    if (rs.next()) {  
                        String s = rs.getString("LNAME"); 
                        String s1 = rs.getString("FNAME");
                        String s2 = rs.getString("studentID");  
                        String s3 = rs.getString("EMAIL"); 
                        String usType = rs.getString("USTYPE");
                        //Sets Records in TextFields.  
                        greeting.setText("Good Day!");  
                        userName.setText(s +", "+ s1);  
                        idNum.setText(s2);  
                        email.setText(s3); 
                        
                        //Throw data to database
                        scon.execute("INSERT INTO LOGIN(studentID, URFID, FNAME, LNAME, EMAIL, USTYPE) VALUES('" + s2 + "','" + str + "','" + s1 + "','" + s + "','"+ s3 +"', '"+usType+"')");
                        
                     
                        int delay = 2500; //milliseconds, Clears the labels for the next user
  				      ActionListener taskPerformer = new ActionListener() {
  				          public void actionPerformed(ActionEvent evt) {
  				        	  greeting.setText("");
  				        	  userName.setText("");
  				        	  idNum.setText("");
  				        	  email.setText(""); 
  				        	  rfidInput.setText(""); 
  				        	  
  				            }
  				        };
  				        javax.swing.Timer tick=new javax.swing.Timer(delay,taskPerformer);
  				        tick.setRepeats(false);
  				        tick.start();
                    } else {  
                        JOptionPane.showMessageDialog(null, "Name not Found");  
                    }  
                    //Create Exception Handler  
                } catch (Exception ex) {  
                    System.out.println(ex);  
                }  
            }  
        });
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(6, 256, 483, 45);
		loginSystem.getContentPane().add(lblNewLabel);
		lblNewLabel.setIcon(new ImageIcon(loginScreen.class.getResource("/img/footer.png")));
		
	
		
		
		rfidInput.setBounds(200, 311, 86, 20);
		loginSystem.getContentPane().add(rfidInput);
		rfidInput.setForeground(Color.WHITE);
		rfidInput.setFont(new Font("Tahoma", Font.PLAIN, 11));
		rfidInput.setHorizontalAlignment(SwingConstants.CENTER);
		rfidInput.setColumns(10);
		loginSystem.setVisible(true);
	}

}
