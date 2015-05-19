package com.mkl.websuites.internal.command.impl.flow.iff;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import mockit.Expectations;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import mockit.Verifications;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import pl.wkr.fluentrule.api.FluentExpectedException;

import com.mkl.websuites.internal.browser.StandardBrowserController;
import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.impl.ParameterizedCommand;
import com.mkl.websuites.internal.services.ServiceFactory;


public class IfControlFlowHandlerTest {

	
	private IfControlFlowHandler sut;
	
	
	@Rule
	public FluentExpectedException expectedException = FluentExpectedException.none();


	
	@Before
	public void init() {
		
		new MockUp<ParameterizedCommand>() {
			@Mock
			void populateBrowser() {}
		};
		
		ServiceFactory.init(null);
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
