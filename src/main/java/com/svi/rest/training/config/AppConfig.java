package com.svi.rest.training.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public enum AppConfig {
	DELIMITTER("DELIMITTER"),
	IP_ADDRESS("IP_ADDRESS");
	private String value ="";
	private static Properties prop;
	
	private AppConfig(String value) {
		this.value = value;
	}
	
	public String value() {
		return prop.getProperty(value).trim();
	}
	
	public static void initializeConfig(InputStream is) {
		synchronized(is) {
			if(prop == null) {
				
				try {
					prop = new Properties();
					prop.load(is);
					
					is.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
}