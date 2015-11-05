package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckCheckboxCommand;


@CommandDescriptor(name = "~softCheckCheckboxSelected", argumentTypes = {String.class})
public class NegSoftCheckCheckboxCommand extends NegCheckCheckboxCommand {

    public NegSoftCheckCheckboxCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckCheckboxCommand(String selector) {
        super(selector);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckCheckBox() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
