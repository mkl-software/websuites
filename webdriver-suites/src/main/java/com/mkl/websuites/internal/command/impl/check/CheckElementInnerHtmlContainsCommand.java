package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;



@CommandDescriptor(name = "checkElementInnerHTMLContains", argumentTypes = {String.class, String.class})
public class CheckElementInnerHtmlContainsCommand extends
		CheckElementInnerHtmlCommand {

	public CheckElementInnerHtmlContainsCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public CheckElementInnerHtmlContainsCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}

	
	protected class CheckElementInnerHtmlContains extends CheckElementInnerHTML {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting inner HTML in the web page element with selector '%s'"
						+ " to contain '%s', but it was '%s'", by, expectedInnerHTML, elementText)
				.contains(expectedInnerHTML);
		}
	}
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new CheckElementInnerHtmlContains();
	}
	
}
