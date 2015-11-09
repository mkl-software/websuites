package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;



@CommandDescriptor(name = "checkElementTextContains", argumentTypes = {String.class, String.class})
public class CheckElementTextContainsCommand extends CheckElementTextCommand {

    public CheckElementTextContainsCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public CheckElementTextContainsCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class CheckElementTextContains extends CheckElementText {


        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion.overridingErrorMessage(
                    "Expecting inner text of web page element with selector '%s'" + " to contain '%s', but was '%s'",
                    by, expectedText, elementText).contains(expectedText);
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckElementTextContains();
    }

}
