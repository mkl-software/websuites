package com.mkl.websuites.client;

import com.mkl.websuites.OneFileScenarioTest;

public class SampleCategoryTest extends OneFileScenarioTest {

	public SampleCategoryTest(String browserId) {
		super(browserId);
	}

	@Override
	protected String getScenarioFileLocation() {
		
		return "ui-test/scenarios/search/search1.scn";
	}

}
