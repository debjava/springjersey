package com.ddlab.web.resources;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.springframework.beans.factory.annotation.Autowired;

import com.ddlab.entity.User;
import com.ddlab.services.IUserService;

@Path("itc")
public class ITCServices extends BaseResource {

	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;
	
	@Autowired
	private IUserService userService;

	/*
	 * POST http://localhost:8090/springjersey/api/itc/createuser
	 * Content-Type : application/json
	 * Body : 
	 * {
  	 *		"firstName" : "Deb",
  	 *		"lastName" : "Mishra",
  	 *		"id" : 1
  	 *	}
  	 * 
  	 * Response Status Code: 201 Created
	 */
	
	@Path("/createuser")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response createEmp(User user) {
		userService.createUser(user);
		return Response.status(Status.CREATED).entity("User " + user.getFirstName()+" created successfully...").build();
	}
	
	/*
	 * PUT http://localhost:8090/springjersey/api/itc/updateuser
	 * Content-Type : application/json
	 * Body : 
	 * {
  	 *		"firstName" : "Deb",
  	 *		"lastName" : "Mishra",
  	 *		"id" : 1
  	 *	}
  	 *
  	 * Response Status Code: 200 OK
  	 * 
  	 * In case of wrong message like id = 0
  	 * PUT http://localhost:8090/springjersey/api/itc/updateuser
  	 * Content-Type : application/json
  	 * Body : 
  	 * {
  	 *  	"firstName" : "Deb",
  	 *  	"lastName" : "Mishra",
  	 *  	"id" : 0
  	 *  }
  	 *  
  	 *  Response 404 Not Found
	 */
	@Path("/updateuser")
	@PUT
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response updateEmp(User user) {
		if( user.getId() == 0 ) 
			throw createWebappException(new NotFoundException("No such employee exists..."));
		else
			userService.updateUser(user);
		return Response.ok("User " + user.getFirstName()+" updated successfully...").build();
	}
	
	/*
	 * DELETE http://localhost:8090/springjersey/api/itc/deleteuser
	 * Content-Type : application/json
	 * Body : 
	 * {
  	 *		"firstName" : "Deb",
  	 *		"lastName" : "Mishra",
  	 *		"id" : 1
  	 *	}
  	 *
  	 * Response Status Code: 200 OK
  	 * 
  	 * In case of wrong message like id = 0
  	 * PUT http://localhost:8090/springjersey/api/itc/deleteuser
  	 * Content-Type : application/json
  	 * Body : 
  	 * {
  	 *  	"firstName" : "Deb",
  	 *  	"lastName" : "Mishra",
  	 *  	"id" : 0
  	 *  }
  	 *  
  	 *  Response 404 Not Found
	 */
	@Path("/deleteuser")
	@DELETE
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response deleteEmp(User user) {
		if( user.getId() == 0 ) 
			throw createWebappException(new NotFoundException("No such employee exists..."));
		else
			userService.deleteUser(user);
		return Response.ok("User " + user.getFirstName()+" deleted successfully...").build();
	}

}
