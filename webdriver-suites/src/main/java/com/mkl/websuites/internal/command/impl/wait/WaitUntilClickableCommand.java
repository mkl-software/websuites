package com.mkl.websuites.internal.command.impl.wait;

import java.util.Map;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.OperationOnWebElement;


@CommandDescriptor(name = "waitUntilClickable", argumentTypes = {String.class})
public class WaitUntilClickableCommand extends OperationOnWebElement {

	public WaitUntilClickableCommand(String selector) {
		super(selector);
	}



	public WaitUntilClickableCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}
	
	

	@Override
	protected void doOperationOnElement(WebElement elem) {
		WebDriverWait wait = new WebDriverWait(browser, webElementWaitTimeout);
		wait.until(ExpectedConditions. elementToBeClickable(elem));
	}

}
