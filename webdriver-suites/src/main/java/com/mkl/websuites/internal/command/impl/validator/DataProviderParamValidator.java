package com.mkl.websuites.internal.command.impl.validator;

import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.internal.command.impl.flow.RepeatDataProvider;

public class DataProviderParamValidator implements ParameterValueValidator {

	@Override
	public void validateParam(String paramValue) {
		
		try {
			Class<?> dataProviderClass = Class.forName(paramValue);
			if (!RepeatDataProvider.class.isAssignableFrom(dataProviderClass)) {
				throw new WebSuitesException("Data provider class " + paramValue +
						" must implement " + ParameterValueValidator.class.getName());
			}
			dataProviderClass.newInstance();
		} catch (ClassNotFoundException e) {
			throw new WebSuitesException("Cannot find data provider class: " + paramValue);
		} catch (InstantiationException | IllegalAccessException e) {
			throw new WebSuitesException("Cannot instantiate data provider class: " + paramValue +
					" Make sure the class has a no-arg constructor.");
		}

	}

	@Override
	public String getParamName() {
		return "dataProvider";
	}

}
