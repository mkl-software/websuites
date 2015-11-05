package com.mkl.websuites.internal.command.impl.click;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkTextMatchingCommand;


@CommandDescriptor(name = "clickLinkTextMatching", argumentTypes = {String.class})
public class ClickLinkTextMatchingCommand extends CheckLinkTextMatchingCommand {

    public ClickLinkTextMatchingCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertion, String string) {
        super.runSingleStringAssertion(assertion, string);
        foundElem.click();
    }
}