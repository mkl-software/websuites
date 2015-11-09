package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.ListAssert;

import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckTextPresentCommand;


@CommandDescriptor(name = "~checkText", argumentTypes = String.class)
public class NegCheckTextPresentCommand extends CheckTextPresentCommand {

    public NegCheckTextPresentCommand(String text) {
        super(text);
    }


    @Override
    protected void runAssertion(AbstractAssert<?, ?> assertion, Object... args) {

        ((ListAssert<?>) assertion).overridingErrorMessage("Text '%s' was found on the page, but was not expected",
                text).isEmpty();
    }

}
