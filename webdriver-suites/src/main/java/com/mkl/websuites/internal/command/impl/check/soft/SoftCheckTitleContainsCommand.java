package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckTitleContainsCommand;


@CommandDescriptor(name = "softCheckTitleContains", argumentTypes = String.class)
public class SoftCheckTitleContainsCommand extends CheckTitleContainsCommand {

    public SoftCheckTitleContainsCommand(String titleFragment) {
        super(titleFragment);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }
}
