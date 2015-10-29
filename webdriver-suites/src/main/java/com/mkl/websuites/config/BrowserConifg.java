package com.mkl.websuites.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.RUNTIME)
public @interface BrowserConifg {

	public enum BrowserType {
		
		INTERNET_EXPLORER,
		CHROME,
		FIREFOX,
		SAFARI,
		OPERA,
		HTML,
		NONE
	}

	BrowserType browserType();
	
	String id();
	
	String displayName();
	
	String webDriverPath() default "";
}
