package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkTextContaining;


@CommandDescriptor(name = "softCheckLinkTextContaining", argumentTypes = {String.class})
public class SoftCheckLinkTextContainsCommand extends CheckLinkTextContaining {

    public SoftCheckLinkTextContainsCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }

}
