package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkHrefContainingCommand;

@CommandDescriptor(name = "~checkLinkHrefContaining", argumentTypes = String.class)
public class NegCheckLinkHrefContainingCommand extends CheckLinkHrefContainingCommand {

    public NegCheckLinkHrefContainingCommand(String expectedLinkText) {
        super(expectedLinkText);
    }



    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        String actualHref = actualElement != null ? actualElement.getAttribute("href") : "";
        String actualLinkText = actualElement != null ? actualElement.getText() : "";
        assertion.overridingErrorMessage(
                "Expecting link with HREF attribute containing '%s'"
                        + " NOT to exist, but found href='%s' in the link with text '%s'", expectedLinkText,
                actualHref, actualLinkText).isNull();
    }

}
