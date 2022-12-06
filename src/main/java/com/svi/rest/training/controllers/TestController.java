package com.svi.rest.training.controllers;

import java.io.File;
import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.svi.rest.training.config.AppConfig;
import com.svi.rest.training.dto.ProfileDTO;
import com.svi.rest.training.filter.RequestFilter;

@Path("/tictactoe")
public class TestController {
	@GET
	@Path("/sample-get")
	public Response getRequest(@QueryParam("id") String id) {
		AppConfig.DELIMITTER.value();
		return Response.ok("Hello World").build();
	}
	
	@POST
	@RequestFilter
	@Path("/save")
	public Response postSave(ProfileDTO body) {
//		return Response.ok(body.getPlayerid()).build();
			try {
				File myObj = new File("src\\records\\rokki.txt");
				
				if (!myObj.exists()) {
					myObj.createNewFile();
					System.out.println("File created: " + myObj.getName());
					return Response.ok("Record Saved").build();
				} else {
					System.out.println("File already exists.");
					return Response.status(401, "Record Could not be saved").build();
				}
			    } catch (IOException e) {
			    	e.printStackTrace();
			    	return Response.status(500, "The server ran into an unexpected exception").build();
			    }
	}
	
	@GET
	@Path("/listgames")
	public Response getPlayerRecords(@QueryParam("playerid") String playerid) {
		return Response.ok("Records Found!").build();
	}
}
