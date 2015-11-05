package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckHeaderContainsCommand;


@CommandDescriptor(name = "softCheckHeaderContains", argumentTypes = {String.class})
public class SoftCheckHeaderContainsCommand extends CheckHeaderContainsCommand {

    public SoftCheckHeaderContainsCommand(String expectedHeader) {
        super(expectedHeader);
    }



    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }

}
