package com.mkl.websuites.test.integration.command;

import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.tests.SingleScenarioFileTest;

public class EmptyCommandIntegrationTestConfig {
	
	
	@WebSuitesConfig(browsers = {"ff"})
	public static class LocalConfig {}

	public static class LocalEmptyCommandIntegrationTest extends SingleScenarioFileTest {

		@Override
		protected String getScenarioFileLocation() {
			
			return "src/test/resources/unit/scenarios/running/oneSampleCommand.scn";
		}
		
	}
}
