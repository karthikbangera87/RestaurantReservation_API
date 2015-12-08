package io.hotel.entities;

public class Restaurant {
	private long restaurant_id;
	private String name;
	private String email;
	private String address;
	private long phone;
	private String opening_time;
	private String closing_time;
	private String operational_days;
	private boolean autoassign;
	private String city;
	private String state;
	private int zip;
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public int getZip() {
		return zip;
	}
	public void setZip(int zip) {
		this.zip = zip;
	}
	public long getRestaurant_id() {
		return restaurant_id;
	}
	public void setRestaurant_id(long restaurant_id) {
		this.restaurant_id = restaurant_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getOpening_time() {
		return opening_time;
	}
	public void setOpening_time(String opening_time) {
		this.opening_time = opening_time;
	}
	public String getClosing_time() {
		return closing_time;
	}
	public void setClosing_time(String closing_time) {
		this.closing_time = closing_time;
	}
	public String getOperational_days() {
		return operational_days;
	}
	public void setOperational_days(String operational_days) {
		this.operational_days = operational_days;
	}
	public boolean isAutoassign() {
		return autoassign;
	}
	public void setAutoassign(boolean autoassign) {
		this.autoassign = autoassign;
	}
	
	

}
