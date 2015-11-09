package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementTextContainsCommand;



@CommandDescriptor(name = "softCheckElementTextContains", argumentTypes = {String.class, String.class})
public class SoftCheckElementTextContainsCommand extends CheckElementTextContainsCommand {

    public SoftCheckElementTextContainsCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckElementTextContainsCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }

    @Override
    protected AbstractCheck defineCheckLogic() {

        return new CheckElementTextContains() {

            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
