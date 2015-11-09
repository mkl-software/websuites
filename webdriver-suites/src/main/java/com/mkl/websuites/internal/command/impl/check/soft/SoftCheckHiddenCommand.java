package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckHiddenCommand;


@CommandDescriptor(name = "softCheckHidden", argumentTypes = {String.class})
public class SoftCheckHiddenCommand extends CheckHiddenCommand {

    public SoftCheckHiddenCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckHiddenCommand(String selector) {
        super(selector);
    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckHidden() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }

}
