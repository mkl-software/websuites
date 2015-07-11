package com.mkl.websuites.internal.command.impl.click;

import java.util.Map;

import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.navigation.OperationOnWebElement;


@CommandDescriptor(name = "click", argumentTypes = String.class)
public class ClickCommand extends OperationOnWebElement {

	
	
	
	public ClickCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}
	
	public ClickCommand(String elemement) {
		super(null);
		this.element = elemement;
	}

	@Override
	protected void doOperationOnElement(WebElement elem) {

		elem.click();
	}



}
