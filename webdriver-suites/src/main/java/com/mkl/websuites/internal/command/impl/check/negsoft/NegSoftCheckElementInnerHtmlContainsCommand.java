package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckElementInnerHtmlContainsCommand;


@CommandDescriptor(name = "~softCheckElementInnerHTMLContains", argumentTypes = {String.class, String.class})
public class NegSoftCheckElementInnerHtmlContainsCommand extends NegCheckElementInnerHtmlContainsCommand {

    public NegSoftCheckElementInnerHtmlContainsCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckElementInnerHtmlContainsCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckElementInnerHtmlContains() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }

}
