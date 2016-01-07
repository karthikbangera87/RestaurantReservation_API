package io.hotel.rest.controller;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import io.hotel.dao.ReservationDAO;
import io.hotel.dao.RestaurantTableDAO;
import io.hotel.entities.Reservation;
import io.hotel.entities.RestaurantTable;
import io.hotel.exception.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
@Path("/admin")
@Api(tags = {"admin"})
public class AdminReservationController {

	@GET
	@Path("/findall/{email}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "(Admin) Find all the reservations", notes = "Admin makes this request to get all reservation details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
	@ApiResponse (code = 400, message = "Bad Request"),
	@ApiResponse(code = 404, message = "Not Found"),
	@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<Reservation> findAllReservations(@PathParam("email") String email,@PathParam("token") String token) 
	{	
		if (AuthController.isSessionValid(email,token)){
			ReservationDAO dao = new ReservationDAO();
			try {
				return dao.findAllReservations();
			} catch (AppException e) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
		}
		else
		{
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
	}
	
	@Path("/editReservation/{reservationid}/{email}/{token}")
	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "(Admin) Edit reservations", notes = "Admin can make edits to reservation details, like change table etc")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
	@ApiResponse (code = 400, message = "Bad Request"),
	@ApiResponse(code = 404, message = "Not Found"),
	@ApiResponse(code = 500, message = "Internal Server Error") })
	public String editReservation(Reservation reservation,@PathParam("reservationid") long reservationid,@PathParam("email") String email,@PathParam("token") String token)  {
		if (AuthController.isSessionValid(email,token)){
			ReservationDAO dao = new ReservationDAO();
			try {
				return dao.editReservation(reservation,reservationid);
			} catch (AppException e) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
		}
		else
		{
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
	}
	
	@GET
	@Path("/findAvailableTables/{email}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "(Admin) Find all available tables", notes = "Admin makes request to get all available table details")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
	@ApiResponse (code = 400, message = "Bad Request"),
	@ApiResponse(code = 404, message = "Not Found"),
	@ApiResponse(code = 500, message = "Internal Server Error") })
	public List<RestaurantTable> findAvailableTables(@PathParam("email") String email,@PathParam("token") String token)
	{	
		if (AuthController.isSessionValid(email,token)){
			RestaurantTableDAO dao = new RestaurantTableDAO();
			try {
				return dao.findAvailableTables();
			}
			catch (AppException e) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
		}
		else {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
	}

	
}
