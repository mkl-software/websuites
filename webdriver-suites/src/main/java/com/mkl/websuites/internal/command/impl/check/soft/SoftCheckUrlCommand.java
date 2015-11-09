package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckUrlCommand;


@CommandDescriptor(name = "softCheckUrl", argumentTypes = String.class)
public class SoftCheckUrlCommand extends CheckUrlCommand {

    public SoftCheckUrlCommand(String url) {
        super(url);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }
}
