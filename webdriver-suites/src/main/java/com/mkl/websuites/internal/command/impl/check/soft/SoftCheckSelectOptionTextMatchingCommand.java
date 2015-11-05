package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectOptionTextMatchingCommand;


@CommandDescriptor(name = "softCheckSelectOptionTextMatching", argumentTypes = {String.class, String.class})
public class SoftCheckSelectOptionTextMatchingCommand extends CheckSelectOptionTextMatchingCommand {

    public SoftCheckSelectOptionTextMatchingCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckSelectOptionTextMatchingCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectOptionTextMatching() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
