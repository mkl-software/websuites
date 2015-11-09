package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.CommandUtils;


@CommandDescriptor(name = "checkPageSourceMatches", argumentTypes = String.class)
public class CheckPageSourceMatchesCommand extends CheckPageSourceCommand {

    public CheckPageSourceMatchesCommand(String pageSource) {
        super(pageSource);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentPageSource) {

        assertThatUrl.overridingErrorMessage(
                "Page source expected to match '%s', but the actual page source was\n'%s'", pageSource,
                currentPageSource).matches(CommandUtils.patternOf(pageSource));
    }

}
