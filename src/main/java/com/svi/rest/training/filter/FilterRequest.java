package com.svi.rest.training.filter;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

@RequestFilter
@Provider
@Priority(Priorities.AUTHENTICATION)
public class FilterRequest implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
//		String bearer = requestContext.getHeaderString("Authorization");
		
		//		Parsing JWT token to authenticate Users
//		System.out.println("Modifying Request Filter");
		
//		if(bearer == null) {
//			requestContext.abortWith(Response.status(403).entity("Forbidden Invalid Token").build());
//		}
	}
}