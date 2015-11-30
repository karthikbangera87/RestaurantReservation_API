package io.hotel.rest.controller;

import io.hotel.dao.ReservationDAO;
import io.hotel.dao.RestaurantDAO;
import io.hotel.dao.RestaurantTableDAO;
import io.hotel.entities.Reservation;
import io.hotel.exception.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

@Path("/reservation")
@Api(tags = {"reservation"})
public class GuestReservationController {
	
	@GET
	@Path("/find/{reservationid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find reservation based on reservation ID", notes = "Finds reservation details based on Reservation ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
	@ApiResponse(code = 404, message = "Not Found"),
	@ApiResponse(code = 500, message = "Internal Server Error") })
	public Reservation findOne(@PathParam("reservationid") long reservationid)
	{	
		ReservationDAO dao = new ReservationDAO();
		try {
			return dao.findOne(reservationid);
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
	}
	
	@POST
	@Path("/makeReservation")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Make a reservation", notes = "Makes a reservation")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
	@ApiResponse(code = 400, message = "Bad Request"),
	@ApiResponse(code = 500, message = "Internal Server Error") })
	public Reservation makeReservation(Reservation reservation)
	{
		boolean autoassignstatus;
		int tableno;
		ReservationDAO dao = new ReservationDAO();
		autoassignstatus = checkAutoAssign();
		
		/* 
		 *  The idea is check the auto assign status 
		 *  if true find table and assign the table to the reservation and make a reservation with confirmed status
		 *  if 0 is returned no table is available and the status is changed to waiting
		 *  else if auto assign is false
		 *  set the table no to 0 and set the reservation status to waiting and allow the admin to assign the appropriate 
		 *  table to the reservation.
		 */
		
		if(autoassignstatus){
			RestaurantTableDAO tablesdao = new RestaurantTableDAO();
			try {
				tableno = tablesdao.findTables(reservation);
			} catch (AppException e) {
				throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
			}
			if(tableno != 0){	
				reservation.setReservation_table_no(tableno);
				reservation.setReservation_status("Confirmed");
			}
			else{
				reservation.setReservation_table_no(tableno);
				reservation.setReservation_status("Waiting");
			}
		}
		else{
			
			reservation.setReservation_table_no(0);
			reservation.setReservation_status("Waiting");
			
		}
		try {
			return dao.makeReservation(reservation);
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Path("/editReservation/{reservationid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Edit reservation based on reservation ID", notes = "Edit reservation details based on Reservation ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
	@ApiResponse(code = 404, message = "Not Found"),
	@ApiResponse(code = 500, message = "Internal Server Error") })
	public String editReservation(Reservation reservation,@PathParam("reservationid") long reservationid){
		ReservationDAO dao = new ReservationDAO();
		try {
			return dao.editReservation(reservation,reservationid);
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Path("/cancelReservation/{reservationid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Cancel reservation based on reservation ID", notes = "Cancel reservation based on Reservation ID")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
	@ApiResponse (code = 400, message = "Bad Request"),
	@ApiResponse(code = 404, message = "Not Found"),
	@ApiResponse(code = 500, message = "Internal Server Error") })
	public String cancelReservation(Reservation reservation,@PathParam("reservationid") long reservationid){
		ReservationDAO dao = new ReservationDAO();
		try {
			return dao.cancelReservation(reservation, reservationid);
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);
		}
		
	}
	
	@Path("/Reservationcompletion/{reservationid}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Reservation completion based on reservation ID", notes = "Reservation completion, "
			+ "this is when a bill is generated for that Reservation ID indicationg completion of reservation ")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
	@ApiResponse (code = 400, message = "Bad Request"),
	@ApiResponse(code = 404, message = "Not Found"),
	@ApiResponse(code = 500, message = "Internal Server Error") })
	public String reservationComplete(Reservation reservation,@PathParam("reservationid") long reservationid){
		ReservationDAO dao = new ReservationDAO();
		try {
			return dao.reservationComplete(reservation, reservationid);
		} catch (AppException e) {
			throw new WebApplicationException(Status.INTERNAL_SERVER_ERROR);		}
		
	}
	private boolean checkAutoAssign(){
		RestaurantDAO restaurantdao = new RestaurantDAO();
		try {
			return restaurantdao.findAutoAssign();
		} catch (AppException e) {
			throw new WebApplicationException(Status.NOT_FOUND);
		}
		
	}
	
}
