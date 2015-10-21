package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckElementChildrenCountCommand;

@CommandDescriptor(name = "~softCheckElementChildrenCount", argumentTypes = {String.class, Integer.class})
public class NegSoftCheckElementChildrenCountCommand extends
		NegCheckElementChildrenCountCommand {

	public NegSoftCheckElementChildrenCountCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegSoftCheckElementChildrenCountCommand(String selector,
			Integer expectedNumberOfElements) {
		super(selector, expectedNumberOfElements);
	}

	
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckChildrenCount() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return soft(args);
			}
		};
	}
}
