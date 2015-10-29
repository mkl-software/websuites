package com.mkl.websuites;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import junit.framework.Test;
import junit.framework.TestSuite;
import lombok.extern.slf4j.Slf4j;

import org.junit.runner.RunWith;

import com.mkl.websuites.internal.CommonUtils;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.browser.RunnableForBrowser;
import com.mkl.websuites.internal.browser.SetUpAllTest;
import com.mkl.websuites.internal.browser.SwitchBrowserTest;
import com.mkl.websuites.internal.browser.TearDownAllTest;
import com.mkl.websuites.internal.browser.TearDownBrowserTest;
import com.mkl.websuites.internal.config.Folder;
import com.mkl.websuites.internal.config.ScenarioFile;
import com.mkl.websuites.internal.config.TestClass;
import com.mkl.websuites.internal.config.WebSuites;
import com.mkl.websuites.internal.runner.InternalWebSuitesRunner;
import com.mkl.websuites.internal.services.ServiceFactory;
import com.mkl.websuites.tests.ScenarioFileTest;
import com.mkl.websuites.tests.ScenarioFolderTest;



@Slf4j
@RunWith(InternalWebSuitesRunner.class)
public class WebSuitesRunner {

	
	
	private static String currentlyDefiningBrowser;
	
	public WebSuitesRunner() {}
	
	public WebSuitesRunner(Class<? extends WebSuitesRunner> klass) {
		
		WebSuitesConfig.initializeWebsuitesConfig(klass);
		
		ServiceFactory.init();
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
		
		WebSuites config = WebSuitesConfig.get();
		
		TestSuite suite = new TestSuite();
		suite.setName("Multi-browser test suite");
		
		
		ServiceFactory.get(BrowserController.class).initializeBrowsersEnvironment(config);
		
		String[] browsers = clobberPropertiesInBrowserIds(config);
		
		addSetUpSuite(suite);
		
		
		// check if run for non-browser mode:
		if (browsers.length == 1 && browsers[0].equals("none")) {
			configureSingleNonBrowserSuite(suite, buildAllTests(config));
		}
		
		for (String browser : browsers) {
			
			if (browser.equals("none")) {
				log.warn("No-browser found among browser tests - skipping no-browser. Please use a config"
						+ " with only ONE browser set to 'none' if you want to run tests without any browser");
				continue;
			}
			
			TestSuite browserSuite = buildBrowserSuite(browser);
			
			List<Test> allTestsToRun = buildAllTests(config);
			
			addTestsToBrowserSuite(allTestsToRun, browserSuite);
			
			suite.addTest(browserSuite);
			
			addTearDownForBrowser(browserSuite, browser);
			
		}
		
		addTearDownSuite(suite);
		
		return suite;
	}

	private String[] clobberPropertiesInBrowserIds(WebSuites config) {
		String[] origBrowserIds = config.browsers();
		String[] processedIds = new String[origBrowserIds.length];
		for (int i = 0; i < origBrowserIds.length; i++) {
			String id = origBrowserIds[i];
			id = CommonUtils.populateStringWithProperties(id);
			if (id.startsWith("$")) {
				id = "chrome"; // TODO: default, TEMP !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			}
			processedIds[i] = id;
		}
		return processedIds;
	}

	
	
	
	
	private List<Test> buildAllTests(WebSuites config) {
	
		// TODO: with new apprach there's a a big concern - how to
		// retain the order from test declaration in the annotation???
		
		List<Test> result = new ArrayList<>();
		
		ScenarioFile[] scenarios = config.scenarios();
		for (int i = 0; i < scenarios.length; i++) {
			ScenarioFile scenarioFile = scenarios[i];
			String filePath = scenarioFile.value();
			result.add(new ScenarioFileTest(filePath));
		}
		
		Folder[] folders = config.folders();
		for (int i = 0; i < folders.length; i++) {
			Folder folder = folders[i];
			result.add(new ScenarioFolderTest(
					folder.path(), folder.ignoreSubfolders(), folder.sortingStrategy()));
		}
		
		TestClass[] testClasses = config.classes();
		for (int i = 0; i < testClasses.length; i++) {
			TestClass testDefinition = testClasses[i];
			Class<? extends Test> test = testDefinition.value();
			try {
				result.add(test.newInstance());
			} catch (InstantiationException | IllegalAccessException e) {
				throw new WebSuitesException("Cannot create an instance of test class " + test, e);
			}
		}
		
		return result;
	}

	private void addTearDownForBrowser(TestSuite browserSuite, String browser) {
		
		browserSuite.addTest(new TearDownBrowserTest(browser, new RunnableForBrowser() {

			@Override
			public void runForBrowser(String browserId) {
				tearDownAfterBrowser(browserId);
			}
		}));
	}

	
	
	private void configureSingleNonBrowserSuite(TestSuite suite, List<Test> tests) {
		
		currentlyDefiningBrowser = "none"; // TODO: move it to a TestContext
		TestSuite browserSuite = new TestSuite("Running without any browser");
		addTestsToBrowserSuite(tests, browserSuite);
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

	private void addTestsToBrowserSuite(List<Test> tests,
			TestSuite browserSuite) {

		for (Test test : tests) {
			
//			Test dynamicTest = testClass.newInstance();
			browserSuite.addTest(test);
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
