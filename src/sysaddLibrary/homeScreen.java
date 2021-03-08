package sysaddLibrary;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;
import net.proteanit.sql.DbUtils;

public class homeScreen {

	public static void home() {
		// TODO Auto-generated method stub
		JFrame frmApcLibraryManagement = new JFrame();
		frmApcLibraryManagement.setIconImage(Toolkit.getDefaultToolkit().getImage(home.class.getResource("/img/icon.png")));
		frmApcLibraryManagement.setResizable(false);
		frmApcLibraryManagement.setTitle("APC Library Management System");
		frmApcLibraryManagement.setBounds(100, 100, 884, 455);
		frmApcLibraryManagement.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
		JMenuBar menuBar = new JMenuBar();
		frmApcLibraryManagement.setJMenuBar(menuBar);
		
		
		JMenu manageMenu = new JMenu("Manage");
		menuBar.add(manageMenu);
		
		//Add Book
		JMenuItem addBookMenuItem = new JMenuItem("Add Book");
		manageMenu.add(addBookMenuItem);
		addBookMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				addBook.add();
				frmApcLibraryManagement.dispose();
			}
		});
		
		JMenuItem rmvBookMenuItem = new JMenuItem("Remove Book");
		manageMenu.add(rmvBookMenuItem);
		rmvBookMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				removeBook.remove();
				frmApcLibraryManagement.dispose();
			}
		});
		
		JMenuItem upBookMenuItem = new JMenuItem("Update Book");
		manageMenu.add(upBookMenuItem);
		upBookMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				updateBook.update();
				frmApcLibraryManagement.dispose();
			}
		});
		
		JMenu genMenu = new JMenu("Generate");
		menuBar.add(genMenu);
		
		JMenuItem attendanceMenuItem = new JMenuItem("Attendance");
		genMenu.add(attendanceMenuItem);
		
		JMenuItem bookLogMenuItem = new JMenuItem("Book Log");
		genMenu.add(bookLogMenuItem);
		
		//Debug Menu
		JMenu debugMenu = new JMenu("Debug");
		menuBar.add(debugMenu);
		
		//Reset Menu (disabled)
		JMenuItem dbResetMenuItem = new JMenuItem("Database Reset");
		dbResetMenuItem.setEnabled(false);
		debugMenu.add(dbResetMenuItem);
		dbResetMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				reset.reset();
				frmApcLibraryManagement.dispose();
			}
		});
		
		JMenuItem mntmLoginSystem = new JMenuItem("Login System");
		debugMenu.add(mntmLoginSystem);
		debugMenu.add(mntmLoginSystem);
		mntmLoginSystem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				loginScreen.login();
			}
		});
		
		//About 
		JMenuItem aboutMenuItem = new JMenuItem("About");
		debugMenu.add(aboutMenuItem);
		aboutMenuItem.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				about.init();
			}
		});
		frmApcLibraryManagement.getContentPane().setLayout(new MigLayout("", "[242.00px,grow][303.00px,grow][-276.00]", "[69px,fill][36.00px,grow][]"));
		
		//Show book database
		Connection connection = main.connect();
	    String sql="SELECT BACCNUM as 'Accession No.', BTITLE as 'Title', BAUTH as 'Author', GENRE as 'Genre', PUBLISHER as 'Publisher', PUBYEAR as 'Year Published', ISSUED_DATE as 'Issued Date', RETURN_DATE as 'Return Date', PERIOD as 'Period', STATUS as 'Status' FROM BOOKS"; //select all books 

        try {
            Statement stmt = connection.createStatement();
            stmt.executeUpdate("USE LIBRARY"); 
            stmt=connection.createStatement();
            ResultSet rs=stmt.executeQuery(sql);
    		
    		//Header Logo
    		JLabel headerLogo = new JLabel("");
    		headerLogo.setHorizontalAlignment(SwingConstants.CENTER);
    		headerLogo.setIcon(new ImageIcon(home.class.getResource("/img/GUI.png")));
    		frmApcLibraryManagement.getContentPane().add(headerLogo, "cell 0 0 2 1,alignx center,aligny center");
    		
    		//JTable Scroll Pane
    		JScrollPane scrollPane = new JScrollPane();
    		frmApcLibraryManagement.getContentPane().add(scrollPane, "cell 0 1 2 1,grow");
    		JTable book_list = new JTable();
    		book_list.setRowSelectionAllowed(false);
    		scrollPane.setViewportView(book_list);
    		book_list.setModel(DbUtils.resultSetToTableModel(rs));
    		
    		//Issue book 
    		JButton issueButton = new JButton("");
    		issueButton.setToolTipText("Issue Book");
    		issueButton.setBackground(Color.WHITE);
    		issueButton.setOpaque(false);
    		issueButton.setBorderPainted(false);
    		issueButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				issueBook.issueBooks();
    				frmApcLibraryManagement.dispose();
    			}
    		});
    		
    		//Created by
    		JLabel lblNewLabel = new JLabel("Created by: LibraryBoys");
    		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 8));
    		frmApcLibraryManagement.getContentPane().add(lblNewLabel, "cell 0 2,alignx leading,aligny bottom");
    		issueButton.setIcon(new ImageIcon(home.class.getResource("/img/borrow.png")));
    		frmApcLibraryManagement.getContentPane().add(issueButton, "flowx,cell 1 2,alignx right,aligny top");
    		
    		//Return book
    		JButton returnButton = new JButton("");
    		returnButton.setToolTipText("Return Book");
    		returnButton.setBackground(Color.WHITE);
    		returnButton.setOpaque(false);
    		returnButton.setBorderPainted(false);
    		returnButton.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {
    				returnBook.returnBooks();;
    				frmApcLibraryManagement.dispose();
    			}
    		});
    		returnButton.setIcon(new ImageIcon(home.class.getResource("/img/return.png")));
    		frmApcLibraryManagement.getContentPane().add(returnButton, "cell 1 2,alignx right,aligny top");
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
             JOptionPane.showMessageDialog(null, e1);
        }
        
        frmApcLibraryManagement.setVisible(true);
	}

}
