package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementAttributeValueCommand;


@CommandDescriptor(name = "softCheckElementAttrValue", argumentTypes = {String.class, String.class, String.class})
public class SoftCheckElementAttributeValueCommand extends CheckElementAttributeValueCommand {

    public SoftCheckElementAttributeValueCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckElementAttributeValueCommand(String selector, String attributeName, String expectedAttributeValue) {
        super(selector, attributeName, expectedAttributeValue);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckElementAttrValue() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }

}
