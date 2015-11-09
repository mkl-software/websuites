package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckSelectSelectedValueCommand;


@CommandDescriptor(name = "~softCheckSelectedValue", argumentTypes = {String.class, String.class})
public class NegSoftCheckSelectSelectedValueCommand extends NegCheckSelectSelectedValueCommand {

    public NegSoftCheckSelectSelectedValueCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckSelectSelectedValueCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSelectSelectedValue() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
