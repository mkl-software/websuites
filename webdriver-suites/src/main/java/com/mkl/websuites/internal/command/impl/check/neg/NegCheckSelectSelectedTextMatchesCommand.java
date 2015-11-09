package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectSelectedTextMatchesCommand;


@CommandDescriptor(name = "~checkSelectedTextMatches", argumentTypes = {String.class, String.class})
public class NegCheckSelectSelectedTextMatchesCommand extends CheckSelectSelectedTextMatchesCommand {

    public NegCheckSelectSelectedTextMatchesCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckSelectSelectedTextMatchesCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class NegCheckSelectSelectedTextMatches extends CheckSelectSelectedTextMatches {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String foundText) {

            assertion.overridingErrorMessage(
                    "Expecting selected option NOT to match regexp '%s' in the SELECT "
                            + "element picked by selector '%s', but found selected option '%s'", expectedSelectText,
                    by, foundText).isEmpty();
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSelectSelectedTextMatches();
    }
}
