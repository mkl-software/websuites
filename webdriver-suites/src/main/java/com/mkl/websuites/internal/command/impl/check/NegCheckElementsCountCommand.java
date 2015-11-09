package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "~checkElementsCount", argumentTypes = {String.class, Integer.class})
public class NegCheckElementsCountCommand extends CheckElementsCountCommand {

    public NegCheckElementsCountCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckElementsCountCommand(String selector, Integer expectedNumberOfElements) {
        super(selector, expectedNumberOfElements);
    }


    protected class NegCheckElementsCount extends CheckElementsCount {


        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String actualNumberOfElements) {

            assertion.overridingErrorMessage("Expecting number of elements picked by selector '%s' NOT to be %s", by,
                    actualNumberOfElements).isNotEqualTo(expectedNumberOfElements);
        }
    }


    protected AbstractCheck defineCheckLogic() {
        return new NegCheckElementsCount();
    }
}
