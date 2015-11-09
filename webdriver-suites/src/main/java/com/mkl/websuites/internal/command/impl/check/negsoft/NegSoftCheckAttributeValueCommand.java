package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckAttributeValueCommand;


@CommandDescriptor(name = "~softCheckAttributeValue", argumentTypes = {String.class})
public class NegSoftCheckAttributeValueCommand extends NegCheckAttributeValueCommand {

    public NegSoftCheckAttributeValueCommand(String attribute) {
        super(attribute);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }
}
