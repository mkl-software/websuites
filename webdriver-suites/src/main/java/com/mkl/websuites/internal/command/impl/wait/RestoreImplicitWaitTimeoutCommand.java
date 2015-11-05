package com.mkl.websuites.internal.command.impl.wait;

import java.util.concurrent.TimeUnit;

import com.mkl.websuites.config.WebSuitesConfig;
import com.mkl.websuites.internal.command.BaseCommand;
import com.mkl.websuites.internal.command.CommandDescriptor;


@CommandDescriptor(name = "restoreImplicitWaitTimeout")
public class RestoreImplicitWaitTimeoutCommand extends BaseCommand {



    @Override
    protected void runStandardCommand() {
        browser.manage().timeouts().implicitlyWait(WebSuitesConfig.get().site().waitTimeout(), TimeUnit.SECONDS);
    }

}
