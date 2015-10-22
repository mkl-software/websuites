package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementSiblingCountCommand;


@CommandDescriptor(name = "softCheckElementSiblingCount", argumentTypes = {String.class, Integer.class})
public class SoftCheckElementSiblingCountCommand extends
		CheckElementSiblingCountCommand {

	public SoftCheckElementSiblingCountCommand(Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public SoftCheckElementSiblingCountCommand(String selector,
			Integer expectedNumberOfElements) {
		super(selector, expectedNumberOfElements);
	}

	
	protected AbstractCheck defineCheckLogic() {
		return new CheckSiblingCount() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}
}
