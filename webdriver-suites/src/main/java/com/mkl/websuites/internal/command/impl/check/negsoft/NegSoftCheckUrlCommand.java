package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckUrlCommand;


@CommandDescriptor(name = "~softCheckUrl", argumentTypes = String.class)
public class NegSoftCheckUrlCommand extends NegCheckUrlCommand {

    public NegSoftCheckUrlCommand(String url) {
        super(url);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }

}
