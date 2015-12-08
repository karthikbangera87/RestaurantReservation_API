package io.hotel.dao;

import io.hotel.entities.Reservation;
import io.hotel.exception.AppException;
import io.hotel.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAO {

	public List<Reservation> findAllReservations() throws AppException {
		List<Reservation> reservation = new ArrayList<Reservation>();
		
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * from Reservations");
			rs = ps.executeQuery();
			
			while(rs.next()) {
				Reservation reservationobj = this.mapToEntity(rs);
				reservation.add(reservationobj);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResources(ps, rs, con);
		}
		
		return reservation;
	}
	
	public Reservation findOne(long reservationid) throws AppException {
		Reservation reservationobj = null;
		
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("SELECT * from Reservations WHERE RESERVATION_ID = ? ");
			ps.setLong(1, reservationid);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				reservationobj = this.mapToEntity(rs);
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResources(ps, rs, con);
		}
		
		return reservationobj;
	}
	public Reservation makeReservation(Reservation reservationobj) throws AppException {
		
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("INSERT INTO Reservations(EMAIL,CUSTOMER_NAME,PHONE,RESERVATION_DATE, RESERVATION_TIME,PARTY_SIZE) VALUES (?,?,?,?,?,?)", PreparedStatement.RETURN_GENERATED_KEYS);
			ps.setString(1, reservationobj.getEmail());
			ps.setString(2, reservationobj.getCustomer_name());
			ps.setLong(3, reservationobj.getPhone());
			ps.setString(4, reservationobj.getReservation_date());
			ps.setString(5, reservationobj.getReservation_time());
			ps.setInt(6, reservationobj.getParty_size());
			ps.executeUpdate();
			
			rs = ps.getGeneratedKeys();
			
			if(rs.next()) {
				reservationobj.setReservation_id(rs.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResources(ps, rs, con);
		}
		
		return reservationobj;
	}
	
public String editReservation(Reservation reservationobj, long reservationid) throws AppException {
		
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("UPDATE Reservations "
					+ "SET EMAIL= ? ,CUSTOMER_NAME = ?,PHONE = ? ,RESERVATION_DATE = ?, RESERVATION_TIME = ?,PARTY_SIZE = ?) "
					+ "WHERE RESERVATION_ID = ?; ");
			ps.setString(1, reservationobj.getEmail());
			ps.setString(2, reservationobj.getCustomer_name());
			ps.setLong(3, reservationobj.getPhone());
			ps.setString(4, reservationobj.getReservation_date());
			ps.setString(5, reservationobj.getReservation_time());
			ps.setInt(6, reservationobj.getParty_size());
			ps.setLong(7, reservationid);
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
	
	public String cancelReservation(Reservation reservationobj, long reservationid) throws AppException {
		
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("UPDATE Reservations SET RESERVATION_STATUS = 'CANCELLED'  WHERE RESERVATION_ID = ?; ");
			ps.setLong(1, reservationid);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResources(ps, rs, con);
		}
		
		
		return "Reservation status changed to cancel";
	}
	
	public String reservationComplete(Reservation reservationobj, long reservationid) throws AppException {
		
		Connection con = DBUtils.connect();
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			ps = con.prepareStatement("UPDATE Reservations SET RESERVATION_STATUS = 'COMPLETED'  WHERE RESERVATION_ID = ?; ");
			ps.setLong(1, reservationid);
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}
		
		finally {
			DBUtils.closeResources(ps, rs, con);
		}
		
		return "Reservation status changed to complete";
	}
	private Reservation mapToEntity (ResultSet rs) throws SQLException {
		Reservation reservationobj = new Reservation();
		
		reservationobj.setReservation_id(rs.getLong("RESERVATION_ID"));
		reservationobj.setCustomer_name(rs.getString("CUSTOMER_NAME"));
		reservationobj.setEmail(rs.getString("EMAIL"));
		reservationobj.setPhone(rs.getLong("PHONE"));
		reservationobj.setReservation_date(rs.getString("RESERVATION_DATE"));
		reservationobj.setReservation_time(rs.getString("RESERVATION_TIME"));
		reservationobj.setParty_size(rs.getInt("PARTY_SIZE"));
		reservationobj.setReservation_restaurant_id(rs.getInt("RESERVATION_RESTAURANT_ID"));
		reservationobj.setReservation_table_no(rs.getInt("RESERVATION_TABLE_NO"));
		reservationobj.setReservation_status(rs.getString("RESERVATION_STATUS"));
		return reservationobj;
	}
}


