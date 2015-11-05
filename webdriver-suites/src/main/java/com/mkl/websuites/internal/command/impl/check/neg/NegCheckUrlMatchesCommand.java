package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.CommandUtils;
import com.mkl.websuites.internal.command.impl.check.CheckUrlMatchesCommand;


@CommandDescriptor(name = "~checkUrlMatches", argumentTypes = String.class)
public class NegCheckUrlMatchesCommand extends CheckUrlMatchesCommand {

    public NegCheckUrlMatchesCommand(String url) {
        super(url);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentUrl) {

        assertThatUrl.overridingErrorMessage("Page URL expected NOT to match regex '%s', but the URL was '%s'",
                expectedUrl, currentUrl).doesNotMatch(CommandUtils.patternOf(expectedUrl));
    }

}
