package com.mkl.websuites.internal.impl;

import lombok.Getter;
import lombok.Setter;

import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.internal.ConfigurationManager;



public class ConfigurationManagerImpl implements ConfigurationManager {

	
	private @Getter @Setter WebSuitesConfig configuration;

	
	private ConfigurationManagerImpl() {}
	
	private static ConfigurationManager instance = new ConfigurationManagerImpl();
	
	public static ConfigurationManager getInstance() {
		return instance;
	}


	
}
