package com.mkl.websuites.itests.cmd;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;
import com.mkl.websuites.itests.web.core.CommandInvocationVerifier;


@CommandDescriptor(name = "internal-test", argumentTypes = String.class)
public class UnitTestVerificationCommand extends BaseCommand {

    private String message;

    public UnitTestVerificationCommand(String message) {
        super();
        this.message = message;
    }

    @Override
    protected void runStandardCommand() {

        CommandInvocationVerifier.getInstance().verifyInvocation(populateStringWithProperties(message));

    }


    @Override
    public String toString() {
        return "internal-test (" + message + ")";
    }

}
