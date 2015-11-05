package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkHrefCommand;


@CommandDescriptor(name = "softCheckLinkHref", argumentTypes = String.class)
public class SoftCheckLinkHrefCommand extends CheckLinkHrefCommand {

    public SoftCheckLinkHrefCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }
}
