package com.mkl.websuites.internal.command.impl.click;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckTextPresentCommand;


@CommandDescriptor(name = "clickText", argumentTypes = String.class)
public class ClickTextCommand extends CheckTextPresentCommand {

    public ClickTextCommand(String text) {
        super(text);
    }


    @Override
    protected void runAssertion(AbstractAssert<?, ?> assertion, Object... args) {
        super.runAssertion(assertion, args);
        foundElem.click();
    }

}
