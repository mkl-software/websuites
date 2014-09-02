package com.mkl.websuites.test.client;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;




@WebSuitesRunner(
		configurationClass = Config.class,
		suite = {MySuite.class, SampleCategoryTest.class})

public class Runner extends WebSuites {}
