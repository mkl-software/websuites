package com.mkl.websuites.internal.command.impl.check.negsoft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckAttributeNameCommand;


@CommandDescriptor(name = "~softCheckAttributeName", argumentTypes = {String.class})
public class NegSoftCheckAttributeNameCommand extends NegCheckAttributeNameCommand {

    public NegSoftCheckAttributeNameCommand(String attribute) {
        super(attribute);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }

}
