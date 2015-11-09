package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckLinkHrefMatchingCommand;



@CommandDescriptor(name = "~softCheckLinkHrefMatching", argumentTypes = String.class)
public class NegSoftCheckLinkHrefMatchingCommand extends NegCheckLinkHrefMatchingCommand {

    public NegSoftCheckLinkHrefMatchingCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }
}
