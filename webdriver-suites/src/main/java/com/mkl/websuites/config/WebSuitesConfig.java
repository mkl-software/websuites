package com.mkl.websuites.config;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;


/**
 * Main configuration storage.
 * Using this class you can access system configuration
 * @author Marcin Klosinski
 *
 */
@Slf4j
public class WebSuitesConfig {

	private static WebSuites config;

	public static void initializeWebsuitesConfig(Class<? extends WebSuitesRunner> runningClass) {
		config = runningClass.getAnnotation(WebSuites.class);
		log.debug("config class: " + config);
		
	}
	
	public static WebSuites get() {
		return config;
	}
}
