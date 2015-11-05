package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckPageSourceCommand;


@CommandDescriptor(name = "~checkPageSource", argumentTypes = String.class)
public class NegCheckPageSourceCommand extends CheckPageSourceCommand {

    public NegCheckPageSourceCommand(String pageSource) {
        super(pageSource);
    }

    @Override
    protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentPageSource) {

        assertThatUrl.overridingErrorMessage("Page source expected NOT to be '%s'", pageSource)
                .isNotEqualTo(pageSource);
    }

}
