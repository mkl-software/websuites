package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.NegCheckElementsCountCommand;


@CommandDescriptor(name = "~softCheckElementsCount", argumentTypes = {String.class, Integer.class})
public class NegSoftCheckElementsCountCommand extends NegCheckElementsCountCommand {

    public NegSoftCheckElementsCountCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckElementsCountCommand(String selector, Integer expectedNumberOfElements) {
        super(selector, expectedNumberOfElements);
    }


    protected AbstractCheck defineCheckLogic() {
        return new NegCheckElementsCount() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
