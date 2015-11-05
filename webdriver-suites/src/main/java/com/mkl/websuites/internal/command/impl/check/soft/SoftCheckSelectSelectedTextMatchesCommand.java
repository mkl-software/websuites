package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectSelectedTextMatchesCommand;


@CommandDescriptor(name = "softCheckSelectedTextMatches", argumentTypes = {String.class, String.class})
public class SoftCheckSelectSelectedTextMatchesCommand extends CheckSelectSelectedTextMatchesCommand {

    public SoftCheckSelectSelectedTextMatchesCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckSelectSelectedTextMatchesCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectSelectedTextMatches() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
