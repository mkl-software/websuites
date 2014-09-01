package com.mkl.websuites;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public abstract class MultiBrowserTestCase extends TestCase {

	
	private String browserId;

	public MultiBrowserTestCase(String browserId) {
		super();
		this.browserId = browserId;
	}
	
	
	@Override
	public String getName() {
		return getTestName() + " [" + browserId + "]";
	}
	
	
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
