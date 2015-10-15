package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckSelectSelectedTextMatchesCommand;


@CommandDescriptor(name = "~softCheckSelectedTextMatches", argumentTypes = {String.class, String.class})
public class NegSoftCheckSelectSelectedTextMatchesCommand extends
		NegCheckSelectSelectedTextMatchesCommand {

	public NegSoftCheckSelectSelectedTextMatchesCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegSoftCheckSelectSelectedTextMatchesCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}


	@Override
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckSelectSelectedTextMatches() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}
}
