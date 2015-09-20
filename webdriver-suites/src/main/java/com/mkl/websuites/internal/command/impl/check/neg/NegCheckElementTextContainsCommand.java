package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementTextContainsCommand;



@CommandDescriptor(name = "~checkElementTextContains", argumentTypes = {String.class, String.class})
public class NegCheckElementTextContainsCommand extends
		CheckElementTextContainsCommand {

	public NegCheckElementTextContainsCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegCheckElementTextContainsCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}
	
	protected class NegCheckElementTextContains extends CheckElementTextContains {

		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting inner text of web page element with selector '%s'"
						+ " NOT to contain '%s', but was '%s'", by, expectedText, elementText)
				.doesNotContain(expectedText);
		}
	}

	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckElementTextContains();
	}
}
