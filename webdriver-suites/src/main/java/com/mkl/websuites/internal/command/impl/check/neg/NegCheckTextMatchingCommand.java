package com.mkl.websuites.internal.command.impl.check.neg;

import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.BooleanAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.check.CheckTextMatchingCommand;


/**
 * See limitation for non-negated version.
 * 
 * @author klosinskim
 *
 */
@CommandDescriptor(name = "~checkTextMatching", argumentTypes = String.class)
public class NegCheckTextMatchingCommand extends CheckTextMatchingCommand {

    public NegCheckTextMatchingCommand(String regex) {
        super(regex);
    }


    @Override
    protected void runAssertion(AbstractAssert<?, ?> assertion, Object... args) {

        ((BooleanAssert) assertion).overridingErrorMessage(
                "The regular expression '%s' matched a text on the page, but it shoudn't. ", regex).isFalse();
    }

}
