package com.mkl.websuites.test.integration.nonweb;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;
import com.mkl.websuites.test.core.TestUtils;
import com.mkl.websuites.test.unit.scenario.CommandInvocationVerifier;
import com.mkl.websuites.tests.Folders;
import com.mkl.websuites.tests.ScenarioFolderTest;

public class FolderedScenarioFilesTest {

	
//	private static final String SCN_DIR = "src/test/resources/integration/non-web/folderedScenarios/";
	private final CommandInvocationVerifier commandVerifier = CommandInvocationVerifier.getInstance();


	@Folders(path = "src/test/resources/integration/non-web/folderedScenarios/1", ignoreSubfolders = true)
	public static class FolderTest extends ScenarioFolderTest {}
	
	@WebSuitesConfig(browsers = "none")
	public static class LocalConfig {}
	
	@WebSuitesRunner(suite = FolderTest.class, configurationClass = LocalConfig.class)
	public static class Runner extends WebSuites {}
	
	
	@Test
	public void shouldRunTopLevelScenariosNoSubfolders() throws Throwable {
		//given
		commandVerifier.clearVerificationQueue();
		commandVerifier.expectInvocations("top1.scn", "top2.scn", "top3.scn", "top4.scn");
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		//then
		TestUtils.checkIfNoFailures(result);
		commandVerifier.checkRemaining();
		assertThat(result.getRunCount()).isEqualTo(4);
	}
}
