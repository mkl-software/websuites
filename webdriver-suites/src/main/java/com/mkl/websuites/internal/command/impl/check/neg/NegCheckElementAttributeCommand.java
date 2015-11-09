package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementAttributeCommand;



@CommandDescriptor(name = "~checkElementAttr", argumentTypes = {String.class, String.class})
public class NegCheckElementAttributeCommand extends CheckElementAttributeCommand {

    public NegCheckElementAttributeCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckElementAttributeCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class NegCheckElementAttr extends CheckElementAttr {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            String found = foundElement != null ? foundElement.getAttribute("outerHTML") : "";

            assertion.overridingErrorMessage(
                    "Expecting attribute '%s' in the web page element with selector '%s'"
                            + " NOT to exist, but found it:\n%s", inputAttributeName, by, found).isNullOrEmpty();
        }

    }

    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckElementAttr();
    }
}
