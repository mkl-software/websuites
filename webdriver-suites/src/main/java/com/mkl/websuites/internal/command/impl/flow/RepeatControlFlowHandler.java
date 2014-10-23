package com.mkl.websuites.internal.command.impl.flow;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


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
		
		if (parameterMap.containsKey("times")) {
			doTimes();
		}
	}

	
	
	
	private void doTimes() {
		WebSuitesUserProperties props = WebSuitesUserProperties.get();
		int n = Integer.valueOf(parameterMap.get("times"));
		String counterProperty = "1";
		if (parameterMap.containsKey("counter")) {
			counterProperty = parameterMap.get("counter");
		}
		for (int i = 0; i < n; i++) {
			props.setProperty(counterProperty, (i + 1) + "");
			for (Command command : nestedCommands) {
				command.run();
			}
		}
	}

	
	@Override
	protected List<SchemaValidationRule> defineValidationRules() {
		
		return Arrays.asList(
				new SchemaValidationRule("times").addOptionalElements("counter"));
	}

	

}
