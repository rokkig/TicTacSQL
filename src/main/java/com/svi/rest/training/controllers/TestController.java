package com.svi.rest.training.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.svi.rest.training.config.AppConfig;

@Path("/demo")
public class TestController {
	@GET
	@Path("/sample-get")
	public Response getRequest(@QueryParam("id") String id) {
		AppConfig.DELIMITTER.value();
		return Response.ok("Hello World").build();
	}
	
	@POST
	@Path("/sample-post")
	public Response postRequest(String body) {
		
		System.out.println(body);
		
		String delimitted = AppConfig.DELIMITTER.value();
		String ip = AppConfig.IP_ADDRESS.value();
		
		return Response.ok(body + ip).build();
	}
}
