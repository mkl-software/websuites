package com.mkl.websuites.internal.command.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.WebSuitesUserProperties;
import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.impl.validator.CommandSchemaValidator;
import com.mkl.websuites.internal.command.impl.validator.ParameterValueValidator;
import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;
import com.mkl.websuites.internal.services.ServiceFactory;


@Slf4j
public abstract class ParameterizedCommand extends BaseCommand {

	
	
	protected Map<String, String> parameterMap;
	
	
	public ParameterizedCommand(Map<String, String> parameterMap) {
		this.parameterMap = parameterMap;
	}
	
	
	
	@Override
	public void run() {
		
		if (parameterMap == null) {
			
			super.run();
		} else {
			
			log.debug("running parameterized command " + this.getClass() +
					" with parameters " + parameterMap);
			
			resolvePropertyValuesInParameterMap();
			
			validateParameters();
			
			populateBrowser();
			
			runCommandWithParameters();
		}
	}



	protected void populateBrowser() {
		browser = ServiceFactory.get(BrowserController.class).getWebDriver();
	}



	protected void validateParameters() {
		new CommandSchemaValidator(defineValidationRules(), defineParameterValueValidators())
			.validateCommandSchema(parameterMap);
	}
	
	
	protected void resolvePropertyValuesInParameterMap() {
		Map<String, String> populatedMap = new HashMap<String, String>();
		for (String key : parameterMap.keySet()) {
			
			String origValue = parameterMap.get(key);
			String withPopulatedProperties = populateStringWithProperties(origValue);
			populatedMap.put(key, withPopulatedProperties);
			
		}
		parameterMap = populatedMap;
	}



	protected String populateStringWithProperties(String origValue) {
		String populated = new String(origValue);
		String regex = "\\$\\{(.*?)\\}";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(origValue);
		while (matcher.find()) {
			String propName = matcher.group(1);
			String value = WebSuitesUserProperties.get().getProperty(propName);
			if (value != null) {
				populated = populated.replaceAll("\\$\\{" + propName +"\\}", value);
			}
		}
		return populated;
	}



//	protected boolean validateAnyOf(String ... paramNames) {
//		
//		return checkNumberOfMatchingParams(paramNames) > 0;
//	}
//	
//	
//	protected boolean validateAllOf(String ... paramNames) {
//		
//		return checkNumberOfMatchingParams(paramNames) == paramNames.length;
//	}


	protected abstract void runCommandWithParameters();


	protected abstract List<SchemaValidationRule> defineValidationRules();
	
	protected  List<ParameterValueValidator> defineParameterValueValidators() {
		return Collections.emptyList();
	}
	
	

//	protected int checkNumberOfMatchingParams(String... validParams) {
//		
//		int k = 0;
//		for (String key : validParams) {
//			
//			if (parameterMap.containsKey(key)) {
//				k++;
//			}
//			
//		}
//		return k;
//	}


}
