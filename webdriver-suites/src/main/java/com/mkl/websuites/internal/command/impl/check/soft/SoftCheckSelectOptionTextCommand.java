package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectOptionTextCommand;



@CommandDescriptor(name = "softCheckSelectOptionText", argumentTypes = {String.class, String.class})
public class SoftCheckSelectOptionTextCommand extends CheckSelectOptionTextCommand {

    public SoftCheckSelectOptionTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckSelectOptionTextCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectOptionText() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return softly.assertThat(args);
            }
        };
    }
}
