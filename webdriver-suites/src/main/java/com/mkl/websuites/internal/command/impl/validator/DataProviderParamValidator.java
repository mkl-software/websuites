package com.mkl.websuites.internal.command.impl.validator;

import com.mkl.websuites.internal.command.impl.flow.repeat.RepeatDataProvider;


public class DataProviderParamValidator extends ClassParameterValidator {

	@Override
	public String getParamName() {
		return "dataProvider";
	}

	@Override
	protected Class<?> getTargetClassForInstantiation() {
		return RepeatDataProvider.class;
	}

}
