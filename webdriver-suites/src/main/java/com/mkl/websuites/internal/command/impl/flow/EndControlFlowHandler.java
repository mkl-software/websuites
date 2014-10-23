package com.mkl.websuites.internal.command.impl.flow;

import java.util.List;
import java.util.Map;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;

import lombok.extern.slf4j.Slf4j;


@CommandDescriptor(name = "end")
@Slf4j
public class EndControlFlowHandler extends ControlFlowHandler {

	
	public EndControlFlowHandler() {}
	
	public EndControlFlowHandler(Map<String, String> parameterMap) {
		log.warn("\"end\" statement doesn't take any parameters, any existign will be ignored.");
	}
	
	
	@Override
	protected void runCommandWithParameters() {

	}

	@Override
	protected List<SchemaValidationRule> defineValidationRules() {
		
		return SchemaValidationRule.emptyValidationRules();
	}

}
