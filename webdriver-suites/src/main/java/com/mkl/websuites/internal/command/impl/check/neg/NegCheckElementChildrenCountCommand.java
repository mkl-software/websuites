package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementChildrenCountCommand;


@CommandDescriptor(name = "~checkElementChildrenCount", argumentTypes = {String.class, Integer.class})
public class NegCheckElementChildrenCountCommand extends CheckElementChildrenCountCommand {

    public NegCheckElementChildrenCountCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckElementChildrenCountCommand(String selector, Integer expectedNumberOfElements) {
        super(selector, expectedNumberOfElements);
    }


    protected class NegCheckChildrenCount extends CheckChildrenCount {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String actualNumberOfElements) {

            assertion.overridingErrorMessage(
                    "Expecting number of direct children of element picked by " + "selector '%s' NOT to be %s", by,
                    actualNumberOfElements).isNotEqualTo(expectedNumberOfElements);
        }
    }


    protected AbstractCheck defineCheckLogic() {
        return new NegCheckChildrenCount();
    }
}
