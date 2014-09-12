package com.mkl.websuites.internal;

import lombok.Getter;
import lombok.Setter;

import com.mkl.websuites.WebSuitesConfig;



public class StandardConfigurationManager implements ConfigurationManager {

	
	private @Getter @Setter WebSuitesConfig configuration;

	
	private StandardConfigurationManager() {}
	
	private static ConfigurationManager instance = new StandardConfigurationManager();
	
	public static ConfigurationManager getInstance() {
		return instance;
	}


	
}
