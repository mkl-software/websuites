package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.OperationOnWebElement;


@CommandDescriptor(name = "checkVisible", argumentTypes = {String.class})
public class CheckVisibleCommand extends OperationOnWebElement {


    public CheckVisibleCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    @SuppressWarnings("serial")
    public CheckVisibleCommand(final String selector) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
            }
        });
    }



    protected class CheckVisible extends AbstractSingleStringCheck {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String visibilityValue) {

            assertion.overridingErrorMessage(
                    "Expecting web page element with selector '%s'" + " to be visible, but 'visibility' was set '%s'",
                    by, visibilityValue).isNotEqualTo("hidden");
        }

        @Override
        protected String getStringParam() {
            return foundElement.getCssValue("visibility");
        }
    }



    @Override
    protected void doOperationOnElement(WebElement elem) {

        AbstractCheck checkLogic = defineCheckLogic();

        checkLogic.runStandardCommand();
    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckVisible();
    }


}
