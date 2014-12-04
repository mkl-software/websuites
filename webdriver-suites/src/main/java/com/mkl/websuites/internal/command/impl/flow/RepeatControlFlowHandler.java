package com.mkl.websuites.internal.command.impl.flow;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import junit.framework.TestCase;

import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.flow.repeat.InlineDataRepeatHandler;
import com.mkl.websuites.internal.command.impl.flow.repeat.RepeatDataProvider;
import com.mkl.websuites.internal.command.impl.flow.repeat.RepeatHandler;
import com.mkl.websuites.internal.command.impl.validator.DataProviderParamValidator;
import com.mkl.websuites.internal.command.impl.validator.IntegerNumberParamValidator;
import com.mkl.websuites.internal.command.impl.validator.ParameterValueValidator;
import com.mkl.websuites.internal.command.impl.validator.RepeatHandlerValidator;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;


@CommandDescriptor(name = "repeat")
public class RepeatControlFlowHandler extends ControlFlowHandler implements Subtestable {

	
	public RepeatControlFlowHandler() {
		super();
	}
	
	public RepeatControlFlowHandler(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	@Override
	protected void runCommandWithParameters() {
		
		if (parameterMap.containsKey("times")) {
			doRepeatNTimes();
		} else if (parameterMap.containsKey("data")) {
			doRepeatWithInlineData();
		} else if (parameterMap.containsKey("dataProvider")) {
			doRepeatWithDataProviderClass();
		} else if (parameterMap.containsKey("handler")) {
			doRepeatWithCustomRepeatHandlerClass();
		}
	}

	
	
	
	private void doRepeatWithCustomRepeatHandlerClass() {
		String handlerClass = parameterMap.get("handler");
		try {
			RepeatHandler handler = (RepeatHandler) Class.forName(handlerClass).newInstance();
			handler.doRepeat(nestedCommands);
		} catch (Exception e) {
			throw new WebSuitesException("Unepected exception when trying to initialize data "
					+ "repeat handler class " + handlerClass, e);
		}
	}

	
	
	
	private void doRepeatWithDataProviderClass() {
		String dataProviderClass = parameterMap.get("dataProvider");
		try {
			RepeatDataProvider dataProvider =
					(RepeatDataProvider) Class.forName(dataProviderClass).newInstance();
			List<Map<String,String>> data = dataProvider.provideData();
			WebSuitesUserProperties userProperties = WebSuitesUserProperties.get();
			
			for (Map<String, String> params : data) {
				
				userProperties.populateFrom(params);
				runNestedCommands();
			}
		} catch (Exception e) {
			throw new WebSuitesException("Unepected exception when trying to acquire data from "
					+ "provider class " + dataProviderClass, e);
		}
	}

	/**
	 * Without "params" there will never be an exception, only the unfilled properties
	 * will have null values.
	 */
	private void doRepeatWithInlineData() {
		
		new InlineDataRepeatHandler(parameterMap).doRepeat(nestedCommands);
	}

	
	
	private void doRepeatNTimes() {
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
				new SchemaValidationRule("data").addOptionalElements("params"),
				new SchemaValidationRule("data")
					.addMandatoryElements("subtest")
					.addOptionalElements("params"),
				new SchemaValidationRule("dataProvider"),
				new SchemaValidationRule("handler"));
	}
	
	@Override
	protected List<ParameterValueValidator> defineParameterValueValidators() {
		
		return Arrays.asList((ParameterValueValidator)
				new IntegerNumberParamValidator("times"),
				new DataProviderParamValidator(),
				new RepeatHandlerValidator());
	}

	@Override
	public boolean isSubtest() {
		if (parameterMap.containsKey("subtest") &&
			"TRUE".equalsIgnoreCase(parameterMap.get("subtest"))) {
			return true;
		}
		return false;
	}

	@Override
	public String getSubtestName() {
		return "REPEAT";
	}

	
	/**
	 * Invoked at test tree creation time.
	 */
	@Override
	public List<String> getSubTestCaseNames() {
		return null;
	}


	

}
