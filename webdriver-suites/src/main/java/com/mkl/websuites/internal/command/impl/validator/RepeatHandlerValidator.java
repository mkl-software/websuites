package com.mkl.websuites.internal.command.impl.validator;

import com.mkl.websuites.internal.command.impl.flow.RepeatHandler;

public class RepeatHandlerValidator extends ClassParameterValidator {

	@Override
	public String getParamName() {
		return "handler";
	}

	@Override
	protected Class<?> getTargetClassForInstantiation() {
		return RepeatHandler.class;
	}

}
