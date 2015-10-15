package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkSelectedTextMatches", argumentTypes = {String.class, String.class})
public class CheckSelectSelectedTextMatchesCommand extends CheckSelectSelectedTextCommand {

	public CheckSelectSelectedTextMatchesCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public CheckSelectSelectedTextMatchesCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}

	
	protected class CheckSelectSelectedTextMatches extends CheckSelectSelectedText {


		protected boolean predicate(String text) {
			return text.matches(expectedSelectText);
		}


		@Override
		protected void runSingleStringAssertion(StringAssert assertion, String foundText) {
			
			assertion
				.overridingErrorMessage("Expecting selected option matching regexp '%s' in the SELECT element picked by "
						+ "selector '%s'", expectedSelectText, by)
				.isNotEmpty();
		}
	}
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new CheckSelectSelectedTextMatches();
	}
}
