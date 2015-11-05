package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckLinkTextCommand;



@CommandDescriptor(name = "~softCheckLinkText", argumentTypes = {String.class})
public class NegSoftCheckLinkTextCommand extends NegCheckLinkTextCommand {

    public NegSoftCheckLinkTextCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }

}
