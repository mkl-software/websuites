package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckAttributeValueContainingCommand;



@CommandDescriptor(name = "softCheckAttributeValueContaining", argumentTypes = {String.class})
public class SoftCheckAttributeValueContainingCommand extends CheckAttributeValueContainingCommand {

    public SoftCheckAttributeValueContainingCommand(String attribute) {
        super(attribute);
    }


    @Override
    protected AbstractAssert<?, ?> buildAssertion(Object... args) {
        return soft(args);
    }
}
