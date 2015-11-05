package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckElementTextCommand;


@CommandDescriptor(name = "~softCheckElementText", argumentTypes = {String.class, String.class})
public class NegSoftCheckElementTextCommand extends NegCheckElementTextCommand {

    public NegSoftCheckElementTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckElementTextCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckElement() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
