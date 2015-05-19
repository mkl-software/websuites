package com.mkl.websuites.internal.command.impl.flow.iff;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.flow.ControlFlowHandler;
import com.mkl.websuites.internal.command.impl.validator.IfConditionParamValidation;
import com.mkl.websuites.internal.command.impl.validator.IntegerNumberParamValidator;
import com.mkl.websuites.internal.command.impl.validator.ParameterValueValidator;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@CommandDescriptor(name = "if")
public class IfControlFlowHandler extends ControlFlowHandler {

	
	
	public IfControlFlowHandler() {
		super();
	}
	
	public IfControlFlowHandler(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	
	
	
	@Override
	protected void runCommandWithParameters() {
		
		IfCondition ifStatement = null;
		
		String param = "browser";
		if (parameterMap.containsKey(param)) {
			ifStatement = new BrowserCondition(parameterMap.get(param));
		}
		param = "browserIsNot";
		if (parameterMap.containsKey(param)) {
			ifStatement = new BrowserCondition(parameterMap.get(param), true);
		}
		param = "browserIn";
		if (parameterMap.containsKey(param)) {
			ifStatement = new BrowserSetCondition(parameterMap.get(param));
		}
		param = "browserNotIn";
		if (parameterMap.containsKey(param)) {
			ifStatement = new BrowserSetCondition(parameterMap.get(param), true);
		}
		
		if (ifStatement == null) {
			throw new WebSuitesException("Unrecognized \"if\" configuration parameters: " + parameterMap);
		}
		
		if (ifStatement.isConditionMet()) {
			
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
				new SchemaValidationRule("browser"),
				new SchemaValidationRule("browserIsNot"),
				new SchemaValidationRule("browserIn"),
				new SchemaValidationRule("browserNotIn"),
				new SchemaValidationRule("condition").addOptionalElements("params"));
	}
	
	@Override
	protected List<ParameterValueValidator> defineParameterValueValidators() {
		
		return Arrays.asList((ParameterValueValidator)
				new IntegerNumberParamValidator("times"),
				new IfConditionParamValidation());
	}



	
	
	
	@Override
	public String toString() {
		return "If, " + nestedCommands.size() + " nested commands";
	}


	

}
