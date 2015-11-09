package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkTextMatchingCommand;


@CommandDescriptor(name = "~checkLinkTextMatching", argumentTypes = {String.class})
public class NegCheckLinkTextMatchingCommand extends CheckLinkTextMatchingCommand {

    public NegCheckLinkTextMatchingCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        assertion.overridingErrorMessage(
                "Expecting link with display text '%s'" + " NOT to exist, but found link text '%s'", expectedLinkText,
                actualLinkText).isNull();
    }


}
