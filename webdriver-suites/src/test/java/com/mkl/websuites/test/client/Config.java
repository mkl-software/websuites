package com.mkl.websuites.test.client;

import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.test.BrowsersConfig;



@WebSuitesConfig(
		
	host = "localhost",
	port = 8080,
	basePath = "/",
	browsers = {"chrome", "ff"},
	waitTimeout = 20,
	browsersConfiguration = BrowsersConfig.class,
	doNotCloseBrowserAtTheEnd = false
//	serviceOverrides = LocalExtensions.class
	
)
public class Config {}
