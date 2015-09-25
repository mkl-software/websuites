package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkElementTextMatches", argumentTypes = {String.class, String.class})
public class CheckElementTextMatchesCommand extends CheckElementTextCommand {

	public CheckElementTextMatchesCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public CheckElementTextMatchesCommand(String selector, String expectedText) {
		super(selector, expectedText);
	}

	
	protected class CheckElementTextMatches extends CheckElementText {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting inner text of web page element with selector '%s'"
						+ " to match regexp '%s', but text was '%s'", by, expectedText, elementText)
				.matches(expectedText);
		}
	}
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new CheckElementTextMatches();
	}
}
