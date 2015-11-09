package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckAttributeNameCommand;


@CommandDescriptor(name = "~checkAttributeName", argumentTypes = {String.class})
public class NegCheckAttributeNameCommand extends CheckAttributeNameCommand {

    public NegCheckAttributeNameCommand(String attribute) {
        super(attribute);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        String elemDesc = elementWithAttribute != null ? elementWithAttribute.getAttribute("outerHTML") : "";

        assertion.overridingErrorMessage(
                "The attribute with name '%s' is expected NOT to appear in any element on the page "
                        + "but was found in the fragmet '%s'", attribute, elemDesc).isNull();
    }

}
