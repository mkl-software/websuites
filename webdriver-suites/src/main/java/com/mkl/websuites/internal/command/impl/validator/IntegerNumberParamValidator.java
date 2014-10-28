package com.mkl.websuites.internal.command.impl.validator;

import com.mkl.websuites.WebSuitesException;

public class IntegerNumberParamValidator implements ParameterValueValidator {

	
	private int lowerRange;
	private int upperRange;
	private String paramName;
	
	public IntegerNumberParamValidator(String paramName) {
		this(paramName, 0, Integer.MAX_VALUE);
	}
	
	public IntegerNumberParamValidator(String param, int upperRange) {
		this(param, 0, upperRange);
	}

	public IntegerNumberParamValidator(String param, int lowerRange, int upperRange) {
		this.lowerRange = lowerRange;
		this.upperRange = upperRange;
		this.paramName = param;
	}

	@Override
	public void validateParam(String paramValue) {

		try {
			Integer value = Integer.valueOf(paramValue);
			if (value < lowerRange || value > upperRange) {
				throw new WebSuitesException("Integer value for param " + paramName +
						" must be between " + lowerRange + " and " + upperRange);
			}
		} catch (NumberFormatException e) {
			throw new WebSuitesException("Parameter " + paramName + " with value '" +
					paramValue + "'must be proper integer value");
		}
		
	}
	
	
	@Override
	public String getParamName() {
		return paramName;
	}

}