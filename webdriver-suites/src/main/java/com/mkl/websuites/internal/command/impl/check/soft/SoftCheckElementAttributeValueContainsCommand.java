package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementAttributeValueContainsCommand;


@CommandDescriptor(name = "softCheckElementAttrValueContains", argumentTypes = {String.class, String.class,
        String.class})
public class SoftCheckElementAttributeValueContainsCommand extends CheckElementAttributeValueContainsCommand {

    public SoftCheckElementAttributeValueContainsCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckElementAttributeValueContainsCommand(String selector, String attributeName,
            String expectedAttributeValue) {
        super(selector, attributeName, expectedAttributeValue);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckElementAttrValueContains() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
