package com.svi.rest.training.init;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import com.svi.rest.training.config.AppConfig;

@WebListener
public class ContextInitializer implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {

	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext sc = sce.getServletContext();
		
		boolean is_prod = Boolean.parseBoolean(sc.getInitParameter("IS_PROD"));
		
		if(is_prod) {
//			Initialize production config
		}else {
//			Initialize development config
			try {
//			initialize Config File
				AppConfig.initializeConfig(new FileInputStream(new File(sc.getRealPath(sc.getInitParameter("CONFIG_LOCATION")))));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}
	
}
