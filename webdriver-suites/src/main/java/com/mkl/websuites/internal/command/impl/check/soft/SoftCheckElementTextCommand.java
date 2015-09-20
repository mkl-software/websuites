package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementTextCommand;



@CommandDescriptor(name = "softCheckElementText", argumentTypes = {String.class, String.class})
public class SoftCheckElementTextCommand extends CheckElementTextCommand {

	public SoftCheckElementTextCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public SoftCheckElementTextCommand(String selector, String expectedText) {
		super(selector, expectedText);
	}
	
	
	protected class SoftCheckElementText extends CheckElementText {

		public SoftCheckElementText(String elementText) {
			super(elementText);
		}
		
		@Override
		protected AbstractAssert<?, ?> buildAssertion(Object... args) {
			return soft(args);
		}
	}
	
	
	@Override
	protected AbstractCheck defineCheckLogic(WebElement webElement) {
		return new SoftCheckElementText(webElement.getText());
	}

}
