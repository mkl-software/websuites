package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectSelectedTextContainsCommand;


@CommandDescriptor(name = "~checkSelectedTextContains", argumentTypes = {String.class, String.class})
public class NegCheckSelectSelectedTextContainsCommand extends CheckSelectSelectedTextContainsCommand {

    public NegCheckSelectSelectedTextContainsCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckSelectSelectedTextContainsCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class NegCheckSelectSelectedTextContains extends CheckSelectSelectedTextContains {


        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String foundText) {

            assertion.overridingErrorMessage(
                    "Expecting selected option NOT to contain text '%s' in the SELECT "
                            + "element picked by selector '%s', but found selected option '%s'", expectedSelectText,
                    by, foundText).isEmpty();
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSelectSelectedTextContains();
    }
}
