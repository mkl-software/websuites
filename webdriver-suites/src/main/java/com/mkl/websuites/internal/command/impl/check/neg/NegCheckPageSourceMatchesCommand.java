package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.CommandUtils;
import com.mkl.websuites.internal.command.impl.check.CheckPageSourceMatchesCommand;


@CommandDescriptor(name = "~checkPageSourceMatches", argumentTypes = String.class)
public class NegCheckPageSourceMatchesCommand extends CheckPageSourceMatchesCommand {

    public NegCheckPageSourceMatchesCommand(String pageSource) {
        super(pageSource);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentPageSource) {

        assertThatUrl.overridingErrorMessage(
                "Page source expected NOT to match '%s', but it does for page source: '%s'", pageSource,
                currentPageSource).doesNotMatch(CommandUtils.patternOf(pageSource));
    }

}
