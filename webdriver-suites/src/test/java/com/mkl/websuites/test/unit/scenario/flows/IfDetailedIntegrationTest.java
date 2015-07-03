package com.mkl.websuites.test.unit.scenario.flows;

import java.lang.annotation.Annotation;

import mockit.Mock;
import mockit.MockUp;

import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import com.mkl.websuites.WebSuites;
import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.browser.StandardBrowserController;
import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;
import com.mkl.websuites.test.core.TestUtils;
import com.mkl.websuites.test.unit.scenario.CommandInvocationVerifier;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.Scenarios;

public class IfDetailedIntegrationTest {

	
	private static final String SCN_DIR = "src/test/resources/integration/non-web/if/";
	private final CommandInvocationVerifier commandVerifier = CommandInvocationVerifier.getInstance();


	@Scenarios(SCN_DIR + "04.scn")
	public static class ScenarioFile extends ScenarioFileTest {}
	
	@WebSuitesConfig(browsers = "none")
	public static class LocalConfig {}
	
	@WebSuitesRunner(suite = ScenarioFile.class, configurationClass = LocalConfig.class)
	public static class Runner extends WebSuites {}
	
	
	@Test
	public void shouldRunForSimpleBrowserCheck() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "01.scn");
		mockCurrentBrowser("ff");
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("this is firefox");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		TestUtils.checkCorrectResultRunsCount(result, 1);
		commandVerifier.checkRemaining();
	}
	
	
	
	@Test
	public void shouldRunForComplexBrowserCheck() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "02.scn");
		mockCurrentBrowser("ie");
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("this is ie", "not chrome", "not firefox");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		TestUtils.checkCorrectResultRunsCount(result, 1);
		commandVerifier.checkRemaining();
	}
	
	
	@Test
	public void shouldRunForNestedBrowserCheck() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "03.scn");
		mockCurrentBrowser("ie");
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("this is firefox or ie",
				"this is not firefox and not ie", "not ff, not chrome, not safari");
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		TestUtils.checkCorrectResultRunsCount(result, 1);
		commandVerifier.checkRemaining();
	}
	
	
	
	
	@Test
	public void shouldRunForPropertyCondition() throws Throwable {
		
		//given
		overrideScenarioFileNameAnnotation(SCN_DIR + "04.scn");
		commandVerifier.clearVerificationQueue();
		
		commandVerifier.expectInvocations("correct1", "correct2", "correct3", "correct4");
		
		// and
		WebSuitesUserProperties.get().clear();
		
		//when
		Result result = new JUnitCore().run(new InternalWebSuitesRunner(Runner.class));
		
		//then
		TestUtils.checkCorrectResultRunsCount(result, 1);
		commandVerifier.checkRemaining();
	}

	
	
	

	private void mockCurrentBrowser(final String browser) {
		new MockUp<StandardBrowserController>() {
			@Mock
			String currentBrowser() {
				return browser;
			}
		};
	}
	
		
	private void overrideScenarioFileNameAnnotation(final String scenarioName) throws Throwable {
		
		Annotation newValue = new Scenarios() {
			
			
			@Override
			public String[] value() {
				return new String[] {scenarioName};
			}

			@Override
			public Class<? extends Annotation> annotationType() {
				return ((Scenarios) ScenarioFile.class.getAnnotation(Scenarios.class)).annotationType();
			}
		};
		
		TestUtils.overrideScenarioFileNameAnnotation(ScenarioFile.class, Scenarios.class, newValue);
	}
	
	
	
	
}