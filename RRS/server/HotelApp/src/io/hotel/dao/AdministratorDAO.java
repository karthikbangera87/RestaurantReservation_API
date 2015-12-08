package io.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import io.hotel.entities.Administrator;
import io.hotel.exception.AppException;
import io.hotel.utils.DBUtils;

public class AdministratorDAO {
	
	public static Map<String,String> sessionmap = null;
	
	public AdministratorDAO(){	
		if (sessionmap == null){
		sessionmap = new HashMap<String,String>();
		}
		
	}
	
	public Administrator authenticate(Administrator logindetails) throws AppException{
		
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		boolean flag = false;
		
		try {
			ps = con.prepareStatement("SELECT * from Administartor WHERE email = ? AND password = ?");
			ps.setString(1, logindetails.getEmail());
			ps.setString(2, logindetails.getPassowrd());
			rs = ps.executeQuery();
			if(rs.next()) {
				flag = true;
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResources(ps, rs, con);
		}
		
		if (flag){
			logindetails.setPassowrd(null);
			logindetails.setToken(UUID.randomUUID().toString());
			sessionmap.put(logindetails.getEmail(),logindetails.getToken());
		}
		else {
			logindetails.setToken(null);
		}
		
		return logindetails;
	}
	
	public Administrator logout(Administrator logindetails){
		logindetails.setEmail(null);
		logindetails.setToken(null);
		return logindetails;
	}
	
	

}
