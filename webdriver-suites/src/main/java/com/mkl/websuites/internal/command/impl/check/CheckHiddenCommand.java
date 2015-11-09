package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "checkHidden", argumentTypes = {String.class})
public class CheckHiddenCommand extends CheckVisibleCommand {



    public CheckHiddenCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    public CheckHiddenCommand(String selector) {
        super(selector);
    }


    protected class CheckHidden extends AbstractSingleStringCheck {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String displayValue) {

            assertion.overridingErrorMessage(
                    "Expecting web page element with selector '%s'"
                            + " to be have property 'display: none;', but it was '%s'", by, displayValue).isEqualTo(
                    "none");
        }

        @Override
        protected String getStringParam() {
            return foundElement.getCssValue("display");
        }
    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckHidden();
    }


}
