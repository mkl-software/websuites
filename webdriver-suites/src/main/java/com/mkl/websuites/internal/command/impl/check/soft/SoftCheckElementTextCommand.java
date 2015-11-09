package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementTextCommand;



@CommandDescriptor(name = "softCheckElementText", argumentTypes = {String.class, String.class})
public class SoftCheckElementTextCommand extends CheckElementTextCommand {

    public SoftCheckElementTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckElementTextCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }



    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckElementText() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }

}
