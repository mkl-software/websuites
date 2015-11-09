package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "checkLinkHrefContaining", argumentTypes = String.class)
public class CheckLinkHrefContainingCommand extends CheckLinkHrefCommand {

    public CheckLinkHrefContainingCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected boolean predicate(String href) {
        return href == null ? false : href.contains(expectedLinkText);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        assertion.overridingErrorMessage("Expecting link with HREF attribute containing '%s'" + " to exist",
                expectedLinkText).isEqualTo("OK");
    }
}
