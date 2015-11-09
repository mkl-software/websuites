package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementChildrenCountCommand;


@CommandDescriptor(name = "softCheckElementChildrenCount", argumentTypes = {String.class, Integer.class})
public class SoftCheckElementChildrenCountCommand extends CheckElementChildrenCountCommand {

    public SoftCheckElementChildrenCountCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckElementChildrenCountCommand(String selector, Integer expectedNumberOfElements) {
        super(selector, expectedNumberOfElements);
    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckChildrenCount() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }
}
