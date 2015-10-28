package com.mkl.websuites.internal;

import lombok.Getter;
import lombok.Setter;

import com.mkl.websuites.WebSuitesConfig_rename;



public class StandardConfigurationManager implements ConfigurationManager {

	
	private @Getter @Setter WebSuitesConfig_rename configuration;

	
	private StandardConfigurationManager() {}
	
	private static ConfigurationManager instance = new StandardConfigurationManager();
	
	public static ConfigurationManager getInstance() {
		return instance;
	}


	
}
