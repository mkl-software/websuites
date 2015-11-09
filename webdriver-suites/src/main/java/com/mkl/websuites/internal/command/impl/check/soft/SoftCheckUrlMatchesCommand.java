package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckUrlMatchesCommand;


@CommandDescriptor(name = "softCheckUrlMatches", argumentTypes = String.class)
public class SoftCheckUrlMatchesCommand extends CheckUrlMatchesCommand {

    public SoftCheckUrlMatchesCommand(String url) {
        super(url);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        // TODO Auto-generated method stub
        return soft(args);
    }
}
