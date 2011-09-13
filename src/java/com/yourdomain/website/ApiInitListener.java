package com.yourdomain.website;

import com.renren.api.client.RenrenApiConfig;
import com.yourdomain.website.config.AppConfig;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ApiInitListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		//Nothing to do
	}

	public void contextInitialized(ServletContextEvent arg0) {
		RenrenApiConfig.renrenApiKey = AppConfig.API_KEY;
		RenrenApiConfig.renrenApiSecret = AppConfig.APP_SECRET;
	}
}