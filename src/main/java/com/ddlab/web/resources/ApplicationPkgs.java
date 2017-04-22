package com.ddlab.web.resources;

import java.util.HashSet;
import java.util.Set;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.server.ResourceConfig;

//To know about WADl : GET http://localhost:8090/springjersey/api/application.wadl
public class ApplicationPkgs extends ResourceConfig {
	
	public ApplicationPkgs() {
		
//		super( ITCServices.class,ITCParamServices.class,ITCUploadService.class,ITCDownloadService.class,ITCHeaderParams.class,JacksonFeature.class,MultiPartFeature.class);
		
		// OR you can write below
		
		Set<Class<?>> classes = new HashSet<Class<?>>();
		classes.add(ITCServices.class);
		classes.add(ITCParamServices.class);
		classes.add(ITCUploadService.class);
		classes.add(ITCDownloadService.class);
		classes.add(ITCHeaderParams.class);
		classes.add(JacksonFeature.class);
		classes.add(MultiPartFeature.class);
		
		registerClasses(classes);
	}
}
