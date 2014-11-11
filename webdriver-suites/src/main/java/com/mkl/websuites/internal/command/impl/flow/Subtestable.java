package com.mkl.websuites.internal.command.impl.flow;

import java.util.List;

import junit.framework.TestCase;

public interface Subtestable {

	
	boolean isSubtest();
	
	String getSubtestName();
	
	List<TestCase> subTestCases();
}
