package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckVisibleCommand;


@CommandDescriptor(name = "~checkVisible", argumentTypes = {String.class})
public class NegCheckVisibleCommand extends CheckVisibleCommand {

    public NegCheckVisibleCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckVisibleCommand(String selector) {
        super(selector);
    }


    protected class NegCheckVisible extends CheckVisible {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String visibilityValue) {

            assertion
                    .overridingErrorMessage("Expecting web page element with selector '%s'" + " NOT to be visible", by)
                    .isEqualTo("hidden");
        }

        @Override
        protected String getStringParam() {
            return foundElement.getCssValue("visibility");
        }
    }


    protected AbstractCheck defineCheckLogic() {
        return new NegCheckVisible();
    }
}
