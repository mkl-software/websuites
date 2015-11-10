package com.mkl.websuites.itests.custom;

import com.mkl.websuites.command.Command;
import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "customUserCommand")
public class CustomCommandInClasspath implements Command {

    @Override
    public void run() {

    }

}
