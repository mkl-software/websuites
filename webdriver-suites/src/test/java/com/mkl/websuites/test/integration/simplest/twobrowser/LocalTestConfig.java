package com.mkl.websuites.test.integration.simplest.twobrowser;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.WebSuitesConfig_rename;
import com.mkl.websuites.WebSuites;
import com.mkl.websuites.test.BrowsersConfig;
import com.mkl.websuites.test.core.JettyBasedTest;

public class LocalTestConfig {

	@WebSuitesConfig_rename(
			basePath = "/integration/simple/titleOnly.html",
			browsers = {"ff", "ff"},
			host = "http://localhost",
			port = JettyBasedTest.PORT_NUMER,
			browsersConfiguration = BrowsersConfig.class
			
	)
	public static class Config {}

	
	
	@WebSuites(
			configurationClass = Config.class,
			suite = {TwoBrowserTitleOnlyTest.class}
	)
	public static class Runner extends WebSuitesRunner {}
	
}
