package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkSelectedTextContains", argumentTypes = {String.class, String.class})
public class CheckSelectSelectedTextContainsCommand extends CheckSelectSelectedTextCommand {

    public CheckSelectSelectedTextContainsCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public CheckSelectSelectedTextContainsCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class CheckSelectSelectedTextContains extends CheckSelectSelectedText {


        protected boolean predicate(String text) {
            return text.contains(expectedSelectText);
        }


        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String foundText) {

            assertion
                    .overridingErrorMessage(
                            "Expecting selected option containing text '%s' in the SELECT element picked by "
                                    + "selector '%s'", expectedSelectText, by).isNotEmpty();
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectSelectedTextContains();
    }
}
