package io.hotel.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import io.hotel.entities.Reservation;
import io.hotel.entities.RestaurantTable;
import io.hotel.exception.AppException;
import io.hotel.utils.DBUtils;

public class RestaurantTableDAO {
	
	public int findTables(Reservation reservationobj) throws AppException {
		
		int tableno=0;
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			
			
			ps = con.prepareStatement("SELECT TABLENO from RestaurantTables "
					+ "WHERE RESTAURANTID = 1 AND AVAILABILITY = true AND CAPACITY >= ? ORDER BY CAPACITY ");
			
			ps.setInt(1, reservationobj.getParty_size());
			rs = ps.executeQuery();
			
			while(rs.next()){
				tableno = rs.getInt("TABLENO");
				break;
			};
				
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResources(ps, rs, con);
		}
		
		return tableno;
	}
	
	public List<RestaurantTable> findAvailableTables() throws AppException {
			List<RestaurantTable> tables = new ArrayList<RestaurantTable>();
			Connection con = DBUtils.connect();
			PreparedStatement ps = null;
			ResultSet rs = null;
			
			try {
				
				
				ps = con.prepareStatement("SELECT TABLENO from RestaurantTables "
						+ "WHERE RESTAURANTID = 1 AND AVAILABILITY = true ");
				
				rs = ps.executeQuery();
				
				while(rs.next()){
					RestaurantTable tableobj = this.mapToEntity(rs);
					tables.add(tableobj);
				};
					
			} catch (SQLException e) {
				e.printStackTrace();
				throw new AppException(e.getMessage());
			}
			
			finally {
				DBUtils.closeResources(ps, rs, con);
			}
			
			return tables;
		}
	
	private RestaurantTable mapToEntity (ResultSet rs) throws SQLException {
		RestaurantTable tableobj = new RestaurantTable();
		
		tableobj.setRestaurantid(rs.getInt("RESERVATIONID"));
		tableobj.setTableno(rs.getInt("TABLENO"));
		tableobj.setCapacity(rs.getInt("CAPACITY"));
		tableobj.setAvailability(rs.getBoolean("AVAILABILITY"));
		
		return tableobj;
	}
}
