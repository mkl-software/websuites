package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "checkTitle", argumentTypes = {String.class})
public class CheckTitleCommand extends AbstractSingleStringCheck {


    protected String expectedTitle;


    public CheckTitleCommand(String expectedTitle) {
        super();
        this.expectedTitle = expectedTitle;
    }


    @Override
    protected String getStringParam() {
        return browser.getTitle();
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatTitle, String title) {

        assertThatTitle.overridingErrorMessage("Expecting web page title to be '%s', but was '%s'", expectedTitle,
                title).isEqualTo(expectedTitle);
    }


}
