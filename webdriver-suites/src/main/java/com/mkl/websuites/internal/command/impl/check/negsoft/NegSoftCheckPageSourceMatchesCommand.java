package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckPageSourceMatchesCommand;


@CommandDescriptor(name = "~softCheckPageSourceMatches", argumentTypes = String.class)
public class NegSoftCheckPageSourceMatchesCommand extends NegCheckPageSourceMatchesCommand {

    public NegSoftCheckPageSourceMatchesCommand(String pageSource) {
        super(pageSource);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }
}
