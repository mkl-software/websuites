package com.mkl.websuites.internal.command.impl.flow.repeat;

import java.util.List;
import java.util.Map;

import com.mkl.websuites.WebSuitesException;
import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.command.Command;

public class InlineDataRepeatHandler implements RepeatHandler {

	private Map<String, String> parameterMap;

	public InlineDataRepeatHandler(Map<String, String> parameterMap) {
		super();
		this.parameterMap = parameterMap;
	}

	@Override
	public void doRepeat(List<Command> nestedCommands) {
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
				for (Command command : nestedCommands) {
					command.run();
				}
			}
		} catch (Exception e) {
			String msg = "Error while parsing data string. Please provide inline parameters in data,"
					+ "parameters should be seperated using a coma ',' and rows should be seperated "
					+ "by a semicolon ';'. Sample correct parameters: data=1,2,3;i,j,k;x1,x2,x3";
			throw new WebSuitesException(msg, e);
		}
	}

}
