package com.mkl.websuites.internal.command.impl.click;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkTextCommand;


@CommandDescriptor(name = "clickLinkText", argumentTypes = {String.class})
public class ClickLinkTextCommand extends CheckLinkTextCommand {

    public ClickLinkTextCommand(String expectedLinkText) {
        super(expectedLinkText);
    }


    @Override
    protected void runAssertion(AbstractAssert<?, ?> assertion, Object... args) {
        super.runAssertion(assertion, args);
        foundElem.click();
    }
}
