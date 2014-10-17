package com.mkl.websuites.internal.command.impl;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.command.CommandDescriptor;


@Slf4j
@CommandDescriptor(name = "setProperties")
public class SetPropCommand extends ParameterizedCommand {


	
	
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

}
