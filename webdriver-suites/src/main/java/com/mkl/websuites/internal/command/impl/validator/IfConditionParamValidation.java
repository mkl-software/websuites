package com.mkl.websuites.internal.command.impl.validator;

import com.mkl.websuites.internal.command.impl.flow.iff.IfCondition;

public class IfConditionParamValidation extends ClassParameterValidator {

	@Override
	public String getParamName() {
		return "condition";
	}

	@Override
	protected Class<?> getTargetClassForInstantiation() {
		return IfCondition.class;
	}

}
