package com.mkl.websuites.test.client;

import com.mkl.websuites.AnnotatedSuite;
import com.mkl.websuites.SuiteClasses;
import com.mkl.websuites.WebSuitesConfig;



@SuiteClasses(tests = {SampleStandaloneTest.class})
public class MySuite extends AnnotatedSuite {

	public MySuite(String browserId, WebSuitesConfig config) {
		super(browserId, config);
	}

}
