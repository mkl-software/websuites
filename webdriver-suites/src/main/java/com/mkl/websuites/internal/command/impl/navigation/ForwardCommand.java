package com.mkl.websuites.internal.command.impl.navigation;

import com.mkl.websuites.command.BaseCommand;
import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "forward")
public class ForwardCommand extends BaseCommand {

    @Override
    protected void runStandardCommand() {
        browser.navigate().forward();
    }

}
