package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckSelectOptionTextMatchingCommand;


@CommandDescriptor(name = "~softCheckSelectOptionTextMatching", argumentTypes = {String.class, String.class})
public class NegSoftCheckSelectOptionTextMatchingCommand extends NegCheckSelectOptionTextMatchingCommand {

    public NegSoftCheckSelectOptionTextMatchingCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckSelectOptionTextMatchingCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSelectOptionTextMatching() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
