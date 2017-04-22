package com.ddlab.web.resources;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.ddlab.entity.User;

@Path("itcparams")
public class ITCParamServices extends BaseResource {
	
	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;
	
	//GET http://localhost:8090/springjersey/api/itcparams
	//Status Code : 200 OK
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getOrganisationName() {
		
		return Response.ok().entity("ITC Infotech, Bangalore, Karnataka").build();
	}
	
	// GET http://localhost:8090/springjersey/api/itcparams/address
	//Status Code : 200 OK
	@Path("/address")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getAddress() {
		String addressString = "ITC Infotech India Limited, 18, Banaswadi Main Rd, Maruthi Sevanagar, Bangalore, 560005";
		return Response.ok().entity(addressString).build();
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~ @PathParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	//GET http://localhost:8090/springjersey/api/itcparams/user/1
	//Status Code : 200 OK
	//Response : {"firstName":"Deb","lastName":"Mishra","id":1}
	
	//In case of id = 0
	
	//GET http://localhost:8090/springjersey/api/itcparams/user/0
	//Status Code : 400 Bad Request
	//Response body : Id cannot be 0, enter a valid id
	@Path("/user/{id}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getUserById(@PathParam("id") int id) {
		
		if( id == 0 ) throw createWebappException(new IllegalArgumentException("Id cannot be 0, enter a valid id"));
		User user = new User();
		user.setFirstName("Deb");
		user.setLastName("Mishra");
		user.setId(id);
		return Response.ok().entity(user).build();
	}
	
	//GET http://localhost:8090/springjersey/api/itcparams/address/USA
	//GET http://localhost:8090/springjersey/api/itcparams/address/europe
	//Status Code : 200 OK
	@Path("/address/{areaCode}") //USA,EUROPE
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getAddressByCode(@PathParam("areaCode") String areaCode) {
		
		String addressString = null;
		try {
			if (areaCode.equalsIgnoreCase("USA"))
				addressString = "12 North State, Route 17,Suite 303,Paramus,New Jersey,NJ-07652";
			else if (areaCode.equalsIgnoreCase("Europe"))
				addressString =  "Newell Consulting Oy,P.O. Box 16 , Olari,02211 Espoo, Helsinki";
			else if (areaCode.equalsIgnoreCase("Africa"))
				addressString =  "Johannesburg,2nd Floor, West Tower,Nelson Mandela Square,Maude Street, Sandton,Johannesburg, 2196";
			else if (areaCode.equalsIgnoreCase("Asia"))
				addressString =  "ITC Infotech India Limited, 18, Banaswadi Main Rd, Maruthi Sevanagar, Bangalore, 560005";
			else {
				addressString =  "No such area code exists for ITC";
			}
				
		} catch (Exception e) {
			throw createWebappException( new IllegalArgumentException("No such area code exists for ITC"));
		}
		return Response.ok().entity(addressString).build();
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~ @QueryParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	//GET http://localhost:8090/springjersey/api/itcparams/regionaladdress/Europe?country=SE
	//Status Code : 200 OK
	@Path("/regionaladdress/{areaCode}") //USA,EUROPE
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getAddressByCountry(@PathParam("areaCode") String areaCode, @QueryParam("country") String country) {
		
		String adrsByCountry = null;
		try {
			if (areaCode.equalsIgnoreCase("USA")
					&& country.equalsIgnoreCase("NJ"))
				adrsByCountry = "12 North State, Route 17,Suite 303,Paramus,New Jersey,NJ-07652";
			else if (areaCode.equalsIgnoreCase("Europe")
					&& country.equalsIgnoreCase("FI"))
				adrsByCountry = "Newell Consulting Oy,P.O. Box 16 , Olari,02211 Espoo, Helsinki";
			else if (areaCode.equalsIgnoreCase("Europe")
					&& country.equalsIgnoreCase("SE"))
				adrsByCountry = "C/o Matrisen AB,Box 22059 , 104 22 Stockholm";
			else if (areaCode.equalsIgnoreCase("Europe")
					&& country.equalsIgnoreCase("DK"))
				adrsByCountry = "Havnegade 39, 3. sal,1058 Copenhagen K";
			else if (areaCode.equalsIgnoreCase("Asia")
					&& country.equalsIgnoreCase("IN"))
				adrsByCountry = "ITC Infotech India Limited, 18, Banaswadi Main Rd, Maruthi Sevanagar, Bangalore, 560005";
			else
				adrsByCountry = "No such area code exists for ITC";
		} catch (Exception e) {
			throw createWebappException( new IllegalArgumentException("No such area code exists for ITC"));
		}
		return Response.ok().entity(adrsByCountry).build();
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~ @MatrixParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	//GET http://localhost:8090/springjersey/api/itcparams/itcaddress;country=FI;areacode=europe
	//Status Code : 200 OK
	@Path("/itcaddress")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public Response getITCAddress(@MatrixParam("country") String country, @MatrixParam("areacode") String areaCode) {
		return getAddressByCountry(areaCode, country);
	}
	
	// ~~~~~~~~~~~~~~~~~~~~~~~ @FormParam ~~~~~~~~~~~~~~~~~~~~~~~~~~~~
	
	//POST http://localhost:8090/springjersey/api/itcparams/postaladdress
	//Content-Type : application/x-www-form-urlencoded
	//Body : country=FI&areacode=Europe
	//Status Code : 200 OK
	@Path("/postaladdress")
	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public Response postNGetITCAddress(@FormParam("country") String country,@FormParam("areacode") String areaCode) {
		
		return getAddressByCountry(areaCode, country);
	}

}
