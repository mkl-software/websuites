package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckCssClassCommand;


@CommandDescriptor(name = "~softCheckCssClass", argumentTypes = {String.class, String.class})
public class NegSoftCheckCssClassCommand extends NegCheckCssClassCommand {

    public NegSoftCheckCssClassCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckCssClassCommand(String selector, String cssClassName) {
        super(selector, cssClassName);
    }


    protected AbstractCheck defineCheckLogic() {
        return new NegCheckCssClass() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
