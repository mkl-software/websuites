package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckElementAttributeCommand;


@CommandDescriptor(name = "~softCheckElementAttr", argumentTypes = {String.class, String.class})
public class NegSoftCheckElementAttributeCommand extends NegCheckElementAttributeCommand {

    public NegSoftCheckElementAttributeCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckElementAttributeCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckElementAttr() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
