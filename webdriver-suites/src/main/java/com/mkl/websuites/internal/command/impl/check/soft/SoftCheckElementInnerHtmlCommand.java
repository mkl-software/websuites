package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementInnerHtmlCommand;


@CommandDescriptor(name = "softCheckElementInnerHTML", argumentTypes = {String.class, String.class})
public class SoftCheckElementInnerHtmlCommand extends CheckElementInnerHtmlCommand {

    public SoftCheckElementInnerHtmlCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckElementInnerHtmlCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckElementInnerHTML() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }

}
