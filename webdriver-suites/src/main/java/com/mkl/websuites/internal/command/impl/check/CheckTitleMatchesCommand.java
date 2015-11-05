package com.mkl.websuites.internal.command.impl.check;

import org.assertj.core.api.StringAssert;

import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.internal.command.impl.CommandUtils;


@CommandDescriptor(name = "checkTitleMatches", argumentTypes = {String.class})
public class CheckTitleMatchesCommand extends CheckTitleCommand {

    public CheckTitleMatchesCommand(String titleFragment) {
        super(titleFragment);
    }


    @Override
    protected void runSingleStringAssertion(StringAssert assertThatTitle, String title) {

        assertThatTitle.overridingErrorMessage("Page title expected to match regex '%s', but the title was '%s'",
                expectedTitle, title).matches(CommandUtils.patternOf(expectedTitle));
    }
}
