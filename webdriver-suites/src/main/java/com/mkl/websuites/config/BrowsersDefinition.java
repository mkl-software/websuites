package com.mkl.websuites.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import com.mkl.websuites.config.BrowserConifg.BrowserType;


/**
 * Default definitions for browsers that don't need any additional drivers and come OOTB.
 * @author Marcin Klosinski
 *
 */
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
