package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "~softCheckLinkTextContaining", argumentTypes = {String.class})
public class NegSoftCheckLinkTextContainsCommand extends NegCheckLinkTextContainsCommand {

    public NegSoftCheckLinkTextContainsCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }

}
