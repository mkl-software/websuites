package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckPageSourceCommand;


@CommandDescriptor(name = "softCheckPageSource", argumentTypes = String.class)
public class SoftCheckPageSourceCommand extends CheckPageSourceCommand {

    public SoftCheckPageSourceCommand(String pageSource) {
        super(pageSource);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }

}
