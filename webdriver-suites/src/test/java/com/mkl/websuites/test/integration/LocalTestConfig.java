package com.mkl.websuites.test.integration;

import com.mkl.websuites.AnnotatedSuite;
import com.mkl.websuites.SuiteClasses;
import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.WebSuitesRunner;

public class LocalTestConfig {

	@WebSuitesConfig(
			basePath = "/integration/simple/titleOnly.html",
			browsers = {"ff"},
			host = "http://localhost",
			port = 90
			
		)
	public static class Config {}

	
	
	@SuiteClasses(suite = {OneBrowserTitleOnlyTest.class})
	public static class LocalSuite extends AnnotatedSuite {}
	
	
	@WebSuitesRunner(configurationClass = Config.class, suite = {LocalSuite.class})
	public static class Runner extends WebSuites {}
	
}
