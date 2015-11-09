package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.CommandUtils;
import com.mkl.websuites.internal.command.impl.check.CheckHeaderMatchesCommand;


@CommandDescriptor(name = "~checkHeaderMatches", argumentTypes = {String.class})
public class NegCheckHeaderMatchesCommand extends CheckHeaderMatchesCommand {

    public NegCheckHeaderMatchesCommand(String expectedHeader) {
        super(expectedHeader);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatHeader, String header) {

        assertThatHeader.overridingErrorMessage("Expecting web page header NOT to match regexp '%s', but was '%s'",
                expectedHeader, header).doesNotMatch(CommandUtils.patternOf(expectedHeader));
    }
}
