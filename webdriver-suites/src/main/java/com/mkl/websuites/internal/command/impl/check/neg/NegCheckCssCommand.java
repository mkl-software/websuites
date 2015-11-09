package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckCssCommand;


@CommandDescriptor(name = "~checkCss", argumentTypes = {String.class, String.class, String.class})
public class NegCheckCssCommand extends CheckCssCommand {

    public NegCheckCssCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckCssCommand(String selector, String cssAttName, String expectedCssValue) {
        super(selector, cssAttName, expectedCssValue);
    }


    protected class NegCheckCss extends CheckCss {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion.overridingErrorMessage(
                    "Expecting CSS attribute '%s' on the web page element with selector '%s'"
                            + " NOT to have value '%s'", cssAttributeName, by, expectedCssAttributeValue).isNotEqualTo(
                    expectedCssAttributeValue);
        }

    }


    protected AbstractCheck defineCheckLogic() {
        return new NegCheckCss();
    }
}
