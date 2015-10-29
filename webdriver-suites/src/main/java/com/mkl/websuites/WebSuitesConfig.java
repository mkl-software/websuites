package com.mkl.websuites;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.internal.config.WebSuites;


/**
 * Main configuration storage.
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
