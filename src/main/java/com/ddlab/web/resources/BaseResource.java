package com.ddlab.web.resources;

import java.nio.file.AccessDeniedException;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class BaseResource {

	protected WebApplicationException createWebappException(Exception incomingException) {

		Status status;
		String string = null;
		if (incomingException instanceof SecurityException || incomingException instanceof AccessDeniedException) {
			status = Status.FORBIDDEN;
		} else if (incomingException instanceof IllegalArgumentException) {
			status = Status.BAD_REQUEST;
			string=incomingException.getMessage();
		} else if (incomingException instanceof NotFoundException) {
			status = Status.NOT_FOUND;
			string=incomingException.getMessage();
		} else if (incomingException instanceof Exception) {
			status = Status.INTERNAL_SERVER_ERROR;
		} else {
			status = Status.INTERNAL_SERVER_ERROR;
		}
		return new WebApplicationException(Response.status(status).entity(string).type("text/plain").build());
	}

}
