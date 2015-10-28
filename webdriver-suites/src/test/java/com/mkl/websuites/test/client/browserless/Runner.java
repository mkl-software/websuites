package com.mkl.websuites.test.client.browserless;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.WebSuites;




@WebSuites(
		configurationClass = NoBrowserConfig.class,
		suite = {LocalScenarioTest.class})

public class Runner extends WebSuitesRunner {}
