package tn.poly.bahri.gestion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ConnectDB {
	String url="jdbc:mysql://localhost/salesDB";
	String login="root";
	String pass="";
	public static Connection cn;
	
	private ConnectDB(){
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			cn=DriverManager.getConnection(url,login,pass);	
		}
		catch(Exception exception) {
			System.out.println(exception.getMessage());
		}

	}
	
	public static Connection getConnexion(){
		if (cn==null)
			new ConnectDB();
		return cn;
	}
	
}
