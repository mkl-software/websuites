package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSubelementsCountCommand;


@CommandDescriptor(name = "softCheckSubelementsCount", argumentTypes = {String.class, String.class, Integer.class})
public class SoftCheckSubelementsCountCommand extends
		CheckSubelementsCountCommand {

	public SoftCheckSubelementsCountCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public SoftCheckSubelementsCountCommand(String selector,
			String subElementSelector, Integer expectedNumberOfElements) {
		super(selector, subElementSelector, expectedNumberOfElements);
	}

	
	protected AbstractCheck defineCheckLogic() {
		return new CheckSubelementsCount() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}
}
