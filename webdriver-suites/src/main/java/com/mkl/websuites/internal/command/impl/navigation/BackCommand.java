package com.mkl.websuites.internal.command.impl.navigation;

import com.mkl.websuites.command.BaseCommand;
import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "back")
public class BackCommand extends BaseCommand {


    @Override
    protected void runStandardCommand() {

        browser.navigate().back();
    }

}
