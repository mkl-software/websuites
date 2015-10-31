package com.mkl.websuites.internal.tests;

import static com.mkl.websuites.itests.web.core.WebSuitesResultCheck.BASE_RUN_COUNT_FOR_NONE_BROWSER_TEST;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import mockit.Deencapsulation;
import mockit.Invocation;
import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.config.Folder;
import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;
import com.mkl.websuites.internal.tests.ScenarioFolderTest;
import com.mkl.websuites.internal.tests.SortingStrategy;
import com.mkl.websuites.itests.web.core.CommandInvocationVerifier;
import com.mkl.websuites.itests.web.core.TestUtils;

public class ScenarioFolderTestTest {

	private static final String FOLDERS_BASE = "src/test/resources/integration/non-web/folderedScenarios/";
	
	private final CommandInvocationVerifier commandVerifier = CommandInvocationVerifier.getInstance();

	
	
	@WebSuites(
		folders = @Folder(path = ""), // will be set dynamically
		browsers = "none"
	)
	public static class Runner extends WebSuitesRunner {}
	
	
	@Test
	public void shouldRunTopLevelScenariosNoSubfolders() throws Throwable {
		
		//given
		overrideFolderAnnotation(FOLDERS_BASE + "1", true);
		
		commandVerifier.clearVerificationQueue();
		commandVerifier.expectInvocations("top1.scn", "top2.scn", "top3.scn", "top4.scn");
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		//then
		TestUtils.checkCorrectResultRunsCount(result, BASE_RUN_COUNT_FOR_NONE_BROWSER_TEST + 4);
		commandVerifier.checkRemaining();
	}
	
	
	@Test
	public void shouldRunSimpleFolders() throws Throwable {
		
		//given
		overrideFolderAnnotation(FOLDERS_BASE + "2", false);
		
		commandVerifier.clearVerificationQueue();
		commandVerifier.expectInvocations("top1.scn", "top2.scn");
		commandVerifier.expectInvocations("sub1/1.scn", "sub1/2.scn", "sub1/3.scn");
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		//then
		TestUtils.checkCorrectResultRunsCount(result, BASE_RUN_COUNT_FOR_NONE_BROWSER_TEST + 5);
		commandVerifier.checkRemaining();
	}
	
	
	
	
	@Test
	public void shouldRunSimpleFoldersWithIgnoreSubfolders() throws Throwable {
		
		//given (same as above but with ignore subfolders)
		overrideFolderAnnotation(FOLDERS_BASE + "2", true);
		
		commandVerifier.clearVerificationQueue();
		commandVerifier.expectInvocations("top1.scn", "top2.scn");
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		//then
		TestUtils.checkIfNoFailures(result);
		commandVerifier.checkRemaining();
		assertThat(result.getRunCount()).isEqualTo(BASE_RUN_COUNT_FOR_NONE_BROWSER_TEST + 2);
	}
	
	
	
	@Test
	public void shouldRunDeepSubfolderOneScn() throws Throwable {
		
		//given
		overrideFolderAnnotation(FOLDERS_BASE + "3", false);
		
		commandVerifier.clearVerificationQueue();
		commandVerifier.expectInvocations("deep one");
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		//then
		TestUtils.checkCorrectResultRunsCount(result, BASE_RUN_COUNT_FOR_NONE_BROWSER_TEST + 1);
		commandVerifier.checkRemaining();
	}
	
	
	
	
	
	
	@Test
	public void shouldRunOneLevelFolders() throws Throwable {
		
		//given
		overrideFolderAnnotation(FOLDERS_BASE + "4", false);
		
		commandVerifier.clearVerificationQueue();
		commandVerifier.expectInvocations("1.scn", "2.scn", "3.scn");
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		//then
		TestUtils.checkCorrectResultRunsCount(result, BASE_RUN_COUNT_FOR_NONE_BROWSER_TEST + 3);
		commandVerifier.checkRemaining();
	}
	
	
	
	
	@Test
	public void shouldRunOneLevelFoldersWithIgnoreSubfolders() throws Throwable {
		
		//given
		overrideFolderAnnotation(FOLDERS_BASE + "4", true);
		commandVerifier.clearVerificationQueue();
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		//then
		TestUtils.checkCorrectResultRunsCount(result, BASE_RUN_COUNT_FOR_NONE_BROWSER_TEST);
		commandVerifier.checkRemaining();
	}
	
	
	
	
	
	private void overrideFolderAnnotation(final String folderPath, final boolean ignoreSubFolders)
			throws Throwable {
		
		new MockUp<ScenarioFolderTest>() {
			
			@Mock
			List<junit.framework.Test> defineTests(Invocation invocation) {
				
				Deencapsulation.setField(invocation.getInvokedInstance(),
						"genericParams", new Object[] {folderPath, ignoreSubFolders, SortingStrategy.APLHABETICAL});
				
				return invocation.proceed();
			}
		};
	}
}
