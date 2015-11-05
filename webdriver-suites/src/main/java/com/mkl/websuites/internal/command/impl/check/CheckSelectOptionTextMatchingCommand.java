package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkSelectOptionTextMatching", argumentTypes = {String.class, String.class})
public class CheckSelectOptionTextMatchingCommand extends CheckSelectOptionTextContainingCommand {

    public CheckSelectOptionTextMatchingCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public CheckSelectOptionTextMatchingCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class CheckSelectOptionTextMatching extends CheckSelectOptionTextContaining {

        protected boolean predicate(String text) {
            return text.matches(expectedSelectText);
        }

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String string) {

            assertion.overridingErrorMessage(
                    "Expecting SELECT element picked by selector '%s'"
                            + " to have text containing '%s' in one of its options", by, expectedSelectText)
                    .isNotEmpty();
        }
    }

    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectOptionTextMatching();
    }
}
