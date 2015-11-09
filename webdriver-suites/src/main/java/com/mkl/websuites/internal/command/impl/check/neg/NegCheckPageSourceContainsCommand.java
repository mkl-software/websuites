package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckPageSourceContainsCommand;


@CommandDescriptor(name = "~checkPageSourceContains", argumentTypes = String.class)
public class NegCheckPageSourceContainsCommand extends CheckPageSourceContainsCommand {

    public NegCheckPageSourceContainsCommand(String pageSource) {
        super(pageSource);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentPageSource) {

        assertThatUrl.overridingErrorMessage(
                "Page source expected NOT to contain '%s', but the actual page source was '%s'", pageSource,
                currentPageSource).doesNotContain(pageSource);
    }

}
