package com.mkl.websuites.test.client;

import com.mkl.websuites.WebSuitesConfig;



@WebSuitesConfig(
		
	host = "localhost",
	port = 8080,
	basePath = "/",
	browsers = {"ff"},
	waitTimeout = 10
	
)
public class Config {}
