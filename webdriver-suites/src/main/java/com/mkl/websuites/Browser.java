package com.mkl.websuites;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;



@Retention(RetentionPolicy.RUNTIME)
public @interface Browser {

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
	
	String localId();
	
	String displayName();
	
	String webDriverPath() default "";
}
