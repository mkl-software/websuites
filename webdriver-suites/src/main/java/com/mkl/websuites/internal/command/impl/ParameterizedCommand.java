package com.mkl.websuites.internal.command.impl;

import java.util.Map;

import lombok.extern.slf4j.Slf4j;

import com.mkl.websuites.internal.browser.BrowserController;
import com.mkl.websuites.internal.command.BaseCommand;
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
			
			browser = ServiceFactory.get(BrowserController.class).getWebDriver();
			log.debug("running parameterized command " + this.getClass() +
					" with parameters " + parameterMap);
			runCommandWithParameters();
		}
	}
	
	
	protected boolean validateAnyOf(String ... paramNames) {
		
		return checkNumberOfMatchingParams(paramNames) > 0;
	}
	
	
	protected boolean validateAllOf(String ... paramNames) {
		
		return checkNumberOfMatchingParams(paramNames) == paramNames.length;
	}


	protected abstract void runCommandWithParameters();


	
	

	protected int checkNumberOfMatchingParams(String... validParams) {
		
		int k = 0;
		for (String key : validParams) {
			
			if (parameterMap.containsKey(key)) {
				k++;
			}
			
		}
		return k;
	}


}
