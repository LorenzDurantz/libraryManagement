package sysaddLibrary;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

public class databaseConnect {
	public static void databaseConnect() {
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
            
            
        } catch (SQLException e1) {
            // TODO Auto-generated catch block
             JOptionPane.showMessageDialog(null, e1);
        }
	}
}
