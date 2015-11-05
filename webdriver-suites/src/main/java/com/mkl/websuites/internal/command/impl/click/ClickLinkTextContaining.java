package com.mkl.websuites.internal.command.impl.click;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckLinkTextContaining;


@CommandDescriptor(name = "clickLinkTextContaining", argumentTypes = {String.class})
public class ClickLinkTextContaining extends CheckLinkTextContaining {

    public ClickLinkTextContaining(String expectedLinkText) {
        super(expectedLinkText);
    }



    @Override
    protected void runAssertion(AbstractAssert<?, ?> assertion, Object... args) {
        super.runAssertion(assertion, args);
        foundElem.click();
    }
}
