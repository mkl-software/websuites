package com.mkl.websuites.internal.command.impl.check;

import java.util.Map;

import org.assertj.core.api.StringAssert;
import org.openqa.selenium.WebElement;

import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "checkSelectOptionTextContaining", argumentTypes = {String.class, String.class})
public class CheckSelectOptionTextContainingCommand extends CheckSelectOptionTextCommand {

    public CheckSelectOptionTextContainingCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public CheckSelectOptionTextContainingCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class CheckSelectOptionTextContaining extends AbstractSingleStringCheck {


        @Override
        protected String getStringParam() {
            for (WebElement elem : selectOptions) {
                String text = elem.getText();
                if (text != null && predicate(text)) {
                    return text;
                }
            }
            return "";
        }

        protected boolean predicate(String text) {
            return text.contains(expectedSelectText);
        }



        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String string) {

            assertion.overridingErrorMessage(
                    "Expecting SELECT element picked by selector '%s'"
                            + " to have text containing '%s' in one of its options", by, expectedSelectText)
                    .isNotEmpty();
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new CheckSelectOptionTextContaining();
    }
}
