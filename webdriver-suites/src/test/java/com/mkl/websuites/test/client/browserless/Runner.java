package com.mkl.websuites.test.client.browserless;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;




@WebSuitesRunner(
		configurationClass = NoBrowserConfig.class,
		suite = {LocalScenarioTest.class})

public class Runner extends WebSuites {}
