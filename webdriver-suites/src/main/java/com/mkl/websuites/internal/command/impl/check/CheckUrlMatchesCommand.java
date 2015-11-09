package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.CommandUtils;


@CommandDescriptor(name = "checkUrlMatches", argumentTypes = String.class)
public class CheckUrlMatchesCommand extends CheckUrlCommand {

    public CheckUrlMatchesCommand(String url) {
        super(url);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentUrl) {

        assertThatUrl.overridingErrorMessage("Page URL expected to match regex '%s', but the URL was '%s'",
                expectedUrl, currentUrl).matches(CommandUtils.patternOf(expectedUrl));
    }

}
