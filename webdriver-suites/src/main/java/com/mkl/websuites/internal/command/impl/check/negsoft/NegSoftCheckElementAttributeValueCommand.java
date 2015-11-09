package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckElementAttributeValueCommand;


@CommandDescriptor(name = "~softCheckElementAttrValue", argumentTypes = {String.class, String.class, String.class})
public class NegSoftCheckElementAttributeValueCommand extends NegCheckElementAttributeValueCommand {

    public NegSoftCheckElementAttributeValueCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckElementAttributeValueCommand(String selector, String attributeName, String expectedAttributeValue) {
        super(selector, attributeName, expectedAttributeValue);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckElementAttrValue() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
