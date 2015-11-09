package com.mkl.websuites.internal.command.impl.check.neg;

import java.util.Map;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.CommandUtils;
import com.mkl.websuites.internal.command.impl.check.AbstractCheck;
import com.mkl.websuites.internal.command.impl.check.CheckElementInnerHtmlMatchesCommand;


@CommandDescriptor(name = "~checkElementInnerHTMLMatches", argumentTypes = {String.class, String.class})
public class NegCheckElementInnerHtmlMatchesCommand extends CheckElementInnerHtmlMatchesCommand {

    public NegCheckElementInnerHtmlMatchesCommand(Map<String, String> parameterMap) {
        super(parameterMap);
    }

    public NegCheckElementInnerHtmlMatchesCommand(String selector, String expectedText) {
        super(selector, expectedText);
    }


    protected class NegCheckElementInnerHtmlMatches extends CheckElementInnerHtmlMatches {

        @Override
        protected void runSingleStringAssertion(StringAssert assertion, String elementText) {

            assertion.overridingErrorMessage(
                    "Expecting inner HTML in the web page element with selector '%s'"
                            + " NOT to match regexp '%s', but it was '%s'", by, expectedInnerHTML, elementText)
                    .doesNotMatch(CommandUtils.patternOf(expectedInnerHTML));
        }
    }


    @Override
    protected AbstractCheck defineCheckLogic() {
        return new NegCheckElementInnerHtmlMatches();
    }

}
