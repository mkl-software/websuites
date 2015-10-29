package com.mkl.websuites;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.BrowserConifg.BrowserType;



@Retention(RetentionPolicy.RUNTIME)
public @interface BrowsersDefinition {

	BrowserConifg[] browsers() default {
		
		@BrowserConifg(
			browserType = BrowserType.FIREFOX,
			id = "ff",
			displayName = "Firefox",
			webDriverPath = ""
		),
		
		@BrowserConifg(
			browserType = BrowserType.NONE,
			id = "none",
			displayName = "None",
			webDriverPath = ""
		),
		
		@BrowserConifg(
			browserType = BrowserType.HTML,
			id = "html",
			displayName = "HTML Unit",
			webDriverPath = ""
		)
	};
}
