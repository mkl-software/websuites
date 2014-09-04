package com.mkl.websuites;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;

import com.mkl.websuites.internal.BrowserController;
import com.mkl.websuites.internal.ConfigurationManager;


@Slf4j
public abstract class MultiBrowserTestCase extends TestCase {

	
	protected WebSuitesConfig configuration = ConfigurationManager.getInstance().getConfiguration();
	
	protected String currentBrowser;
	
	protected WebDriver webDriver;
	
	protected String basePath;
	

	public MultiBrowserTestCase() {
		super();
		this.basePath = configuration.host() + ":" + configuration.port() + configuration.basePath();
		this.currentBrowser = BrowserController.getInstance().getLocalBrowserNameForTestInit();
	}
	
	
	@Override
	public String getName() {
		return getTestName() + " [" + currentBrowser + "]";
	}
	
	
	@Override
	protected void runTest() throws Throwable {
		
		this.webDriver = BrowserController.getInstance().getWebDriver();
		
		runLocally();
	}
	
	protected abstract void runLocally();


	protected abstract String getTestName();


	@Override
	protected void setUp() throws Exception {
		log.debug("setUp for test: " + this.getName());
		super.setUp();
		
	}
	
	
	@Override
	protected void tearDown() throws Exception {
		log.debug("tearDown for test: " + this.getName());
		
		// check if last test in the suite list and close/switch browser
		
//		if (parentSuite.getClass().equals(BrowserController.getInstance().getLastSuiteClass())) {
//			
//			log.debug("this is last test case in the top suite");
//		}
		
		super.tearDown();
	}
}
