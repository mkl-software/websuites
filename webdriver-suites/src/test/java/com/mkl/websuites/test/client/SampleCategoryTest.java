package com.mkl.websuites.test.client;

import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.tests.OneFileScenarioTest;

public class SampleCategoryTest extends OneFileScenarioTest {


	public SampleCategoryTest(String browserId, WebSuitesConfig config) {
		super(browserId, config);
	}

	@Override
	protected String getScenarioFileLocation() {
		
		return "ui-test/scenarios/search/search1.scn";
	}

}
