package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "checkUrl", argumentTypes = String.class)
public class CheckUrlCommand extends AbstractSingleStringCheck {


    protected String expectedUrl;

    public CheckUrlCommand(String url) {
        this.expectedUrl = url;
    }

    @Override
    protected String getStringParam() {
        return browser.getCurrentUrl();
    }

    @Override
    protected void runSingleStringAssertion(StringAssert assertThatUrl, String currentUrl) {

        assertThatUrl.overridingErrorMessage("Page URL expected to be exactly '%s', but the URL was '%s'", expectedUrl,
                currentUrl).isEqualTo(expectedUrl);
    }
}
