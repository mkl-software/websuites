package com.mkl.websuites.test.client;

import com.mkl.websuites.WebSuitesConfig;



@WebSuitesConfig(
		
	host = "localhost",
	port = 8080,
	basePath = "/",
	browsers = {"ie", "chrome", "safari"},
	waitTimeout = 10
)
public class Config {}
