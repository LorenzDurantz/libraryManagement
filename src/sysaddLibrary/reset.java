package sysaddLibrary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class reset {

	public static void reset() {
		 try {
			    Connection connection = databaseConnect.connect();
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
			          String sql1 = "CREATE TABLE USERS(studentID INT NOT NULL, URFID VARCHAR(10), FNAME VARCHAR(50), LNAME VARCHAR(50), EMAIL VARCHAR(50), USTYPE VARCHAR(50), ADMIN BOOLEAN)";
			          stmt.executeUpdate(sql1);
			          //Insert into users table
			          stmt.executeUpdate("INSERT INTO USERS(studentID, URFID, FNAME, LNAME, EMAIL, USTYPE, ADMIN) VALUES('2018100195','0013413635', 'Lorenz Miguel', 'Durante', 'lcdurante@student.apc.edu.ph','student',TRUE), ('2015100438','0013413507', 'William', 'Magalong', 'wmagalong@student.apc.edu.ph','student',FALSE), ('2017100312','0013413339', 'Jaymz', 'Johnson', 'jjohnson@student.apc.edu.ph','student',FALSE), ('2010100245','0005724375', 'John', 'Cena', 'johnc@apc.edu.ph','teacher',TRUE),('2011100025','0005721420', 'Michael', 'Reeves', 'michaelr@apc.edu.ph','teacher',TRUE) ");
			          //Create Books table
			          stmt.executeUpdate("CREATE TABLE BOOKS(BACCNUM INT NOT NULL AUTO_INCREMENT PRIMARY KEY, BRFID VARCHAR(10),BTITLE VARCHAR(50), BAUTH VARCHAR(50), GENRE VARCHAR(50), PUBLISHER VARCHAR(50), PUBYEAR INT(4), ISSUED_DATE VARCHAR(20), RETURN_DATE VARCHAR(20), PERIOD INT, STATUS VARCHAR(20), UID INT)");
			          //Create Issued Table
			          stmt.executeUpdate("CREATE TABLE ISSUED(ISSUEID INT NOT NULL AUTO_INCREMENT PRIMARY KEY, BRFID INT(10), UID INT, BACCNUM INT, ISSUED_DATE VARCHAR(20), RETURN_DATE VARCHAR(20), PERIOD INT, BORROWED INT)");
			          //Insert into books table
			          stmt.executeUpdate("INSERT INTO BOOKS(BRFID, BTITLE, BAUTH, GENRE, PUBLISHER, PUBYEAR, STATUS) VALUES ('0007315860', 'War and Peace', 'Mike Stint', 'Fiction', 'Publeash Inc.', 2002, 'Available'),  ('0007315861', 'The Sost Lymbol', 'Ban Drown', 'Nonfiction', 'Penguin Inc.', 2010, 'Available'), ('0007315862', 'Jomeo and Ruliet', 'Shilliam Wakespeare', 'Mystery', 'Microsoft Publishing.', 2022, 'Available'),('0007315863', '1489', 'Borge Borwell', 'Comedy', 'Mystery Inc.', 1489, 'Available'), ('0007315864', 'Tarey Fails', 'Princess Aurora', 'Horror', 'The Printer Inc', 2012, 'Available'),('0007315865', 'Bambee', 'Salt Widney', 'Mythology', 'Jack Sparrow Publishers', 1985, 'Available'), ('0007315866', 'Mankey', 'Shmibid Attenmibid', 'Educational', 'SF Publishing', 1521, 'Available')");
			          //login
			          stmt.executeUpdate("CREATE TABLE LOGIN(studentID INT NOT NULL, URFID VARCHAR(10), FNAME VARCHAR(50), LNAME VARCHAR(50), EMAIL VARCHAR(50), USTYPE VARCHAR(50), DATE datetime default now())");
			    resultSet.close();
			    
			    }
			     catch (Exception ex) {
			         ex.printStackTrace();
		             home.initialize();
			     }
			}
	}


