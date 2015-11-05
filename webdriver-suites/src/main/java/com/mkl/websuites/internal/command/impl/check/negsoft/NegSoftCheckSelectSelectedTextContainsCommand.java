package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckSelectSelectedTextContainsCommand;


@CommandDescriptor(name = "~softCheckSelectedTextContains", argumentTypes = {String.class, String.class})
public class NegSoftCheckSelectSelectedTextContainsCommand extends NegCheckSelectSelectedTextContainsCommand {

    public NegSoftCheckSelectSelectedTextContainsCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckSelectSelectedTextContainsCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSelectSelectedTextContains() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
