package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectSelectedValueCommand;


@CommandDescriptor(name = "softCheckSelectedValue", argumentTypes = {String.class, String.class})
public class SoftCheckSelectSelectedValueCommand extends CheckSelectSelectedValueCommand {

    public SoftCheckSelectSelectedValueCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckSelectSelectedValueCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectSelectedValue() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
