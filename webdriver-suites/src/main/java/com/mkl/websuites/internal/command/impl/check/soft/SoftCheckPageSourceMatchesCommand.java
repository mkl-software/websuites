package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckPageSourceMatchesCommand;


@CommandDescriptor(name = "softCheckPageSourceMatches", argumentTypes = String.class)
public class SoftCheckPageSourceMatchesCommand extends CheckPageSourceMatchesCommand {

    public SoftCheckPageSourceMatchesCommand(String pageSource) {
        super(pageSource);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }

}
