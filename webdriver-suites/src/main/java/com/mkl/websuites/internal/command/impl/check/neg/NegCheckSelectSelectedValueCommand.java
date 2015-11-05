package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectSelectedValueCommand;


@CommandDescriptor(name = "~checkSelectedValue", argumentTypes = {String.class, String.class})
public class NegCheckSelectSelectedValueCommand extends CheckSelectSelectedValueCommand {

    public NegCheckSelectSelectedValueCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckSelectSelectedValueCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class NegCheckSelectSelectedValue extends CheckSelectSelectedValue {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String string) {

            assertion.overridingErrorMessage(
                    "Expecting option with id '%s' NOT to be selected in the SELECT element picked "
                            + "by selector '%s'", expectedSelectText, by).isEmpty();
        }
    }

    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSelectSelectedValue();
    }
}
