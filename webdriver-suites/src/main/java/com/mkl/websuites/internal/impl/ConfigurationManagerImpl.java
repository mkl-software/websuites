package com.mkl.websuites.internal.impl;

import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.internal.ConfigurationManager;

public class ConfigurationManagerImpl implements ConfigurationManager {

	private WebSuitesConfig configuration;


	
	private ConfigurationManagerImpl() {}
	
	private static ConfigurationManager instance = new ConfigurationManagerImpl();
	
	public static ConfigurationManager getInstance() {
		return instance;
	}

	
	
	

	@Override
	public WebSuitesConfig getConfiguration() {
		return configuration;
	}

	@Override
	public void setConfiguration(WebSuitesConfig configuration) {
		this.configuration = configuration;
	}
	
}
