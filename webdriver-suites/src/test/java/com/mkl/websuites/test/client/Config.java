package com.mkl.websuites.test.client;

import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.test.BrowsersConfig;



@WebSuitesConfig(
		
	host = "localhost",
	port = 8080,
	basePath = "/",
	browsers = {"ie", "chrome"},
	waitTimeout = 10,
	browsersConfiguration = BrowsersConfig.class
	
)
public class Config {}
