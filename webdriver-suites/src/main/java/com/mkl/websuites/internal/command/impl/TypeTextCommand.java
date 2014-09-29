package com.mkl.websuites.internal.command.impl;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "type", argumentTypes = {String.class, String.class})
public class TypeTextCommand extends BaseCommand {

	
	private String element;
	private String textToType;
	
	
	public TypeTextCommand(String element, String textToType) {
		super();
		this.element = element;
		this.textToType = textToType;
	}


	@Override
	protected void runCommand() {
		try {
			WebElement elem = browser.findElement(By.cssSelector(element));
			elem.sendKeys(textToType);
		} catch (NoSuchElementException e) {
			Assert.fail("No element for selecor " + element + " can be found on the page.");
		}
	}

}
