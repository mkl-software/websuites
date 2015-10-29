package com.mkl.websuites.test.client;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.Extension;
import com.mkl.websuites.config.SiteConfig;
import com.mkl.websuites.config.TestClass;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.services.ServiceDefinition.Service;
import com.mkl.websuites.test.BrowsersConfig;
import com.mkl.websuites.test.client.ext.MyBrowserController;




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
	),
	extension = @Extension(serviceOverrides = @Service(
			service = BrowserController.class,
			implementation = MyBrowserController.class
			)
	)
)
public class Runner extends WebSuitesRunner {}
