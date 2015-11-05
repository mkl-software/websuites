package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementTextCommand;


@CommandDescriptor(name = "~checkElementText", argumentTypes = {String.class, String.class})
public class NegCheckElementTextCommand extends CheckElementTextCommand {

    public NegCheckElementTextCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckElementTextCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class NegCheckElement extends CheckElementText {


        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion
                    .overridingErrorMessage(
                            "Expecting inner text of web page element with selector '%s'" + " NOT to be '%s'", by,
                            expectedText).isNotEqualTo(expectedText);
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {

        return new NegCheckElement();
    }

}
