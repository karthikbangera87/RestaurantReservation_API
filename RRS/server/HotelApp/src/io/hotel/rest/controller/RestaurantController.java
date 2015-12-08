package io.hotel.rest.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import io.hotel.dao.RestaurantDAO;
import io.hotel.entities.Restaurant;
import io.hotel.exception.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Path("/restaurant")
@Api(tags = {"restaurant"})
public class RestaurantController {
	
	/* Assuming we store these in Session and get these values */
	@GET
	@Path("/{email}/{token}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "(Admin) Get the restaurant profile ", notes = "Gets the details of the restaurant and different settings set for the restaurant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
	@ApiResponse (code = 400, message = "Bad Request"),
	@ApiResponse(code = 404, message = "Not Found"),
	@ApiResponse(code = 500, message = "Internal Server Error") })
	public Restaurant restaurantDetails(@PathParam("email") String email,@PathParam("token") String token)
	{	
		if (AuthController.isSessionValid(email,token)){
			RestaurantDAO restdao = new RestaurantDAO();
			try {
				return restdao.restaurantDetails();
			} catch (AppException e) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
		}
		else {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
		
	}
	
	@PUT
	@Path("/{restaurantid}/{email}/{token}")
	@Consumes(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "(Admin) Edit the restaurant profile", notes = "Edit details of the restaurant and different settings set for the restaurant")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
	@ApiResponse (code = 400, message = "Bad Request"),
	@ApiResponse(code = 404, message = "Not Found"),
	@ApiResponse(code = 500, message = "Internal Server Error") })
	public String updateDetails(Restaurant restaurantobj, @PathParam("restaurantid") long restaurantid, @PathParam("email") String email,@PathParam("token") String token)
	{	
		if (AuthController.isSessionValid(email,token)){
			RestaurantDAO restdao = new RestaurantDAO();
			try {
				return restdao.updateDetails(restaurantobj, restaurantid);
			} catch (AppException e) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
		}
		else {
			throw new WebApplicationException(Status.UNAUTHORIZED);
		}
		
	}
	

}
