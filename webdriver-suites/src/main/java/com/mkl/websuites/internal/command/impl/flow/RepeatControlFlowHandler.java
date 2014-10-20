package com.mkl.websuites.internal.command.impl.flow;

import java.util.Map;

import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "repeat")
public class RepeatControlFlowHandler extends ControlFlowHandler{

	
	public RepeatControlFlowHandler() {
		super();
	}
	
	public RepeatControlFlowHandler(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	@Override
	protected void runCommandWithParameters() {
		
		checkNumberOfMatchingParams("times");
		
		int n = Integer.valueOf(parameterMap.get("times"));
		
		for (int i = 0; i < n; i++) {
			WebSuitesUserProperties.get().setProperty("1", (i + 1) + "");
			for (Command command : nestedCommands) {
				command.run();
			}
		}
	}

	

}
