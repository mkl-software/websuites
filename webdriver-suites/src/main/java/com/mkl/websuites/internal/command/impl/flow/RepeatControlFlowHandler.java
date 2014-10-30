package com.mkl.websuites.internal.command.impl.flow;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.command.Command;
import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.validator.DataProviderParamValidator;
import com.mkl.websuites.internal.command.impl.validator.IntegerNumberParamValidator;
import com.mkl.websuites.internal.command.impl.validator.ParameterValueValidator;
import com.mkl.websuites.internal.command.impl.validator.RepeatHandlerValidator;
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
		} else if (parameterMap.containsKey("dataProvider")) {
			doDataProvider();
		} else if (parameterMap.containsKey("handler")) {
			doHandler();
		}
	}

	
	
	
	private void doHandler() {
		String handlerClass = parameterMap.get("handler");
		try {
			RepeatHandler handler = (RepeatHandler) Class.forName(handlerClass).newInstance();
			handler.doRepeat(nestedCommands);
		} catch (Exception e) {
			throw new WebSuitesException("Unepected exception when trying to initialize data "
					+ "repeat handler class " + handlerClass, e);
		}
	}

	private void doDataProvider() {
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
			throw new WebSuitesException("Unepected exception when trying to initialize data "
					+ "provider class " + dataProviderClass, e);
		}
	}

	/**
	 * Without "params" there will never be an exception, only the unfilled properties
	 * will have null values.
	 */
	private void doData() {
		try {
			String data = parameterMap.get("data");
			String[] paramNames = null;
			if (parameterMap.containsKey("params")) {
				paramNames = parameterMap.get("params").split(",");
			}
			String[] dataRows = data.split(";");
			for (String dataRow : dataRows) {
				String[] params = dataRow.split(",");
				if (paramNames != null && params.length != paramNames.length) {
					throw new Exception("Wrong parameter length in row: '" + dataRow +
							"' in string '" + data + "'");
				}
				for (int i = 0; i < params.length; i++) {
					if (paramNames == null) {
						WebSuitesUserProperties.get().setProperty((i+1) + "", params[i]);
					} else {
						WebSuitesUserProperties.get().setProperty(paramNames[i] + "", params[i]);
					}
				}
				runNestedCommands();
			}
		} catch (Exception e) {
			String msg = "Error while parsing data string. Please provide inline parameters in data,"
					+ "parameters should be seperated using a coma ',' and rows should be seperated "
					+ "by a colon ';'. Sample correct parameters: data=1,2,3;i,j,k;x1,x2,x3";
			throw new WebSuitesException(msg, e);
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
				new SchemaValidationRule("data").addOptionalElements("params"),
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

	

}
