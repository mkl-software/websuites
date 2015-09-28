package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementInnerHtmlCommand;


@CommandDescriptor(name = "~checkElementInnerHTML", argumentTypes = {String.class, String.class})
public class NegCheckElementInnerHtmlCommand extends
		CheckElementInnerHtmlCommand {

	public NegCheckElementInnerHtmlCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegCheckElementInnerHtmlCommand(String selector, String expectedText) {
		super(selector, expectedText);
	}
	
	
	protected class NegCheckElementInnerHTML extends CheckElementInnerHTML {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting inner HTML in the web page element with selector '%s'"
						+ " NOT to be '%s'", by, expectedInnerHTML)
				.isNotEqualTo(expectedInnerHTML);
		}
	}
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckElementInnerHTML();
	}

}
