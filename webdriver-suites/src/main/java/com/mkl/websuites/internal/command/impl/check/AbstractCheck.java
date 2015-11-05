package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.BaseCommand;

public abstract class AbstractCheck extends BaseCommand {



    /**
     * Template method which decomposes asserting logic to enable elegant way of running soft and
     * negated assertions.
     */
    @Override
    protected void runStandardCommand() {

        Object[] args = getAssertionsParameters();

        AbstractAssert<?, ?> assertion = buildAssertion(args);

        runAssertion(assertion, args);

    }


    protected abstract Object[] getAssertionsParameters();

    /**
     * Overridden by either hard or soft assertion. Could be more elegant unless AssertJ API which
     * isn't consistent for hard and soft assertions...
     * 
     * @param args Params that undergo an assertion
     */
    protected abstract AbstractAssert<?, ?> buildAssertion(Object... args);

    protected abstract void runAssertion(AbstractAssert<?, ?> assertion, Object... args);

    /**
     * Convenience method invoked many times for soft assertions. Again, AssertJ API...
     * 
     * @param args
     * @return
     */
    protected AbstractAssert<?, ?> soft(Object... args) {
        return softly.assertThat((String) args[0]);
    }

}
