package com.mkl.websuites.internal.command.impl.key;

import java.util.Map;

import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.navigation.OperationOnWebElement;


@CommandDescriptor(name = "type", argumentTypes = {String.class, String.class})
public class TypeTextCommand extends OperationOnWebElement {

	
	private String textToType;
	
	
	public TypeTextCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}


	public TypeTextCommand(String element, String textToType) {
		super(null);
		super.element = element;
		this.textToType = textToType;
	}


	@Override
	protected void doOperationOnElement(WebElement elem) {
		String text = populateStringWithProperties(textToType);
		elem.clear();
		elem.sendKeys(text);
		
	}



}
