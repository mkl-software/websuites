package com.mkl.websuites.internal.command.impl.wait;

import java.util.concurrent.TimeUnit;

import com.mkl.websuites.command.BaseCommand;
import com.mkl.websuites.command.CommandDescriptor;
import com.mkl.websuites.config.WebSuitesConfig;


@CommandDescriptor(name = "restoreImplicitWaitTimeout")
public class RestoreImplicitWaitTimeoutCommand extends BaseCommand {



    @Override
    protected void runStandardCommand() {
        browser.manage().timeouts().implicitlyWait(WebSuitesConfig.get().site().waitTimeout(), TimeUnit.SECONDS);
    }

}
