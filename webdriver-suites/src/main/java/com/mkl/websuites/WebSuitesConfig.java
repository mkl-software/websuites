package com.mkl.websuites;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.ext.Customization;



@Retention(RetentionPolicy.RUNTIME)
public @interface WebSuitesConfig {
	
	
	@Customization
	public static class EmptyServiceOverrideDefinition {}


	@BrowsersConfiguration
	public static class DefaultBrowserConfig {}

	String host();

	int port() default 80;

	String basePath() default "/";

	String[] browsers();
	
	Class<?> browsersConfiguration() default DefaultBrowserConfig.class;

	int waitTimeout() default 30;
	
	String[] scenarioFileExtensions() default {"scn"};

	Class<?> serviceOverrides() default EmptyServiceOverrideDefinition.class;

}
