package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectSelectedTextCommand;


@CommandDescriptor(name = "~checkSelectedText", argumentTypes = {String.class, String.class})
public class NegCheckSelectSelectedTextCommand extends CheckSelectSelectedTextCommand {

    public NegCheckSelectSelectedTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckSelectSelectedTextCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }

    protected class NegCheckSelectSelectedText extends CheckSelectSelectedText {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String string) {

            assertion.overridingErrorMessage(
                    "Expecting option '%s' NOT to be selected in the SELECT element picked " + "by selector '%s'",
                    expectedSelectText, by).isEmpty();
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSelectSelectedText();
    }

}
