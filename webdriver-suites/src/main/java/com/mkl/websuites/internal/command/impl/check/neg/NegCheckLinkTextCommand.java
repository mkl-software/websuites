package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkTextCommand;


@CommandDescriptor(name = "~checkLinkText", argumentTypes = {String.class})
public class NegCheckLinkTextCommand extends CheckLinkTextCommand {

    public NegCheckLinkTextCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {

        assertion.overridingErrorMessage("Expecting link with display text '%s'" + " NOT to exist", expectedLinkText)
                .isNull();
    }

}
