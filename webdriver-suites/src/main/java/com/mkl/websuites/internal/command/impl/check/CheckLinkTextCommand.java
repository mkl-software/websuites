package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkLinkText", argumentTypes = {String.class})
public class CheckLinkTextCommand extends AbstractSingleStringCheck {

	protected String expectedLinkText;
	

	public CheckLinkTextCommand(String expectedLinkText) {
		super();
		this.expectedLinkText = expectedLinkText;
	}

	
	
	@Override
	protected String getStringParam() {
		
		try {
			browser.findElement(By.linkText(expectedLinkText));
		} catch (NoSuchElementException e) {
			return null;
		}
		
		return "OK";
	}

	@Override
	protected void runSingleStringAssertion(StringAssert assertion,
			String string) {
		
		assertion
			.overridingErrorMessage("Expecting link with display text '%s'"
					+ " to exist", expectedLinkText)
			.isNotNull();
	}

}
