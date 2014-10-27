package com.mkl.websuites.internal.command.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.impl.validator.SchemaValidationRule;

public abstract class OperationOnWebElement extends ParameterizedCommand {

	
	protected String element;
	

	public OperationOnWebElement(Map<String, String> parameterMap) {
		super(parameterMap);
	}


	@Override
	protected void runStandardCommand() {
			
		parameterMap = new HashMap<String, String>();
		parameterMap.put("css", populateStringWithProperties(element));
		runCommandWithParameters();
	}
	
	
	@Override
	protected void runCommandWithParameters() {
		
		
		By by = null;
		
		if (parameterMap.keySet().contains("id")) {
			by = By.id(parameterMap.get("id"));
		}
		
		if (parameterMap.keySet().contains("css")) {
			by = By.cssSelector(parameterMap.get("css"));
		}
		
		if (parameterMap.keySet().contains("xpath")) {
			by = By.xpath(parameterMap.get("xpath"));
		}
		
		if (parameterMap.keySet().contains("className")) {
			by = By.className(parameterMap.get("className"));
		}
		
		if (parameterMap.keySet().contains("linkText")) {
			by = By.linkText(parameterMap.get("linkText"));
		}
		
		if (parameterMap.keySet().contains("partialLinkText")) {
			by = By.partialLinkText(parameterMap.get("partialLinkText"));
		}
		
		if (parameterMap.keySet().contains("name")) {
			by = By.name(parameterMap.get("name"));
		}
		
		if (parameterMap.keySet().contains("tagName")) {
			by = By.tagName(parameterMap.get("tagName"));
		}
		
		
		try {
			
			WebElement elem = browser.findElement(by);
			
			doOperationOnElement(elem);
			
		} catch (NoSuchElementException e) {
			Assert.fail("No element for selecor " + by + " can be found on the page. "
					+ "Selection parameters: " + parameterMap);
		}
	}

	
	@Override
	protected List<SchemaValidationRule> defineValidationRules() {
		
		List<SchemaValidationRule> rules = new ArrayList<SchemaValidationRule>();
		String[] validParams = new String[]{
				"css", "id", "xpath", "linkText", "partialLinkText",
				"className", "name", "tagName"};
		
		for (String paramName : validParams) {
			SchemaValidationRule rule = new SchemaValidationRule(paramName);
			rules.add(rule);
		}
		
		return rules;
	}
	

	protected abstract void doOperationOnElement(WebElement elem);

}
