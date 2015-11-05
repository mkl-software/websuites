package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkSelectedValue", argumentTypes = {String.class, String.class})
public class CheckSelectSelectedValueCommand extends CheckSelectSelectedTextCommand {



    public CheckSelectSelectedValueCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    @SuppressWarnings("serial")
    public CheckSelectSelectedValueCommand(final String selector, final String expectedText) {
        this(new HashMap<String, String>() {
            {
                put("css", selector);
                put("value", expectedText);
            }
        });
        SELECTED_TEXT_PARAM = "value";
    }


    protected class CheckSelectSelectedValue extends CheckSelectSelectedText {


        protected String getOptionString(WebElement elem) {
            return elem.getAttribute("value");
        }


        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String string) {

            assertion.overridingErrorMessage(
                    "Expecting option with id '%s' to be selected  in the SELECT element picked " + "by selector '%s'",
                    expectedSelectText, by).isNotEmpty();
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectSelectedValue();
    }

}
