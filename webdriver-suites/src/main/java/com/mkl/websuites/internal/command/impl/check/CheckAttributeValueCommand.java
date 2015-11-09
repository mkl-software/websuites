package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "checkAttributeValue", argumentTypes = {String.class})
public class CheckAttributeValueCommand extends CheckAttributeNameCommand {

    public CheckAttributeValueCommand(String attribute) {
        super(attribute);
    }

    @Override
    protected String getPredicateString() {
        return "arguments[0].attributes[i].value === arguments[1]";
    }



    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        assertion.overridingErrorMessage(
                "The attribute with value '%s' is expected to appear somewhere on the page " + "(in any element)",
                attribute).isEqualTo("OK");
    }
}
