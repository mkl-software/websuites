package com.mkl.websuites.client;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.internal.annotation.WebdriverSuites;




@WebdriverSuites(
		configurationClass = Config.class,
		suite = {SampleCategoryTest.class})

public class Runner extends WebSuites {}
