package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckUrlCommand;


@CommandDescriptor(name = "~checkUrl", argumentTypes = String.class)
public class NegCheckUrlCommand extends CheckUrlCommand {

    public NegCheckUrlCommand(String url) {
        super(url);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentUrl) {

        assertThatUrl.overridingErrorMessage("Page URL expected NOT to be '%s'", expectedUrl).isNotEqualTo(expectedUrl);
    }

}
