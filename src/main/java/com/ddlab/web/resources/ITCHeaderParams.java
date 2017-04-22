package com.ddlab.web.resources;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("/headers")
public class ITCHeaderParams {
	
	/*
	 * To know the user-agent header
	 * GET http://localhost:8090/springjersey/api/headers/get
	 * 
	 * Response will be
	 * In FireFox
	 * addUser is called, userAgent : Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0
	 * 
	 * In Chrome
	 * addUser is called, userAgent : Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36
	 */
	@GET
	@Path("/get")
	public Response addUser(@HeaderParam("user-agent") String userAgent) {
		String msg = "addUser is called, userAgent : " + userAgent;
		return Response.status(200).entity(msg).build();
	}
	
	/*
	 * To know the user-agent header
	 * GET http://localhost:8090/springjersey/api/headers/get
	 * 
	 * Response will be
	 * In FireFox
	 * addUser is called, userAgent : Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0
	 * 
	 * In Chrome
	 * addUser is called, userAgent : Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36
	 * 
	 * You can set your custom header like myHeader: myHeaderValue
	 */
	@GET
	@Path("/getAll")
	public Response addUser(@Context HttpHeaders headers) {
		//Show All Headers
		System.out.println("All header details ...\n"+headers.getRequestHeaders());
		//It will display
		//{host=[localhost:8090], connection=[keep-alive], cache-control=[max-age=0], upgrade-insecure-requests=[1], user-agent=[Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36], accept=[text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8], accept-encoding=[gzip, deflate, sdch, br], accept-language=[en-US,en;q=0.8]}
		for(String header : headers.getRequestHeaders().keySet()){
			System.out.println(header);
			//It will display below
			//host
			//connection
			//cache-control
			//upgrade-insecure-requests
			//user-agent
			//accept
			//accept-encoding
			//accept-language
		}
		String userAgent = headers.getRequestHeader("user-agent").get(0);
		String msg = "addUser is called, userAgent : " + userAgent;
		return Response.status(200).entity(msg).build();
	}

}
