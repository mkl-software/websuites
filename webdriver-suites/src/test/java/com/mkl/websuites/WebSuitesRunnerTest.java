package com.mkl.websuites;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.Result;

import com.mkl.websuites.itests.web.core.WebSuitesResultCheck;


public class WebSuitesRunnerTest extends WebSuitesResultCheck {

	
	public static class DefaultWebSuitesNoConfig extends WebSuitesRunner {}
	
	@Test
	public void shouldRunDefaultRunnerNoAnnotation() throws Throwable {
		//when
		Result testResult = super.checkWebTestResult(DefaultWebSuitesNoConfig.class);
		checkIfNoFailures(testResult);
		//then
		int numberOfRunForSetupAndTearDownOnly = 2;
		assertThat(testResult.getRunCount()).isEqualTo(numberOfRunForSetupAndTearDownOnly);
	}
	
	
	
	@WebSuites(browsers = "html")
	public static class HtmlUnitBrowserEmptyTestSuite extends WebSuitesRunner {}
	
	@Test
	public void shouldRunOneHtmlUnitBrowserEmptyTestSuite() throws Throwable {
		//when
		Result testResult = super.checkWebTestResult(HtmlUnitBrowserEmptyTestSuite.class);
		checkIfNoFailures(testResult);
		//then
		int numberOfRunForSetupTearDownAndOneBrowserSetUpTearDown = 2 + 2;
		assertThat(testResult.getRunCount()).isEqualTo(numberOfRunForSetupTearDownAndOneBrowserSetUpTearDown);
	}
	
	
	
	@WebSuites(
		browsers = {"html", "html", "html"}
	)
	public static class ThreeHtmlUnitBrowsersEmptyTestSuite extends WebSuitesRunner {}
	
	@Test
	public void shouldRunThreeHtmlUnitBrowsersEmptyTestSuite() throws Throwable {
		//when
		Result testResult = super.checkWebTestResult(ThreeHtmlUnitBrowsersEmptyTestSuite.class);
		checkIfNoFailures(testResult);
		//then
		int numberOfRunForSetupTearDownAndThreeBrowsersSetUpTearDown = 2 + (3 * 2);
		assertThat(testResult.getRunCount()).isEqualTo(numberOfRunForSetupTearDownAndThreeBrowsersSetUpTearDown);
	}
}
