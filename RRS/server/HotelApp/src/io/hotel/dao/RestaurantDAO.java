package io.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import io.hotel.entities.Restaurant;
import io.hotel.exception.AppException;
import io.hotel.utils.DBUtils;

public class RestaurantDAO {
	
	public Restaurant restaurantDetails() throws AppException{
		Restaurant restaurantobj = null;
		
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * from Restaurant");
			rs = ps.executeQuery();
			
			if(rs.next()) {
				restaurantobj = this.mapToEntity(rs);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResources(ps, rs, con);
		}
		
		return restaurantobj;
		
	}
	
	public String updateDetails(Restaurant restaurantobj,long restaurantid) throws AppException{
		
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			ps = con.prepareStatement("UPDATE Restaurant "
					+ "SET EMAIL= ? ,RESTAURANTNAME = ?,PHONE = ? ,ADDRESS = ?, CITY = ?,STATE = ?,ZIP= ?, OPERATINGDAYS = ?,OPENINGTIME = ?, CLOSINGTIME = ?, AUTOASSIGN = ?) "
					+ "WHERE RESERVATION_ID = ?; ");
			ps.setString(1, restaurantobj.getEmail());
			ps.setString(2, restaurantobj.getName());
			ps.setLong(3, restaurantobj.getPhone());
			ps.setString(4, restaurantobj.getAddress());
			ps.setString(5, restaurantobj.getCity());
			ps.setString(6, restaurantobj.getState());
			ps.setLong(7, restaurantobj.getZip());
			ps.setString(8, restaurantobj.getOperational_days());
			ps.setString(9, restaurantobj.getOpening_time());
			ps.setString(10, restaurantobj.getClosing_time());
			ps.setBoolean(11, restaurantobj.isAutoassign());
			ps.setLong(12, restaurantid);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResources(ps, rs, con);
		}
		
		return "Update Successful";
		
		
		
	}

	public boolean findAutoAssign() throws AppException {
		
		boolean flag = true;
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			/* 
			 * Assuming that currently there is only one restaurant with RESTAURANT_ID = 1, 
			 * can be scaled to add multiple restaurants and their corresponding auto assign status
			*/ 
			
			ps = con.prepareStatement("SELECT AUTOASSIGN from Restaurant where RESTAURANT_ID = 1; ");
			rs = ps.executeQuery();
			
			flag = rs.next();
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResources(ps, rs, con);
		}
		
		return flag;
	}
	
	
		private Restaurant mapToEntity (ResultSet rs) throws SQLException {
		Restaurant restaurantobj = new Restaurant();
		
		restaurantobj.setRestaurant_id(rs.getLong("RESERVATION_ID"));
		restaurantobj.setName(rs.getString("RESTAURANTNAME"));
		restaurantobj.setEmail(rs.getString("EMAIL"));
		restaurantobj.setPhone(rs.getLong("PHONE"));
		restaurantobj.setAddress(rs.getString("ADDRESS"));
		restaurantobj.setCity(rs.getString("CITY"));
		restaurantobj.setState(rs.getString("STATE"));
		restaurantobj.setZip(rs.getInt("ZIP"));
		restaurantobj.setOpening_time(rs.getString("OPENINGTIME"));
		restaurantobj.setClosing_time(rs.getString("CLOSING_TIME"));
		restaurantobj.setOperational_days(rs.getString("OPERATINGDAYS"));
		restaurantobj.setAutoassign(rs.getBoolean("AUTOASSIGN"));
		return restaurantobj;
	}
}
