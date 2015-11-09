package com.mkl.websuites.internal.command.impl.click;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkHrefMatchingCommand;


@CommandDescriptor(name = "clickLinkHrefMatching", argumentTypes = String.class)
public class ClickLinkHrefMatchingCommand extends CheckLinkHrefMatchingCommand {

    public ClickLinkHrefMatchingCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {
        super.runSingleStringAssertion(assertion, string);

        actualElement.click();
    }
}
