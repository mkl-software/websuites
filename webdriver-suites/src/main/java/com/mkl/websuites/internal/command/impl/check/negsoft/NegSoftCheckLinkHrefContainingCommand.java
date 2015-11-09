package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckLinkHrefContainingCommand;


@CommandDescriptor(name = "~softCheckLinkHrefContaining", argumentTypes = String.class)
public class NegSoftCheckLinkHrefContainingCommand extends NegCheckLinkHrefContainingCommand {

    public NegSoftCheckLinkHrefContainingCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }
}
