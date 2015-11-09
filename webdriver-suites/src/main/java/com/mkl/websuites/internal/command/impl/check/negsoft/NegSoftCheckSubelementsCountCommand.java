package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckSubelementsCountCommand;


@CommandDescriptor(name = "~softCheckSubelementsCount", argumentTypes = {String.class, String.class, Integer.class})
public class NegSoftCheckSubelementsCountCommand extends NegCheckSubelementsCountCommand {

    public NegSoftCheckSubelementsCountCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckSubelementsCountCommand(String selector, String subElementSelector,
            Integer expectedNumberOfElements) {
        super(selector, subElementSelector, expectedNumberOfElements);
    }

    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSubelementsCount() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
