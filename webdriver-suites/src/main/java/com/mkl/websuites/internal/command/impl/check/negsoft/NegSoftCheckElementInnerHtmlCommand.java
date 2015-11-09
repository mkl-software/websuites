package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckElementInnerHtmlCommand;


@CommandDescriptor(name = "~softCheckElementInnerHTML", argumentTypes = {String.class, String.class})
public class NegSoftCheckElementInnerHtmlCommand extends NegCheckElementInnerHtmlCommand {

    public NegSoftCheckElementInnerHtmlCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckElementInnerHtmlCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckElementInnerHTML() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }

}
