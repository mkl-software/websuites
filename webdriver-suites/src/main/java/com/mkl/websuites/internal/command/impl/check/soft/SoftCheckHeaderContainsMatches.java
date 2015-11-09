package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckHeaderMatchesCommand;


@CommandDescriptor(name = "softCheckHeaderMatches", argumentTypes = {String.class})
public class SoftCheckHeaderContainsMatches extends CheckHeaderMatchesCommand {

    public SoftCheckHeaderContainsMatches(String expectedHeader) {
        super(expectedHeader);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }
}
