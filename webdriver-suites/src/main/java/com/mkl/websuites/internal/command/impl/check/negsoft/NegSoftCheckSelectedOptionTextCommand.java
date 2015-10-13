package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckSelectedOptionTextCommand;


@CommandDescriptor(name = "~softCheckSelectOptionText", argumentTypes = {String.class, String.class})
public class NegSoftCheckSelectedOptionTextCommand extends
		NegCheckSelectedOptionTextCommand {

	public NegSoftCheckSelectedOptionTextCommand(
			Map<String, String> parameterMap) {
		super(parameterMap);
	}

	public NegSoftCheckSelectedOptionTextCommand(String selector,
			String expectedText) {
		super(selector, expectedText);
	}

	
	protected AbstractCheck defineCheckLogic() {
		return new NegCheckCheckBox() {
			@Override
			protected AbstractAssert<?, ?> buildAssertion(Object... args) {
				return softly.assertThat(args);
			}
		};
	}
}
