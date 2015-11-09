package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectSelectedIndexCommand;


@CommandDescriptor(name = "~checkSelectedIndex", argumentTypes = {String.class, String.class})
public class NegCheckSelectSelectedIndexCommand extends CheckSelectSelectedIndexCommand {

    public NegCheckSelectSelectedIndexCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckSelectSelectedIndexCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class NegCheckSelectSelectedIndex extends CheckSelectSelectedIndex {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String string) {

            assertion.overridingErrorMessage(
                    "Expecting option at index '%s' NOT to be selected  in the SELECT element picked "
                            + "by selector '%s'", expectedSelectText, by).isEmpty();
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSelectSelectedIndex();
    }
}
