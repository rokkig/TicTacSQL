package com.svi.rest.training.dto;

import javax.xml.bind.annotation.XmlElement;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProfileDTO {

	@XmlElement
	String gameid;
	@XmlElement
	String symbol;
	@XmlElement
	Integer location;
	@XmlElement
	private String playerid;
	@XmlElement
	private String datesave;
	
	public String getGameid() {
		return gameid;
	}
	public void setGameid(String gameid) {
		this.gameid = gameid;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Integer getLocation() {
		return location;
	}
	public void setLocation(Integer location) {
		this.location = location;
	}
	public String getPlayerid() {
		return playerid;
	}
	public void setPlayerid(String playerid) {
		this.playerid = playerid;
	}
	public String getDatesave() {
		return datesave;
	}
	public void setDatesave(String datesave) {
		this.datesave = datesave;
	}
	
	@Override
	public String toString() {
		String json = "";
		ObjectMapper mapper = new ObjectMapper();
		try {
			json = mapper.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}
}
