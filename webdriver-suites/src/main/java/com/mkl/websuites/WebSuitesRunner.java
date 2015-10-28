package com.mkl.websuites;

import java.lang.reflect.InvocationTargetException;

import junit.framework.Test;
import junit.framework.TestSuite;
import lombok.extern.slf4j.Slf4j;

import org.junit.runner.RunWith;

import com.mkl.websuites.internal.ConfigurationManager;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.browser.RunnableForBrowser;
import com.mkl.websuites.internal.browser.SetUpAllTest;
import com.mkl.websuites.internal.browser.TearDownAllTest;
import com.mkl.websuites.internal.browser.SwitchBrowserTest;
import com.mkl.websuites.internal.browser.TearDownBrowserTest;
import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;
import com.mkl.websuites.internal.services.ServiceFactory;



@Slf4j
@RunWith(InternalWebSuitesRunner.class)
public class WebSuitesRunner {

	
	private Class<? extends WebSuitesRunner> runningClass;
	
	private static String currentlyDefiningBrowser;
	
	public WebSuitesRunner() {}
	
	public WebSuitesRunner(Class<? extends WebSuitesRunner> klass) {
		
		ServiceFactory.init(klass);
		runningClass = klass;
	}
	
	
	/**
	 * Runs before all tests are started.
	 */
	protected void setUp() {
		log.debug("set up all tests (default impl");
	}
	
	/**
	 * Runs after all tests for all browsers are finished and browsers are shut down.
	 */
	protected void tearDown() {
		log.debug("tear down all tests (default impl)");
	}
	
	/**
	 * Runs before all tests for given browser are started.
	 */
	protected void setUpBeforeBrowser(String currentBrowser) {
		log.debug("set up before browser '{}' (default impl)", currentBrowser);
	}
	
	/**
	 * Runs after all tests for given browser are finished.
	 */
	protected void tearDownAfterBrowser(String currentBrowser) {
		log.debug("tear down after browser '{}' (default impl)", currentBrowser);
	}
	
	

	public TestSuite defineMasterSuite() throws
			InstantiationException,
			IllegalAccessException, NoSuchMethodException, SecurityException,
			IllegalArgumentException, InvocationTargetException {

		log.debug("master suite method");
		
		WebSuitesConfig.initializeWebsuitesConfig(runningClass);
		
		TestSuite suite = new TestSuite();
		suite.setName("Multi-browser test suite");
		
		WebSuites runner = runningClass.getAnnotation(WebSuites.class);
		
		Class<? extends Test>[] tests = runner.suite();
		
		WebSuitesConfig_rename config = runner.configurationClass().
				getAnnotation(WebSuitesConfig_rename.class);
		
		ServiceFactory.get(ConfigurationManager.class).setConfiguration(config);
		
		ServiceFactory.get(BrowserController.class).initializeBrowsersEnvironment(config);
		
		String[] browsers = config.browsers();
		
		addSetUpSuite(suite);
		
		
		// check if run for non-browser mode:
		if (browsers.length == 1 && browsers[0].equals("none")) {
			configureSingleNonBrowserSuite(suite, tests);
		}
		
		for (String browser : browsers) {
			
			if (browser.equals("none")) {
				log.warn("No-browser found among browser tests - skipping no-browser. Please use a config"
						+ " with only ONE browser set to 'none' if you want to run tests without any browser");
				continue;
			}
			
			TestSuite browserSuite = buildBrowserSuite(browser);
			
			addTestsToBrowserSuite(tests, browserSuite);
			
			suite.addTest(browserSuite);
			
			addTearDownForBrowser(browserSuite, browser);
			
		}
		
		addTearDownSuite(suite);
		
		return suite;
	}

	
	
	
	
	private void addTearDownForBrowser(TestSuite browserSuite, String browser) {
		
		browserSuite.addTest(new TearDownBrowserTest(browser, new RunnableForBrowser() {

			@Override
			public void runForBrowser(String browserId) {
				tearDownAfterBrowser(browserId);
			}
		}));
	}

	
	
	private void configureSingleNonBrowserSuite(TestSuite suite,
			Class<? extends Test>[] suites) throws InstantiationException,
			IllegalAccessException {
		currentlyDefiningBrowser = "none"; // TODO: move it to a TestContext
		TestSuite browserSuite = new TestSuite("Running without any browser");
		addTestsToBrowserSuite(suites, browserSuite);
		suite.addTest(browserSuite);
	}

	
	
	
	
	private TestSuite buildBrowserSuite(String browser) {
		
		currentlyDefiningBrowser = browser;
		
		ServiceFactory.get(BrowserController.class).addBrowser(browser);

		TestSuite browserSuite = new TestSuite("Running for [" + browser + "]");
		
		browserSuite.addTest(new SwitchBrowserTest(browser, new RunnableForBrowser() {

			@Override
			public void runForBrowser(String browserId) {
				setUpBeforeBrowser(browserId);
			}
		}));
		return browserSuite;
	}

	
	
	private void addTearDownSuite(TestSuite suite) {
		suite.addTest(new TearDownAllTest(new Runnable() {
			
			@Override
			public void run() {
				
				tearDown();
			}
		}));
	}

	private void addSetUpSuite(TestSuite suite) {
		suite.addTest(new SetUpAllTest(new Runnable() {
			
			@Override
			public void run() {
				
				setUp();
			}
		}));
	}

	private void addTestsToBrowserSuite(Class<? extends Test>[] tests,
			TestSuite browserSuite) throws InstantiationException,
			IllegalAccessException {
		for (Class<? extends Test> testClass : tests) {
			
			Test dynamicTest = testClass.newInstance();
			browserSuite.addTest(dynamicTest);
		}
	}

	
	
	/**
	 * To allow quickly identify in the code for which browser
	 * are tests currently defined for.
	 * @return
	 */
	public static String getCurrentlyDefiningBrowser() {
		return currentlyDefiningBrowser;
	}
}
