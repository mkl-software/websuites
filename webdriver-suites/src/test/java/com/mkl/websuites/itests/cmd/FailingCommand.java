package com.mkl.websuites.itests.cmd;

import static org.assertj.core.api.Assertions.fail;

import com.mkl.websuites.command.BaseCommand;
import com.mkl.websuites.command.CommandDescriptor;

@CommandDescriptor(name = "failingCommand")
public class FailingCommand extends BaseCommand {

    @Override
    protected void runStandardCommand() {

        fail("I'm failing");
    }


}
