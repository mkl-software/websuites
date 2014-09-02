package com.mkl.websuites;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


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
		
		webDriver = new FirefoxDriver();
	}
	
	
	@Override
	protected void tearDown() throws Exception {
		log.debug("tearDown for test: " + this.getName());
		super.tearDown();
	}
}
