package sysaddLibrary;

import javax.swing.*;

import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class design {

	private JFrame frmLibraryLogin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					design window = new design();
					window.frmLibraryLogin.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public design() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @wbp.parser.entryPoint
	 */
	private void initialize() {
		
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