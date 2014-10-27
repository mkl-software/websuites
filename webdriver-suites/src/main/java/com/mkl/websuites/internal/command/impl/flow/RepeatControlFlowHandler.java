package com.mkl.websuites.internal.command.impl.flow;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.validator.IntegerNumberParamValidator;
import com.mkl.websuites.internal.command.impl.validator.ParameterValueValidator;
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
		} else if (parameterMap.containsKey("data")) {
			doData();
		}
	}

	
	
	
	private void doData() {
		String data = parameterMap.get("data");
		String[] dataRows = data.split(";");
		for (String dataRow : dataRows) {
			String[] params = dataRow.split(",");
			for (int i = 0; i < params.length; i++) {
				WebSuitesUserProperties.get().setProperty((i+1) + "", params[i]);
			}
			runNestedCommands();
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
			runNestedCommands();
		}
	}

	private void runNestedCommands() {
		for (Command command : nestedCommands) {
			command.run();
		}
	}

	
	@Override
	protected List<SchemaValidationRule> defineValidationRules() {
		
		return Arrays.asList(
				new SchemaValidationRule("times").addOptionalElements("counter"),
				new SchemaValidationRule("data").addOptionalElements("params"));
	}
	
	@Override
	protected List<ParameterValueValidator> defineParameterValueValidators() {
		
		return Arrays.asList((ParameterValueValidator) new IntegerNumberParamValidator("times"));
	}

	

}
