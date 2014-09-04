package com.mkl.websuites.internal;

import com.mkl.websuites.WebSuitesConfig;

public class ConfigurationManager {

	private WebSuitesConfig configuration;

	
	
	private static ConfigurationManager instance = new ConfigurationManager();
	
	public static ConfigurationManager getInstance() {
		return instance;
	}

	
	
	

	public WebSuitesConfig getConfiguration() {
		return configuration;
	}

	public void setConfiguration(WebSuitesConfig configuration) {
		this.configuration = configuration;
	}
	
}
