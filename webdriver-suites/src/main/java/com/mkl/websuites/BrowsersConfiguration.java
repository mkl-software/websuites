package com.mkl.websuites;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.Browser.BrowserType;



@Retention(RetentionPolicy.RUNTIME)
public @interface BrowsersConfiguration {

	Browser[] browsers() default {
		
		@Browser(
			browserType = BrowserType.FIREFOX,
			localId = "ff",
			displayName = "Firefox",
			webDriverPath = ""
	)};
}
