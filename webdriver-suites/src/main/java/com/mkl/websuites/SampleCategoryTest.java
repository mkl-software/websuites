package com.mkl.websuites;

public class SampleCategoryTest extends OneScenarioTest {

	@Override
	protected String getScenarioFileLocation() {
		
		return "ui-test/scenarios/search/search1.scn" + (int) (Math.random() * 100 % 30);
	}

}
