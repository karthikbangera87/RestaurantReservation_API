package io.hotel.entities;

public class Reservation {
	
	private long reservation_id;
	private String customer_name;
	private String email;
	private long phone;
	private String reservation_time;
	private String reservation_date;
	private int party_size;
	private int reservation_restaurant_id;
	private int reservation_table_no;
	private String reservation_status;
	public long getReservation_id() {
		return reservation_id;
	}
	public void setReservation_id(long reservation_id) {
		this.reservation_id = reservation_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getReservation_time() {
		return reservation_time;
	}
	public void setReservation_time(String reservation_time) {
		this.reservation_time = reservation_time;
	}
	public String getReservation_date() {
		return reservation_date;
	}
	public void setReservation_date(String reservation_date) {
		this.reservation_date = reservation_date;
	}
	public int getParty_size() {
		return party_size;
	}
	public void setParty_size(int party_size) {
		this.party_size = party_size;
	}
	public int getReservation_restaurant_id() {
		return reservation_restaurant_id;
	}
	public void setReservation_restaurant_id(int reservation_restaurant_id) {
		this.reservation_restaurant_id = reservation_restaurant_id;
	}
	public int getReservation_table_no() {
		return reservation_table_no;
	}
	public void setReservation_table_no(int reservation_table_no) {
		this.reservation_table_no = reservation_table_no;
	}
	public String getReservation_status() {
		return reservation_status;
	}
	public void setReservation_status(String reservation_status) {
		this.reservation_status = reservation_status;
	}

	
}

