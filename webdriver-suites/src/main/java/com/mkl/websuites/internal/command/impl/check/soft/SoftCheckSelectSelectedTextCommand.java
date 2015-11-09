package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectSelectedTextCommand;


@CommandDescriptor(name = "softCheckSelectedText", argumentTypes = {String.class, String.class})
public class SoftCheckSelectSelectedTextCommand extends CheckSelectSelectedTextCommand {

    public SoftCheckSelectSelectedTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckSelectSelectedTextCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectSelectedText() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
