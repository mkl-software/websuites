package com.mkl.websuites.internal.tests;

import junit.framework.TestCase;

public class SetUpAllTest extends TestCase {

	private Runnable setUpLogic;


	public SetUpAllTest(Runnable setUpLogic) {
		this.setUpLogic = setUpLogic;
	}
	
	@Override
	public String getName() {
		return "Set up all tests";
	}
	
	
	@Override
	protected void runTest() throws Throwable {
		
		setUpLogic.run();
	}
	
}
