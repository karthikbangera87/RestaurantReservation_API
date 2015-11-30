package io.hotel.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtils {
	
	private static final String DB_USER = "root";
	private static final String DB_PASSWORD = "root";
	private static final String DB_URL = "jdbc:mysql://localhost:3306/hotel_db";
	
	static {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver loaded successfully");
		} catch (ClassNotFoundException e) {
			System.out.println("Error loading JDBC driver" + e.getMessage());
			e.printStackTrace();
		}
		
	}
	public static Connection connect(){
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			System.out.println("Connection successful");
		} catch (SQLException e) {
			System.out.println("Error in getting connection" + e.getMessage());
			e.printStackTrace();
		}
		return con;
		
	}
	
	public static void closeResources (PreparedStatement ps, ResultSet rs, Connection con) {
		try {
			if(ps != null) {
				ps.close();
			}
			if(rs != null) {
				rs.close();
			}
			
			if(con != null) {
				con.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String args[]){
		DBUtils.connect();
	}
}
