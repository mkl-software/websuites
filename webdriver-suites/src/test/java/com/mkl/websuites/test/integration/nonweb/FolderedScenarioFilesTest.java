package com.mkl.websuites.test.integration.nonweb;

import static org.assertj.core.api.Assertions.assertThat;

import java.lang.annotation.Annotation;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;
import com.mkl.websuites.test.core.TestUtils;
import com.mkl.websuites.test.unit.scenario.CommandInvocationVerifier;
import com.mkl.websuites.test.unit.scenario.flows.RepeatDetailedIntegrationTest.ScenarioFile;
import com.mkl.websuites.tests.Folders;
import com.mkl.websuites.tests.ScenarioFolderTest;
import com.mkl.websuites.tests.Scenarios;
import com.mkl.websuites.tests.SortingStrategy;

public class FolderedScenarioFilesTest {

	private static final String FOLDERS_BASE = "src/test/resources/integration/non-web/folderedScenarios/";
	
	private final CommandInvocationVerifier commandVerifier = CommandInvocationVerifier.getInstance();


	@Folders(path = "src/test/resources/integration/non-web/folderedScenarios/2", ignoreSubfolders = false)
	public static class FolderTest extends ScenarioFolderTest {}
	
	@WebSuitesConfig(browsers = "none")
	public static class LocalConfig {}
	
	@WebSuitesRunner(suite = FolderTest.class, configurationClass = LocalConfig.class)
	public static class Runner extends WebSuites {}
	
	
	@Test
	public void shouldRunTopLevelScenariosNoSubfolders() throws Throwable {
		
		//given
		overrideFolderAnnotation(FOLDERS_BASE + "1", true);
		
		commandVerifier.clearVerificationQueue();
		commandVerifier.expectInvocations("top1.scn", "top2.scn", "top3.scn", "top4.scn");
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		//then
		TestUtils.checkCorrectResultRunsCount(result, 4);
		commandVerifier.checkRemaining();
	}
	
	
	@Test
	public void shouldRunTopLevelScenariosSimpleSubfolders() throws Throwable {
		
		//given
		overrideFolderAnnotation(FOLDERS_BASE + "2", false);
		
		commandVerifier.clearVerificationQueue();
		commandVerifier.expectInvocations("top1.scn", "top2.scn");
		commandVerifier.expectInvocations("sub1/1.scn", "sub1/2.scn", "sub1/3.scn");
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		//then
		TestUtils.checkCorrectResultRunsCount(result, 5);
		commandVerifier.checkRemaining();
	}
	
	
	@Test
	public void shouldRunSubfolderedScenariosWithIgnoreSubfolders() throws Throwable {
		
		//given (same as above but with ignore subfolders)
		overrideFolderAnnotation(FOLDERS_BASE + "2", true);
		
		commandVerifier.clearVerificationQueue();
		commandVerifier.expectInvocations("top1.scn", "top2.scn");
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		//then
		TestUtils.checkIfNoFailures(result);
		commandVerifier.checkRemaining();
		assertThat(result.getRunCount()).isEqualTo(2);
	}
	
	
	
	@Test
	public void shouldRunTopLevelScenariosDeepSubfolderOneScn() throws Throwable {
		
		//given
		overrideFolderAnnotation(FOLDERS_BASE + "3", false);
		
		commandVerifier.clearVerificationQueue();
		commandVerifier.expectInvocations("deep one");
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		//then
		TestUtils.checkIfNoFailures(result);
		commandVerifier.checkRemaining();
		assertThat(result.getRunCount()).isEqualTo(1);
	}
	
	
	
	
	
	private void overrideFolderAnnotation(final String folderPath, final boolean ignoreSubFolders)
			throws Throwable {
		
		Annotation newValue = new Folders() {
			
			
			@Override
			public String[] path() {
				return new String[] {folderPath};
			}

			@Override
			public Class<? extends Annotation> annotationType() {
				return ((Scenarios) ScenarioFile.class.getAnnotation(Scenarios.class)).annotationType();
			}

			@Override
			public boolean ignoreSubfolders() {
				return ignoreSubFolders;
			}

			@Override
			public SortingStrategy sortingStrategy() {
				return SortingStrategy.APLHABETICAL;
			}
		};
		
		TestUtils.overrideScenarioFileNameAnnotation(FolderTest.class, Folders.class, newValue);
	}
}
