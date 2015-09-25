package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckElementTextMatchesCommand;


@CommandDescriptor(name = "~softCheckElementTextMatches", argumentTypes = {String.class, String.class})
public class NegSoftCheckElementTextMatchesCommand extends
		NegCheckElementTextMatchesCommand {

	public NegSoftCheckElementTextMatchesCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegSoftCheckElementTextMatchesCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckElementTextMatches() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}

}
