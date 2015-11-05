package com.mkl.websuites.internal.command.impl.navigation;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "back")
public class BackCommand extends BaseCommand {


    @Override
    protected void runStandardCommand() {

        browser.navigate().back();
    }

}
