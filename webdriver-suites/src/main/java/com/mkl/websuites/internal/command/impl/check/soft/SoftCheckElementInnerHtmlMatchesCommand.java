package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementInnerHtmlMatchesCommand;


@CommandDescriptor(name = "softCheckElementInnerHTMLMatches", argumentTypes = {String.class, String.class})
public class SoftCheckElementInnerHtmlMatchesCommand extends CheckElementInnerHtmlMatchesCommand {

    public SoftCheckElementInnerHtmlMatchesCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckElementInnerHtmlMatchesCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckElementInnerHtmlMatches() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
