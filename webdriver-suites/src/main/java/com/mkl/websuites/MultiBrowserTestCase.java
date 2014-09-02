package com.mkl.websuites;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;

import org.openqa.selenium.WebDriver;


@Slf4j
public abstract class MultiBrowserTestCase extends TestCase {

	
	protected MultiBrowserSuite parentSuite;
	
	protected WebDriver webDriver;
	

	public MultiBrowserTestCase(MultiBrowserSuite parentSuite) {
		super();
		this.parentSuite = parentSuite;
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
		super.tearDown();
	}
}
