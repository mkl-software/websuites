package com.mkl.websuites.itests.cmd;

import com.mkl.websuites.command.Command;
import com.mkl.websuites.command.CommandDescriptor;



@CommandDescriptor(name = "noArg", argumentTypes = {})
public class NoArgCommand implements Command {


    @Override
    public void run() {

    }

}
