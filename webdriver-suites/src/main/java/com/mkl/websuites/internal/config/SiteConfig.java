package com.mkl.websuites.internal.config;

import com.mkl.websuites.WebSuitesConfig_rename.DefaultBrowserConfig;

public @interface SiteConfig {

	String host() default "localhost";

	int port() default 8080;

	String basePath() default "/";
	
	Class<?> browsersConfiguration() default DefaultBrowserConfig.class;

	int waitTimeout() default 30;
	
	boolean doNotCloseBrowserAtTheEnd() default false;
}
