package com.mkl.websuites.internal.command.impl;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "click", argumentTypes = String.class)
public class ClickCommand extends ParameterizedCommand {

	
	private String element;
	
	
	public ClickCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}
	
	public ClickCommand(String elemement) {
		super(null);
		this.element = elemement;
	}


	@Override
	protected void runStandardCommand() {
			
		parameterMap = new HashMap<String, String>();
		parameterMap.put("css", element);
		runCommandWithParameters();
	}

	@Override
	protected void runCommandWithParameters() {
		
		String[] validParams = new String[]{
				"css", "id", "xpath", "linkText", "className", "name", "tagName"};
		
		int matchingParams = checkNumberOfMatchingParams(validParams);
		
		if (matchingParams != 1) {
			Assert.fail("Click command parameters " + parameterMap + " are not valid. Please specify "
					+ "exactly one of the following params: " + Arrays.toString(validParams));
		}
		
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
			by = By.partialLinkText(parameterMap.get("linkText"));
		}
		
		if (parameterMap.keySet().contains("name")) {
			by = By.name(parameterMap.get("name"));
		}
		
		if (parameterMap.keySet().contains("tagName")) {
			by = By.tagName(parameterMap.get("tagName"));
		}
		
		
		try {
			WebElement elem = browser.findElement(by);
			elem.click();
		} catch (NoSuchElementException e) {
			Assert.fail("No element for selecor " + by + " can be found on the page.");
		}
	}
	

}
