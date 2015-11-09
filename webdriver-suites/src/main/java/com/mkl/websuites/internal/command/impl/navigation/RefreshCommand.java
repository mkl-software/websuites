package com.mkl.websuites.internal.command.impl.navigation;

import com.mkl.websuites.command.BaseCommand;
import com.mkl.websuites.command.CommandDescriptor;


@CommandDescriptor(name = "refresh")
public class RefreshCommand extends BaseCommand {

    @Override
    protected void runStandardCommand() {
        browser.navigate().refresh();
    }

}
