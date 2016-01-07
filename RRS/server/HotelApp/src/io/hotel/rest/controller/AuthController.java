package io.hotel.rest.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import io.hotel.dao.AdministratorDAO;

import io.hotel.entities.Administrator;
import io.hotel.exception.AppException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Path("/authentication")
@Api(tags = {"auth"})
public class AuthController {

		@POST
		@Path("/login")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@ApiOperation(value = "(Admin) Admin login ", notes = "Checks for the login credential of the admin")
		@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
		@ApiResponse (code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error") })
		public Administrator login(Administrator logindetails)
		{	
			Administrator authdetails = null;
			AdministratorDAO Authdao = new AdministratorDAO();
			try {
				authdetails = Authdao.authenticate(logindetails);
			} catch (AppException e) {
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
			
			if (authdetails.getToken() == null){
				throw new WebApplicationException(Status.UNAUTHORIZED);
			}
			return authdetails;
		}
		
		@DELETE
		@Path("/logout")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.APPLICATION_JSON)
		@ApiOperation(value = "(Admin) Admin logout ", notes = "Enables logout mechanism for the admin")
		@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
		@ApiResponse (code = 400, message = "Bad Request"),
		@ApiResponse(code = 404, message = "Not Found"),
		@ApiResponse(code = 500, message = "Internal Server Error") })
		public Administrator logout(Administrator logindetails)
		{	
			Administrator authdetails = null;
			AdministratorDAO Authdao = new AdministratorDAO();
			authdetails = Authdao.logout(logindetails);
			return authdetails;
		}
		
		
		
		
		public static boolean isSessionValid(String email,String token){
			
			boolean status = false;
			String sessiontoken = AdministratorDAO.sessionmap.get(email);
			if (sessiontoken!= null && sessiontoken.equals(token)){
				status = true;
			}
			return status;
		}
	
}

	
