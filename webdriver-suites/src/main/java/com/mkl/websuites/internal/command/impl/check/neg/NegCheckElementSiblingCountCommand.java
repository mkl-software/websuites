package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementSiblingCountCommand;


@CommandDescriptor(name = "~checkElementSiblingCount", argumentTypes = {String.class, Integer.class})
public class NegCheckElementSiblingCountCommand extends CheckElementSiblingCountCommand {

    public NegCheckElementSiblingCountCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckElementSiblingCountCommand(String selector, Integer expectedNumberOfElements) {
        super(selector, expectedNumberOfElements);
    }


    protected class NegCheckSiblingCount extends CheckSiblingCount {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String actualNumberOfElements) {

            assertion.overridingErrorMessage(
                    "Expecting number of siblings of element picked by " + "selector '%s' NOT to be %s", by,
                    actualNumberOfElements).isNotEqualTo(expectedNumberOfElements);
        }
    }


    protected AbstractCheck defineCheckLogic() {
        return new NegCheckSiblingCount();
    }
}
