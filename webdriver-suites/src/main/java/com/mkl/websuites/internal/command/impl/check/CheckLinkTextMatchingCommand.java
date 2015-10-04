package com.mkl.websuites.internal.command.impl.check;

import java.util.List;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkLinkTextMatching", argumentTypes = {String.class})
public class CheckLinkTextMatchingCommand extends CheckLinkTextCommand {

	public CheckLinkTextMatchingCommand(String expectedLinkText) {
		super(expectedLinkText);
	}
	
	
	protected String actualLinkText;
	
	@Override
	protected String getStringParam() {
		
		try {
			List<WebElement> elements = browser.findElements(By.tagName("a"));
			for (WebElement webElement : elements) {
				if (webElement.getText().matches(expectedLinkText)) {
					actualLinkText = webElement.getText();
					return "OK";
				}
			}
		} catch (NoSuchElementException e) {
		}
		return null;
	}
	
	@Override
	protected void runSingleStringAssertion(StringAssert assertion,
			String string) {
		
		assertion
			.overridingErrorMessage("Expecting link with display text matching regexp'%s'"
					+ " to exist", expectedLinkText)
			.isNotNull();
	}

}
