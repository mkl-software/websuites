package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectOptionTextMatchingCommand;


@CommandDescriptor(name = "~checkSelectOptionTextMatching", argumentTypes = {String.class, String.class})
public class NegCheckSelectOptionTextMatchingCommand extends CheckSelectOptionTextMatchingCommand {

    public NegCheckSelectOptionTextMatchingCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckSelectOptionTextMatchingCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class NegCheckSelectOptionTextMatching extends CheckSelectOptionTextMatching {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String foundOption) {

            assertion.overridingErrorMessage(
                    "Expecting SELECT element picked by selector '%s'"
                            + " NOT to have option matching regexp '%s', but found option '%s'", by,
                    expectedSelectText, foundOption).isEmpty();
        }
    }

    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSelectOptionTextMatching();
    }
}
