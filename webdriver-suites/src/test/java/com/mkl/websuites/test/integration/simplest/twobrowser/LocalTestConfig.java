package com.mkl.websuites.test.integration.simplest.twobrowser;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.WebSuitesRunner;

public class LocalTestConfig {

	@WebSuitesConfig(
			basePath = "/integration/simple/titleOnly.html",
			browsers = {"ff", "ff"},
			host = "http://localhost",
			port = 90
			
	)
	public static class Config {}

	
	
	@WebSuitesRunner(
			configurationClass = Config.class,
			suite = {TwoBrowserTitleOnlyTest.class}
	)
	public static class Runner extends WebSuites {}
	
}
