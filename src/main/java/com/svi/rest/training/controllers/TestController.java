package com.svi.rest.training.controllers;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import javax.swing.filechooser.FileSystemView;
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
		FileSystemView filesys = FileSystemView.getFileSystemView();
        String homeDir = filesys.getHomeDirectory().toString();
        String recordsDir = homeDir + "\\records\\";
        
        File recordsFolder = new File(recordsDir);
        
        if(!recordsFolder.exists()) {
        	recordsFolder.mkdirs();
        }
        
        String playersDir = recordsDir + "\\players\\";
        String gamesDir = recordsDir + "\\games\\";
        
        File playersFolder = new File(playersDir);
        File gamesFolder = new File(gamesDir);
        
        if(!playersFolder.exists()) {
        	playersFolder.mkdirs();
        }
        
        if(!gamesFolder.exists()) {
        	gamesFolder.mkdirs();
        }
        
        SimpleDateFormat DTS = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String presentTime = DTS.format(timestamp);
        
		try {
			File gameFile = new File(gamesDir + body.getGameid() + ".txt");
			File playerFile = new File(playersDir + body.getPlayerid() + ".txt");
			if (playerFile.exists()){
				System.out.println("File already exists.");
				
				FileWriter fw = new FileWriter(playerFile, true);
				String str = String.format(body.getGameid() + ",");
				fw.write(str);
				fw.close();
			}else {
				playerFile.createNewFile();
				FileWriter fw = new FileWriter(playerFile, true);
				String str = String.format(body.getGameid() + ",");
				fw.write(str);
				fw.close();
			}
			
			if (gameFile.exists()) {
				System.out.println("File already exists.");
				
				FileWriter fw = new FileWriter(gameFile, true);
				String str = String.format(",%s,%s,%s,%d,%s", body.getGameid(), body.getPlayerid(), body.getSymbol(), body.getLocation(), body.getDatesave());
				fw.write(str);
				fw.close();
				
			} else {
				gameFile.createNewFile();
				System.out.println("File created: " + gameFile.getName());
				String str = String.format("%s,%s,%s,%d,%s,", body.getGameid(), body.getPlayerid(), body.getSymbol(), body.getLocation(), presentTime);
				
				FileWriter fw = new FileWriter(gameFile);
				fw.write(str);
				fw.close();
			}
			
//			throw new IOException();
			return Response.ok("Record Saved").build();
			
		} catch (IOException e) {
			e.printStackTrace();
			return Response.status(500, "The server ran into an unexpected exception").build();
		}
	}
	
	@GET
	@Path("/listgames/")
	public Response getPlayerRecords(@QueryParam("playerid") String playerid) {
		return Response.ok("Records Found!").build();
	}
}