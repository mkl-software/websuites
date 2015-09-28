package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementInnerHtmlContainsCommand;


@CommandDescriptor(name = "softCheckElementInnerHTMLContains", argumentTypes = {String.class, String.class})
public class SoftCheckElementInnerHtmlContainsCommand extends
		CheckElementInnerHtmlContainsCommand {

	public SoftCheckElementInnerHtmlContainsCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public SoftCheckElementInnerHtmlContainsCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}
	
	
	@Override
	protected AbstractCheck defineCheckLogic() {
		return new CheckElementInnerHtmlContains() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}

}
