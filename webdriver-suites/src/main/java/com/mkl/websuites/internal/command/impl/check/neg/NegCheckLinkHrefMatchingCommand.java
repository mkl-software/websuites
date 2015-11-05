package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkHrefMatchingCommand;


@CommandDescriptor(name = "~checkLinkHrefMatching", argumentTypes = String.class)
public class NegCheckLinkHrefMatchingCommand extends CheckLinkHrefMatchingCommand {

    public NegCheckLinkHrefMatchingCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        String actualHref = actualElement != null ? actualElement.getAttribute("href") : "";
        String actualLinkText = actualElement != null ? actualElement.getText() : "";

        assertion.overridingErrorMessage(
                "Expecting link with HREF attribute matching regexp '%s'"
                        + " NOT to exist, but found href='%s' in the link with text '%s'", expectedLinkText,
                actualHref, actualLinkText).isNull();
    }
}
