package com.mkl.websuites.internal.command.impl.validator;

import com.mkl.websuites.WebSuitesException;

public class BooleanParamValidator extends SingleValueValidator {

	public BooleanParamValidator(String string) {
		super(string);
	}

	@Override
	public void validateParam(String paramValue) {
		
		if (!"false".equalsIgnoreCase(paramValue) && !"true".equalsIgnoreCase(paramValue)) {
			
			throw new WebSuitesException("Parameter " + paramName + " with value '" +
					paramValue + "'must be proper BOOLEAN value");
		}
	}

	@Override
	public String getParamName() {
		return paramName;
	}

}
