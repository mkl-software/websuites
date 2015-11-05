package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkHrefCommand;


@CommandDescriptor(name = "~checkLinkHref", argumentTypes = String.class)
public class NegCheckLinkHrefCommand extends CheckLinkHrefCommand {

    public NegCheckLinkHrefCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        assertion.overridingErrorMessage(
                "Expecting link with HREF attribute '%s'" + " NOT to exist, but was found for link with text '%s'",
                expectedLinkText, actualElement != null ? actualElement.getText() : "").isNotEqualTo("OK");
    }
}
