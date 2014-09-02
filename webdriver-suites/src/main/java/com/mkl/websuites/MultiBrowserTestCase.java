package com.mkl.websuites;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;

import com.mkl.websuites.internal.BrowserController;


@Slf4j
public abstract class MultiBrowserTestCase extends TestCase {

	
	protected MultiBrowserSuite parentSuite;
	
	protected WebDriver webDriver;
	
	protected String basePath;
	

	public MultiBrowserTestCase(MultiBrowserSuite parentSuite) {
		super();
		this.parentSuite = parentSuite;
		WebSuitesConfig config = parentSuite.configuration;
		this.basePath = config.host() + ":" + config.port() + config.basePath();
		
		this.webDriver = BrowserController.getInstance().getWebDriver();
	}
	

	protected WebSuitesConfig getConfig() {
		
		return parentSuite.configuration;
	}
	
	
	@Override
	public String getName() {
		return getTestName() + " [" + parentSuite.browserId + "]";
	}
	
	
	@Override
	protected void runTest() throws Throwable {
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
