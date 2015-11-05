package com.mkl.websuites.internal.command.impl.check;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.validator.IntegerNumberParamValidator;
import com.mkl.websuites.internal.command.impl.validator.ParameterValueValidator;


@CommandDescriptor(name = "checkSelectedIndex", argumentTypes = {String.class, String.class})
public class CheckSelectSelectedIndexCommand extends CheckSelectSelectedValueCommand {


    public CheckSelectSelectedIndexCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    @SuppressWarnings("serial")
    public CheckSelectSelectedIndexCommand(final String selector, final String expectedText) {
        this(new HashMap<String, String>() {
            {
                put("css", selector);
                put("index", expectedText);
            }
        });
        SELECTED_TEXT_PARAM = "index";
    }

    protected class CheckSelectSelectedIndex extends CheckSelectSelectedValue {


        protected String getOptionString(WebElement elem) {
            return elem.getAttribute("index");
        }


        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String string) {

            assertion
                    .overridingErrorMessage(
                            "Expecting option at index '%s' to be selected  in the SELECT element picked "
                                    + "by selector '%s'", expectedSelectText, by).isNotEmpty();
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectSelectedIndex();
    }



    @Override
    protected List<ParameterValueValidator> defineParameterValueValidators() {

        List<ParameterValueValidator> parentValidators = super.defineParameterValueValidators();

        parentValidators.add(new IntegerNumberParamValidator("index"));

        return parentValidators;
    }
}
