package com.mkl.websuites.internal.command.impl.click;

import org.assertj.core.api.AbstractAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckTextMatchingCommand;


@CommandDescriptor(name = "clickTextMatching", argumentTypes = String.class)
public class ClickTextMatchingCommand extends CheckTextMatchingCommand {

    public ClickTextMatchingCommand(String regex) {
        super(regex);
    }

    @Override
    protected void runAssertion(AbstractAssert<?, ?> assertion, Object... args) {
        super.runAssertion(assertion, args);
        foundElem.click();
    }

}
