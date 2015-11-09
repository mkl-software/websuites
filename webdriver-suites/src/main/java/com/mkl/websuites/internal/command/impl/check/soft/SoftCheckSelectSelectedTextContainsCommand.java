package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectSelectedTextContainsCommand;


@CommandDescriptor(name = "softCheckSelectedTextContains", argumentTypes = {String.class, String.class})
public class SoftCheckSelectSelectedTextContainsCommand extends CheckSelectSelectedTextContainsCommand {

    public SoftCheckSelectSelectedTextContainsCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckSelectSelectedTextContainsCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectSelectedTextContains() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }

}
