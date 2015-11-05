package com.mkl.websuites.internal.command.impl.click;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkHrefCommand;



@CommandDescriptor(name = "clickLinkHref", argumentTypes = String.class)
public class ClickLinkHrefCommand extends CheckLinkHrefCommand {

    public ClickLinkHrefCommand(String expectedLinkText) {
        super(expectedLinkText);
    }

    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {
        super.runSingleStringAssertion(assertion, string);
        actualElement.click();
    }

}
