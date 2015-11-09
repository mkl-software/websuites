package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckElementTextContainsCommand;


@CommandDescriptor(name = "~softCheckElementTextContains", argumentTypes = {String.class, String.class})
public class NegSoftCheckElementTextContains extends NegCheckElementTextContainsCommand {

    public NegSoftCheckElementTextContains(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckElementTextContains(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckElementTextContains() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
