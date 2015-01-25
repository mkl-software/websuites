package com.mkl.websuites.internal.command.impl.flow.repeat;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.mkl.websuites.WebSuitesException;

public class InlineDataProvider implements RepeatDataProvider {

	private Map<String, String> parameterMap;

	public InlineDataProvider(Map<String, String> parameterMap) {
		super();
		this.parameterMap = parameterMap;
	}

	
	
	/**
	 * Without "params" there will never be an exception, only the unfilled properties
	 * will have null values.
	 */
	@Override
	public List<Map<String, String>> provideData() {
		
		List<Map<String, String>> resultData = new ArrayList<Map<String,String>>();
		
		try {
			
			String data = parameterMap.get("data");
			String[] paramNames = null;
			
			// check for explicit parameter names:
			if (parameterMap.containsKey("params")) {
				paramNames = parameterMap.get("params").split(",");
			}
			
			String[] dataRows = data.split(";");
			
			for (String dataRow : dataRows) {
				
				Map<String, String> row = new LinkedHashMap<String, String>();
				
				String[] values = dataRow.split(",");
				
				if (paramNames != null && values.length != paramNames.length) {
					throw new Exception("Wrong parameter length in row: '" + dataRow +
							"' in string '" + data + "'");
				}
				
				for (int i = 0; i < values.length; i++) {
					
					if (paramNames == null) { // number starting 1 are the keys
//						WebSuitesUserProperties.get().setProperty((i+1) + "", params[i]);
						row.put((i+1) + "", values[i]);
					} else {
//						WebSuitesUserProperties.get().setProperty(paramNames[i] + "", params[i]);
						row.put(paramNames[i] + "", values[i]);
					}
				}
				
				resultData.add(row);
				
			}
		} catch (Exception e) {
			String msg = "Error while parsing data string. Please provide inline parameters in data,"
					+ "parameters should be seperated using a coma ',' and rows should be seperated "
					+ "by a semicolon ';'. Sample correct parameters: data=1,2,3;i,j,k;x1,x2,x3";
			throw new WebSuitesException(msg, e);
		}
		
		return resultData;
	}

}
