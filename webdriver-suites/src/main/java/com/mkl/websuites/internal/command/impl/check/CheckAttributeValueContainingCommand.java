package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;



@CommandDescriptor(name = "checkAttributeValueContaining", argumentTypes = {String.class})
public class CheckAttributeValueContainingCommand extends CheckAttributeNameCommand {

    public CheckAttributeValueContainingCommand(String attribute) {
        super(attribute);
    }


    @Override
    protected String getPredicateString() {
        return "arguments[0].attributes[i].value != null && "
                + "arguments[0].attributes[i].value.indexOf(arguments[1]) != -1";
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        assertion.overridingErrorMessage(
                "The attribute with value containing '%s' is expected to appear somewhere on the page "
                        + "(in any element)", attribute).isEqualTo("OK");
    }

}
