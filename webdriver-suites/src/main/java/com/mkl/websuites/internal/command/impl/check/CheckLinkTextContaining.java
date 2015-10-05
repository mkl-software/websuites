package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;



@CommandDescriptor(name = "checkLinkTextContaining", argumentTypes = {String.class})
public class CheckLinkTextContaining extends CheckLinkTextCommand {

	public CheckLinkTextContaining(String expectedLinkText) {
		super(expectedLinkText);
	}
	
	
	protected String actualLinkText;
	
	
	@Override
	protected String getStringParam() {
		
		try {
			WebElement element = browser.findElement(By.partialLinkText(expectedLinkText));
			actualLinkText = element.getText();
		} catch (NoSuchElementException e) {
			return null;
		}
		return "OK";
	}

	@Override
	protected void runSingleStringAssertion(StringAssert assertion,
			String string) {
		
		assertion
			.overridingErrorMessage("Expecting link containing text '%s'"
					+ " to exist", expectedLinkText)
			.isNotNull();
	}

}
