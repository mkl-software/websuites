package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckCssClassCommand;


@CommandDescriptor(name = "softCheckCssClass", argumentTypes = {String.class, String.class})
public class SoftCheckCssClassCommand extends CheckCssClassCommand {

    public SoftCheckCssClassCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckCssClassCommand(String selector, String cssClassName) {
        super(selector, cssClassName);
    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckCssClass() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }

}
