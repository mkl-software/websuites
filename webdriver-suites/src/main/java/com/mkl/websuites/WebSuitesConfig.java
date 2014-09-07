package com.mkl.websuites;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.internal.ext.EmptyExtensionDefinition;



@Retention(RetentionPolicy.RUNTIME)
public @interface WebSuitesConfig {

	@BrowsersConfiguration
	public static class DefaultBrowserConfig {}

	String host();

	int port() default 80;

	String basePath();

	String[] browsers();
	
	Class<?> browsersConfiguration() default DefaultBrowserConfig.class;

	int waitTimeout() default 30;
	
	String[] scenarioFileExtensions() default {"scn"};

	Class<?> serviceOverrides() default EmptyExtensionDefinition.class;

}
