package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkHrefContainingCommand;


@CommandDescriptor(name = "softCheckLinkHrefContaining", argumentTypes = String.class)
public class SoftCheckLinkHrefContainingCommand extends CheckLinkHrefContainingCommand {

    public SoftCheckLinkHrefContainingCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }
}
