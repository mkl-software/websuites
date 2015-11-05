package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckAttributeValueContainingCommand;


@CommandDescriptor(name = "~softCheckAttributeValueContaining", argumentTypes = {String.class})
public class NegSoftCheckAttributeValueContainingCommand extends NegCheckAttributeValueContainingCommand {

    public NegSoftCheckAttributeValueContainingCommand(String attribute) {
        super(attribute);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }
}
