package com.mkl.websuites.test.client;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;




@WebSuitesRunner(
		configurationClass = Config.class,
		suite = {SampleCategoryTest.class, MySuite.class})

public class Runner extends WebSuites {}
