package com.mkl.websuites.internal.command.impl.check.soft;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementTextMatchesCommand;


@CommandDescriptor(name = "softCheckElementTextMatches", argumentTypes = {String.class, String.class})
public class SoftCheckElementTextMatchesCommand extends CheckElementTextMatchesCommand {

    public SoftCheckElementTextMatchesCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public SoftCheckElementTextMatchesCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckElementTextMatches() {
            @Override
            protected AbstractAssert<?, ?> buildAssertion(Object... args) {
                return soft(args);
            }
        };
    }

}
