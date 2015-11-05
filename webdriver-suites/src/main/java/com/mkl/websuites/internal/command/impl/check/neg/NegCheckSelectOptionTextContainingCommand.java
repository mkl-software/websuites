package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectOptionTextContainingCommand;


@CommandDescriptor(name = "~checkSelectOptionTextContaining", argumentTypes = {String.class, String.class})
public class NegCheckSelectOptionTextContainingCommand extends CheckSelectOptionTextContainingCommand {

    public NegCheckSelectOptionTextContainingCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckSelectOptionTextContainingCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class NegCheckSelectOptionTextContaining extends CheckSelectOptionTextContaining {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String foundOption) {

            assertion.overridingErrorMessage(
                    "Expecting SELECT element picked by selector '%s'"
                            + " NOT to have option containing text '%s', but found option '%s'", by,
                    expectedSelectText, foundOption).isEmpty();
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSelectOptionTextContaining();
    }
}
