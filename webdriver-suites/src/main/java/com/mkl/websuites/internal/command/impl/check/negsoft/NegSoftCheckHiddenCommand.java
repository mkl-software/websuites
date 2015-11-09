package com.mkl.websuites.internal.command.impl.check.negsoft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.neg.NegCheckHiddenCommand;


@CommandDescriptor(name = "~softCheckHidden", argumentTypes = {String.class})
public class NegSoftCheckHiddenCommand extends NegCheckHiddenCommand {

    public NegSoftCheckHiddenCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegSoftCheckHiddenCommand(String selector) {
        super(selector);
    }


    protected AbstractCheck defineCheckLogic() {
        return new NegCheckHidden() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }

}
