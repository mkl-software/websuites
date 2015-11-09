package com.mkl.websuites.internal.command.impl.click;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckAttributeValueCommand;


@CommandDescriptor(name = "clickAttributeValue", argumentTypes = {String.class})
public class ClickAttributeValueCommand extends CheckAttributeValueCommand {

    public ClickAttributeValueCommand(String attribute) {
        super(attribute);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {
        super.runSingleStringAssertion(assertion, string);

        elementWithAttribute.click();
    }
}
