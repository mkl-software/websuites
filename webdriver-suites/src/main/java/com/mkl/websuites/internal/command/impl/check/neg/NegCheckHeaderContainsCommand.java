package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckHeaderContainsCommand;


@CommandDescriptor(name = "~checkHeaderContains", argumentTypes = {String.class})
public class NegCheckHeaderContainsCommand extends CheckHeaderContainsCommand {

    public NegCheckHeaderContainsCommand(String expectedHeader) {
        super(expectedHeader);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatHeader, String title) {

        assertThatHeader.overridingErrorMessage("Expecting web page header NOT to contain '%s', but was '%s'",
                expectedHeader, title).doesNotContain(expectedHeader);
    }
}
