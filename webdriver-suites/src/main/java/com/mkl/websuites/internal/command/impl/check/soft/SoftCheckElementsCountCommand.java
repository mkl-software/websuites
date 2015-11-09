package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementsCountCommand;


@CommandDescriptor(name = "softCheckElementsCount", argumentTypes = {String.class, Integer.class})
public class SoftCheckElementsCountCommand extends CheckElementsCountCommand {

    public SoftCheckElementsCountCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckElementsCountCommand(String selector, Integer expectedNumberOfElements) {
        super(selector, expectedNumberOfElements);
    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckElementsCount() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
