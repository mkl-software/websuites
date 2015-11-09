package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkTextCommand;


@CommandDescriptor(name = "softCheckLinkText", argumentTypes = {String.class})
public class SoftCheckLinkTextCommand extends CheckLinkTextCommand {

    public SoftCheckLinkTextCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }

}
