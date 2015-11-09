package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementAttributeMatchesCommand;


@CommandDescriptor(name = "softCheckElementAttrValueMatches",
        argumentTypes = {String.class, String.class, String.class})
public class SoftCheckElementAttributeMatchesCommand extends CheckElementAttributeMatchesCommand {

    public SoftCheckElementAttributeMatchesCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckElementAttributeMatchesCommand(String selector, String attributeName, String expectedAttributeValue) {
        super(selector, attributeName, expectedAttributeValue);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckElementAttrValueMatches() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
