package com.mkl.websuites.test.client;

import com.mkl.websuites.WebSuitesConfig_rename;
import com.mkl.websuites.test.BrowsersConfig;



@WebSuitesConfig_rename(
		
	host = "localhost",
	port = 8080,
	basePath = "/",
	browsers = {"chrome"},
	waitTimeout = 5,
	browsersConfiguration = BrowsersConfig.class,
	doNotCloseBrowserAtTheEnd = false
//	serviceOverrides = LocalExtensions.class
	
)
public class Config {}
