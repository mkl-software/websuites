package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.BaseCommand;

public abstract class AbstractCheck extends BaseCommand {

	
	
	@Override
	protected void runStandardCommand() {

		Object[] args = getAssertionsParameters();
		
		AbstractAssert<?, ?> assertion = buildAssertion(args);
		
		runAssertion(assertion, args);
		
	}
	
	
	protected abstract Object[] getAssertionsParameters();

	protected abstract AbstractAssert<?, ?> buildAssertion(Object ... args);
	
	protected abstract void runAssertion(AbstractAssert<?, ?> assertion, Object ... args);

}
