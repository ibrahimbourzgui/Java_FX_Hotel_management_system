package databaseconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetConnection {
	public static Connection con;
	public static Statement st;
	
	public static void connect()  {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/mini_projet_java";
			con = DriverManager.getConnection(url,"root","");
		} catch (Exception e) {
			System.out.println(e.getMessage()+" Connect");
		}	
	}

	public static int Maj(String sql) {
		int nbr = 0;
		try {
			st = con.createStatement();
			nbr = st.executeUpdate(sql); 
		} catch (SQLException e) {
			System.out.println(e.getMessage() +" Maaaj : ");
			return 0;
		}
		return nbr;
	}
	
	public static ResultSet Select(String sql) {
		ResultSet rs = null;
		try {
			st = con.createStatement();
			rs = st.executeQuery(sql); 
			
		} catch (SQLException e) {
			System.out.println(e.getMessage()+" Select :   "+sql);
			return null;
		}
		return rs;
	}
	
	public static void disconnect(){
		try {
			con.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage()+" disonnect");
		} 
	}
	public static void main(String[] args) {
		connect();
		System.out.println(con);
		
	}

}
