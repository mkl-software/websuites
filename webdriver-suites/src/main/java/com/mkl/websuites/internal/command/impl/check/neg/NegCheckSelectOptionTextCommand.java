package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.ObjectArrayAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckSelectOptionTextCommand;


@CommandDescriptor(name = "~checkSelectOptionText", argumentTypes = {String.class, String.class})
public class NegCheckSelectOptionTextCommand extends CheckSelectOptionTextCommand {

    public NegCheckSelectOptionTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckSelectOptionTextCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class NegCheckSelectOptionText extends CheckSelectOptionText {

        @Override
        protected void runAssertion(AbstractAssert<?, ?> assertion, Object... args) {

            ((ObjectArrayAssert<?>) assertion)
                    .extracting("text")
                    .overridingErrorMessage(
                            "Expecting SELECT element picked by selector '%s'" + " NOT to have option with text '%s'",
                            by, expectedSelectText).doesNotContain(expectedSelectText);
        }
    }


    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSelectOptionText();
    }

}
