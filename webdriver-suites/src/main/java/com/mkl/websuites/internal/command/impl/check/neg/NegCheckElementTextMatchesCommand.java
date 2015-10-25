package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementTextMatchesCommand;
import com.mkl.websuites.internal.command.impl.check.CheckUtils;


@CommandDescriptor(name = "~checkElementTextMatches", argumentTypes = {String.class, String.class})
public class NegCheckElementTextMatchesCommand extends
		CheckElementTextMatchesCommand {

	public NegCheckElementTextMatchesCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegCheckElementTextMatchesCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}
	
	
	protected class NegCheckElementTextMatches extends CheckElementTextMatches {
		
		@Override
		protected void runSingleStringAssertion(StringAssert assertion,
				String elementText) {
			
			assertion
				.overridingErrorMessage("Expecting inner text of web page element with selector '%s'"
						+ " NOT to match regexp '%s', but text was '%s'", by, expectedText, elementText)
				.doesNotMatch(CheckUtils.patternOf(expectedText));
		}
	}
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckElementTextMatches();
	}

}
