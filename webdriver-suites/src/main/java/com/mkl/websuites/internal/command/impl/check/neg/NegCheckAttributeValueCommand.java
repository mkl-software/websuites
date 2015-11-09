package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckAttributeValueCommand;


@CommandDescriptor(name = "~checkAttributeValue", argumentTypes = {String.class})
public class NegCheckAttributeValueCommand extends CheckAttributeValueCommand {

    public NegCheckAttributeValueCommand(String attribute) {
        super(attribute);
    }

    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        String elemDesc = elementWithAttribute != null ? elementWithAttribute.getAttribute("outerHTML") : "";

        assertion.overridingErrorMessage(
                "The attribute with value '%s' is expected NOT to appear in any element on the page "
                        + "but was found in the fragmet '%s'", attribute, elemDesc).isNull();
    }
}
