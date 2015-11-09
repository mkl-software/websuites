package com.mkl.websuites.internal.command.impl.click;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckAttributeValueMatchingCommand;


@CommandDescriptor(name = "clickAttributeValueMatching", argumentTypes = {String.class})
public class ClickAttributeValueMatchingCommand extends CheckAttributeValueMatchingCommand {

    public ClickAttributeValueMatchingCommand(String attribute) {
        super(attribute);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {
        super.runSingleStringAssertion(assertion, string);

        elementWithAttribute.click();
    }
}
