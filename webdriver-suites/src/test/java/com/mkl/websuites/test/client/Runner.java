package com.mkl.websuites.test.client;

import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.WebSuites;




@WebSuites(
		configurationClass = Config.class,
		suite = {SampleSingleScenarioTest.class})

public class Runner extends WebSuitesRunner {}
