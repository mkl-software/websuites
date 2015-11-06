package com.mkl.websuites.internal.command.impl.navigation;

import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "refresh")
public class RefreshCommand extends BaseCommand {

    @Override
    protected void runStandardCommand() {
        browser.navigate().refresh();
    }

}
