package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.CommandUtils;


@CommandDescriptor(name = "checkElementInnerHTMLMatches", argumentTypes = {String.class, String.class})
public class CheckElementInnerHtmlMatchesCommand extends
		CheckElementInnerHtmlCommand {

	public CheckElementInnerHtmlMatchesCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public CheckElementInnerHtmlMatchesCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}
	
	
	protected class CheckElementInnerHtmlMatches extends CheckElementInnerHTML {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting inner HTML in the web page element with selector '%s'"
						+ " to match regexp '%s', but it was\n%s", by, expectedInnerHTML, elementText)
				.matches(CommandUtils.patternOf(expectedInnerHTML));
		}
	}
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new CheckElementInnerHtmlMatches();
	}

}
