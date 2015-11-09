package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkHrefMatchingCommand;


@CommandDescriptor(name = "softCheckLinkHrefMatching", argumentTypes = String.class)
public class SoftCheckLinkHrefMatchingCommand extends CheckLinkHrefMatchingCommand {

    public SoftCheckLinkHrefMatchingCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }
}
