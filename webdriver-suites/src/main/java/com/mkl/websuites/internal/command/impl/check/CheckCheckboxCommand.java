package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.OperationOnWebElement;



@CommandDescriptor(name = "checkCheckboxSelected", argumentTypes = {String.class})
public class CheckCheckboxCommand extends OperationOnWebElement {


    protected String actualCheckedValue;
    private String CHECKED_ATTRIBUTE = "checked";



    public CheckCheckboxCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }


    @SuppressWarnings("serial")
    public CheckCheckboxCommand(final String selector) {
        super(new HashMap<String, String>() {
            {
                put("css", selector);
            }
        });
    }



    protected class CheckCheckBox extends AbstractSingleStringCheck {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion.overridingErrorMessage(
                    "Expecting checkbox selected by selector '%s'" + " to be checked (selected)", by)
                    .isEqualToIgnoringCase("true");
        }

        @Override
        protected String getStringParam() {
            return actualCheckedValue;
        }
    }



    @Override
    protected void doOperationOnElement(WebElement elem) {

        if (!(elem.getTagName().equalsIgnoreCase("input") && elem.getAttribute("type").equalsIgnoreCase("checkbox"))) {
            fail("Element expected to be a checkbox");
        }

        actualCheckedValue = elem.getAttribute(CHECKED_ATTRIBUTE);

        actualCheckedValue = actualCheckedValue == null ? "false" : actualCheckedValue;

        AbstractCheck checkLogic = defineCheckLogic();

        checkLogic.runStandardCommand();


    }


    protected AbstractCheck defineCheckLogic() {
        return new CheckCheckBox();
    }


}
