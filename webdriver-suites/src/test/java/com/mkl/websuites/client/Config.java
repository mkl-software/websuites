package com.mkl.websuites.client;

import com.mkl.websuites.internal.annotation.WebdriverSuitesConfiguration;



@WebdriverSuitesConfiguration(
		
	host = "localhost",
	port = 8080,
	basePath = "/",
	browsers = {"ie", "chrome", "safari"}
)
public class Config {

}
