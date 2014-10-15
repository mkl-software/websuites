package com.mkl.websuites.internal.command.impl;

import java.util.Map;

import com.mkl.websuites.internal.command.CommandDescriptor;

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

}
