package com.mkl.websuites.internal.command.impl.misc;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.ParameterizedCommand;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@Slf4j
@CommandDescriptor(name = "setProperties")
public class SetPropCommand extends ParameterizedCommand {


	public SetPropCommand() {
		this (new HashMap<String, String>());
	}
	
	
	public SetPropCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	@Override
	protected void runCommandWithParameters() {

		WebSuitesUserProperties.get().populateFrom(parameterMap);
	}

	@Override
	protected void runStandardCommand() {
		
		log.warn("no properties to set in this command");

	}


	@Override
	protected List<SchemaValidationRule> defineValidationRules() {
		
		return SchemaValidationRule.emptyValidationRules();
	}

}
