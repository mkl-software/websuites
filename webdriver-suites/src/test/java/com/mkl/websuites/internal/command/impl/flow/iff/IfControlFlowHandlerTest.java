package com.mkl.websuites.internal.command.impl.flow.iff;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import pl.wkr.fluentrule.api.FluentExpectedException;

import com.mkl.websuites.WebSuitesConfig;
import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.WebSuitesRunner;
import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.browser.StandardBrowserController;
import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.impl.ParameterizedCommand;
import com.mkl.websuites.internal.config.WebSuites;
import com.mkl.websuites.internal.services.ServiceFactory;



@RunWith(JUnitParamsRunner.class)
public class IfControlFlowHandlerTest {

	
	private IfControlFlowHandler sut;
	
	
	@Rule
	public FluentExpectedException expectedException = FluentExpectedException.none();


	@WebSuites
	public static class MockRunner extends WebSuitesRunner {}
	
	
	@Before
	public void init() {
		
		new MockUp<ParameterizedCommand>() {
			@Mock
			void populateBrowser() {}
		};
		
		WebSuitesConfig.initializeWebsuitesConfig(MockRunner.class);
		ServiceFactory.init();
	}
	
	
	
	private void mockCurrentBrowser(final StandardBrowserController browserController, final String browser) {
		
		new Expectations() {{
			
			browserController.currentBrowser();
			result = browser;
		}};
	}
	
	
	@Test
	public void shouldDoIfWhenBrowserMatches(@Mocked final Command command,
			@Mocked StandardBrowserController browserController) {
		
		expectCommandRunCountForBrowserConfig(command, browserController, "browser", "ff", "ff", 1);
	}



	@Test
	public void shouldNotDoIfWhenBrowserDoesnNotMatches(@Mocked final Command command,
			@Mocked StandardBrowserController browserController) {
		
		expectCommandRunCountForBrowserConfig(command, browserController, "browser", "ff", "chrome", 0);
	}
	
	
	
	@Test
	public void shouldDoIfWhenBrowserDoesnNotMatches(@Mocked final Command command,
			@Mocked StandardBrowserController browserController) {
		
		expectCommandRunCountForBrowserConfig(command, browserController, "browserIsNot", "ff", "chrome", 1);
	}
	
	
	@Test
	public void shouldNotDoIfWhenBrowserMatches(@Mocked final Command command,
			@Mocked StandardBrowserController browserController) {
		
		expectCommandRunCountForBrowserConfig(command, browserController, "browserIsNot", "ie", "ie", 0);
	}
	
	
	@Test
	public void shouldDoIfWhenBrowserMatchesWithinSet(@Mocked final Command command,
			@Mocked StandardBrowserController browserController) {
		
		expectCommandRunCountForBrowserConfig(command, browserController, "browserIn", "ff,chrome,ie", "ie", 1);
	}
	
	
	@Test
	public void shouldNotDoIfWhenBrowserDoesnNotMatchesWithinSet(@Mocked final Command command,
			@Mocked StandardBrowserController browserController) {
		
		expectCommandRunCountForBrowserConfig(command, browserController, "browserIn", "ff,chrome,ie", "safari", 0);
	}
	
	
	
	@Test
	public void shouldNotDoIfWhenBrowserMatchesWithSet(@Mocked final Command command,
			@Mocked StandardBrowserController browserController) {
		
		expectCommandRunCountForBrowserConfig(command, browserController, "browserNotIn", "ff,chrome,ie", "ie", 0);
	}
	
	
	@Test
	public void shouldDoIfWhenBrowserDoesNotMatcheWithinSet(@Mocked final Command command,
			@Mocked StandardBrowserController browserController) {
		
		expectCommandRunCountForBrowserConfig(command, browserController,
				"browserNotIn", "ff,chrome,ie", "safari", 1);
	}
	
	
	
	@Test
	public void shouldThrowExceptionWhenConditionConfigIsWrong() {
		expectedException.expect(WebSuitesException.class);
		runPropertyConditionTestFor("unrecognized", "a", "a", false, null);
	}
	
	
	
	@Test
	public void shouldNotPassValidationForIncorrectIsset(final @Mocked Command command) {
		
		expectedException.expect(WebSuitesException.class);
		runPropertyConditionTestFor("isset", "incorrect", "existing", false, command);
	}
	
	
	@Test
	public void shouldDoWhenPropertyIsSet(final @Mocked Command command) {
		
		runPropertyConditionTestFor("isset", "true", "existing", true, command);
	}
	
	
	
	@Test
	public void shouldNotDoWhenPropertyIsNotSet(final @Mocked Command command) {
		
		runPropertyConditionTestFor("isset", "true", null, false, command);
	}
	
	
	@Test
	public void shouldDoWhenPropertyIsNotSet(final @Mocked Command command) {
		
		runPropertyConditionTestFor("isset", "false", null, true, command);
	}
	
	
	private void runPropertyConditionTestFor(String conditionType, String conditionValue,
			String propertyValue, final boolean shouldRun, final Command command) {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("property", "testProperty");
		params.put(conditionType, conditionValue);
		sut = new IfControlFlowHandler(params);
		sut.setNestedCommands(Arrays.asList(command));
		// and
		WebSuitesUserProperties.get().clear();
		WebSuitesUserProperties.get().setProperty("testProperty", propertyValue);
		// when
		sut.run();
		//then
		new Verifications() {{
			command.run();
			times = shouldRun ? 1 : 0;
		}};
	}
	
	
	
	
	@Test
	public void shouldDoWhenPropertyValueMatches(final @Mocked Command command) {
		
		runPropertyConditionTestFor("valueIs", "expected value", "expected value", true, command);
	}
	
	
	
	@Test
	public void shouldNotDoWhenPropertyValueDoesNotMatch(final @Mocked Command command) {
		
		runPropertyConditionTestFor("valueIs", "expected value", "not expected value", false, command);
	}
	
	
	
	@Test
	public void shouldDoWhenPropertyValueDoesNotMatch(final @Mocked Command command) {
		
		runPropertyConditionTestFor("valueIsNot", "expected value", "not expected value", true, command);
	}
	
	
	@Test
	public void shouldNotDoWhenPropertyValueMatches(final @Mocked Command command) {
		
		runPropertyConditionTestFor("valueIsNot", "expected value", "expected value", false, command);
	}
	
	
	
	@Parameters(value = {"...,abc,true", "...,123,true", "...,1234,false","\\d\\d,23,true",
			"\\d\\d,2f,false", "set.*,setPropertyShouldMatch,true", "get.*,setDoesntMatch,false",
			".*firefox.*,this firefox browser matches,true", ".*firefox.*,but IE doesn't,false"})
	@Test
	public void shouldDoWhenPropertyStringMatchesPatter(String regex, String value, boolean match) {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("property", "someProperty");
		params.put("valueMatches", regex);
		sut = new IfControlFlowHandler(params);
		// and
		WebSuitesUserProperties.get().clear();
		WebSuitesUserProperties.get().setProperty("someProperty", value);
		//when
		IfCondition condition = sut.buildPropertyCondition();
		//then
		assertThat(condition.isConditionMet()).isEqualTo(match);
	}
	

	public static class CustomIfConditionWithParams implements IfCondition {

		private List<String> customParams;
		
		public CustomIfConditionWithParams(List<String> customParams) {
			this.customParams = customParams;
		}

		@Override
		public boolean isConditionMet() {
			return "MKL Software".equals(customParams.get(0).toUpperCase() + " " + customParams.get(1));
		}
		
	}
	
	
	public static class CustomSimpleIfConditionWithTrue implements IfCondition {
		@Override
		public boolean isConditionMet() {
			return true;
		}
	}
	public static class CustomSimpleIfConditionWithFalse implements IfCondition {
		@Override
		public boolean isConditionMet() {
			return false;
		}
	}
	
	
	@Test
	public void shouldRunCustomTrueConditionWithoutParams() {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("condition", CustomSimpleIfConditionWithTrue.class.getName());
		sut = new IfControlFlowHandler(params);
		// when
		IfCondition customCondition = sut.buildCustomCondition();
		assertThat(customCondition.isConditionMet()).isTrue();
	}
	
	
	
	@Test
	public void shouldRunCustomFalseConditionWithoutParams() {
		// given
		Map<String, String> params = new HashMap<String, String>();
		params.put("condition", CustomSimpleIfConditionWithFalse.class.getName());
		sut = new IfControlFlowHandler(params);
		// when
		IfCondition customCondition = sut.buildCustomCondition();
		assertThat(customCondition.isConditionMet()).isFalse();
	}
	
	
	
	@Test
	public void shouldRunCustomCondition() {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("condition", CustomIfConditionWithParams.class.getName());
		params.put("params", "mkl,Software");
		sut = new IfControlFlowHandler(params);
		//when
		IfCondition condition = sut.buildCustomCondition();
		//then
		assertThat(condition.isConditionMet()).isTrue();
	}
	
	
	
	
	
	@Test
	public void shouldTolerateNullPropertyValues(final @Mocked Command command) {
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put("property", "someProperty");
		params.put("valueIs", "whatever");
		sut = new IfControlFlowHandler(params);
		sut.setNestedCommands(Arrays.asList(command));
		//when (missing property in WebSuitesUserProperties)
		WebSuitesUserProperties.get().clear();
		sut.run();
		//then
		new Verifications() {{
			command.run();
			times = 0;
		}};
	}
	
	
	

	private void expectCommandRunCountForBrowserConfig(final Command command,
			StandardBrowserController browserController, String configParam,
			String expectedBrowser, String actualBrowser, final int expectedRunCount) {
		
		//given
		Map<String, String> params = new HashMap<String, String>();
		params.put(configParam, expectedBrowser);
		sut = new IfControlFlowHandler(params);
		sut.setNestedCommands(Arrays.asList(command));
		mockCurrentBrowser(browserController, actualBrowser);
		//when
		sut.run();
		//then
		new Verifications() {{
			command.run();
			times = expectedRunCount;
		}};
	}
}
