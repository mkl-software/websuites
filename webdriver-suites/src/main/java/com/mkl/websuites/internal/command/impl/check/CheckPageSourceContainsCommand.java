package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "checkPageSourceContains", argumentTypes = String.class)
public class CheckPageSourceContainsCommand extends CheckPageSourceCommand {

    public CheckPageSourceContainsCommand(String pageSource) {
        super(pageSource);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentPageSource) {

        assertThatUrl.overridingErrorMessage(
                "Page source expected to contain '%s', but the actual page source was '%s'", pageSource,
                currentPageSource).contains(pageSource);
    }


}
