package com.mkl.websuites.internal.command.impl.flow;

import java.util.List;

public interface Subtestable {

	
	boolean isSubtest();
	
	String getSubtestName();
	
	List<String> getSubTestCaseNames();
}
