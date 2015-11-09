package com.mkl.websuites.internal.command.impl.check.soft;

import org.assertj.core.api.SoftAssertions;

import com.mkl.websuites.command.BaseCommand;
import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "checkAllSoft")
public class CheckAllSoftCommand extends BaseCommand {

    @Override
    protected void runStandardCommand() {
        try {
            // can throw AssertionException
            softly.assertAll();
        } finally {
            // reset Soft Assertions for next check
            softly = new SoftAssertions();
        }
    }

}
