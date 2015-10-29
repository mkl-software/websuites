package com.mkl.websuites.test.client;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.internal.config.ScenarioFile;
import com.mkl.websuites.internal.config.SiteConfig;
import com.mkl.websuites.internal.config.TestClass;
import com.mkl.websuites.internal.config.WebSuites;
import com.mkl.websuites.test.BrowsersConfig;




@WebSuites(
	browsers = "${env.testBrowser}",
//	scenarios = @ScenarioFile("src/test/resources/client/temp.scn"),
	classes = @TestClass(SampleStandaloneTest.class),
	browserResusableConfiguration = BrowsersConfig.class,
	site = @SiteConfig(
		host = "localhost",
		port = 8080,
		basePath = "/",
		waitTimeout = 5,
		doNotCloseBrowserAtTheEnd = false
	)
)
public class Runner extends WebSuitesRunner {}
