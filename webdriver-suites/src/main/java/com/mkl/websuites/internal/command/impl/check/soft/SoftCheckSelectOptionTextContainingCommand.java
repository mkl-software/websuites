package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectOptionTextContainingCommand;


@CommandDescriptor(name = "softCheckSelectOptionTextContaining", argumentTypes = {String.class, String.class})
public class SoftCheckSelectOptionTextContainingCommand extends CheckSelectOptionTextContainingCommand {

    public SoftCheckSelectOptionTextContainingCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckSelectOptionTextContainingCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }



    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectOptionTextContaining() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
