package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementInnerHtmlContainsCommand;



@CommandDescriptor(name = "~checkElementInnerHTMLContains", argumentTypes = {String.class, String.class})
public class NegCheckElementInnerHtmlContainsCommand extends
		CheckElementInnerHtmlContainsCommand {

	public NegCheckElementInnerHtmlContainsCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegCheckElementInnerHtmlContainsCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}

	
	protected class NegCheckElementInnerHtmlContains extends CheckElementInnerHtmlContains {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting inner HTML in the web page element with selector '%s'"
						+ " NOT to contain '%s', but it was '%s'", by, expectedInnerHTML, elementText)
				.doesNotContain(expectedInnerHTML);
		}
	}
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckElementInnerHtmlContains();
	}
}
