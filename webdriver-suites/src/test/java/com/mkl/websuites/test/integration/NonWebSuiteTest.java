package com.mkl.websuites.test.integration;

import junit.framework.TestCase;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class NonWebSuiteTest extends TestCase {

	
	@Override
	protected void runTest() throws Throwable {
		log.debug("non-websuite test");
		assertTrue(true);
	}

}
