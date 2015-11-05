package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckElementSiblingCountCommand;


@CommandDescriptor(name = "~softCheckElementSiblingCount", argumentTypes = {String.class, Integer.class})
public class NegSoftCheckElementSiblingCountCommand extends NegCheckElementSiblingCountCommand {

    public NegSoftCheckElementSiblingCountCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckElementSiblingCountCommand(String selector, Integer expectedNumberOfElements) {
        super(selector, expectedNumberOfElements);
    }


    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSiblingCount() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
