package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckCheckboxCommand;


@CommandDescriptor(name = "~checkCheckboxSelected", argumentTypes = {String.class})
public class NegCheckCheckboxCommand extends CheckCheckboxCommand {

    public NegCheckCheckboxCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckCheckboxCommand(String selector) {
        super(selector);
    }


    protected class NegCheckCheckBox extends CheckCheckBox {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion.overridingErrorMessage(
                    "Expecting checkbox selected by selector '%s'" + " to be unchecked (not selected)", by)
                    .isNotEqualTo("true");
        }
    }


    protected AbstractCheck defineCheckLogic() {
        return new NegCheckCheckBox();
    }
}
