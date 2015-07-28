package com.mkl.websuites.internal.command.impl.check;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.StringAssert;

public abstract class AbstractSingleStringCheck extends AbstractCheck {

	@Override
	protected Object[] getAssertionsParameters() {
		return new Object[] {getStringParam()};
	}

	protected abstract String getStringParam();

	@Override
	protected AbstractAssert<?, ?> buildAssertion(Object... args) {
		return assertThat((String) args[0]);
	}

	@Override
	protected void runAssertion(AbstractAssert<?, ?> assertion, Object... args) {
		runSingleStringAssertion((StringAssert) assertion, (String) args[0]);
	}

	protected abstract void runSingleStringAssertion(StringAssert assertion, String string);

}
