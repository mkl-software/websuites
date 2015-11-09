package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckUrlMatchesCommand;


@CommandDescriptor(name = "~softCheckUrlMatches", argumentTypes = String.class)
public class NegSoftCheckUrlMatchesCommand extends NegCheckUrlMatchesCommand {

    public NegSoftCheckUrlMatchesCommand(String url) {
        super(url);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }

}
