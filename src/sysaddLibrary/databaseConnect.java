package sysaddLibrary;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class databaseConnect {
	public static Connection connect() {
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
}
