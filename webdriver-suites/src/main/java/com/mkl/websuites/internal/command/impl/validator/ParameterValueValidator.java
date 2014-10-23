package com.mkl.websuites.internal.command.impl.validator;

public interface ParameterValueValidator {

	void validateParam(String paramValue);
	
	String getParamName();
	
	
}
