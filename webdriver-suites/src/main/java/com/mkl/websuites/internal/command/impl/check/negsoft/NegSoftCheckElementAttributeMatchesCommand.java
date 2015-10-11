package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckElementAttributeMatchesCommand;


@CommandDescriptor(name = "~softCheckElementAttrValueMatches", argumentTypes = {String.class, String.class, String.class})
public class NegSoftCheckElementAttributeMatchesCommand extends
		NegCheckElementAttributeMatchesCommand {

	public NegSoftCheckElementAttributeMatchesCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegSoftCheckElementAttributeMatchesCommand(String selector,
			String attributeName, String expectedAttributeValue) {
		super(selector, attributeName, expectedAttributeValue);
	}

	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckElementAttrValueMatches() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}
}
