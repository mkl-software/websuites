package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckElementInnerHtmlMatchesCommand;


@CommandDescriptor(name = "~softCheckElementInnerHTMLMatches", argumentTypes = {String.class, String.class})
public class NegSoftCheckElementInnerHtmlMatchesCommand extends
		NegCheckElementInnerHtmlMatchesCommand {

	public NegSoftCheckElementInnerHtmlMatchesCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegSoftCheckElementInnerHtmlMatchesCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckElementInnerHtmlMatches() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}

}
