package com.mkl.websuites.internal.command.impl;

import java.util.Map;

import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "check", argumentTypes = String.class)
public class CheckCommand extends OperationOnWebElement {

	
	public CheckCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}
	
	public CheckCommand(String elemement) {
		super(null);
		super.element = elemement;
	}

	@Override
	protected void doOperationOnElement(WebElement elem) {
		
		// no operation, just checking if exists without clicking

	}

}
