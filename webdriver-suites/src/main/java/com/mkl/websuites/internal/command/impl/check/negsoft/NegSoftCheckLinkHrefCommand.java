package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckLinkHrefCommand;


@CommandDescriptor(name = "~softCheckLinkHref", argumentTypes = String.class)
public class NegSoftCheckLinkHrefCommand extends NegCheckLinkHrefCommand {

    public NegSoftCheckLinkHrefCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }

}
